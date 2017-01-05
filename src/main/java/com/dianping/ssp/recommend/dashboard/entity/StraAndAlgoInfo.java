package com.dianping.ssp.recommend.dashboard.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 * StraAndAlgoInfo
 *
 * @author Yuxiang Ye
 * @date 2016/12/2
 * @time 上午10:15
 */
public class StraAndAlgoInfo implements Serializable {
    private static final long serialVersionUID = -6730944204613150713L;

    private int strategyId;
    private int algorithmId;
    private int algorithmVersion;

    public int getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(int strategyId) {
        this.strategyId = strategyId;
    }

    public int getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(int algorithmId) {
        this.algorithmId = algorithmId;
    }

    public int getAlgorithmVersion() {
        return algorithmVersion;
    }

    public void setAlgorithmVersion(int algorithmVersion) {
        this.algorithmVersion = algorithmVersion;
    }
}
