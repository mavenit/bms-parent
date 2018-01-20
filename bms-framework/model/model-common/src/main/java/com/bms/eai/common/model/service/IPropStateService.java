package com.bms.eai.common.model.service;

import java.util.List;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropState;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropStateService extends ICrudService<PropState, String> {

	PropState findEqualByColumn(final String columnName,final String linkId) throws ServiceException;
	
	List<PropState> findStatesByCountry(final String countryId) throws ServiceException;
}
