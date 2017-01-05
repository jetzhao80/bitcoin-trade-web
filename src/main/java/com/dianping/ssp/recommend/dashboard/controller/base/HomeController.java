package com.dianping.ssp.recommend.dashboard.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Mr.Bian
 *
 */
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home(){
		return "redirect:/recommend/statement";
	}
	
}
