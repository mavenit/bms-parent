package com.bms.eai.portal.controllers.idm;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bms.eai.portal.constants.PageConstants;
import com.bms.eai.portal.core.AbstractController;
import com.bms.eai.portal.model.LoginBean;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
@Controller
public class LoginController extends AbstractController {

private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView view(HttpSession session) {
		logger.info("calling login page");
		ModelAndView mav = new ModelAndView();	
		mav.addObject("loginBean", new LoginBean());
		mav.setViewName("login");
		mav.addObject("isLoginPage", true);
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)  
    public ModelAndView login(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result) {
		logger.info("calling login post page");
		return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
	}
	
	@RequestMapping(value=PageConstants.LOGIN_SUC, method=RequestMethod.GET)  
    public ModelAndView successLogin(@ModelAttribute UserProfile profile) {
		logger.info("calling login success page");
    	if(getCurrentUser() != null) {
    		logger.info("---------------successLogin"+getCurrentUserId());
    		//logger.info(getCurrentUser().getUserId());
    		return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
    	}
        return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
    } 
	
	@RequestMapping(value=PageConstants.LOGOUT, method=RequestMethod.GET)  
    public ModelAndView successLogoutGet(@ModelAttribute UserProfile profile, HttpSession session) {
    	if(getCurrentUser() != null) {
    		logger.info("---------------logout GEt "+getCurrentUserId());
    		return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
    	}
        return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
    } 
	
	@RequestMapping(value=PageConstants.LOGOUT, method=RequestMethod.POST)  
    public ModelAndView successLogoutPost(@ModelAttribute UserProfile profile, HttpSession session) {
    	if(getCurrentUser() != null) {
    		logger.info("---------------logout Post "+getCurrentUserId());
    		return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
    	}
        return new ModelAndView(PageConstants.getRedirectUrl(PageConstants.HOME));
    } 

    @RequestMapping(value=PageConstants.LOGIN_ERR, method=RequestMethod.GET)  
    public ModelAndView invalidLogin() {  
    	logger.info("---------------invalidLogin"); 
    	ModelAndView mav = new ModelAndView();	
		mav.addObject("loginBean", new LoginBean());
		mav.setViewName("login");
		mav.addObject("isLoginPage", true);
		return mav;
    }  
    
    @RequestMapping(value=PageConstants.ERROR, method=RequestMethod.GET)  
    public ModelAndView accessDeniedPage() {  
    	logger.info("---------------accessDeniedPage"); 
    	ModelAndView mav = new ModelAndView();	
		mav.setViewName("login");
		return mav;
    }  
    
}
