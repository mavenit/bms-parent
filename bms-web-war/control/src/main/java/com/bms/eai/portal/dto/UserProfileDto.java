package com.bms.eai.portal.dto;

import com.bms.eai.portal.model.LoginBean;
import com.bms.eai.portal.model.UserProfile;

/**
 * @author kul_sudhakar
 *
 */
public interface UserProfileDto {

	public UserProfile login(final LoginBean loginBean);

	public UserProfile createUser(final UserProfile userProfileBean);

}
