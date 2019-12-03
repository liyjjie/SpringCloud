package com.jack.repo;

import com.google.gson.Gson;
import com.jack.es.EsClientFactory;
import com.jack.utils.exception.ServiceException;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.Get;
import io.searchbox.core.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Type;

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

    //es添加数据
    @Override
    public Boolean insert(String index, String type, String json, Type typeOfSrc) {
        JestClient jestClient = esClientFactory.getJestClient();
        try {
            Object o = new Gson().fromJson(json, typeOfSrc);
            Index i = new Index.Builder(o)
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
    public Boolean update(String index, String type, String id, String json, Type typeOfSrc) {
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
            logger.info("修改成功" + json);
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
            }
            if ("not_found".equals(jr.getJsonObject().get("result").getAsString())) {
                logger.info("文档不存在" + id);
            }
        } catch (Exception e) {
            logger.info("删除文档失败", e);
            return false;
        }
        return true;
    }


}
