package com.bms.eai.module.api.config;

import java.io.File;
import java.util.Optional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.bms.eai.module.api.config.xml.BmsModules;
import com.bms.eai.module.api.config.xml.CmServerDetails;
import com.bms.eai.module.api.config.xml.CommonModule;
import com.bms.eai.module.api.config.xml.CountryOperations;
import com.bms.eai.module.api.config.xml.PaOperations;
import com.bms.eai.module.api.config.xml.PbdOperations;
import com.bms.eai.module.api.config.xml.PdServerDetails;
import com.bms.eai.module.api.config.xml.PfarOperations;
import com.bms.eai.module.api.config.xml.PfsOperations;
import com.bms.eai.module.api.config.xml.PfyOperations;
import com.bms.eai.module.api.config.xml.PmOperations;
import com.bms.eai.module.api.config.xml.PpbsOperations;
import com.bms.eai.module.api.config.xml.PropTypeOperations;
import com.bms.eai.module.api.config.xml.PropertyDetails;
import com.bms.eai.module.api.config.xml.PspOperations;
import com.bms.eai.module.api.config.xml.StateOperations;

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

	// ===============  Configure the Common Module Operations ============
	
	public Optional<PdServerDetails> getPdServerDetails() {
		return Optional.ofNullable(this.propertyDetails.getPdServerDetails());
	}
	
	public Optional<PmOperations> getPropertyMasterOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyMaster().getPmBusinessApiConfig().getPmOperations());
	}

	public Optional<PbdOperations> getPropertyBlockDetailsOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyBlockDetails().getPbdBusinessApiConfig().getPbdOperations());
	}

	public Optional<PpbsOperations> getPropertyParkingBayOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyParkingBaySetup().getPpbsBusinessApiConfig().getPpbsOperations());
	}

	public Optional<PfsOperations> getPropertyFacilitiesOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyFacilitiesSetup().getPfsBusinessApiConfig().getPfsOperations());
	}

	public Optional<PfarOperations> getPropertyFixedAnnualRecurringOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyFixedAnnualRecurrings().getPfarBusinessApiConfig().getPfarOperations());
	}

	public  Optional<PfyOperations> getPropertyFinancialYearOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyFinancialYear().getPfyBusinessApiConfig().getPfyOperations());
	}

	public  Optional<PspOperations> getPropertyServiceProviderOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyServiceProvider().getPspBusinessApiConfig().getPspOperations());
	}
	
	public  Optional<PaOperations> getPropertyAssetOperations() {
		return Optional.ofNullable(this.propertyDetails.getPropertyAsset().getPaBusinessApiConfig().getPaOperations());
	}
	
	// ===============  Configure the Common Module Operations ============
	
	public Optional<CmServerDetails> getCommonModuleServerDetails() {
		return Optional.ofNullable(this.commonModule.getCmServerDetails());
	}
	
	public  Optional<StateOperations> getStateOperations() {
		return Optional.ofNullable(this.commonModule.getState().getStateBusinessApiConfig().getStateOperations());
	}
	
	public  Optional<CountryOperations> getCountryOperations() {
		return Optional.ofNullable(this.commonModule.getCountry().getCountryBusinessApiConfig().getCountryOperations());
	}
	
	public  Optional<PropTypeOperations> getPropTypeOperations() {
		return Optional.ofNullable(this.commonModule.getPropType().getPropTypeBusinessApiConfig().getPropTypeOperations());
	}

}
