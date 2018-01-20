package com.bms.eai.module.core;

import org.springframework.web.client.ResourceAccessException;

import com.bms.eai.module.beans.JsonResponseBean;

/**
 * @author kul_sudhakar
 *
 */
public interface SimpleDataAccess<T extends AbstractSdkEntity> {

	public JsonResponseBean create(T t) throws ResourceAccessException, Exception;
	
	public JsonResponseBean update(String updateId,T t) throws ResourceAccessException, Exception;
	
	public JsonResponseBean load(String id) throws ResourceAccessException, Exception;
	
	public JsonResponseBean delete(String id) throws ResourceAccessException, Exception ;
	
	public JsonResponseBean getPage(Integer pageNumber,String fieldName) throws ResourceAccessException, Exception ;
	
}
