package com.dianping.ssp.recommend.dashboard.constant;


import com.dianping.ssp.logger.LoggerConfig;
import com.dianping.ssp.logger.LoggerLevel;

/**
 *
 * @author Mr.Bian
 *
 */
public enum RecDashWebLogEnum {
	RCM_DASHBOARD_INFO("rcm_dashboard","controller",false, LoggerLevel.INFO,false),
	RCM_DASHBOARD_ERROR("rcm_dashboard","controller",true,LoggerLevel.ERROR,false),
	STATEMENT_SERVICE_INFO("statement","service",false, LoggerLevel.INFO,false),
	STATEMENT_SERVICE_ERROR("statement","service",true,LoggerLevel.ERROR,false),
	KW_SUMMARY_INFO("kwsummary","service",false,LoggerLevel.INFO,false),
	KW_SUMMARY_ERROR("kwsummary","service",true,LoggerLevel.ERROR,false),
	KW_DASHBOARD_ERROR("kw_dashboard","controller",true,LoggerLevel.ERROR,false),
	KW_DASHBOARD_INFO("kw_dashboard","controller",false,LoggerLevel.INFO,false)
	;

	RecDashWebLogEnum(String name, String category, boolean isError, LoggerLevel level, boolean perm) {
		loggerConfig = new LoggerConfig();
		loggerConfig.setApp(APP_NAME);
		loggerConfig.setCategory(category);
		loggerConfig.setName(name);
		loggerConfig.setLevel(level);
		loggerConfig.setDaily(true);
		loggerConfig.setPerm(perm);
		loggerConfig.setError(isError);
	}

	private static final String APP_NAME = "ssp-recommend-dashboard-web";

	private LoggerConfig loggerConfig;

	public LoggerConfig getValue() {
		return loggerConfig;
	}
}
