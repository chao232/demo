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
    public static LocalDateTime StringToLocalDate(String date, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDate.parse(date, dateTimeFormatter).atStartOfDay();
        return dateTime;
    }
}
