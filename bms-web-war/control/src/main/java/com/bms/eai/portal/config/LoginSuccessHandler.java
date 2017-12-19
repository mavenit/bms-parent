package com.bms.eai.portal.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.bms.eai.portal.config.idm.CustomUserDetails;
import com.bms.eai.portal.constants.PageConstants;
import com.bms.eai.portal.lib.DateUtil;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@Autowired
	protected MessageService messageService;*/
	
	@SuppressWarnings("unused")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		logger.info("onAuthenticationSuccess>>>>>>>>>>>>>>>>>> " + DateUtil.getCurrentDate());
		if(authentication != null) {
			logger.info(authentication.getName() + " logged in at " + DateUtil.getCurrentDate());
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			logger.info(authentication.getAuthorities().toString());
			
			UserProfile userProfile = null;
			if(authentication.isAuthenticated() && authentication.getPrincipal() instanceof CustomUserDetails) {
				CustomUserDetails cud = (CustomUserDetails) authentication.getPrincipal();
				userProfile = cud.getProfile();
				
				//logger.info("====>"+userProfile.isFWCMSLogin());
				//logger.info(userProfile.getStatus());
				//String portalType = messageService.getMessage("app.portal.type");
				
				/*if(CmnUtil.equalsIgnoreCase(BaseConstants.FIRST_TIME_PWD, userProfile.getStatus())) {
					response.sendRedirect(request.getContextPath() + PageConstants.PAGE_IDM_USR_CHNG_PWORD);
				} else {
					String roleRedirectPg = redirectPageByRole(userProfile.getRolesList(), userProfile.getUserId());
					if(userProfile.isFWCMSLogin()){
						userProfile.setFWCMSLogin(false);
						response.sendRedirect(request.getContextPath() + PageConstants.PAGE_FWCMS_FIRST_LOGIN);
					} else if(BaseUtil.isEqualsCaseIgnore("emp", portalType)) {
						response.sendRedirect(request.getContextPath() + PageConstants.PAGE_APP_LIST);
					} else if(!BaseUtil.isObjNull(roleRedirectPg)){
						response.sendRedirect(request.getContextPath() + roleRedirectPg);
					} else {
						response.sendRedirect(request.getContextPath() + PageConstants.PAGE_APP_INBOX);
					}
				}*/
				request.getRequestDispatcher(PageConstants.HOME).forward(request, response);
				//response.sendRedirect(request.getContextPath() + PageConstants.LOGIN_SUC);
			}
			
		} else {
			//response.sendRedirect(request.getContextPath() + PageConstants.HOME);
			request.getRequestDispatcher( PageConstants.LOGIN).forward(request, response);
		}
		
	}
	
	/*public String redirectPageUrlByRole(String role){
		List<UserRole> roleLst = new ArrayList<UserRole>();
		UserRole ur = new UserRole();
		ur.setRoleCode(role);
		roleLst.add(ur);
		String urlPath = redirectPageByRole(roleLst, null);
		if(BaseUtil.isObjNull(urlPath))
			urlPath = PageConstants.PAGE_APP_INBOX;
		
		return urlPath;
	}*/
	
	
	
}
