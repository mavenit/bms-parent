package com.bms.eai.common.security.entity;

import java.io.Serializable;

/**
 * @author kul_sudhakar
 *
 */
public interface UserIdentity<T extends Serializable> extends Serializable {

	T getUserId();
    String getUserIdString();
    String getUsername();
    byte[] getPassword();
    byte[] getSalt();
    int getIteration();
    String getPrefLanguageCode();
	
}
