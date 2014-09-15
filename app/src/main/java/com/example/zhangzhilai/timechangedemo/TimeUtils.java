package com.example.zhangzhilai.timechangedemo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangzhilai on 9/10/14.
 */
public class TimeUtils {

    /**
     * 传递进来时间戳秒，返回天数，大于14天则显示具体发帖时间
     * @param releasseDate：1409900705
     * @return  10天前
     */
    public static String secondToTime(String releasseDate) {
        if (isBlank(releasseDate))
            return "";
        Date now = new Date();
        BigDecimal db = new BigDecimal(releasseDate);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data = format.format(db.longValue() * 1000);      //根据时间戳毫秒获取格式为yyyy-MM-dd的时间

        long between = (now.getTime()/1000 - db.longValue() + 8*60*60);  //8*60*60这个是为了处理时区问题
        long day = between / (24 * 3600);

        long hour = between % (24 * 3600) / 3600;

        long minute = between % 3600 / 60;

        long second=between % 60;

        String result = "";

        if (day > 14) {
            result = data;
        } else if (day <= 14 && day > 0) {
            result = String.valueOf(day) + "天前";
        } else if (hour > 0) {
            result = String.valueOf(hour) + "小时前";
        } else if (minute > 0) {
            result = String.valueOf(minute) + "分钟前";
        } else if (second > 0){
            result = String.valueOf(second) + "秒前";
        }

        return result;
    }

    /**
     *
     * @param releaseDate format: 2012-04-24T10:00:10+08:00
     * @return 返回天数，大于14天则显示具体发帖时间
     */
    public static String progressDate(String releaseDate) {
        // releaseDate format: 2012-04-24T10:00:10+08:00
        String dateStr = "";
        if (isBlank(releaseDate) || releaseDate.length() < 19)
            return "";
        if (releaseDate.indexOf("+") == -1) {
            dateStr = releaseDate;
        } else {
            dateStr = releaseDate.substring(0, releaseDate.indexOf("+"));
        }

        Date date = new Date();
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return "";
        }
        long between = (now.getTime() - date.getTime()) / 1000; // 2个时间相差多少秒

        long day = between / (24 * 3600);

        long hour = between % (24 * 3600) / 3600;

        long minute = between % 3600 / 60;

        long second=between%60;

        String result = "";


        if (day > 14) {
            result = releaseDate.substring(0, releaseDate.indexOf("T"));
        } else if (day <= 14 && day > 0) {
            result = String.valueOf(day) + "天前";
        } else if (hour > 0) {
            result = String.valueOf(hour) + "小时前";
        } else if (minute > 0) {
            result = String.valueOf(minute) + "分钟前";
        } else {
            result = "1分钟前";
        }

        return result;
    }

    /**
     * 根据传来的毫秒字符串，转换成"yyyy-MM-dd"格式日期
     *
     * @param msStr
     * @return
     */
    public static String progressDateUseMSReturnWithYear(String msStr) {
        long ms = progressMS(msStr);
        if (ms == 0)
            return "";
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    private static long progressMS(String ms) {
        // ms format: /Date(512546554)/
        if (ms == null || ms.length() == 0) {
            return 0;
        }
        String msStr = null;
        if (ms.indexOf("/Date(") < 0 || ms.indexOf(")/") < 0) {
            msStr = ms;
        } else {
            msStr = ms.replace("/Date(", "").replace(")/", "");
        }

        long millisecond = 0L;
        try {
            millisecond = Long.parseLong(msStr);
        } catch (NumberFormatException e) {
            return 0;
        }
        return millisecond;
    }

    public static boolean isBlank(String str) {
        if (str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
