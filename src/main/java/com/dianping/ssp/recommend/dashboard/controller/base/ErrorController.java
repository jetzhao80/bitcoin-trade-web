package com.dianping.ssp.recommend.dashboard.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mr.Bian
 *
 */
@Controller
public class ErrorController {

	@RequestMapping("/404")
	public String NotFound(){
		return "404"; 
	}
	
	@RequestMapping("/500")
	public String ServerErr(){
		return "500";
	}
}
