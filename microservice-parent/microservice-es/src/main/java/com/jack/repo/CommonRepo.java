package com.jack.repo;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：CommonRepo
 * @date ： 2019-11-29 09:59
 * @modified By：
 */
public interface CommonRepo {

    Boolean insert(String index, String type, String json, Long id);

    Object get(String id, String index, String type, Class typeOfSrc);

    Boolean updateOrInsert(String index, String type, String id, String json, Type typeOfSrc);

    Boolean update(String index, String type, String id, String json);

    Boolean delete(String id, String index, String type);

    Boolean deleteIndex(String index);

    Boolean create(String index);

    List<Object> getIds(Class clazz, List<String> ids, String index, String type);

    List<Object> getListAll(String index,String type,Class clazz,List<String> searchContents);
}
