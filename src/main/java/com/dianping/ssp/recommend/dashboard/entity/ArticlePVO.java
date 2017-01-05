package com.dianping.ssp.recommend.dashboard.entity;

import java.util.Date;

/**
 * Created by jnkmhbl on 16/12/29.
 */
public class ArticlePVO {
    private int  id;
    private int  originId;
    private java.lang.String  domainId;
    private java.lang.String  source;
    private java.util.Date  calDt;
    private java.lang.String  url;
    private int  isNew;
    private int  pvDiff;
    private java.util.Date  createTime;
    private java.util.Date  updatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCalDt() {
        return calDt;
    }

    public void setCalDt(Date calDt) {
        this.calDt = calDt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getPvDiff() {
        return pvDiff;
    }

    public void setPvDiff(int pvDiff) {
        this.pvDiff = pvDiff;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
