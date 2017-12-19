package com.bms.eai.property.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.AbstractCrudService;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropAttachments;
import com.bms.eai.property.model.entity.PropContactDetails;
import com.bms.eai.property.model.entity.PropServiceProvider;
import com.bms.eai.property.model.service.IPropAnnualRemindersService;
import com.bms.eai.property.model.service.IPropAttachmentService;
import com.bms.eai.property.model.service.IPropContactDetailsService;
import com.bms.eai.property.model.service.IPropServiceProviderService;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PropServiceProviderServiceImpl extends AbstractCrudService<PropServiceProvider, String> implements IPropServiceProviderService {

	@Autowired
	private IPropAnnualRemindersService propAnnualRemindersService;
	
	@Autowired
	private IPropContactDetailsService contactDetailsService;
	
	@Autowired
	private IPropAttachmentService attachmentService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public PropServiceProvider add(PropServiceProvider entity, 
								   PropAnnualReminders annualReminders,
								   PropAttachments attachment,
								   PropContactDetails contactDetails) throws ServiceException {
		
		entity.setPropAnnualReminders(this.propAnnualRemindersService.add(annualReminders));
		entity.setPropContactDetails(this.contactDetailsService.add(contactDetails));
		PropServiceProvider savedEntity  = super.add(entity);
		
		if(savedEntity==null) {
			return null;
		}
		if(attachment!=null) {
			attachment.setLinkId(savedEntity.getId());
			this.attachmentService.add(attachment);
		}
		
		return savedEntity;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public PropServiceProvider update(String id,PropServiceProvider entity,
									  PropAnnualReminders annualReminders,
									  PropAttachments attachment,
									  PropContactDetails contactDetails) throws ServiceException {
	 
		entity.setPropAnnualReminders(this.propAnnualRemindersService.update(entity.getAnnualRemindersId(), annualReminders));
		entity.setPropContactDetails(this.contactDetailsService.update(entity.getContactDetailsId(), contactDetails));
		PropServiceProvider updatedEntity =super.update(id,entity);
		if(attachment!=null) {
			PropAttachments pa = this.attachmentService.findEqualByColumn("linkId",id);
			this.attachmentService.update(pa.getId(),attachment);
		}
		return updatedEntity;
	}
	
}
