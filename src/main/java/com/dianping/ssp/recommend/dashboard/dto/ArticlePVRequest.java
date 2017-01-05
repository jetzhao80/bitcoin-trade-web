package com.dianping.ssp.recommend.dashboard.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jnkmhbl on 16/12/29.
 */
public class ArticlePVRequest implements Serializable{
    private Date startTime ;
    private Date endTime ;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
