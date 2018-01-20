package com.bms.eai.property.model.service;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropAttachments;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropContactDetails;
import com.bms.eai.property.model.entity.PropServiceProvider;

/**
 * @author kul_sudhakar
 *
 */
public interface IPropServiceProviderService extends ICrudService<PropServiceProvider, String> {

	PropServiceProvider add(PropServiceProvider entity,
							PropAnnualReminders annualReminders,
							PropAttachments attachment,
							PropContactDetails contactDetails) throws ServiceException;
	
	PropServiceProvider update(String id,PropServiceProvider entity,
			PropAnnualReminders annualReminders,
			PropAttachments attachment,
			PropContactDetails contactDetails) throws ServiceException;
	
}
