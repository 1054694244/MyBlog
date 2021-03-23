package com.shenzc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    public static String YYYY_MM_DD="yyyy-MM-dd";
    public static String YYYY_MM_DD_24HH_MI_SS="yyyy-MM-dd HH:mm:ss";
    public DateUtils() {
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        try {
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            return instant.atZone(zoneId).toLocalDateTime();
        } catch (Exception e) {
            throw new RuntimeException("Date转换为LocalDateTime失败"+date,e);
        }
    }

    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        try {
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zdt = localDateTime.atZone(zoneId);
            return Date.from(zdt.toInstant());
        } catch (Exception e) {
            throw new RuntimeException("localDateTime转换为Date失败"+localDateTime,e);
        }
    }

    public static String getAddMinutesDateStr(String  dateStr,int minutes,String pattern) {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long afterTime = date.getTime() / 1000L + minutes*60L;
        date.setTime(afterTime * 1000L);
        return sdf.format(date);
    }


    public static String getYesterDay(Date date) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        long beforeTime = date.getTime() / 1000L - 86400L;
        date.setTime(beforeTime * 1000L);
        return formatter.format(date);
    }


    public static Date convertStrTODate(String str, Class<Date> type, String datePattern) {
        if (str == null) {
            return null;
        } else if (type == Date.class) {
            if (str instanceof String) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
                    return sdf.parse(str);
                } catch (ParseException var4) {
                    throw new RuntimeException("您输入的数据格式不对");
                }
            } else {
                throw new RuntimeException("您要转化的数据输入不是String类型");
            }
        } else {
            throw new RuntimeException("您要转化的数据类型不对");
        }
    }


    /**
     * 按分拆分时间段
     * */
    public static List<String> getDateStrListByMinute(String s1, String s2, String format,int minutes) throws ParseException {
        List<String> list = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date begin = sdf.parse(s1);
        list.add(sdf.format(begin.getTime()));
        Date end = sdf.parse(s2);
        long between =  (end.getTime() - begin.getTime()) / 1000L;
        long  day = between /(minutes*60);

        for(int i = 0;  i < day; i++) {
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s1));
            cd.add(Calendar.MINUTE, minutes*(i+1));
            list.add(sdf.format(cd.getTime()));
        }

        return list;
    }



    public static int getLastDay(int year, int month) {
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        int last = cal.getActualMaximum(5);
        System.out.println(last);
        return last;
    }



    public static String duration(String date1, String date2) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#.0");
        Date start = sdf.parse(date1);
        Date end = sdf.parse(date2);
        long cha = end.getTime() - start.getTime();
        double result = (double)cha * 1.0D / 3600000.0D;
        return df.format(result);
    }

    public static String getDateStr(Date date,String format)  {
        String dateStr="";
        if(date==null) return "";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateStr= sdf.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    public static String getDateStr(Date date)  {
        String dateStr="";
        if(date==null) return "";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.YYYY_MM_DD_24HH_MI_SS);
            dateStr= sdf.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  dateStr;
    }

    public static String lastDayOfMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(5, 1);
        cal.roll(5, -1);
        return sdf.format(cal.getTime());
    }

    /**
     * 计算当天的最后一秒钟是什么时候  比如：8.23 0:0:0   -> 8.23 23:59:59
     * @param date
     * @return
     */
    public static Date dayLastTime(Date date){
        if (date!=null){
            SimpleDateFormat sdf1 = new SimpleDateFormat(YYYY_MM_DD);
            SimpleDateFormat sdf2 = new SimpleDateFormat(YYYY_MM_DD_24HH_MI_SS);
            String dateStr = sdf1.format(date);
            dateStr = dateStr+" 23:59:59";
            try {
                date = sdf2.parse(dateStr);
            }catch (Exception e){
                logger.error("日期格式错误，转化失败");
            }
        }
        return date;
    }


    /**
     * 获取当前的时间
     * @return
     */
    public static String getCurrentDateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_24HH_MI_SS);
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取过去多少天的日期
     * @param day
     */
    public static String getLastDate(int day){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -day);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static void main(String[] args) {

            System.out.print(getDateStr(new Date(),DateUtils.YYYY_MM_DD));

    }

}
