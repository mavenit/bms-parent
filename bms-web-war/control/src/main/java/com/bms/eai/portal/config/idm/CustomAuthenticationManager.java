package com.bms.eai.portal.config.idm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bms.eai.portal.core.AbstractController;
import com.bms.eai.portal.dto.UserProfileDto;
import com.bms.eai.portal.lib.CmnUtil;
import com.bms.eai.portal.model.LoginBean;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
public class CustomAuthenticationManager extends AbstractController implements AuthenticationManager {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserProfileDto userProfileDto;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		logger.info("Performing custom authentication");
		logger.info("Username: " + auth.getName() + " - Password: " + auth.getCredentials());
		
		//String userId = auth.getName();
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(auth.getName());
		loginBean.setPassword(String.valueOf(auth.getCredentials()));
		
		UserProfile profile = userProfileDto.login(loginBean);
		if(CmnUtil.isObjNull(profile) || CmnUtil.isObjNull(profile.getId())) {
			logger.info("Invalid username or password.");
			throw new BadCredentialsException("Invalid username or password.");
		}
		else {
	
		}
				
	/*	try {
			//login = getIdmService().login(login);
		} catch(TokenException t) {
		    if(WebUtil.checkTokenError(t)) throw t;
		} catch (Exception e) {			
			String errorCode = null;
			if(e instanceof IdmException) {
				errorCode = ((IdmException) e).getInternalErrorCode();
			}*/
			
			/*logger.error("IDM Response Error: {} - {}", errorCode, e.getMessage());
			if(BaseUtil.isEqualsCaseIgnore(ResponseCodeEnum.E503IDM000.name(), errorCode)) {
				throw new BadCredentialsException("System unavailable. Please contact administrator. <br/>" + (errorCode != null ? " [ " + errorCode + " ]" : ""));
			} else {
				throw new BadCredentialsException(messageService.getMessage("lbl.err.inv.pwd") + (errorCode != null ? " [ " + errorCode + " ]" : ""));
			}*/
		//}
		
		
		/*if(login == null || login.getToken() == null) {
			throw new BadCredentialsException(messageService.getMessage("lbl.err.inv.pwd"));
		}*/
		
		//TokenDto token = login.getToken();
		
		/*UserProfile userProfile = null;
		
		userProfile = new UserProfile();
		userProfile.setUsername(login.getUserName());*/
		//userProfile.setUserName(login.getPassword());
			
		/*try {
			//userProfile = getIdmService(login.getClientId(), token.getAccessToken()).getUserProfileById(userId, true, true);
			logger.info("" +new ObjectMapper().valueToTree(userProfile));
		} catch(TokenException t) {
		    if(WebUtil.checkTokenError(t)) throw t;
		} catch (IdmException e) {
			if(WebUtil.checkSystemDown(e)) throw e;
		} catch (Exception e) {			
			String errorCode = null;
			if(e instanceof IdmException) {
				errorCode = ((IdmException) e).getInternalErrorCode();
			}
			logger.error("IDM Response Error: {} - {}", errorCode, e.getMessage());
			throw new BadCredentialsException(messageService.getMessage("lbl.err.inv.pwd") + (errorCode != null ? " [ " + errorCode + " ]" : ""));
		}*/
		
		/*logger.debug("userProfile >>>" + userProfile);
		if(BaseUtil.isObjNull(userProfile) || BaseUtil.isObjNull(userProfile.getUserId()) ) {
			throw new BadCredentialsException(messageService.getMessage("lbl.err.inv.pwd"));
		} else {
			logger.info("UserType: " +userProfile.getUserType());
			CompanyProfile cmpnyProf = null;
			CustomUserDetails customUserDetails = null;
			if("emp".equalsIgnoreCase(login.getUserType())) {
				try {
//					cmpnyProf = getCmnService().findCompanyByUserId(login.getUserId());
					logger.debug("getCmpanyRegNo >>>" + userProfile.getCmpanyRegNo());
					cmpnyProf = getCmnService().findCompanyProfileByregNum(userProfile.getCmpanyRegNo());
				} catch(TokenException t) {
				    if(WebUtil.checkTokenError(t)) throw t;
				} catch (IdmException e) {
					if(WebUtil.checkSystemDown(e)) throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw new BadCredentialsException(messageService.getMessage("lbl.err.inv.pwd"));
				}
			}
			userProfile.setFWCMSLogin(login.isFWCMSLogin());
			if(cmpnyProf != null) {
				customUserDetails = new CustomUserDetails(userProfile, cmpnyProf, token);
			} else {
				customUserDetails = new CustomUserDetails(userProfile, token);
			}
			
			return new UsernamePasswordAuthenticationToken(
					customUserDetails, 
					auth.getCredentials(), 
					getAuthorities(userProfile));
		}*/
		
		CustomUserDetails customUserDetails = new CustomUserDetails(profile);
		
		return new UsernamePasswordAuthenticationToken(
				customUserDetails, 
				auth.getCredentials(), 
				getAuthorities(profile));
		//throw new BadCredentialsException("errrrrrrrrrrrrrrrrrrrr");
	}
	
	
	public Collection<GrantedAuthority> getAuthorities(UserProfile userProfile) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		logger.info("userProfile>>>>>>>>>>>>>>>>>>" + !CmnUtil.isObjNull(userProfile));
		
		// Get roles from UserProfile object
		if(!CmnUtil.isObjNull(userProfile) && !CmnUtil.isListNull(userProfile.getRoleList())) {
			logger.info("SIZE>>>>>>>>>>>>>>>>>>" + userProfile.getRoleList().size());
			for(String role : userProfile.getRoleList()) {
				logger.info("ROLE>>>>>>>>>>>>>>>>>>" + role);
				authList.add(new SimpleGrantedAuthority(role));
			}
		}
		// Return list of granted authorities
		return authList;
  	}
	
}
