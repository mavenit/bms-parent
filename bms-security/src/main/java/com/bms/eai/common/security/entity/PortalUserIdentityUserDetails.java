package com.bms.eai.common.security.entity;

import org.springframework.security.core.userdetails.User;

import com.bms.eai.common.security.principal.UserIdentityUserDetails;
import com.google.common.collect.Lists;

/**
 * @author kul_sudhakar
 *
 */
public class PortalUserIdentityUserDetails extends User implements UserIdentityUserDetails<PortalUserIdentity> {

	private PortalUserIdentity userIdentity;

	public PortalUserIdentityUserDetails(PortalUserIdentity userIdentity) {
		super(userIdentity.getUsername(), "pass", Lists.newArrayList());
		this.userIdentity = userIdentity;
	}

	@Override
	public PortalUserIdentity getUserIdentity() {
		return userIdentity;
	}

}
