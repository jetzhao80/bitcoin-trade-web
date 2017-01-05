package com.dianping.ssp.recommend.dashboard.controller.base;

import org.springframework.stereotype.Controller;

import com.sankuai.meituan.filter.util.User;
import com.sankuai.meituan.filter.util.UserUtils;

/**
 *
 * @author Mr.Bian
 *
 */
@Controller
public class BaseController {
	
	/**
	 * Get User Id
	 * 
	 */
	public int getUserId(){
		User user=getUser();
		if (user!=null) {
			return user.getId();
		}else {
			return 0;
		}
	}
	
	/**
	 * Get User Name
	 * 
	 */
	public String getUserName(){
		User user=getUser();
		if (user!=null) {
			return user.getName();
		}else {
			return "";
		}
	}
	
	/**
	 * Get User logIn
	 * 
	 */
	public String getUserLogin(){
		User user=getUser();
		if (user!=null) {
			return user.getLogin();
		}else {
			return "";
		}
	}
	
	public User getUser(){
		return UserUtils.getUser();
	}
}
