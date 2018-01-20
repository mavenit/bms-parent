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
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.common.model.service.IPropStateService;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.lib.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
@RestController
@RequestMapping("${bms.portal.cmn.state.path}")
public class StateController extends AbstractModelController<PropState,String> {

	@Autowired
	private IPropStateService stateService;
	
	@Override
	protected ICrudService<PropState, String> primaryService() {
		return stateService;
	}
	
	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropState reqJson) throws ServiceException {
		logger.info("[Before Update PropState Data :{}]",new Object[]{ObjectUtils.toString(reqJson, true)});
		reqJson.setId(super.generateRequestId());
		PropState savedEntity = this.primaryService().add(reqJson);
		logger.info("[SavedEntity :"+savedEntity+"]");
		if(savedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(savedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@PutMapping(value="${bms.resource.path.update}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropState reqJson,@PathVariable String id) throws ServiceException {
		logger.info("[Before Update PropState Data :{}] - [ID :{}]",new Object[]{reqJson.toString(),id});
		PropState updatedEntity = this.primaryService().update(id, reqJson);
		if(updatedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(updatedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@DeleteMapping(value="${bms.resource.path.delete}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processDelete(@PathVariable String id) throws ServiceException {
		this.primaryService().delete(id);
		logger.info("[Delete-ID :{}]",new Object[]{id});
		return super.generateResponseMsg(id, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadAll() throws ServiceException {
		List<PropState> pdmList = this.primaryService().getAll();
		if(pdmList==null || CollectionUtils.isEmpty(pdmList)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[PropStateList Size :{}]",new Object[]{pdmList.size()});
		return super.generateResponseMsg(pdmList, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.name}/{name}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processLoadByName(@PathVariable String name) throws ServiceException {
		logger.info("[LoadByID :{}]",new Object[]{name});
		PropState loadEntity = this.stateService.findEqualByColumn("name", name);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	
	@GetMapping(value="${bms.resource.path.load}/country/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processLoadByCountry(@PathVariable String id) throws ServiceException {
		logger.info("[LoadByCountry :{}]",new Object[]{id});
		List<PropState> list = this.stateService.findStatesByCountry(id);
		if(list==null || CollectionUtils.isEmpty(list)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(list, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	
	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		logger.info("[LoadByID :{}]",new Object[]{id});
		PropState loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		Page<PropState> list = this.primaryService().getPaged(page);
		logger.info("[PropState TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

}
