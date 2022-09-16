package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date StringToDate(String date, String pattern, int month) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            parse = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        c.add(Calendar.MONTH,month);
        Date m = c.getTime();
        return m;
    }
    public static String DateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String format = simpleDateFormat.format(c.getTime());
        return format;
    }
    //String转LocalDateTime yyyy-MM-dd
    public static LocalDateTime StringToLocalDate(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDate.parse(date, dateTimeFormatter).atStartOfDay();
        return dateTime;
    }
    //String转LocalDateTime yyyy-MM-dd HH:mm:ss
    public static LocalDateTime StringToLocalDateTime(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, dateTimeFormatter);
        return dateTime;
    }
    public static String localDateToString(LocalDateTime date, String pattern) {
        String stringTime = date.format(DateTimeFormatter.ofPattern(pattern));
        return stringTime;
    }
    //提前*天
    public static Date DateChange(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
    public static long getStartTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        return c.getTime().getTime();
    }
    public static long getEndTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY,23);
        c.set(Calendar.MINUTE,59);
        c.set(Calendar.SECOND,59);
        return c.getTime().getTime();
    }
}
