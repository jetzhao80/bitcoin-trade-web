package com.dianping.ssp.recommend.dashboard.dto;

import java.util.List;

/**
 * Created by jnkmhbl on 16/12/22.
 */
public class KwRequest {
     private long startTime ;
     private long endTime ;
     private int analysisType ;    // 维度： 用户， 文章
     private int articleType;
     private List<Integer>  articleTypeList ;

     public int getAnalysisType() {
          return analysisType;
     }

     public void setAnalysisType(int analysisType) {
          this.analysisType = analysisType;
     }

     public long getStartTime() {
          return startTime;
     }

     public void setStartTime(long startTime) {
          this.startTime = startTime;
     }

     public long getEndTime() {
          return endTime;
     }

     public void setEndTime(long endTime) {
          this.endTime = endTime;
     }

     public int getArticleType() {
          return articleType;
     }

     public void setArticleType(int articleType) {
          this.articleType = articleType;
     }

     public List<Integer> getArticleTypeList() {
          return articleTypeList;
     }

     public void setArticleTypeList(List<Integer> articleTypeList) {
          this.articleTypeList = articleTypeList;
     }
}
