package com.jack.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：HappyNewYear
 * @date ： 2019-12-10 11:37
 * @modified By：
 * @PostConstruct服务没启动就执行
 */
@Component
@Order(0)//执行顺序
public class HappyNewYear implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(HappyNewYear.class);

    private static final String yyyyMMdd = "yyyy-MM-dd";

    //线程安全时间类型
    public static LocalDate getLocalDateFromDate(Date date) {
        return LocalDate.from(Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()));
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        try {
            LocalDate nowData = getLocalDateFromDate(new Date());
            String startData = "2021-02-12";
            SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
            Date date = sdf.parse(startData);
            LocalDate data = getLocalDateFromDate(date);
            long between = ChronoUnit.DAYS.between(nowData, data);
            System.err.println("距新年还有" + between + "天");
        } catch (Exception e) {
            logger.info("error" + e);
        }
    }

    // 返回时间区间内工作日（startDate格式为：2020-03-01）
    public static List<Date> getWorkTimeSumInSection(String startDate, String endDate) {

        List<Date> list = new ArrayList<>();

        int sy = Integer.parseInt(startDate.substring(0, 4));
        int sm = Integer.parseInt(startDate.substring(5, 7));
        int sd = Integer.parseInt(startDate.substring(8, 10));

        int ey = Integer.parseInt(endDate.substring(0, 4));
        int em = Integer.parseInt(endDate.substring(5, 7));
        int ed = Integer.parseInt(endDate.substring(8, 10));

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, sy);
        cal1.set(Calendar.MONTH, sm - 1);//默认取后一个月 需要减一
        cal1.set(Calendar.DATE, sd);

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, ey);
        cal2.set(Calendar.MONTH, em - 1);
        cal2.set(Calendar.DATE, ed - 1);

        while (cal1.getTimeInMillis() < cal2.getTimeInMillis()) {
            // 得到当天日期的week
            int day = cal1.get(Calendar.DAY_OF_WEEK);
            // 判断当天是否为工作日
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                list.add((Date) cal1.getTime().clone());
            }
            // 判断完成后日期+1
            cal1.add(Calendar.DATE, 1);
        }
        return list;
    }

    // 返回当月工作日数量
    public static Integer getWorkDates(int year, int month) {
        List<Date> dates = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);

        while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                dates.add((Date) cal.getTime().clone());
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates.size();
    }
}