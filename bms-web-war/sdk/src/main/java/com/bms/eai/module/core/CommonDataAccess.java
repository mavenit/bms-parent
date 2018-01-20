package com.bms.eai.module.core;

import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.module.beans.JsonResponseBean;

/**
 * @author kul_sudhakar
 *
 */
public interface CommonDataAccess<T extends AbstractSdkEntity> extends SimpleDataAccess<T > {

	public JsonResponseBean loadByName(String name) throws ResourceAccessException, Exception;
	
}
