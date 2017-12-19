package com.bms.eai.module.prop;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bms.eai.module.api.config.ClientOperationsApiAccess;
import com.bms.eai.module.api.config.xml.PdServerDetails;
import com.bms.eai.module.core.AbstractModuleConfig;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractPropDetailsRestApiClient<T,V> extends AbstractModuleConfig<T,V>  {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ClientOperationsApiAccess propertyClientApiAccess;

	protected PdServerDetails getPropDetailsServerDetails() {
		final Optional<PdServerDetails> serverDetailsConfig = this.propertyClientApiAccess.getPdServerDetails();
		return serverDetailsConfig.isPresent()?serverDetailsConfig.get():null;
	}
	
}
