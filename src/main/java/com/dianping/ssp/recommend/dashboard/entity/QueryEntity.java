package com.dianping.ssp.recommend.dashboard.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class QueryEntity implements Serializable{

	private static final long serialVersionUID = -720385902887946439L;
	/**
	 * only set algorithmVersions is not allowed， it must be accepted with algorithmIds
	 */
	private List<Integer> strategyIds;
	private List<Integer> algorithmIds;
	private List<Integer> algorithmVersions;
	/**
	 * startDate, >= time;
	 */
	private Date startDate;
	/**
	 * end date, < time;
	 */
	private Date endDate;
	/**
	 * total article number in search engine
	 */
	private Integer totalArticleNum;
	/**
	 * only the two values below this line are valid;
	 * {@link Calendar}
	 * Calendar.HOUR_OF_DAY
	 * Calendar.DAY_OF_YEAR
	 */
	private int timeType;

	/**
	 * sceneId list in config, null means no limit
	 */
	private List<Integer> sceneIds;

	private List<Integer> articleTypes;
	
	/**
	 * total article category number
	 */
	private int totalArticleCategoryNum;
	
	public List<Integer> getStrategyIds() {
		return strategyIds;
	}
	public void setStrategyIds(List<Integer> strategyIds) {
		this.strategyIds = strategyIds;
	}
	public List<Integer> getAlgorithmIds() {
		return algorithmIds;
	}
	public void setAlgorithmIds(List<Integer> algorithmIds) {
		this.algorithmIds = algorithmIds;
	}
	public List<Integer> getAlgorithmVersions() {
		return algorithmVersions;
	}
	public void setAlgorithmVersions(List<Integer> algorithmVersions) {
		this.algorithmVersions = algorithmVersions;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTimeType() {
		return timeType;
	}
	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	public List<Integer> getSceneIds() {
		return sceneIds;
	}

	public void setSceneIds(List<Integer> sceneIds) {
		this.sceneIds = sceneIds;
	}

	public Integer getTotalArticleNum() {
		return totalArticleNum;
	}

	public void setTotalArticleNum(Integer totalArticleNum) {
		this.totalArticleNum = totalArticleNum;
	}

	public int getTotalArticleCategoryNum() {
		return totalArticleCategoryNum;
	}
	public void setTotalArticleCategoryNum(int totalArticleCategoryNum) {
		this.totalArticleCategoryNum = totalArticleCategoryNum;
	}

	public List<Integer> getArticleTypes() {
		return articleTypes;
	}

	public void setArticleTypes(List<Integer> articleTypes) {
		this.articleTypes = articleTypes;
	}

	@Override
	public String toString() {
		return "QueryEntity{" +
				"strategyIds=" + strategyIds +
				", algorithmIds=" + algorithmIds +
				", algorithmVersions=" + algorithmVersions +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", totalArticleNum=" + totalArticleNum +
				", timeType=" + timeType +
				", sceneIds=" + sceneIds +
				", articleTypes=" + articleTypes +
				", totalArticleCategoryNum=" + totalArticleCategoryNum +
				'}';
	}
}
