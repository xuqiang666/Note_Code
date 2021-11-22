package com.y.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;

public class DateTest{

    @Test
    public void test8() {
        // 本地日期/时间
        System.out.println("================本地时间================");
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        // 时区时间
        System.out.println("================带时区的时间ZonedDateTime================");
        System.out.println(ZonedDateTime.now()); // 使用系统时区
        System.out.println(ZonedDateTime.now(ZoneId.of("America/New_York"))); // 自己指定时区
        System.out.println(ZonedDateTime.now(Clock.systemUTC())); // 自己指定时区

        System.out.println("================带时区的时间OffsetDateTime================");
        System.out.println(OffsetDateTime.now()); // 使用系统时区
        System.out.println(OffsetDateTime.now(ZoneId.of("America/New_York"))); // 自己指定时区
        System.out.println(OffsetDateTime.now(Clock.systemUTC())); // 自己指定时区
    }



    @Test
    public void test9() throws ParseException {
        String patternStr = "G GG GGGGG E EE EEEEE a aa aaaaa";
        Date currDate = new Date();

        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓中文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("====================Date->String====================");
        DateFormat dateFormat = new SimpleDateFormat(patternStr, Locale.CHINA);
        System.out.println(dateFormat.format(currDate));

        System.out.println("====================String->Date====================");
        String dateStrParam = "公元 公元 公元 星期六 星期六 星期六 下午 下午 下午";
        System.out.println(dateFormat.parse(dateStrParam));

        System.out.println("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓英文地区模式↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
        System.out.println("====================Date->String====================");
        dateFormat = new SimpleDateFormat(patternStr, Locale.US);
        System.out.println(dateFormat.format(currDate));

        System.out.println("====================String->Date====================");
        dateStrParam = "AD ad bC Sat SatUrday sunDay PM PM Am";
        System.out.println(dateFormat.parse(dateStrParam));
    }
}
