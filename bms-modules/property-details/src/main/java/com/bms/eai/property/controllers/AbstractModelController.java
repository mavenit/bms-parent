package com.bms.eai.property.controllers;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import com.bms.eai.beans.JsonResponseBean;
import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.AbstractEntity;
import com.bms.eai.common.model.core.ICrudService;
import com.bms.eai.common.model.entity.PropState;
import com.bms.eai.common.model.entity.PropType;
import com.bms.eai.constants.CmnConstants;
import com.bms.eai.lib.DateUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractModelController<T extends AbstractEntity<T,ID>, ID extends Serializable> extends AbstractBeanQualifier implements IModelController<T,ID> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Class<T> persistentClass;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	protected abstract ICrudService<T,ID> primaryService();
	
	@SuppressWarnings("unchecked")
	public AbstractModelController() {
		super();
		this.persistentClass =(Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected String generateRequestId(){
		return DateUtils.format(new Date(), CmnConstants.GEN_REQID_FORMAT);
	}
	
	protected JsonResponseBean generateResponseBean(String statusCode,String statusMessage,Object entityObj) {
		return new JsonResponseBean(statusCode,statusMessage,entityObj);
	}
	
	protected ResponseEntity<JsonNode> generateResponseMsg(Object entityObj,final String statusMessage,final String statusCode) {
		if(!StringUtils.hasText(statusCode)) {
			return new ResponseEntity<JsonNode>(JsonNodeFactory.instance.nullNode(), HttpStatus.NO_CONTENT);
		}
		JsonResponseBean jrb = this.generateResponseBean(statusCode, statusMessage, entityObj);
		
		return new ResponseEntity<JsonNode>(objectMapper.convertValue(jrb, JsonNode.class), HttpStatus.OK);
		//return new ResponseEntity<JsonNode>(JsonApiUtil.convertValue(jrb).get(), HttpStatus.OK);
	}

	protected PropState loadByStateId(final String id) throws ServiceException {
		return this.stateService.getById(id);
	}
	
	protected PropType loadByPropTypeId(final String id) throws ServiceException {
		return this.propTypeService.getById(id);
	}

	/*@Override
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page) throws ServiceException {
		this.primaryService().getPaged(page);
		return null;
	}*/
	
}
