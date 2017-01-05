package com.dianping.ssp.recommend.dashboard.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * StraAndAlgoRequest
 *
 * @author Yuxiang Ye
 * @date 2016/11/30
 * @time 上午11:48
 */
public class StraAndAlgoRequest implements Serializable {
    private static final long serialVersionUID = -1517746767079786082L;
    
    private long startTime;
    private long endTime;
    private int timeType;
    private int entrance;
    private int biz;

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

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
    }

    public int getEntrance() {
        return entrance;
    }

    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }

    public int getBiz() {
        return biz;
    }

    public void setBiz(int biz) {
        this.biz = biz;
    }
}
