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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.lib.DateUtils;
import com.bms.eai.lib.ObjectUtils;
import com.bms.eai.property.model.entity.PropAnnualReminders;
import com.bms.eai.property.model.entity.PropDetailsMaster;
import com.bms.eai.property.model.entity.PropFixedAnualRecurring;
import com.bms.eai.property.model.service.IPropAnnualRemindersService;
import com.bms.eai.property.model.service.IPropDetailsMasterService;
import com.bms.eai.property.model.service.IPropFixedAnualRecurringService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
@RestController
@RequestMapping("${bms.portal.prop.fixedannualrecurring.path}")
public class FixedAnnualRecurringController extends AbstractModelController<PropFixedAnualRecurring,String> {

	@Autowired
	private IPropFixedAnualRecurringService fixedAnualRecurringService;
	
	@Autowired
	private IPropDetailsMasterService propDetailsMasterService;
	
	@Autowired
	private IPropAnnualRemindersService propAnnualRemindersService;
	
	@Override
	protected ICrudService<PropFixedAnualRecurring, String> primaryService() {
		return fixedAnualRecurringService;
	}

	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropFixedAnualRecurring reqJson,@RequestParam("file") MultipartFile multipartFile) throws ServiceException {
		
		logger.info("[PropDetailsMaster :{}]",new Object[]{reqJson.getPropDetailsMasterId()});
		
		logger.info("[Days :{}]-[Time :{}]",new Object[]{reqJson.getPropAnnualReminders().getDays(),reqJson.getPropAnnualReminders().getTime()});
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getPropDetailsMasterId()))) {
			return super.generateResponseMsg(null, "Require PropDetailsMaster", UserStatus.FAIL.statusCode());
		}
		
		try {
			
			PropAnnualReminders par = reqJson.getPropAnnualReminders();
			if(StringUtils.hasText(reqJson.getPropAnnualReminders().getTime())) {
				par.setParTime(DateUtils.ofTime(reqJson.getPropAnnualReminders().getTime()));
			}
			par.setId(super.generateRequestId());
			PropAnnualReminders savedPar = this.propAnnualRemindersService.add(par);
			logger.info("[Saved PropAnnualReminders-ID  :{}]",new Object[]{savedPar.getId()});
			
			reqJson.setId(super.generateRequestId());
			reqJson.setPropAnnualReminders(savedPar);
			reqJson.setPropDetailsMaster(this.propDetailsMasterService.getById(reqJson.getPropDetailsMasterId()));
			
			logger.info("[Before Persist PropFixedAnualRecurring Data :{}]",new Object[]{reqJson.toString()});
			PropFixedAnualRecurring savedEntity = this.primaryService().add(reqJson);
			logger.info("[PropFixedAnualRecurring SavedEntity :"+savedEntity+"]");
			
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
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropFixedAnualRecurring reqJson,@PathVariable String id) throws ServiceException {
		
		//logger.info("[propDetailsMasterId :{}]-[annualRemindersId :{}]",new Object[]{reqJson.getPropDetailsMasterId(),reqJson.getAnnualRemindersId()});
		logger.info("[Before Update PropFixedAnualRecurring Data :{}]",new Object[]{ObjectUtils.toString(reqJson, true)});
		
		try {
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getPropDetailsMasterId()))) {
				return super.generateResponseMsg(null, "Require PropDetailsMaster", UserStatus.FAIL.statusCode());
			}
			
			if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getAnnualRemindersId()))) {
				return super.generateResponseMsg(null, "Require AnnualReminders ID", UserStatus.FAIL.statusCode());
			}
			
			if(StringUtils.hasText(reqJson.getPropAnnualReminders().getTime())) {
				reqJson.getPropAnnualReminders().setParTime(DateUtils.ofTime(reqJson.getPropAnnualReminders().getTime()));
			}
			
			PropAnnualReminders par = this.propAnnualRemindersService.update(reqJson.getAnnualRemindersId(), reqJson.getPropAnnualReminders());
			reqJson.setPropAnnualReminders(par);
			
			PropDetailsMaster pdm = this.propDetailsMasterService.getById(reqJson.getPropDetailsMasterId());
			reqJson.setPropDetailsMaster(pdm);
			PropFixedAnualRecurring updatedEntity = this.primaryService().update(id, reqJson);
			logger.info("[PropFixedAnualRecurring updatedEntity :"+updatedEntity+"]");
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
		logger.info("[PropFixedAnualRecurring Delete-ID :{}]",new Object[]{id});
		return super.generateResponseMsg(id, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadAll() throws ServiceException {
		List<PropFixedAnualRecurring> list = this.primaryService().getAll();
		if(list==null || CollectionUtils.isEmpty(list)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[PropFixedAnualRecurring list Size :{}]",new Object[]{list.size()});
		return super.generateResponseMsg(list, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	
	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable p) throws ServiceException {
		Page<PropFixedAnualRecurring> list = this.primaryService().getPaged(p);
		logger.info("[PropFixedAnualRecurring TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		logger.info("[PropFixedAnualRecurring LoadByID :{}]",new Object[]{id});
		PropFixedAnualRecurring loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

}
