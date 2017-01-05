package com.dianping.ssp.recommend.dashboard.dto;

/**
 * Created by Xiaowei on 16/11/29.
 */
public class StatementResponseVo {
	private ChartVo conversionChart;
	private ChartVo exposureChart;
	private ChartVo coverageChart;
	private ChartVo diversityChart;
	private int code;
	private String msg;

	public ChartVo getConversionChart() {
		return conversionChart;
	}

	public void setConversionChart(ChartVo conversionChart) {
		this.conversionChart = conversionChart;
	}

	public ChartVo getExposureChart() {
		return exposureChart;
	}

	public void setExposureChart(ChartVo exposureChart) {
		this.exposureChart = exposureChart;
	}

	public ChartVo getCoverageChart() {
		return coverageChart;
	}

	public void setCoverageChart(ChartVo coverageChart) {
		this.coverageChart = coverageChart;
	}

	public ChartVo getDiversityChart() {
		return diversityChart;
	}

	public void setDiversityChart(ChartVo diversityChart) {
		this.diversityChart = diversityChart;
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
