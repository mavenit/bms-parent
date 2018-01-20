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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.constants.UserStatus;
import com.bms.eai.property.model.entity.PropFacilities;
import com.bms.eai.property.model.service.IPropDetailsMasterService;
import com.bms.eai.property.model.service.IPropFacilitiesService;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
@RestController
@RequestMapping("${bms.portal.prop.facilities.path}")
public class FacilitiesController extends AbstractModelController<PropFacilities,String> {

	@Autowired
	private IPropFacilitiesService facilitiesService;
	
	@Autowired
	private IPropDetailsMasterService propDetailsMasterService;

	@Override
	protected ICrudService<PropFacilities, String> primaryService() {
		return facilitiesService;
	}

	@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processCreate(@Validated @Valid @RequestBody PropFacilities reqJson,@RequestParam("file") MultipartFile multipartFile) throws ServiceException {
		
		logger.info("[PropDetailsMaster :{}]",new Object[]{reqJson.getPropDetailsMasterId()});
		
		if(reqJson==null || (reqJson!=null && !StringUtils.hasText(reqJson.getPropDetailsMasterId()))) {
			return super.generateResponseMsg("Require PropDetailsMaster", UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		
		reqJson.setId(super.generateRequestId());
		reqJson.setPropDetailsMaster(this.propDetailsMasterService.getById(reqJson.getPropDetailsMasterId()));
		
		logger.info("[Before Persist PropFacilities Data :{}]",new Object[]{reqJson.toString()});
		PropFacilities savedEntity = this.primaryService().add(reqJson);
		logger.info("[PropFacilities SavedEntity :"+savedEntity+"]");
		if(savedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(savedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@PutMapping(value="${bms.resource.path.update}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processUpdate(@Validated @Valid @RequestBody PropFacilities reqJson,@PathVariable String id) throws ServiceException {
		logger.info("[Before Update PropFacilities Data :{}]",new Object[]{reqJson.toString()});
		PropFacilities updatedEntity = this.primaryService().update(id, reqJson);
		if(updatedEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(updatedEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@DeleteMapping(value="${bms.resource.path.delete}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processDelete(@PathVariable String id) throws ServiceException {
		this.primaryService().delete(id);
		logger.info("[PropFacilities Delete-ID :{}]",new Object[]{id});
		return super.generateResponseMsg(id, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadAll() throws ServiceException {
		List<PropFacilities> list = this.primaryService().getAll();
		if(list==null || CollectionUtils.isEmpty(list)) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		logger.info("[PropFacilities list Size :{}]",new Object[]{list.size()});
		return super.generateResponseMsg(list, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByID(@PathVariable String id) throws ServiceException {
		logger.info("[PropFacilities LoadByID :{}]",new Object[]{id});
		PropFacilities loadEntity = this.primaryService().getById(id);
		if(loadEntity==null) {
			return super.generateResponseMsg(null, UserStatus.FAIL.name(), UserStatus.FAIL.statusCode());
		}
		return super.generateResponseMsg(loadEntity, UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}

	@GetMapping(value="${bms.resource.path.load.page}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		Page<PropFacilities> list = this.primaryService().getPaged(page);
		logger.info("[PropFacilities TotalPages Size :{}]",new Object[]{list.getTotalPages()});
		return super.generateResponseMsg(list.getContent(), UserStatus.SUCESS.name(), UserStatus.SUCESS.statusCode());
	}
	
}
