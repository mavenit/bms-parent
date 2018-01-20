package com.bms.eai.module.api.config;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import generated.BmsModules;
import generated.CmServerDetails;
import generated.CommonModule;
import generated.PdServerDetails;
import generated.PropertyDetails;


/**
 * @author kul_sudhakar
 *
 */
@Service
public class ClientOperationsApiAccess implements InitializingBean {

 
	private String FILE_PATH = "E:/Personal_Ws/bms_client_api_routes.xml";

	private PropertyDetails propertyDetails;
	
	private CommonModule commonModule;

	@Override
	public void afterPropertiesSet() throws Exception {

		JAXBContext jaxbContext = JAXBContext.newInstance(BmsModules.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		BmsModules bmsModules = (BmsModules) jaxbUnmarshaller.unmarshal(new File(FILE_PATH));

		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		this.propertyDetails = bmsModules.getPropertyDetails();
		this.commonModule = bmsModules.getCommonModule();
	}

	// ===============  Configure the Property Module Operations ============
	
	public Optional<PdServerDetails> getPdServerDetails() {
		return Optional.ofNullable(this.propertyDetails.getPdServerDetails());
	}
	
	// ===============  Configure the Common Module Operations ============
	
	public Optional<CmServerDetails> getCommonModuleServerDetails() {
		return Optional.ofNullable(this.commonModule.getCmServerDetails());
	}
	
}
