package com.bms.eai.common.model.service.impl;

import static com.bms.eai.common.model.support.FindByPropertyValueSpecifications.equal;
import static com.bms.eai.common.model.support.FindByPropertyValueSpecifications.properties;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.cmn.error.support.ServiceExceptionHelper;
import com.bms.eai.common.model.core.AbstractCrudService;
import com.bms.eai.common.model.entity.PropCountry;
import com.bms.eai.common.model.service.IPropCountryService;
import com.bms.eai.error.codes.FrameworkErrorCodes;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PropCountryServiceImpl extends AbstractCrudService<PropCountry, String> implements IPropCountryService {

	@Transactional(readOnly = true)
	@Override
	public PropCountry findEqualByColumn(final String columnName,final String columnValue) throws ServiceException {
		try {
            return getRepository().findOne(properties(equal(columnName, columnValue)));
        } catch (Exception e) {
            throw ServiceExceptionHelper.wrap(e, FrameworkErrorCodes.GET_BY_NAME_ERROR,columnName, columnValue, e.getMessage());
        }
	}
	
}
