package com.bms.eai.property.model.service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropAssets;
import com.bms.eai.property.model.entity.PropContactDetails;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropAssetsService extends ICrudService<PropAssets, String> {

	public PropAssets add(PropAssets entity,
					      PropContactDetails contactDetails,
					      PropAnnualReminders annualReminders) throws ServiceException;
	
	public PropAssets update(String id,PropAssets entity,
		      PropContactDetails contactDetails,
		      PropAnnualReminders annualReminders) throws ServiceException;
		

	}
