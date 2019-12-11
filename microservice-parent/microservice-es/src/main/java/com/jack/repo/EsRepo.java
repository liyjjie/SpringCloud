package com.jack.repo;

import com.google.gson.Gson;
import com.jack.es.EsClientFactory;
import com.jack.utils.exception.ServiceException;
import com.jack.vo.EsInsertVo;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：EsRepo
 * @date ： 2019-11-29 09:59
 * @modified By：es实时搜索两种方式
 */
@Repository
public class EsRepo implements CommonRepo {

    private static final Logger logger = LoggerFactory.getLogger(EsRepo.class);

    @Resource(name = "EsClientFactory")
    private EsClientFactory esClientFactory;

    //es添加数据
    @Override
    public Boolean insert(String index, String type, String json, Long id) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            Index i = new Index.Builder(json)
                    .id(String.valueOf(id))
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
            logger.info("取出文档失败:{}", e);
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
    public Boolean update(String index, String type, String id, String json) {
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

    public List<Object> getIds(Class clazz, String[] ids, String index, String type) {
        JestClient jestClient = esClientFactory.getJestClient();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        try {
            IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
            for (String temp : ids) {
                idsQueryBuilder.addIds(temp);
            }
            searchSourceBuilder.query(idsQueryBuilder);
            searchSourceBuilder.sort(new FieldSortBuilder(EsInsertVo.COL_SEARCHCONTENTS).order(SortOrder.DESC));
//            String append = "{\"query\":" + idsQueryBuilder.toString() + "}";
            Search search = new Search.Builder(idsQueryBuilder.toString()).addIndex(index).addType(type).build();
            JestResult jr = jestClient.execute(search);
            if (!jr.isSucceeded()) {
                logger.info("error es");
                return null;
            }
            List<Object> list = jr.getSourceAsObjectList(clazz);
            return list;
        } catch (Exception e) {
            logger.info("error" + e);
        }
        return null;
    }
}
