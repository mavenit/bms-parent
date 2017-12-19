package com.bms.eai.property.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropCountry;
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.common.model.service.IPropCountryService;
import com.bms.eai.common.model.service.IPropStateService;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.lib.DateUtils;
import com.bms.eai.lib.ObjectUtils;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropAttachments;
import com.bms.eai.property.model.entity.PropContactDetails;
import com.bms.eai.property.model.entity.PropServiceProvider;
import com.bms.eai.property.model.service.IPropDurationService;
import com.bms.eai.property.model.service.IPropServiceProviderCategoryService;
import com.bms.eai.property.model.service.IPropServiceProviderService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
@RestController
@RequestMapping("${bms.portal.prop.serviceprovider.path}")
public class ServiceProviderController extends AbstractModelController<PropServiceProvider,String> {

	@Autowired
	private IPropServiceProviderService serviceProviderService;
	
	@Autowired
	private IPropCountryService countryService;

	@Autowired
	private IPropStateService stateService;
	
	@Autowired
	private IPropDurationService durationService;
	
	@Autowired
	private IPropServiceProviderCategoryService providerCategoryService;
	
	@Override
	protected ICrudService<PropServiceProvider, String> primaryService() {
		return serviceProviderService;
	}

	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processAttachmentCreate(@Validated @Valid @RequestBody PropServiceProvider reqJson, @RequestParam(value = "attachment", required = false) MultipartFile attachment) throws ServiceException {
		
		try {
			PropAttachments pa = null;
			if(attachment!=null) {
				logger.info("[FileName :{}]-[ContentType :{}]-[FileBytes :{}]",new Object[]{attachment.getOriginalFilename(),
																							attachment.getContentType(),
																							attachment.getBytes().length});
				pa = new PropAttachments();
				pa.setId(super.generateRequestId());
				pa.setContentType(attachment.getContentType());
				pa.setFileName(attachment.getOriginalFilename());
				pa.setFileBytes(attachment.getBytes());
			}
			return this.processCreate(reqJson, pa);
		} catch (IOException e) {
			logger.error(e.getMessage(),e.getCause());
			return super.generateResponseMsg(e.getMessage(), UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
	}
	
	private ResponseEntity<JsonNode> processCreate(PropServiceProvider reqJson,PropAttachments attachment) throws ServiceException {
		
		logger.info("[PropServiceProvider Create :{}]",new Object[]{ObjectUtils.toString(reqJson, true)});
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getServiceProviderCategoryId()))) {
			return super.generateResponseMsg(null, "Require ServiceProviderCategory ID", UserStatus.FAIL.statusCode());
		}
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getDurationId()))) {
			return super.generateResponseMsg(null, "Require Duration ID", UserStatus.FAIL.statusCode());
		}
		
		try {
			// Data for PropAnnualReminders
			PropAnnualReminders par = reqJson.getPropAnnualReminders();
			par.setParTime(DateUtils.ofTime(reqJson.getPropAnnualReminders().getTime()));
			par.setId(super.generateRequestId());
			
			reqJson.setPropDuration(this.durationService.getById(reqJson.getDurationId()));
			reqJson.setPropServiceProviderCategory(this.providerCategoryService.getById(reqJson.getServiceProviderCategoryId()));
			
			// Data for PropContactDetails
			PropContactDetails pcd = reqJson.getPropContactDetails();
			pcd.setId(super.generateRequestId());
			PropState state = stateService.getById(reqJson.getPropContactDetails().getStateId());
			PropCountry country =  countryService.getById(reqJson.getPropContactDetails().getCountryId());
			pcd.setPropState(state);
			pcd.setPropCountry(country);
			
			reqJson.setId(super.generateRequestId());
			
			PropServiceProvider savedEntity = this.serviceProviderService.add(reqJson,par,attachment,pcd); 
			logger.info("[PropServiceProvider SavedEntity :"+savedEntity+"]");
			
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
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropServiceProvider reqJson,@PathVariable String id,@RequestParam(value = "attachment", required = false) MultipartFile attachment) throws ServiceException {
		
		try {
			PropAttachments pa = null;
			if(attachment!=null) {
				logger.info("[FileName :{}]-[ContentType :{}]-[FileBytes :{}]",new Object[]{attachment.getOriginalFilename(),
																							attachment.getContentType(),
																							attachment.getBytes().length});
				pa = new PropAttachments();
				pa.setId(super.generateRequestId());
				pa.setContentType(attachment.getContentType());
				pa.setFileName(attachment.getOriginalFilename());
				pa.setFileBytes(attachment.getBytes());
			}
			return this.processUpdate(reqJson,id,pa);
			
		} catch (IOException e) {
			logger.error(e.getMessage(),e.getCause());
			return super.generateResponseMsg(e.getMessage(), UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		
	}
	
	public ResponseEntity<JsonNode> processUpdate(PropServiceProvider reqJson,String id,PropAttachments attachment) throws ServiceException {
		
		logger.info("[Before Update PropServiceProvider Data :{}]",new Object[]{reqJson.toString()});
		
		try {
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getAnnualRemindersId()))) {
				return super.generateResponseMsg(null, "Require AnnualReminders ID", UserStatus.FAIL.statusCode());
			}
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getContactDetailsId()))) {
				return super.generateResponseMsg(null, "Require ContactDetails ID", UserStatus.FAIL.statusCode());
			}
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getDurationId()))) {
				return super.generateResponseMsg(null, "Require Duration ID", UserStatus.FAIL.statusCode());
			}
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getServiceProviderCategoryId()))) {
				return super.generateResponseMsg(null, "Require ServiceProviderCategory ID", UserStatus.FAIL.statusCode());
			}
			
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
			
			reqJson.setPropDuration(this.durationService.getById(reqJson.getDurationId()));
			reqJson.setPropServiceProviderCategory(this.providerCategoryService.getById(reqJson.getServiceProviderCategoryId()));
			
			PropServiceProvider updatedEntity = this.serviceProviderService.update(id, reqJson, par,attachment,pcd); 
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
		logger.info("[PropServiceProvider Delete-ID :{}]",new Object[]{id});
		return super.generateResponseMsg(id, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadAll() throws ServiceException {
		List<PropServiceProvider> list = this.primaryService().getAll();
		if(list==null || CollectionUtils.isEmpty(list)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[PropServiceProvider list Size :{}]",new Object[]{list.size()});
		return super.generateResponseMsg(list, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		logger.info("[PropServiceProvider LoadByID :{}]",new Object[]{id});
		PropServiceProvider loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		Page<PropServiceProvider> list = this.primaryService().getPaged(page);
		logger.info("[PropServiceProvider TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropServiceProvider reqJson) throws ServiceException {
		return null;
	}
	@Override
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropServiceProvider reqJson,@PathVariable String id) throws ServiceException {
		return null;
	}

}
