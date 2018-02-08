package com.lengzhuo.xybh.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * <pre>
 *     author : linzheng
 *     e-mail : 1007687534@qq.com
 *     time   : 2017/05/27
 *     desc   : 时间工具类
 *     version: 1.0
 * </pre>
 */
public class TimeUtils {

    /**
     * 秒 转 分钟:秒
     */
    public static String second2MMSS(int totalSecond) {
        int minute = totalSecond / 60;
        int second = totalSecond % 60;
        return String.format("%02d:%02d", minute, second);
    }

    /**
     * 自己看方法名
     */
    public static String YYYYMMDDHHMMSS2YYYYMMDDHHMM(String date) {
        if (TextUtils.isEmpty(date)) return "";
        return string2String(date, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm");
    }

    /**
     * 自己看方法名
     */
    public static String YYYYMMDDHHMMSS2YYYYMMDD(String date) {
        if (TextUtils.isEmpty(date)) return "";
        return string2String(date, "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd");
    }


    /**
     * String 转 String
     *
     * @param str          str
     * @param format       format
     * @param secondFormat secondFormat
     * @return String
     */
    public static String string2String(String str, String format, String secondFormat) {
        try {
            return date2String(new SimpleDateFormat(format).parse(str).getTime(), secondFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date().toString();
    }

    /**
     * Date（long） 转换 String
     *
     * @param time   time
     * @param format format
     * @return String
     */
    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(time);
    }

    public static String unixTimeStamp2FormatString(long unixTimeStamp) {
        long currentTime = System.currentTimeMillis() / 1000L;
        long diffTime = Math.abs(currentTime - unixTimeStamp);
        ;

        if(diffTime == 0){
            return "刚刚";
        }else if (diffTime < 60) {
            return String.format("%d秒前", diffTime);
        } else if (diffTime < 60 * 60) {
            return String.format("%d分钟前", diffTime / 60);
        } else if (diffTime < 60 * 60 * 24) {
            return String.format("%d小时前", diffTime / 60 / 60);
        }
        return date2String(unixTimeStamp * 1000L, "yyyy-MM-dd");
    }

}
