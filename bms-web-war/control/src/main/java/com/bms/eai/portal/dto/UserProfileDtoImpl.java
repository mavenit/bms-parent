package com.bms.eai.portal.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.codehaus.jackson.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bms.eai.common.lib.JsonApiUtil;
import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.portal.lib.CmnUtil;
import com.bms.eai.portal.model.LoginBean;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
@Component
public class UserProfileDtoImpl implements UserProfileDto {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileDtoImpl.class);
	
	@Override
	 public UserProfile createUser(final UserProfile userProfileBean){
			try {
				logger.info("Before Going to createPortalUser");
				JsonResponseBean jrb = new JsonResponseBean(); 
						//restTemplate.postForObject(ServiceUrlConstants.USER_PROFILE_CREATE, userProfileBean, JsonResponseBean.class);
				logger.info("createPortalUser [loginId :"+userProfileBean.getFullName()+"]-[createPortalUser Response :"+jrb+"]");
				if(!CmnUtil.isObjNull(jrb) && CmnUtil.isObjNull(jrb.getStatusCode()) && (CmnUtil.isEquals(jrb.getStatusCode(), "00"))) {
					logger.info("createPortalUser [Status message :"+jrb.getStatusMessage()+"]-[Status Code :"+jrb.getStatusCode()+"]");
					logger.info("createPortalUser [Response message :"+jrb.getResponseBean().toString()+"]");
					Optional<JsonNode> jn = JsonApiUtil.toJsonNode(jrb.getResponseBean());
					Optional<UserProfile> rb=JsonApiUtil.fromJsonNode(jn.get(), UserProfile.class);				
					logger.info("createPortalUser [profile-id :"+rb.get()+"]");
					return rb.get();
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
				logger.info("login [Response message :"+jrb.getResponseBean().toString()+"]");
				Optional<JsonNode> jn = JsonApiUtil.toJsonNode(jrb.getResponseBean());
				Optional<UserProfile> rb=JsonApiUtil.fromJsonNode(jn.get(), UserProfile.class);				
				logger.info("login [profile-id :"+rb.get()+"]");
				return rb.get();
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
		jrb.setResponseBean(up);
		jrb.setStatusCode("00");
		jrb.setStatusMessage("Success");
		
		return jrb;
	}

}
