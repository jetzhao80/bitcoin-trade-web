package com.dianping.ssp.recommend.dashboard.util;

import com.meituan.kafka.javaclient.common.utils.CopyOnWriteMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by tianrui03 on 16/12/22.
 */
public class DateUtil {
    private static volatile Map<String,SimpleDateFormat> formatMap = new CopyOnWriteMap<String, SimpleDateFormat>();
    public static long DAY = 1000 * 60* 60 *24 ;
    public static long HOUR = 1000 * 60 * 60 ;
    public static Date date4String(String dateString,String formatString) throws ParseException{
        SimpleDateFormat format =  formatMap.get(formatString);
        if(format == null){
            format = new SimpleDateFormat(formatString);
            formatMap.put(formatString,format);
        }
        Date date = format.parse(dateString);
        return date ;
    }

    public static String getHourAfterString(Date date ,String formatString){
        SimpleDateFormat format =  formatMap.get(formatString);
        if(format == null){
            format = new SimpleDateFormat(formatString);
            formatMap.put(formatString,format);
        }
        long hour = date.getTime() / HOUR ;
        hour ++ ;
        Date targetDate = new Date(hour * HOUR);
        return format.format(targetDate);
    }


    public static List<String> dateGaps(Date startTime ,Date endTime)throws ParseException{
        List<String> dateGaps = new ArrayList<String>();
        long endDayValue = endTime.getTime() /DAY;
        long startDayValue = startTime.getTime()/DAY ;
        long timeCursor = startDayValue + 1;
        for(;timeCursor <= endDayValue + 1 ; timeCursor ++ ){
            dateGaps.add(string4Date(new Date(timeCursor * DAY),"yyyy-MM-dd"));
        }
        return dateGaps ;
    }

    public static List<String> dateGaps(String startTimeString ,String endTimeString,String format)throws ParseException{
        Date startTime = date4String(startTimeString,format);
        Date endTime = date4String(endTimeString,format);
        return dateGaps(startTime,endTime);
    }

    public static List<String> dateGaps(long startTimeString ,long endTimeString)throws ParseException{
        Date startTime = new Date(startTimeString);
        Date endTime = new Date(endTimeString);
        List<String> dateGaps = new ArrayList<String>();
        long endDayValue = endTime.getTime() /DAY;
        long startDayValue = startTime.getTime()/DAY ;
        long timeCursor = startDayValue ;
        for(;timeCursor <= endDayValue ; timeCursor ++ ){
            dateGaps.add(string4Date(new Date(timeCursor * DAY),"yyyy-MM-dd"));
        }
        return dateGaps ;
    }

    public static  String string4Date(Date date ,String formatString) throws ParseException{
        SimpleDateFormat format =  formatMap.get(formatString);
        if(format == null){
            format = new SimpleDateFormat(formatString);
            formatMap.put(formatString,format);
        }
        return format.format(date);
    }
    public static boolean compare(Date date1,Date date2){
        return date1.getTime() >= date2.getTime() ;
    }

    public static List<String> hourGaps(Date startTime ,Date endTime)throws ParseException{
        List<String> hourGaps = new ArrayList<String>();
        long endDayValue = endTime.getTime() /HOUR;
        long startDayValue = startTime.getTime()/HOUR ;
        long timeCursor = startDayValue + 1;
        for(;timeCursor <= endDayValue + 1 ; timeCursor ++ ){
            hourGaps.add(string4Date(new Date(timeCursor * HOUR),"yyyy-MM-dd HH"));
        }
        return hourGaps ;
    }

    public static Date dateByDayDiff(Date date ,int dayDiff){
        return new Date(date.getTime() + dayDiff * DAY) ;
    }


}
