package com.bms.eai.common.model.support;

/**
 * @author kul_sudhakar
 *
 */
public interface SimpleDataAccess<T> {

	public T create(T t);
	
	public T update(T t);
	
	public T load(int id);
	
}
