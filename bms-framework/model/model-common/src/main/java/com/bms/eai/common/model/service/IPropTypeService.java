package com.bms.eai.common.model.service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropType;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropTypeService extends ICrudService<PropType, String> {

	PropType findEqualByColumn(final String columnName,final String linkId) throws ServiceException;
}
