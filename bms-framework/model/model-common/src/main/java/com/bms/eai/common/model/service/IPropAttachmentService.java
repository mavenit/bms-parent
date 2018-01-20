package com.bms.eai.common.model.service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropAttachments;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropAttachmentService extends ICrudService<PropAttachments, String> {

	PropAttachments findEqualByColumn(final String columnName,final String linkId) throws ServiceException;
	
}
