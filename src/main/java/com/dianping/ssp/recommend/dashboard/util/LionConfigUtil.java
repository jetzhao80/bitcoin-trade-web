package com.dianping.ssp.recommend.dashboard.util;

import com.dianping.lion.client.Lion;

/**
 *
 * @author Mr.Bian
 *
 */
public class LionConfigUtil {
	
	public static String getAuthAdmin(){
		return Lion.getStringValue("ssp-recommend-dashboard-web.auth.admin", "yuxiang.ye");
	}

}
