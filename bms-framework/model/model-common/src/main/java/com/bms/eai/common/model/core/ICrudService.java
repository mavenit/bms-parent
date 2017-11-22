package com.bms.eai.common.model.core;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bms.eai.cmn.error.ServiceException;

/**
 * @author kul_sudhakar
 *
 */
public interface ICrudService <T extends AbstractEntity<T, ID>, ID extends Serializable> {

	Page<T> getPaged(Pageable p) throws ServiceException;

	List<T> getAll() throws ServiceException;

	T getById(ID id) throws ServiceException;

	T add(T entity) throws ServiceException;

	List<T> addAll(List<T> entities) throws ServiceException;

	T update(ID id, T entity) throws ServiceException;

	void delete(ID id) throws ServiceException;

	void deleteAll(List<ID> ids) throws ServiceException;

	long totalCount() throws ServiceException;

	boolean exists(ID id) throws ServiceException;

	Page<T> search(String searchTerm, Pageable p) throws ServiceException;

	Class<T> getEntityClass();

	String getEntityName();

	String getTableName();
	
}
