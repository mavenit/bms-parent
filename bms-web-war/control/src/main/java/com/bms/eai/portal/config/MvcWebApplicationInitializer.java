package com.bms.eai.portal.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.bms.eai.module.api.config.ClientSdkApiConfig;

/**
 * @author kul_sudhakar
 *
 */
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { 				
				BmsSecurityConfig.class,
				BmsWebAppConfig.class,
				ClientSdkApiConfig.class
			};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { };
	}

	@Override
	protected String[] getServletMappings() {
		//return new String[] { "/","*.css","*.js","*.png","*.jpg","*.gif","*.ico","*.map" };
		return new String[] { "/" };
	}
	
	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        
        DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");
        
        return new Filter[] { 
        		characterEncodingFilter 
        		, securityFilterChain
        		//, new HiddenHttpMethodFilter()
        };
    }

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
	}
	
}
