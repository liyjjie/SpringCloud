package com.jack.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author ：liyongjie
 * @ClassName ：MessageReader
 * @date ： 2019-11-27 14:06
 * @modified By：
 */
public class MessageReader {

    public MessageReader() {
    }

    public static String getMessage(String key) {
        String value = getMessage(key, (Object[])null);
        return value;
    }

    public static String getMessage(String key, Object[] params) {
        Locale locale = MessageReaderWarpper.getInstance().getLocale();
        return getMessage(key, params, locale);
    }

    public static String getMessage(String key, String[] params) {
        if (params == null) {
            return getMessage(key);
        } else {
            Object[] objParams = new Object[params.length];

            for(int i = 0; i < params.length; ++i) {
                objParams[i] = params[i];
            }

            return getMessage(key, objParams);
        }
    }

    public static String getMessage(String key, Object[] params, Locale locale) {
        MessageSource messageSource = MessageReaderWarpper.getInstance().getMessageSource();
        String s = null;

        try {
            s = messageSource.getMessage(key, params, locale);
            return s;
        } catch (Exception var6) {
            return "[" + key + "]";
        }
    }
}
