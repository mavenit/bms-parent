package com.bms.eai.portal.controllers.propdetails;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bms.eai.module.beans.FileUploadDetails;
import com.bms.eai.module.beans.JsonResponseBean;
import com.bms.eai.module.beans.RestResponsePage;
import com.bms.eai.module.common.ModelInfo;
import com.bms.eai.module.core.SimpleDataAccess;
import com.bms.eai.module.prop.beans.PropDetailsMaster;
import com.bms.eai.portal.constants.WebConstants;
import com.bms.eai.portal.model.PropDetailsMasterInfo;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author kul_sudhakar
 *
 */
@Controller
@RequestMapping(value = WebConstants.PATH_PROP_SETTINGS)
public class SettingController extends AbstractPropController<PropDetailsMaster> {

	private static final String VIEW_NAME_ADD = "prop_settings_edit";
	private static final String MODELNAME = "detailsMasterInfo";
	private static final String VIEW_NAME_DISPLAY = "view_prop_settings";
	private static final String READONLY_NAME_ADD = "read_prop_settings";
	
	@Override
	protected SimpleDataAccess<PropDetailsMaster> primaryService() {
		return super.settingsApiService;
	}

	@Override
	protected ModelInfo<PropDetailsMaster> create(ModelInfo<PropDetailsMaster> info) {
		PropDetailsMasterInfo hi = (PropDetailsMasterInfo)  info;
		if(hi == null){
			hi = new PropDetailsMasterInfo();
		}
		return hi;
	}

	@RequestMapping(value = WebConstants.PATH_NEW, method = RequestMethod.GET)
	public ModelAndView processNew(){
		logger.info("Comes to processNew Operation GET");
		return super.processNew(VIEW_NAME_ADD,MODELNAME);
	}

