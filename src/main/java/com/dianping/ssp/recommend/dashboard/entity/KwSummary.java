package com.dianping.ssp.recommend.dashboard.entity;

import java.util.Date;

/**
 * Created by jnkmhbl on 16/12/23.
 */
public class KwSummary {
    private int  articleType;
    private int  actionType;
    private Date updatetime;
    private int actionNum ;
    private int analysisType;
    private int  id;
    private String partitionDate ;

    public String getPartitionDate() {
        return partitionDate;
    }

    public void setPartitionDate(String partitionDate) {
        this.partitionDate = partitionDate;
    }

    public int getActionNum() {
        return actionNum;
    }

    public void setActionNum(int actionNum) {
        this.actionNum = actionNum;
    }

    public int getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(int analysisType) {
        this.analysisType = analysisType;
    }





    public int getArticleType() {
        return articleType;
    }

    public void setArticleType(int articleType) {
        this.articleType = articleType;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
