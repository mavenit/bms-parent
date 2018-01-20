package com.bms.eai.portal.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kul_sudhakar
 *
 */
@Controller
public class MiscController {

 private static final Logger logger = LoggerFactory.getLogger(MiscController.class);
	
	@RequestMapping(value={"/","/home"})
	public ModelAndView viewIndex() {
		logger.info("mapping index/home page");
		ModelAndView mav = new ModelAndView();	
		mav.setViewName("home");
		return mav;
	}
	
}
