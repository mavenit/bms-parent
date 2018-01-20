package com.bms.eai.property.controllers;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bms.eai.cmn.error.ServiceException;
import com.bms.eai.common.model.core.AbstractEntity;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author kul_sudhakar
 *
 */
public interface IModelController<T extends AbstractEntity<T,ID>, ID extends Serializable> {

	//@PostMapping(value="${bms.resource.path.create}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processCreate(T reqJson,@RequestParam("file") MultipartFile multipartFile) throws ServiceException;
	
	//@PutMapping(value="${bms.resource.path.update}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processUpdate( T reqJson,@PathVariable String id) throws ServiceException;
	
	//@DeleteMapping(value="${bms.resource.path.delete}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processDelete(ID id)	throws ServiceException ;
	
	//@GetMapping(value="${bms.resource.path.loadall}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processLoadAll()throws ServiceException ;
	
	//@GetMapping(value="${bms.resource.path.load.id}/{id}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> processLoadByID(ID id)throws ServiceException ;
	
	public ResponseEntity<JsonNode> processLoadByPage(Pageable page)throws ServiceException ;
	
}
