package com.dianping.ssp.recommend.dashboard.dto;

import java.util.List;

/**
 * Created by Xiaowei on 16/11/29.
 */
public class ChartVo {
	private List<String> timeline;
	private List<LineVo> lines;

	public List<String> getTimeline() {
		return timeline;
	}

	public void setTimeline(List<String> timeline) {
		this.timeline = timeline;
	}

	public List<LineVo> getLines() {
		return lines;
	}

	public void setLines(List<LineVo> lines) {
		this.lines = lines;
	}
}
