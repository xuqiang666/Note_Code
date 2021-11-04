package com.y;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class testDate {

    public static void main(String[] args) throws ParseException {
        Date date = new Date(4070880000000L);
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println(sdf.parse("2099-01-01 00:00:00").getTime());
        System.out.println(sdf.format(date));

        Date date1 = new Date();
        System.out.println(date1);
        Date expdate = parseObject2Date(date1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        System.out.println(cal.getTime());
        //license.set("expdate", cal.getTime());
    }

    private static Date parseObject2Date(Object createDate) throws ParseException {
        DateFormat returnFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(createDate.toString());
        String returnStr = returnFormat.format(parse);
        return returnFormat.parse(returnStr);
    }


}
