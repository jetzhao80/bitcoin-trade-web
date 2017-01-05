package com.dianping.ssp.recommend.dashboard.dto;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 * StatementRequest
 *
 * @author Yuxiang Ye
 * @date 2016/11/30
 * @time 上午11:52
 */
public class StatementRequest extends StraAndAlgoRequest {
    private static final long serialVersionUID = -3405528009796492142L;

    private List<Integer> strategyIds;
    private List<AlgoInfoVo> algos;
    
    private int statementType;

    public List<Integer> getStrategyIds() {
        return strategyIds;
    }

    public void setStrategyIds(List<Integer> strategyIds) {
        this.strategyIds = strategyIds;
    }

    public List<AlgoInfoVo> getAlgos() {
        return algos;
    }

    public void setAlgos(List<AlgoInfoVo> algos) {
        this.algos = algos;
    }

	public int getStatementType() {
		return statementType;
	}

	public void setStatementType(int statementType) {
		this.statementType = statementType;
	}
    
}
