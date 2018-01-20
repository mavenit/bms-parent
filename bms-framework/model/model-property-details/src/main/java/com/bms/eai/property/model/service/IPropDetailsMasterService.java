package com.bms.eai.property.model.service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropAttachments;
import com.bms.eai.property.model.entity.PropDetailsMaster;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropDetailsMasterService extends ICrudService<PropDetailsMaster, String> {

	public PropDetailsMaster add(PropDetailsMaster entity,PropAttachments attachment) throws ServiceException;
	
}
