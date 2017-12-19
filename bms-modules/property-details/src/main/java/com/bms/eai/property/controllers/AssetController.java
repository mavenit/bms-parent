package com.bms.eai.property.controllers;

import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropCountry;
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.lib.DateUtils;
import com.bms.eai.lib.ObjectUtils;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropAssets;
import com.bms.eai.property.model.entity.PropContactDetails;
import com.bms.eai.property.model.service.IPropAssetsCategoryService;
import com.bms.eai.property.model.service.IPropAssetsService;
import com.bms.eai.property.model.service.IPropServiceProviderCategoryService;
import com.bms.eai.property.model.service.IPropServiceProviderService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
@RestController
@RequestMapping("${bms.portal.prop.asset.path}")
public class AssetController extends AbstractModelController<PropAssets,String> {

	@Autowired
	private IPropAssetsService assetsService;
	
	@Autowired
	private IPropAssetsCategoryService assetsCategoryService;
	
	@Autowired
	private IPropServiceProviderService propServiceProvider;
	
	@Autowired
	private IPropServiceProviderCategoryService providerCategoryService;
	

	@Override
	protected ICrudService<PropAssets, String> primaryService() {
		return assetsService;
	}

	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropAssets reqJson) throws ServiceException {
		
			logger.info("[Before Persist PropAssets Data :{}]",new Object[]{ObjectUtils.toString(reqJson, true)});
		
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getAssetsCategoryId()))) {
				return super.generateResponseMsg(null, "Require AssetsCategory ID", UserStatus.FAIL.statusCode());
			}
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getServiceProviderId()))) {
				return super.generateResponseMsg(null, "Require ServiceProvider ID", UserStatus.FAIL.statusCode());
			}
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getServiceProviderCategoryId()))) {
				return super.generateResponseMsg(null, "Require ServiceProviderCategory ID", UserStatus.FAIL.statusCode());
			}
		
		try {
			
			reqJson.setPropAssetsCategory(this.assetsCategoryService.getById(reqJson.getAssetsCategoryId()));
			reqJson.setPropServiceProvider(this.propServiceProvider.getById(reqJson.getServiceProviderId()));
			reqJson.setPropServiceProviderCategory(this.providerCategoryService.getById(reqJson.getServiceProviderCategoryId()));
			
			// Data for PropAnnualReminders
			PropAnnualReminders par = reqJson.getPropAnnualReminders();
			par.setParTime(DateUtils.ofTime(reqJson.getPropAnnualReminders().getTime()));
			par.setId(super.generateRequestId());
						
			// Data for PropContactDetails
			PropContactDetails pcd = reqJson.getPropContactDetails();
			pcd.setId(super.generateRequestId());
			PropState state = stateService.getById(reqJson.getPropContactDetails().getStateId());
			PropCountry country =  countryService.getById(reqJson.getPropContactDetails().getCountryId());
			pcd.setPropState(state);
			pcd.setPropCountry(country);
			
			reqJson.setId(super.generateRequestId());
			PropAssets savedEntity = this.assetsService.add(reqJson, pcd, par); 
			logger.info("[PropAssets SavedEntity :"+savedEntity+"]");
			if(savedEntity==null) {
				return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
			}
			return super.generateResponseMsg(savedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
			
		} catch (ParseException e) {
			logger.error(e.getMessage(),e.getCause());
			return super.generateResponseMsg(e.getMessage(), UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		 
	}

	@PutMapping(value="${bms.resource.path.update}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropAssets reqJson,@PathVariable String id) throws ServiceException {
		logger.info("[Before Update PropAssets Data :{}]",new Object[]{ObjectUtils.toString(reqJson, true)});
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getAssetsCategoryId()))) {
			return super.generateResponseMsg(null, "Require AssetsCategory ID", UserStatus.FAIL.statusCode());
		}
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getServiceProviderId()))) {
			return super.generateResponseMsg(null, "Require ServiceProvider ID", UserStatus.FAIL.statusCode());
		}
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getServiceProviderCategoryId()))) {
			return super.generateResponseMsg(null, "Require ServiceProviderCategory ID", UserStatus.FAIL.statusCode());
		}
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getAnnualRemindersId()))) {
			return super.generateResponseMsg(null, "Require AnnualReminders ID", UserStatus.FAIL.statusCode());
		}
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getContactDetailsId()))) {
			return super.generateResponseMsg(null, "Require ContactDetails ID", UserStatus.FAIL.statusCode());
		}
		
		try {
			
			reqJson.setPropAssetsCategory(this.assetsCategoryService.getById(reqJson.getAssetsCategoryId()));
			reqJson.setPropServiceProvider(this.propServiceProvider.getById(reqJson.getServiceProviderId()));
			reqJson.setPropServiceProviderCategory(this.providerCategoryService.getById(reqJson.getServiceProviderCategoryId()));
			
			// Data for PropAnnualReminders
			PropAnnualReminders par = reqJson.getPropAnnualReminders();
			if(StringUtils.hasText(reqJson.getPropAnnualReminders().getTime())) {
				par.setParTime(DateUtils.ofTime(reqJson.getPropAnnualReminders().getTime()));
			}
			
			// Data for PropContactDetails
			PropContactDetails pcd = reqJson.getPropContactDetails();
			PropState state = stateService.getById(reqJson.getPropContactDetails().getStateId());
			PropCountry country =  countryService.getById(reqJson.getPropContactDetails().getCountryId());
			pcd.setPropState(state);
			pcd.setPropCountry(country);
			
			PropAssets updatedEntity = this.assetsService.update(id,reqJson, pcd, par); 
			
			if(updatedEntity==null) {
				return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
			}
			return super.generateResponseMsg(updatedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
			
		} catch (ParseException e) {
			logger.error(e.getMessage(),e.getCause());
			return super.generateResponseMsg(e.getMessage(), UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
	}

	@DeleteMapping(value="${bms.resource.path.delete}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processDelete(@PathVariable String id) throws ServiceException {
		this.primaryService().delete(id);
		logger.info("[PropAssets Delete-ID :{}]",new Object[]{id});
		return super.generateResponseMsg(id, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadAll() throws ServiceException {
		List<PropAssets> list = this.primaryService().getAll();
		if(list==null || CollectionUtils.isEmpty(list)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[PropAssets list Size :{}]",new Object[]{list.size()});
		return super.generateResponseMsg(list, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		logger.info("[PropAssets LoadByID :{}]",new Object[]{id});
		PropAssets loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		Page<PropAssets> list = this.primaryService().getPaged(page);
		logger.info("[PropAssets TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	 
}
