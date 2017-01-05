package com.dianping.ssp.recommend.dashboard.dto;

import com.dianping.ssp.recommend.dashboard.entity.KwSummary;
import com.dianping.ssp.recommend.dashboard.util.DateUtil;
import com.dianping.ssp.recommend.dashboard.util.IsNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianrui03 on 16/12/23.
 */
public class KwSummaryDto {

    public static final int ARTICLE = 1;
    public static final int USER = 2 ;

    public static final int VIEW = 2 ;
    public static final int CLICK = 1 ;

    private static final int ARTICLE_TYPE_NUM = 3 ;

    private Map<String,VC> usrMap ;
    private Map<String,VC> articleMap ;


    public void setArticleMap(Map<String, VC> articleMap) {
        this.articleMap = articleMap;
    }

    public void setUsrMap(Map<String, VC> usrMap) {
        this.usrMap = usrMap;
    }

    public Map<String,VC> getUserMap(){
        return usrMap;
    }

    public Map<String,VC> getArticleMap(){
        return articleMap;
    }

    public KwSummaryDto(List<KwSummary> summaries){
        AutoInstance helper =new  AutoInstance();
        helper.autoInstance(summaries);
    }









    public class   AutoInstance{
        public void autoInstance(List<KwSummary> summaries){
            Map<String,VC> usrMap = new HashMap<String, VC>();
            Map<String,VC> articleMap = new HashMap<String, VC>();
            setArticleMap(articleMap);
            setUsrMap(usrMap);
            for(KwSummary summary : summaries){
                    put(summary.getPartitionDate(), summary);
            }
        }
        public boolean put(String time,KwSummary summary){
            if(usrMap == null || articleMap == null){
                return false ;
            }


            Map<String,VC> sourceMap = null ;
            if(summary.getAnalysisType() == USER) {
                sourceMap = usrMap;
            }
            else if( summary.getAnalysisType() == ARTICLE) {
                sourceMap = articleMap;
            }else {
                return false ;
            }


            VC vc = null ;
            vc = sourceMap.get(time);
            if(IsNull.is(vc)){
                vc = new VC();
                vc.clickCount = new int [ARTICLE_TYPE_NUM +1 ];
                vc.viewCount = new int [ARTICLE_TYPE_NUM +1 ];
                vc.setDate(time);
                sourceMap.put(time,vc);
            }

            if(summary.getActionType() ==VIEW){
                vc.addViewCount(summary.getActionNum(),summary.getArticleType());
            }else  if (summary.getActionType() == CLICK){
                vc.addClickCount(summary.getActionNum(),summary.getArticleType());
            }else{
                return false ;
            }

            return true ;
        }

    }

    public  class VC  {
       private  String date ;
       private  int[] viewCount ;
       private  int[] clickCount ;




        public  VC(){

        }
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int[] getViewCount() {
            return viewCount;
        }

        public void addViewCount(int viewCount,int articleType) {
            this.viewCount[articleType] += viewCount;
        }

        public int[] getClickCount() {
            return clickCount;
        }

        public void addClickCount(int clickCount,int articleType) {
            this.clickCount[articleType] += clickCount;
        }


    }

}

