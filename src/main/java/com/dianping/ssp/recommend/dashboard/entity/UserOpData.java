package com.dianping.ssp.recommend.dashboard.entity;

import java.io.Serializable;

public class UserOpData implements Serializable{
	
	private static final long serialVersionUID = 9194135162212745231L;
	
	private long strategyId;
	private String algoIdAndVersion;
	private String time;
	private Double conversionRate;
	private Double coverageRate;
	private Double showRate;
	private Double diversity;
	/**
	 * 可选展示，点击数
	 */
	private long clickCount;
	/**
	 * 可选展示，推荐数
	 */
	private long recmdCount;
	/**
	 * 可选展示，展示数
	 */
	private long showCount;
	/**
	 * 可选展示，文章数
	 */
	private long articleCount;
	
	//------------多样性
	/**
	 * 可选展示，用户数
	 */
	private long userCount;
	/**
	 * 可选展示，推荐总类目数
	 */
	private long recmdCategoryCount;
	/**
	 * 可选展示，总类目数
	 */
	private long totalCategoryCount;
	
	public long getStrategyId() {
		return strategyId;
	}
	public void setStrategyId(long strategyId) {
		this.strategyId = strategyId;
	}
	public String getAlgoIdAndVersion() {
		return algoIdAndVersion;
	}
	public void setAlgoIdAndVersion(String algoIdAndVersion) {
		this.algoIdAndVersion = algoIdAndVersion;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(Double conversionRate) {
		this.conversionRate = conversionRate;
	}
	public Double getCoverageRate() {
		return coverageRate;
	}
	public void setCoverageRate(Double coverageRate) {
		this.coverageRate = coverageRate;
	}
	public Double getShowRate() {
		return showRate;
	}
	public void setShowRate(Double showRate) {
		this.showRate = showRate;
	}
	public long getClickCount() {
		return clickCount;
	}
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}
	public long getRecmdCount() {
		return recmdCount;
	}
	public void setRecmdCount(long recmdCount) {
		this.recmdCount = recmdCount;
	}
	public long getShowCount() {
		return showCount;
	}
	public void setShowCount(long showCount) {
		this.showCount = showCount;
	}
	public Double getDiversity() {
		return diversity;
	}
	public void setDiversity(Double diversity) {
		this.diversity = diversity;
	}
	public long getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(long articleCount) {
		this.articleCount = articleCount;
	}
	public long getUserCount() {
		return userCount;
	}
	public void setUserCount(long userCount) {
		this.userCount = userCount;
	}
	public long getRecmdCategoryCount() {
		return recmdCategoryCount;
	}
	public void setRecmdCategoryCount(long recmdCategoryCount) {
		this.recmdCategoryCount = recmdCategoryCount;
	}
	public long getTotalCategoryCount() {
		return totalCategoryCount;
	}
	public void setTotalCategoryCount(long totalCategoryCount) {
		this.totalCategoryCount = totalCategoryCount;
	}
	
}
