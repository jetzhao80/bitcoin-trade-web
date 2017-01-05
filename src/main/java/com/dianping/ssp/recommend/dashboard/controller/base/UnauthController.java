package com.dianping.ssp.recommend.dashboard.controller.base;

import com.dianping.ssp.recommend.dashboard.constant.FrontGroundText;
import com.dianping.ssp.recommend.dashboard.constant.ResultCode;
import com.dianping.ssp.recommend.dashboard.util.LionConfigUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dianping.ssp.recommend.dashboard.dto.ResultDto;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mr.Bian
 *
 */
@Controller
@RequestMapping("/recommend")
@SuppressWarnings("rawtypes")
public class UnauthController extends BaseController{
	
	@RequestMapping("/unauthorized")
	@ResponseBody
	public ModelAndView unAuth(){
		ResultDto dto= new ResultDto(ResultCode.NO_AUTH,String.format(FrontGroundText.NO_AUTH_TEXT,getUserName(), LionConfigUtil.getAuthAdmin()));
		ModelAndView mv = new ModelAndView();
		mv.setViewName("unauth");
		mv.addObject("name", getUserName());
		mv.addObject("msg", dto.getErrMsg());
		return mv;
	}
	
}
