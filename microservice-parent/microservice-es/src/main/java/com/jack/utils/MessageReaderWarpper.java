package com.jack.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author ：liyongjie
 * @ClassName ：MessageReaderWarpper
 * @date ： 2019-11-27 14:06
 * @modified By：
 */
public class MessageReaderWarpper {

    private static final MessageReaderWarpper instance = new MessageReaderWarpper();
    public static final Locale CHINESE_LOCALE = new Locale("zh", "CN");
    private ThreadLocal<Locale> buffer = new ThreadLocal();
    private MessageSource messageSource;

    public static final MessageReaderWarpper getInstance() {
        return instance;
    }

    private MessageReaderWarpper() {
    }

    public void setLocale(Locale locale) {
        this.buffer.set(locale);
    }

    Locale getLocale() {
        Locale locale = (Locale)this.buffer.get();
        return locale == null ? CHINESE_LOCALE : locale;
    }

    public void setMessageSource(MessageSource source) {
        this.messageSource = source;
    }

    public boolean hasMessageSource() {
        return this.messageSource != null;
    }

    MessageSource getMessageSource() {
        return this.messageSource;
    }
}