	@RequestMapping(value = WebConstants.PATH_NEW, method = RequestMethod.POST,headers=("content-type="+MediaType.MULTIPART_FORM_DATA_VALUE),consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ModelAndView processSave(@Valid @ModelAttribute(value = MODELNAME) PropDetailsMasterInfo detailsMasterInfo,
									BindingResult bindingResult,@RequestParam(value="file") MultipartFile file,
									//@RequestParam(value = "_csrf", required = false) String csrf,
									@RequestParam(value = WebConstants.SUBMIT_BUTTON, required = false) String submit){
		
		logger.info("Comes to processNew Operation POST getModel :"+detailsMasterInfo.getModel());
		logger.info("Comes to processNew Name :"+detailsMasterInfo.getModel().getEmail()+"]");
		logger.info("[DetailsMasterInfo Values :"+ObjectUtils.nullSafeToString(detailsMasterInfo.getModel())+"]");
		try {
			
			if(file!=null && file.getBytes()!=null && file.getBytes().length!=0) {
				logger.info("\n <======================================> \n " +
							"UPLOAD FILE DETAILS \n [OrginalFilename :{}] \n[File Bytes Length :{}] \n [File Content Type :{}] \n [Filename :{}]" +
							"\n <======================================>" +
							" ",new Object[]{file.getOriginalFilename(),file.getBytes().length,file.getContentType(),file.getName()});
				
				logger.info("[FileUploadDetails :"+detailsMasterInfo.getModel().getFileUploadDetails()+"]");
				
				FileUploadDetails fud = new FileUploadDetails();
				fud.setBytes(file.getBytes());
				fud.setContentType(file.getContentType());
				fud.setOriginalFilename(file.getOriginalFilename());
				fud.setSize(file.getBytes().length);
				fud.setFileName(file.getName());
				fud.setEmpty(false);
				detailsMasterInfo.getModel().setFileUploadDetails(fud);
			}

			return super.processSave(detailsMasterInfo, bindingResult, submit,VIEW_NAME_ADD);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return super.processNew(VIEW_NAME_ADD,MODELNAME);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView processLoadById(@Valid @ModelAttribute(value = MODELNAME) PropDetailsMasterInfo detailsMasterInfo,@PathVariable("id") String id){
		
		logger.info("Comes to processUpdate Operation GET");
		ModelAndView mav = new ModelAndView(VIEW_NAME_ADD);
		mav.addObject(MODELNAME, detailsMasterInfo);
		
		try {
			
			JsonResponseBean responseEntity = this.primaryService().load(id);
			
			if(responseEntity==null || (responseEntity!=null && responseEntity.getResponseBean()==null) ) {
				return mav;
			}
			
			String jsonStr = super.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getResponseBean());
			PropDetailsMaster pdm = super.objectMapper.readValue(jsonStr, PropDetailsMaster.class);
			pdm.setStateId(pdm.getPropState().getId());
			pdm.setPropTypeId(pdm.getPropType().getId());
			
			detailsMasterInfo.setModel(pdm);
			mav.addObject("model", pdm);
			mav.addObject("update", "update_record");
			return mav;
			
		} catch (ResourceAccessException e) {
			logger.error(e.getMessage(),e);
			return mav;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return mav;
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST) 
	public ModelAndView processUpdate(@Valid @ModelAttribute(value = MODELNAME) PropDetailsMasterInfo detailsMasterInfo,
									  BindingResult bindingResult,@PathVariable("id") String id,
									  @RequestParam(value = WebConstants.SUBMIT_BUTTON, required = false) String submit){
		
		logger.info("Comes to processUpdate Operation POST");
		logger.info("[DetailsMasterInfo Values :"+ObjectUtils.nullSafeToString(detailsMasterInfo.getModel())+"]");
		try {
			return super.processUpdate(id,detailsMasterInfo, bindingResult, submit,VIEW_NAME_ADD);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return super.processNew(VIEW_NAME_ADD,MODELNAME);
		}
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET) 
	public ModelAndView processView(@Valid @ModelAttribute(value = MODELNAME) PropDetailsMasterInfo detailsMasterInfo,
									@PathVariable("id") String id){
		
		logger.info("Comes to processView Operation GET");
		logger.info("[DetailsMasterInfo Values :"+ObjectUtils.nullSafeToString(detailsMasterInfo.getModel())+"]");
		
		//ModelAndView mav = new ModelAndView(READONLY_NAME_ADD);
		//mav.addObject(MODELNAME, detailsMasterInfo);
		try {
			
			return super.processView(id,detailsMasterInfo,"view",READONLY_NAME_ADD);
			/*JsonResponseBean responseEntity = this.primaryService().load(id);
			
			if(responseEntity==null || (responseEntity!=null && responseEntity.getResponseBean()==null) ) {
				mav.addObject(CmnConstants.MAV_ALERT_TYPE, CmnConstants.MAV_ERROR_CODE);
				mav.addObject(CmnConstants.MAV_ALERT_MSG, CmnConstants.MAV_ERROR_MSG);
				return mav;
			}
			
			String jsonStr = super.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getResponseBean());
			PropDetailsMaster pdm = super.objectMapper.readValue(jsonStr, PropDetailsMaster.class);
			pdm.setStateId(pdm.getPropState().getId());
			pdm.setPropTypeId(pdm.getPropType().getId());
			
			detailsMasterInfo.setModel(pdm);
			mav.addObject("model", pdm);
			mav.addObject("update", "update_record");
			return mav;*/
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return super.processNew(READONLY_NAME_ADD,MODELNAME);
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET) 
	public ModelAndView processDelete(@Valid @ModelAttribute(value = MODELNAME) PropDetailsMasterInfo detailsMasterInfo,
									  @PathVariable("id") String id){
		
		logger.info("Comes to processDelete Operation GET");
		logger.info("[DetailsMasterInfo Values :"+ObjectUtils.nullSafeToString(detailsMasterInfo.getModel())+"]");
		try {
			ModelAndView mav =  super.processDelete(id,detailsMasterInfo,"delete",WebConstants.PATH_PROP_SETTINGS);
			return mav;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return super.processNew(VIEW_NAME_DISPLAY,MODELNAME);
		}
	}
	
	@RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
	public ModelAndView getPage(@Valid @ModelAttribute(value = MODELNAME) PropDetailsMasterInfo detailsMasterInfo,
								@PathVariable Integer pageNumber) {
		
		logger.info("[pageNumber :{}]",pageNumber);
		ModelAndView mav = null;
		
		try {
			
			JsonResponseBean responseEntity = this.primaryService().getPage(pageNumber, null);
			
			if(responseEntity==null || (responseEntity!=null && responseEntity.getResponseBean()==null) ) {
				mav = super.processNew(VIEW_NAME_DISPLAY,MODELNAME);
				mav.addObject("results", Collections.EMPTY_LIST);
				return mav;
			}
			String jsonStr = super.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseEntity.getResponseBean());
			Page<PropDetailsMaster> page = super.objectMapper.readValue(jsonStr, new TypeReference<RestResponsePage<PropDetailsMaster>>(){});
			
			/*logger.info("[Page<PropDetailsMaster> :"+page+"]-[page.getTotalPages() :"+page.getTotalPages()+"]-[page.getNumber() :"+page.getNumber()+"]");
			logger.info("[first :{}]-[last :{}]-[previousPage :{}]-[nextPage :{}]",
						  new Object[]{page.isFirstPage(),page.isLastPage(),page.hasPreviousPage(),page.hasNextPage()});*/
			
			int current = page.getNumber() + 1;
		    int begin = Math.max(1, current - 5);
		    int end = Math.min(begin + 10, page.getTotalPages());
		    
		    logger.info("[CURRENT :{}]-[BEGIN :{}]-[END :{}]",new Object[]{current,begin,end});
			
		    mav = new ModelAndView(VIEW_NAME_DISPLAY);
			mav.addObject(MODELNAME, detailsMasterInfo);
		    
		    mav.addObject("results", page.getContent());
		    mav.addObject("resourcePath", "settings");
		    mav.addObject("deploymentLog", page);
		    mav.addObject("beginIndex", begin);
		    mav.addObject("endIndex", end);
		    mav.addObject("currentIndex", current);
			return mav;
			
		} catch (ResourceAccessException e) {
			logger.error(e.getMessage(),e);
			mav = super.processNew(VIEW_NAME_DISPLAY,MODELNAME);
			mav.addObject("results", Collections.EMPTY_LIST);
			return mav;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			mav = super.processNew(VIEW_NAME_DISPLAY,MODELNAME);
			mav.addObject("results", Collections.EMPTY_LIST);
			return mav;
		}
	}
	
}
