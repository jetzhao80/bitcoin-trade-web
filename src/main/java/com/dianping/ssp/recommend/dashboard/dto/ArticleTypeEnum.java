package com.dianping.ssp.recommend.dashboard.dto;

import scala.actors.threadpool.Arrays;

import java.util.List;

/**
 * Created by jnkmhbl on 16/12/23.
 */
public enum ArticleTypeEnum {
    NEWS(1,"新闻") , MEETING(2,"会议") ,STRATEGY(3,"攻略") ;

    private int code ;
    private String msg;
     ArticleTypeEnum(int value,String msg){
        this.code = code;
        this.msg = msg ;
    }

    public int getCode(){
        return code ;
    }
    public static int getTypeSize(){
        return 3 ;
    }

    public static List<Integer> getTypeList(){
        return Arrays.asList(new Integer[]{1,2,3});
    }
}
