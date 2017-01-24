package com.snxun.core.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间格式化帮助类
 * Created by zhouL on 2016/12/19.
 */
public class DateUtils {

    public static final String Type_1 = "HH:mm";
    public static final String Type_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String Type_3 = "yyyyMMddHHmmssSSS";
    public static final String Type_4 = "yyyyMMddHHmmss";
    public static final String Type_5 = "yyyyMMdd";
    public static final String Type_6 = "yyyy-MM-dd";

    @StringDef({Type_1, Type_2, Type_3, Type_4, Type_5, Type_6})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatType {}


    /**
     * 格式化当前时间
     * @param formatType 格式化类型
     */
    public static String getCurrentFormatString(@FormatType String formatType) {
        return getFormatString(formatType, new Date(System.currentTimeMillis()));
    }

    /**
     * 格式化当前时间
     * @param formatType 格式化类型
     * @param date 日期
     */
    public static String getFormatString(@FormatType String formatType, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatType, Locale.CHINA);
        return format.format(date);
    }

    /**
     * 将格式化后的时间转成Date
     * @param formatType source对应的格式化类型
     * @param source 时间数据
     */
    public static Date parseFormatDate(@FormatType String formatType, String source) {
        SimpleDateFormat format = new SimpleDateFormat(formatType, Locale.CHINA);
        try {
            return format.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 改变日期格式
     * @param oldFormatType 旧格式
     * @param newFormatType 新格式
     * @param source 时间数据
     */
    public static String changeFormatString(@FormatType String oldFormatType, @FormatType String newFormatType, String source) {
        Date date = parseFormatDate(oldFormatType, source);
        if (date == null){
            return "";
        }
        return getFormatString(newFormatType, date);
    }
}
