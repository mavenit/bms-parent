package com.bms.eai.common.model.service.impl;

import static com.bms.eai.common.model.support.FindByPropertyValueSpecifications.equal;
import static com.bms.eai.common.model.support.FindByPropertyValueSpecifications.properties;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.cmn.error.support.ServiceExceptionHelper;
import com.bms.eai.common.model.core.AbstractCrudService;
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.common.model.service.IPropStateService;
import com.bms.eai.error.codes.FrameworkErrorCodes;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PropStateServiceImpl extends AbstractCrudService<PropState, String> implements IPropStateService {

	@Transactional(readOnly = true)
	@Lock(LockModeType.READ)
	@Override
	public PropState findEqualByColumn(final String columnName,final String columnValue) throws ServiceException {
		try {
            return getRepository().findOne(properties(equal(columnName, columnValue)));
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.GET_BY_NAME_ERROR,columnName, columnValue, e.getMessage());
        }
	}
	
	@Transactional(readOnly = true)
	@Lock(LockModeType.READ)
	@Override
	public List<PropState> findStatesByCountry(final String countryId) throws ServiceException {
		try {
			return getRepository().findAll(properties(equal("country", countryId)));
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.GET_BY_NAME_ERROR,"country", countryId, e.getMessage());
        }
	}
	
	
}
