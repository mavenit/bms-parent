package com.bms.eai.portal.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bms.eai.portal.constants.ServiceUrlConstants;
import com.bms.eai.portal.core.AbstractRestTemplate;
import com.bms.eai.portal.lib.CmnUtil;
import com.bms.eai.portal.lib.JsonUtil;
import com.bms.eai.portal.model.JsonResponseBean;
import com.bms.eai.portal.model.LoginBean;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class UserProfileDtoImpl extends AbstractRestTemplate implements UserProfileDto {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileDtoImpl.class);
	
	@Override
	 public UserProfile createUser(final UserProfile userProfileBean){
			try {
				logger.info("Before Going to createPortalUser");
				JsonResponseBean jrb = restTemplate.postForObject(ServiceUrlConstants.USER_PROFILE_CREATE, userProfileBean, JsonResponseBean.class);
				logger.info("createPortalUser [loginId :"+userProfileBean.getFullName()+"]-[createPortalUser Response :"+jrb+"]");
				if(!CmnUtil.isObjNull(jrb) && CmnUtil.isObjNull(jrb.getStatusCode()) && (CmnUtil.isEquals(jrb.getStatusCode(), "00"))) {
					logger.info("createPortalUser [Status message :"+jrb.getStatusMessage()+"]-[Status Code :"+jrb.getStatusCode()+"]");
					logger.info("createPortalUser [Response message :"+jrb.getResponseObj().toString()+"]");
					JsonNode jn = JsonUtil.toJsonNode(jrb.getResponseObj());
					UserProfile rb=JsonUtil.fromJsonNode(jn, UserProfile.class);				
					logger.info("createPortalUser [profile-id :"+rb.getId()+"]");
					return rb;
				}
				return null;
				 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	@Override
	public UserProfile login(LoginBean loginBean) {
		try {
			logger.info("Before Going to login");
			JsonResponseBean jrb = this.prepareDummyData();
					//restTemplate.postForObject(ServiceUrlConstants.URL_LOGIN, loginBean, JsonResponseBean.class);
			logger.info("login [loginId :"+loginBean.getUserName()+"]-[createPortalUser Response :"+jrb+"]");
			if(!CmnUtil.isObjNull(jrb) && !CmnUtil.isObjNull(jrb.getStatusCode()) && CmnUtil.isEquals(jrb.getStatusCode(), "00")) {
				logger.info("login [Status message :"+jrb.getStatusMessage()+"]-[Status Code :"+jrb.getStatusCode()+"]");
				logger.info("login [Response message :"+jrb.getResponseObj().toString()+"]");
				JsonNode jn = JsonUtil.toJsonNode(jrb.getResponseObj());
				UserProfile rb=JsonUtil.fromJsonNode(jn, UserProfile.class);				
				logger.info("login [profile-id :"+rb.getId()+"]");
				return rb;
			}
			return null;
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private JsonResponseBean prepareDummyData() {
		
		List<String> rl = new ArrayList<String> ();
		rl.add("ROLE_USER");
		rl.add("ROLE_ADMIN");
		UserProfile up = new UserProfile();
		up.setEmail("asd@gmail.com");
		up.setContactNo("1654646123231");
		up.setRoleList(rl);
		up.setId(123161L);
		up.setUsername("sudhakar");
		up.setPassword("sudhakar");
		
		JsonResponseBean jrb = new JsonResponseBean();
		jrb.setResponseObj(up);
		jrb.setStatusCode("00");
		jrb.setStatusMessage("Success");
		
		return jrb;
	}

}
