package com.bms.eai.portal.controllers.propdetails;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.ModelAndView;

import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.common.ModelInfo;
import com.bms.eai.module.core.AbstractSdkEntity;
import com.bms.eai.module.core.SimpleDataAccess;
import com.bms.eai.module.prop.PropBlockDetailsApiService;
import com.bms.eai.module.prop.PropSettingsApiService;
import com.bms.eai.portal.constants.CmnConstants;
import com.bms.eai.portal.constants.WebConstants;
import com.bms.eai.portal.core.AbstractController;

/**
 * @author kul_sudhakar
 *
 */
public abstract class AbstractPropController<T extends AbstractSdkEntity> extends AbstractController {

	protected Class<T> persistentClass;
	
	private Validator validator;
	
	@Autowired
	protected PropSettingsApiService settingsApiService;
	
	@Autowired
	protected PropBlockDetailsApiService blockDetailsApiService;
	
	protected abstract SimpleDataAccess<T> primaryService();
	
	protected abstract ModelInfo<T> create(ModelInfo<T> info);
	
	@SuppressWarnings("unchecked")
	public AbstractPropController() {
		super();
		this.persistentClass =(Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected void validate(ModelInfo<T> info, BindingResult bindingResult){
		Validator v = this.getValidator();
		if(v != null){
			v.validate(info, bindingResult);
		}
		else{
			logger.info("no custom validator configured");
		}
	}
	
	protected void checkReferenceComponents(T t){
		// no default checks
	}

	/**
	 * Process Entity fields.
	 * If a field was set we have ID only. If this flow needs to be shown to
	 * the user we need to load the field data.
	 * If a field was set and operation succeeds the field will be loaded
	 * anyway during save/update.
	 * @param info
	 * @param load whether to load entity now
	 */
	protected void checkComplexComponents(ModelInfo<T> info, boolean load){
		logger.debug("checking complex in {}, will load data: {}", info, load);
		// no default checks
	}
	
	protected ModelAndView processNew(String viewNameAdd,String modelName){
		ModelInfo<T> info = this.create(null);
		try {
			info.setModel(persistentClass.newInstance());
		} catch (Exception e) {
			logger.error("cannot instantiate model class ["	+ persistentClass + "], is it a JavaBean?", e);
		}
		ModelAndView mav = new ModelAndView(viewNameAdd);
		mav.addObject(modelName, info);
		return mav;
	}
	
	protected ModelAndView processSave(ModelInfo<T> info,BindingResult bindingResult, String submit,String viewNameAdd) throws ResourceAccessException, Exception{
		
		logger.info("save called for {} with submit [{}]",info, submit);
		
		ModelAndView mav = new ModelAndView();
		
		this.checkReferenceComponents(info.getModel());
		this.validate(info, bindingResult);
		
		if(bindingResult.hasErrors()){
			logger.info("validation errors in object {}", info);
			this.create(info);
			this.checkComplexComponents(info, true);
			mav.setViewName(viewNameAdd);
			return mav;
		}
		this.checkComplexComponents(info, false);
		T t = this.doOperations(info,submit,null);
		if(t==null) {
			mav.setViewName(this.buildRedirect(submit, null));
			mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_ERROR_CODE);
			mav.addObject(CmnConstants.MAV_ALERT_MSG, CmnConstants.MAV_ERROR_MSG);
			return mav; 
		}
		
		mav.setViewName(this.buildRedirect(submit, t.getId()));
		mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_SUCCESS);
		mav.addObject(CmnConstants.MAV_ALERT_MSG, super.generateMAVMsg(CmnConstants.MAV_SUCCESS_PERSIST_MSG, t.getId()));
		return mav; 
	}
	
	protected ModelAndView processUpdate(String id, ModelInfo<T> info,BindingResult bindingResult, String submit,String viewNameAdd) throws ResourceAccessException, Exception{
		logger.info("Update called for {} with submit[{} ID [{}]",info, submit,id);
		ModelAndView mav = new ModelAndView();
		
		T t = this.doOperations(info,submit,id);
		
		if(t==null) {
			mav.setViewName(this.buildRedirect(submit, null));
			mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_ERROR_CODE);
			mav.addObject(CmnConstants.MAV_ALERT_MSG, CmnConstants.MAV_ERROR_MSG);
			return mav; 
		}
		
