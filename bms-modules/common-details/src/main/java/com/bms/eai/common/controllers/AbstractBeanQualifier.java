package com.bms.eai.common.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.bms.eai.common.model.service.IPropCountryService;
import com.bms.eai.common.model.service.IPropStateService;
import com.bms.eai.common.model.service.IPropTypeService;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractBeanQualifier {

	@Autowired
	protected IPropCountryService countryService;
	
	@Autowired
	protected IPropStateService stateService ;
	
	@Autowired
	protected IPropTypeService propTypeService;
}
