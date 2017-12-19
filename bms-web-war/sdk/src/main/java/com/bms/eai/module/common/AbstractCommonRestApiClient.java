package com.bms.eai.module.common;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bms.eai.module.api.config.ClientOperationsApiAccess;
import com.bms.eai.module.api.config.xml.CmServerDetails;
import com.bms.eai.module.core.AbstractModuleConfig;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractCommonRestApiClient <T,V> extends AbstractModuleConfig<T,V>  {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ClientOperationsApiAccess clientOperationsApiAccess;

	protected CmServerDetails getCommonModuleServerDetails() {
		final Optional<CmServerDetails> serverDetailsConfig = this.clientOperationsApiAccess.getCommonModuleServerDetails();
		return serverDetailsConfig.isPresent()?serverDetailsConfig.get():null;
	}
	
}
