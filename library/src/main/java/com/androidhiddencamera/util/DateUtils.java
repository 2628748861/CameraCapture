package com.androidhiddencamera.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式化工具类
 */
public class DateUtils {
    /**
     * yyyy-MM-dd
     */
    public static final String yyyy_MM_dd = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final String yyyy_MM_dd_HH_mm_ss2 = "yyyy/MM/dd HH:mm:ss";
    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    /**
     * yyyyMMdd_HHmmss
     */
    public static final String yyyyMMdd_HHmmss = "yyyyMMdd_HHmmss";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String yyyy_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String MM_dd_HH_mm = "MM-dd HH:mm";
    /**
     * ssSSS
     */
    public static final String ssSSS = "ssSSS";

    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 将毫秒数转换为String
     *
     * @param time 毫秒数
     * @return String yyyy/MM/dd HH:mm:ss
     */
    public static String longTimeToDate(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.CHINA);
        return formatter.format(new Date(time));
    }

    /**
     * 将毫秒数转换为String
     *
     * @param time
     * @param format String yyyy/MM/dd HH:mm:ss
     */
    public static String longTimeToDate(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        return formatter.format(new Date(time));
    }

    /**
     * 得到当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentLongTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return formatter.format(new Date());
    }

    /**
     * 得到当前时间
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentShortTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return formatter.format(new Date());
    }

    /**
     * 得到当前时间
     *
     * @param format 时间格式
     * @return String 时间字符串
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        return formatter.format(new Date());
    }

    /**
     * 得到当前时间
     *
     * @return String 时间字符串
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 格式化时间
     *
     * @param timeStr
     * @return "yyyy-MM-dd"
     */
    public static String getFormatterTime(String timeStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            timeStr = formatter.format(formatter.parse(timeStr));
        } catch (Exception e) {
            timeStr = "";
        }
        return timeStr;
    }

    /**
     * 字符串转时间 将 yyyy-MM-dd HH:mm:ss 转换为其他格式的时间
     *
     * @param dateStr 需要转换的时间字符串
     * @param format  转出的时间格式
     * @return
     */
    public static String getFormatterTime(String dateStr, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            ParsePosition pos = new ParsePosition(0);
            SimpleDateFormat formatter2 = new SimpleDateFormat(format, Locale.CHINA);
            dateStr = formatter2.format(formatter.parse(dateStr, pos));
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 将时间转换为其他格式的时间
     *
     * @param date   需要转换的时间
     * @param format 转出的时间格式
     * @return
     */
    public static String getFormatterTime(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        return formatter.format(date);
    }

    /**
     * 计算两个时间的差
     *
     * @param startTimeStr 开始时间
     * @param endTimeStr   结束时间
     * @param format       时间格式
     * @return 时间差  long
     */
    public static long getTimeDifferenceDays(String startTimeStr, String endTimeStr, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.CHINA);
        long differenceTime = 0l;
        try {
            differenceTime = getTimeDifferenceDays(formatter.parse(startTimeStr), formatter.parse(endTimeStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return differenceTime;
    }

    /**
     * 计算两个时间的差
     *
     * @param startTimeStr 开始时间
     * @param endTimeStr   结束时间
     * @return 时间差  long
     */
    public static long getTimeDifferenceDays(String startTimeStr, String endTimeStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        long differenceTime = 0L;
        try {
            differenceTime = getTimeDifferenceDays(formatter.parse(startTimeStr), formatter.parse(endTimeStr));
//            CLog.d("时间差异 startTimeStr"+startTimeStr+"endTimeStr"+endTimeStr+"differenceTime"+differenceTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return differenceTime;
    }


    /**
     * 得到当前时间是星期几
     *
     * @return 星期X
     */
    public static String getCurrentWeekDay() {
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE", Locale.CHINA);
        return dateFm.format(new Date());
    }

    /**
     * 计算两个时间的差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 时间差  long
     */
    public static long getTimeDifferenceDays(Date startTime, Date endTime) {
        long time = endTime.getTime() - startTime.getTime();
        return time;
    }

    /**
     * 计算两个时间的差
     *
     * @param startTimeStr 开始时间字符串   yyyy-MM-dd HH:mm:ss
     * @param endTimeStr   结束时间字符串   yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTimeDifference(String startTimeStr, String endTimeStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String differenceTime = "";
        try {
            differenceTime = getTimeDifference(formatter.parse(startTimeStr), formatter.parse(endTimeStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return differenceTime;
    }

    /**
     * 计算两个时间的差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static String getTimeDifference(Date startTime, Date endTime) {
        long l = endTime.getTime() - startTime.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        // long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuilder sbTime = new StringBuilder();
        if (day > 0) {
            sbTime.append(day).append("天");
        }
        if (hour > 0) {
            sbTime.append(hour).append("小时");
        }
        sbTime.append(min).append("分钟");
        return sbTime.toString();
    }

    /**
     * 判断 时间1 是否大于 时间2 如果大于 返回true
     *
     * @param dataFirst  时间1
     * @param dateSecond 时间2
     * @param format     时间格式
     * @return 如果大于 返回true
     */
    public static boolean compareDate(String dataFirst, String dateSecond, String format) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(format, Locale.CHINA);
            Date dt1 = df.parse(dataFirst);
            Date dt2 = df.parse(dateSecond);
            return compareDate(dt1, dt2);
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * 判断 时间1 是否大于 时间2 如果大于 返回true
     *
     * @param dateFirst  时间1
     * @param dateSecond 时间2
     * @return 如果大于 返回true
     */
    public static boolean compareDate(Date dateFirst, Date dateSecond) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(dateFirst);
        calendar2.setTime(dateSecond);
        int result = calendar1.compareTo(calendar2);
        if (result >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到当前时间多少天之前的时间
     *
     * @param days 天数
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getPreTime(int days) {
        return getPreTime(days, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间多少天之前的时间
     *
     * @param days      天数
     * @param formatter 时间格式
     * @return
     */
    public static String getPreTime(int days, String formatter) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -days);
        return getFormatterTime(calendar.getTime(), formatter);
    }

    /**
     * 得到当前时间多少天之后的时间
     *
     * @param days 天数
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getAfterTime(int days) {
        return getAfterTime(days, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间多少天之后的时间
     *
     * @param days      天数
     * @param formatter 时间格式
     * @return 时间字符串
     */
    public static String getAfterTime(int days, String formatter) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return getFormatterTime(calendar.getTime(), formatter);
    }

    /**
     * 得到某天时间之后的多少天
     *
     * @param time      某天时间
     * @param days      天数
     * @param formatter 时间格式
     * @return 时间字符串
     */
    public static String getAfterTime(String time, int days, String formatter) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(formatter, Locale.CHINA);
            Date date = df.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, days);
            return getFormatterTime(calendar.getTime(), formatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 得到上周一的时间 yyyy-MM-dd HH:mm:ss
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getFirstOfLastWeek() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Calendar cal = Calendar.getInstance();
        //设置每周第一天为星期一 (默认是星期日)
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        return df.format(cal.getTime());
    }

    /**
     * 获取年
     * @return
     */
    public static int getYear(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.YEAR);
    }
    /**
     * 获取月
     * @return
     */
    public static int getMonth(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.MONTH)+1;
    }
    /**
     * 获取日
     * @return
     */
    public static int getDay(){
        Calendar cd = Calendar.getInstance();
        return  cd.get(Calendar.DATE);
    }
}
