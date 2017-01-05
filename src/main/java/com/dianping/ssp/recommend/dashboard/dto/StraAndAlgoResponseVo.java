package com.dianping.ssp.recommend.dashboard.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Xiaowei on 16/11/28.
 */
public class StraAndAlgoResponseVo {
	private Map<Integer, String> strategies;
	private List<AlgoInfoVo> algos;
	private int code;
	private String msg;

	public Map<Integer, String> getStrategies() {
		return strategies;
	}

	public void setStrategies(Map<Integer, String> strategies) {
		this.strategies = strategies;
	}

	public List<AlgoInfoVo> getAlgos() {
		return algos;
	}

	public void setAlgos(List<AlgoInfoVo> algos) {
		this.algos = algos;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
