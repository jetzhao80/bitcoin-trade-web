package com.dianping.ssp.recommend.dashboard.dto;

/**
 * Created with IntelliJ IDEA
 * AlgoInfoVo
 *
 * @author Yuxiang Ye
 * @date 2016/12/2
 * @time 上午11:27
 */
public class AlgoInfoVo {
    private int algoId;
    private int algoVersion;
    private String desc;

    public int getAlgoId() {
        return algoId;
    }

    public void setAlgoId(int algoId) {
        this.algoId = algoId;
    }

    public int getAlgoVersion() {
        return algoVersion;
    }

    public void setAlgoVersion(int algoVersion) {
        this.algoVersion = algoVersion;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
