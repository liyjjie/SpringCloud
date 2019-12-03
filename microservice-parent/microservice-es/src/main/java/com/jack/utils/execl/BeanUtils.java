package com.jack.utils.execl;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author ：liyongjie
 * @ClassName ：BeanUtils
 * @date ： 2019-11-27 14:09
 * @modified By：
 */
public class BeanUtils {

    private static final Logger log = Logger.getLogger(BeanUtils.class);

    public BeanUtils() {
    }

    public static Object getProperty(Object bean, String propNameChain) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return getProperty(bean, propNameChain, ".");
    }

    public static Object getProperty(Object bean, String propNameChain, String delim) {
        if (bean == null) {
            return null;
        } else {
            try {
                StringTokenizer st = new StringTokenizer(propNameChain, delim);

                do {
                    if (!st.hasMoreTokens()) {
                        return bean;
                    }

                    String s = st.nextToken();
                    bean = PropertyUtils.getProperty(bean, s);
                } while (bean != null);

                return null;
            } catch (Exception var5) {
                log.error("反射错误", var5);
                return null;
            }
        }
    }

    public static Object invokGetMethodChain(Object bean, String propNameChain, String delim, Object[] args, Object nullValue) {
        try {
            Object title = bean;
            StringTokenizer st = new StringTokenizer(propNameChain, delim);

            while (st.hasMoreTokens()) {
                String methodName = st.nextToken();
                if (title == null) {
                    return nullValue;
                }

                if (title instanceof Map) {
                    title = ((Map) title).get(methodName);
                } else if (title instanceof List) {
                    title = ((List) title).get(new Integer(methodName));
                } else {
                    title = MethodUtils.invokeMethod(title, getGetterMethodName(String.class, methodName), args);
                }
            }

            return title == null ? nullValue : title;
        } catch (Exception var8) {
            log.error("反射错误", var8);
            return null;
        }
    }

    public static String getGetterMethodName(Class<?> clazz, String propertyName) {
        return "get" + propertyName.replaceFirst(propertyName.substring(0, 1), propertyName.substring(0, 1).toUpperCase());
    }

    public static Map beanToMap(Object bean) throws Exception {
        JSONObject json = JSONObject.fromObject(bean);
        return parseJSON2Map(json);
    }

    public static Map<String, Object> parseJSON2Map(JSONObject json) {
        Map<String, Object> map = new HashMap();
        Iterator var2 = json.keySet().iterator();

        while (true) {
            while (var2.hasNext()) {
                Object k = var2.next();
                Object v = json.get(k);
                if (v instanceof JSONArray) {
                    List<Map<String, Object>> list = new ArrayList();
                    Iterator it = ((JSONArray) v).iterator();

                    while (it.hasNext()) {
                        JSONObject json2 = (JSONObject) it.next();
                        list.add(parseJSON2Map(json2));
                    }

                    map.put(k.toString(), list);
                } else {
                    map.put(k.toString(), v);
                }
            }

            return map;
        }
    }

    public static Object mapToBean(Map map, Class clazz) {
        JSONObject jsonObject = JSONObject.fromObject(map);
        return JSONObject.toBean(jsonObject, clazz);
    }

    public static Object mapToBean(Map map, Class clazz, Map<String, Class> classMap) {
        JSONObject jsonObject = JSONObject.fromObject(map);
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss,SSS"}));
        return JSONObject.toBean(jsonObject, clazz, classMap);
    }
}