		mav.setViewName(this.buildRedirect(submit, t.getId()));
		mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_SUCCESS);
		mav.addObject(CmnConstants.MAV_ALERT_MSG, super.generateMAVMsg(CmnConstants.MAV_SUCCESS_UPDATE_MSG, t.getId()));
		return mav; 
	}
	
	protected ModelAndView processView(String id, ModelInfo<T> info,String submit,String viewName) throws ResourceAccessException, Exception{
		logger.info("View called for {} with submit[{} ID [{}]",info, submit,id);
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		T t = this.doOperations(info,submit,id);
		if(t==null) {
			mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_ERROR_CODE);
			mav.addObject(CmnConstants.MAV_ALERT_MSG, CmnConstants.MAV_ERROR_MSG);
			return mav; 
		}
		mav.addObject("model", t);
		info.setModel(t);
		mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_SUCCESS);
		mav.addObject(CmnConstants.MAV_ALERT_MSG, super.generateMAVMsg(CmnConstants.MAV_SUCCES_VIEW_MSG, t.getId()));
		return mav;
	}
	
	protected ModelAndView processDelete(String id, ModelInfo<T> info,String submit,String viewName) throws ResourceAccessException, Exception{
		
		logger.info("Delete called for {} with submit[{} ID [{}]",info, submit,id);
		ModelAndView mav = new ModelAndView();
		//String ret = "redirect:"  +WebConstants.PATH_PROP_SETTINGS+ "/pages/1";
		String ret = "redirect:"+viewName+ "/pages/1";
		mav.setViewName(ret);
		this.doOperations(info,submit,id);
		return mav;
	}
	
	protected T doOperations(ModelInfo<T> info,String submit,String id) throws ResourceAccessException, Exception{
		T t = info.getModel();
		logger.info("Before Saving/Updating/Delete/Display from {} ",persistentClass.getSimpleName());
		
		JsonResponseBean responseEntity = null ; 
		
		if(StringUtils.hasText(id) && StringUtils.pathEquals(submit, "save")) {
			responseEntity = this.primaryService().update(id, t);
			
		}else if(StringUtils.hasText(id) && StringUtils.pathEquals(submit, "view")) {
			responseEntity = this.primaryService().load(id);
			
		}else if(StringUtils.hasText(id) && StringUtils.pathEquals(submit, "delete")) {
			responseEntity = this.primaryService().delete(id);
			
		}else {
			responseEntity = this.primaryService().create(t);
		}
		
		if(responseEntity==null || (responseEntity!=null && responseEntity.getResponseBean()==null) ) {
			return null;
		}
		
		logger.info("[Response Details StatusCode :{} And StatusMsg : {}",new Object[]{responseEntity.getStatusCode(),responseEntity.getStatusMessage()});
		
		String jsonStr = super.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getResponseBean());
		logger.info("[responseEntity jsonStr :"+jsonStr+"]");
		t = super.objectMapper.readValue(jsonStr, persistentClass);
		
		t.setId(t.getId());
		logger.info("saved/updated {} from {}",persistentClass.getSimpleName(), info);
		return t;
	}
	
	protected String buildRedirect(String button, String id){
		String ret = null;
		if(StringUtils.hasText(button)){
			if("save".equalsIgnoreCase(button) && id != null){
				ret = "redirect:" + WebConstants.PATH_HOME;
			}else if("savenew".equalsIgnoreCase(button)){
				ret = "redirect:" +WebConstants.PATH_HOME;
				
			}else if("view".equalsIgnoreCase(button)){
				ret = "redirect:" +WebConstants.PATH_HOME;
			}
		}
		if(ret == null){
			ret = "redirect:" + WebConstants.PATH_HOME;
		}
		logger.info("from button [{}] and id [{}] redirect is [{}]",new Object[]{ button, id, ret });
		return ret;
	}
	
	/*protected String buildRedirect1(String button, String id){
		String ret = null;
		if(StringUtils.hasText(button)){
			if("save".equalsIgnoreCase(button) && id != null){
				ret = "redirect:./" + id;
			}
			else if("savenew".equalsIgnoreCase(button)){
				ret = "redirect:." + WebConstants.PATH_NEW;
			}
		}
		if(ret == null){
			ret = "redirect:." + WebConstants.PATH_SEARCH;
		}
		logger.info("from button [{}] and id [{}] redirect is [{}]",new Object[]{ button, id, ret });
		return ret;
	}*/
	
	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
}
