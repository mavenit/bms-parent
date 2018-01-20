package com.bms.eai.common.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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
import com.bms.eai.common.model.service.IPropCountryService;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.lib.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
@RestController
@RequestMapping("${bms.portal.cmn.country.path}")
public class CountryController extends AbstractModelController<PropCountry,String>{

	@Autowired
	private IPropCountryService countryService;
	
	@Override
	protected ICrudService<PropCountry, String> primaryService() {
		return countryService;
	}
	
	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropCountry reqJson) throws ServiceException {
		
		logger.info("[Before Update PropCountry Data :{}]",new Object[]{ObjectUtils.toString(reqJson, true)});
		reqJson.setId(super.generateRequestId());
		PropCountry savedEntity = this.primaryService().add(reqJson);
		logger.info("[SavedEntity :"+savedEntity+"]");
		if(savedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(savedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@PutMapping(value="${bms.resource.path.update}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropCountry reqJson,@PathVariable String id) throws ServiceException {
		logger.info("[Before Update PropCountry Data :{}] - [ID :{}]",new Object[]{reqJson.toString(),id});
		PropCountry updatedEntity = this.primaryService().update(id, reqJson);
		if(updatedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(updatedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@DeleteMapping(value="${bms.resource.path.delete}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processDelete(@PathVariable String id) throws ServiceException {
		PropCountry loadEntity = this.primaryService().getById(id);
		this.primaryService().delete(id);
		logger.info("[Delete-ID :{}]",new Object[]{id});
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadAll() throws ServiceException {
		List<PropCountry> list = this.primaryService().getAll();
		if(list==null || CollectionUtils.isEmpty(list)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[countryList Size :{}]",new Object[]{list.size()});
		return super.generateResponseMsg(list, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.name}/{name}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processLoadByName(@PathVariable String name) throws ServiceException {
		logger.info("[LoadByID :{}]",new Object[]{name});
		PropCountry loadEntity = this.countryService.findEqualByColumn("name", name);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	
	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		logger.info("[LoadByID :{}]",new Object[]{id});
		PropCountry loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		Page<PropCountry> list = this.primaryService().getPaged(page);
		logger.info("[PropCountry TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

}
