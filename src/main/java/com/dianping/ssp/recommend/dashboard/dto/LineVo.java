package com.dianping.ssp.recommend.dashboard.dto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by Xiaowei on 16/11/29.
 */
public class LineVo {
	private String name;
	private List<Double> values;
	private List<LinkedHashMap<String, Double>> extraValues;

	public List<LinkedHashMap<String, Double>> getExtraValues() {
		return extraValues;
	}

	public void setExtraValues(List<LinkedHashMap<String, Double>> extraValues) {
		this.extraValues = extraValues;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	public void addValue(double value){
		if(values == null){
			values = new ArrayList<Double>();
		}
		values.add(value);
	}
	public void addDescription(LinkedHashMap<String, Double> map){
		if(extraValues == null){
			extraValues = new ArrayList<LinkedHashMap<String, Double>>();
		}
		extraValues.add(map);
	}
}
