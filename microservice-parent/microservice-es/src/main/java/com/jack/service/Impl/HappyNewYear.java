package com.jack.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author ：liyongjie
 * @ClassName ：HappyNewYear
 * @date ： 2019-12-10 11:37
 * @modified By：
 * @PostConstruct服务没启动就执行
 *
 */
@Component
@Order(0)//执行顺序
public class HappyNewYear implements ApplicationRunner {

    private static final Logger logger= LoggerFactory.getLogger(HappyNewYear.class);

    private static final String yyyyMMdd = "yyyy-MM-dd";

    //线程安全时间类型
    public static LocalDate getLocalDateFromDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        try {
            LocalDate nowData = getLocalDateFromDate(new Date());
            String startData = "2020-01-24";
            SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
            Date date = sdf.parse(startData);
            LocalDate data = getLocalDateFromDate(date);
            long between = ChronoUnit.DAYS.between(nowData, data);
            System.err.println("距新年还有"+between+"天");
        } catch (Exception e) {
            logger.info("error"+e);
        }
    }
}
