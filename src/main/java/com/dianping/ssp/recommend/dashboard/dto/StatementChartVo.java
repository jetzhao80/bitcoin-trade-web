package com.dianping.ssp.recommend.dashboard.dto;

/**
 * Created by Xiaowei on 16/11/29.
 */
public class StatementChartVo {
	private ChartVo chartVo;
	private int code;
	private String msg;

	public ChartVo getChartVo() {
		return chartVo;
	}

	public void setChartVo(ChartVo chartVo) {
		this.chartVo = chartVo;
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

	public static StatementChartVo errorChartVo(int code,String msg){
		StatementChartVo vo = new StatementChartVo();
		vo.setChartVo(null);
		vo.setCode(code);
		vo.setMsg(msg);
		return vo ;
	}
	public static StatementChartVo successChartVo(ChartVo chartVo){
		StatementChartVo vo = new StatementChartVo();
		vo.setChartVo(chartVo);
		vo.setCode(200);
		vo.setMsg("success");
		return vo ;
	}
}
