package com.bms.eai.property.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.AbstractCrudService;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropAssets;
import com.bms.eai.property.model.entity.PropContactDetails;
import com.bms.eai.property.model.service.IPropAnnualRemindersService;
import com.bms.eai.property.model.service.IPropAssetsService;
import com.bms.eai.property.model.service.IPropContactDetailsService;

/**
 * @author kul_sudhakar
 *
 */
@Service
public class PropAssetsServiceImpl extends AbstractCrudService<PropAssets, String> implements IPropAssetsService {

	@Autowired
	private IPropContactDetailsService contactDetailsService;
	
	@Autowired
	private IPropAnnualRemindersService propAnnualRemindersService;

	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public PropAssets add(PropAssets entity,PropContactDetails contactDetails,PropAnnualReminders annualReminders) throws ServiceException {
		
		entity.setPropAnnualReminders(this.propAnnualRemindersService.add(annualReminders));
		entity.setPropContactDetails( this.contactDetailsService.add(contactDetails));
		PropAssets savedEntity = super.add(entity);
		return savedEntity;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	@Override
	public PropAssets update(String id, PropAssets entity, PropContactDetails contactDetails,PropAnnualReminders annualReminders) throws ServiceException {
		 
		entity.setPropAnnualReminders(this.propAnnualRemindersService.update(entity.getAnnualRemindersId(), annualReminders));
		entity.setPropContactDetails(this.contactDetailsService.update(entity.getContactDetailsId(), contactDetails));
		PropAssets updatedEntity = super.update(id,entity);
		return updatedEntity;
	}
	
}
