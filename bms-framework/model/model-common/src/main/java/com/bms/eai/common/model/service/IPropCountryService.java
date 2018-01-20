package com.bms.eai.common.model.service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropCountry;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropCountryService extends ICrudService<PropCountry, String> {

	PropCountry findEqualByColumn(final String columnName,final String linkId) throws ServiceException;
}
