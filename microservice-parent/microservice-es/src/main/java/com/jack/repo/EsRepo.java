package com.jack.repo;

import com.google.gson.Gson;
import com.jack.es.EsClientFactory;
import com.jack.utils.exception.ServiceException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import io.searchbox.core.Update;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ：liyongjie
 * @ClassName ：EsRepo
 * @date ： 2019-11-29 09:59
 * @modified By：
 */
@Repository
public class EsRepo implements CommonRepo {

    private static final Logger logger = LoggerFactory.getLogger(EsRepo.class);

    @Resource(name = "EsClientFactory")
    private EsClientFactory esClientFactory;


    //线程安全时间类型
    private LocalDate getLocalDateFromDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
    }

    //es添加数据
    @Override
    public Boolean insert(String index, String type, String json) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            Index i = new Index.Builder(json)
                    .index(index)
                    .type(type)
                    .build();
            JestResult jr = jestClient.execute(i);
            if (jr != null && !jr.isSucceeded()) {
                throw new RuntimeException(jr.getErrorMessage() + "插入文档失败");
            }
            logger.info("插入文档成功：{}", json);
        } catch (Exception e) {
            logger.error("插入文档失败：{}", e);
            return false;
        }
        return true;
    }

    //取出数据
    @Override
    public Object get(String id, String index, String type, Class typeOfSrc) {
        Get get = new Get.Builder(index, id).type(type).build();
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            JestResult jr = jestClient.execute(get);
            if (jr == null) {
                throw new ServiceException("es error");
            }
            if (jr.getJsonObject().get("found") != null && !jr.getJsonObject().get("found").getAsBoolean()) {
                return null;
            }
            if (!jr.isSucceeded()) {
                throw new ServiceException("es error");
            }
            return jr.getSourceAsObject(typeOfSrc);
        } catch (Exception e) {
            logger.info("插入文档失败:{}", e);
        }
        return null;
    }

    @Override
    public Boolean updateOrInsert(String index, String type, String id, String json, Type typeOfSrc) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            Object o = new Gson().fromJson(json, typeOfSrc);
            Index i = new Index.Builder(o)
                    .index(index)
                    .type(type)
                    .id(id)
                    .build();
            JestResult jr = jestClient.execute(i);
            if (jr == null && !jr.isSucceeded()) {
                throw new ServiceException("es error");
            }
            logger.info("修改/添加成功" + json);
        } catch (Exception e) {
            logger.info("修改/添加文档失败", e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean update(String index, String type, String id, String json, Type typeOfSrc) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            Update update = new Update.Builder(json)
                    .index(index)
                    .type(type)
                    .id(id)
                    .build();
            JestResult jr = jestClient.execute(update);
            if (!jr.isSucceeded()) {
                throw new ServiceException("es error");
            }
        } catch (Exception e) {
            logger.info("修改文档失败", e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(String id, String index, String type) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            JestResult jr = jestClient.execute(new Delete.Builder(id)
                    .index(index)
                    .type(type)
                    .build());
            if (jr == null && !jr.isSucceeded()) {
                logger.info("es error");
                throw new ServiceException("删除失败");
            }
            if ("not_found".equals(jr.getJsonObject().get("result").getAsString())) {
                logger.info("文档不存在" + id);
                throw new ServiceException("文档不存在");
            }
        } catch (Exception e) {
            logger.info("删除文档失败", e);
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteIndex(String index) {
        JestClient jestClient = esClientFactory.getJestClient();
//        ChronoUnit.DAYS.between();
        try {
            JestResult jr = jestClient.execute(new DeleteIndex.Builder(index).build());
            if (!jr.isSucceeded()) {
                logger.info("es error");
            }
        } catch (Exception e) {
            logger.info("删除索引失败");
            return false;
        }
        return true;
    }

    @Override
    public Boolean create(String index) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            JestResult jr = jestClient.execute(new CreateIndex.Builder(index).build());
            if (!jr.isSucceeded()) {
                logger.info("es error");
                return false;
            }
        } catch (Exception e) {
            logger.info("添加索引失败");
            return false;
        }
        return true;
    }

    /**
     * number_of_shards：分片数量
     * enabled: false 该字段不能被查询和store
     * field1:
     *
     * @param index
     * @return
     */
    @Override
    public Boolean createIndex(String index) {
        JestClient jestClient = esClientFactory.getJestClient();
        String settings="{\n" +
                "\t\"number_of_shards\": 5,\n" +
                "\t\"analysis\": {\n" +
                "\t\t\"filter\": {\n" +
                "\t\t\t\"ngram_filter\": {\n" +
                "\t\t\t\t\"type\": \"ngram\",\n" +
                "\t\t\t\t\"min_gram\": 1,\n" +
                "\t\t\t\t\"max_gram\": 2\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"analyzer\": {\n" +
                "\t\t\t\"ngram_analyzer\": {\n" +
                "\t\t\t\t\"type\": \"custom\",\n" +
                "\t\t\t\t\"tokenizer\": \"standard\",\n" +
                "\t\t\t\t\"filter\": [\n" +
                "\t\t\t\t\t\"lowercase\",\n" +
                "\t\t\t\t\t\"ngram_filter\"\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";

        String mappings="{\n" +
                "\t\t\"doc\": {\n" +
                "\t\t\t\"_all\": {\n" +
                "\t\t\t\t\"type\": \"text\",\n" +
                "\t\t\t\t\"analyzer\": \"ngram_analyzer\",\n" +
                "\t\t\t\t\"search_analyzer\": \"standard\"\n" +
                "\t\t\t},\n" +
                "\t\t\t\"properties\": {\n" +
                "\t\t\t\t\"word\": {\n" +
                "\t\t\t\t\t\"type\": \"keyword\",\n" +
                "\t\t\t\t\t\"analyzer\": \"ngram_analyzer\",\n" +
                "\t\t\t\t\t\"search_analyzer\": \"standard\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"definition\": {\n" +
                "\t\t\t\t\t\"type\": \"keyword\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}";
        try {
            JestResult jr = jestClient.execute(new CreateIndex
                    .Builder(index)
                    .settings(settings)
                    .mappings(mappings)
                    .build());
            if (!jr.isSucceeded()) {
                logger.info("es error");
                return false;
            }
        } catch (Exception e) {
            logger.info("创建索引失败" + index);
            return false;
        }
        return true;
    }
}
