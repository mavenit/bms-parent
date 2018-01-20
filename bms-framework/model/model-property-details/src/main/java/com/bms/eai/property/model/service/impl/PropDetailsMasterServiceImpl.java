package com.bms.eai.property.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.AbstractCrudService;
import com.bms.eai.common.model.entity.PropAttachments;
import com.bms.eai.common.model.service.IPropAttachmentService;
import com.bms.eai.property.model.entity.PropDetailsMaster;
import com.bms.eai.property.model.service.IPropDetailsMasterService;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PropDetailsMasterServiceImpl extends AbstractCrudService<PropDetailsMaster, String> implements IPropDetailsMasterService {

	@Autowired
	private IPropAttachmentService attachmentService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public PropDetailsMaster add(PropDetailsMaster entity,PropAttachments attachment) throws ServiceException {
		PropDetailsMaster savedEntity  = super.add(entity);
		if(savedEntity==null) {return null;}
		if(attachment!=null) {
			attachment.setLinkId(savedEntity.getId());
			this.attachmentService.add(attachment);
		}
		return savedEntity;
	}
	
}
