package com.bms.eai.property.controllers;

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
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.common.model.entity.PropType;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.property.model.entity.PropDetailsMaster;
import com.bms.eai.property.model.service.IPropDetailsMasterService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 */
@RestController
@RequestMapping("${bms.portal.prop.setting.path}")
public class SettingController extends AbstractModelController<PropDetailsMaster,String> {

	@Autowired
	private IPropDetailsMasterService propDetailsMasterService;

	@Override
	protected ICrudService<PropDetailsMaster, String> primaryService() {
		return propDetailsMasterService;
	}

	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropDetailsMaster reqJson) throws ServiceException {
		
		logger.info("[StateId :{}]-[PropTypeId :{}]",new Object[]{reqJson.getStateId(),reqJson.getPropTypeId()});
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getStateId()))) {
			return super.generateResponseMsg("Require State", UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getPropTypeId()))) {
			return super.generateResponseMsg("Require Property Type", UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		
		PropState ps = super.loadByStateId(reqJson.getStateId());
		PropType pt = super.loadByPropTypeId(reqJson.getPropTypeId());
		logger.info("[State :{}]-[PropType :{}]",new Object[]{ps,pt});
		
		reqJson.setPropState(ps);
		reqJson.setPropType(pt);
		reqJson.setId(super.generateRequestId());

		logger.info("[Before Persist PropDetailsMaster Data :{}]",new Object[]{reqJson.toString()});
		PropDetailsMaster savedEntity = this.primaryService().add(reqJson);
		logger.info("[SavedEntity :"+savedEntity+"]");
		if(savedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(savedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@PutMapping(value="${bms.resource.path.update}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropDetailsMaster reqJson,@PathVariable String id) throws ServiceException {
		
		logger.info("[Before Update PropDetailsMaster Data :{}]",new Object[]{reqJson.toString()});
		PropDetailsMaster updatedEntity = this.primaryService().update(id, reqJson);
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
		
		List<PropDetailsMaster> pdmList = this.primaryService().getAll();
		if(pdmList==null || CollectionUtils.isEmpty(pdmList)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[pdmList Size :{}]",new Object[]{pdmList.size()});
		return super.generateResponseMsg(pdmList, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		
		logger.info("[LoadByID :{}]",new Object[]{id});
		PropDetailsMaster loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		Page<PropDetailsMaster> list = this.primaryService().getPaged(page);
		logger.info("[PropDetailsMaster TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	
}
