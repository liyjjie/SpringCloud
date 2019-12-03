package com.jack.repo;


import java.lang.reflect.Type;

/**
 * @author ：liyongjie
 * @ClassName ：CommonRepo
 * @date ： 2019-11-29 09:59
 * @modified By：
 */
public interface CommonRepo {

    Boolean insert(String index, String type, String json, Type typeOfSrc);

    Object get(String id, String index, String type, Class typeOfSrc);

    Boolean update(String index, String type, String id, String json, Type typeOfSrc);

    Boolean delete(String id, String index, String type);

}
