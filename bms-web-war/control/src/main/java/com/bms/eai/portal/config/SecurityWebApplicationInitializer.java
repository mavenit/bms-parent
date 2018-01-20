package com.bms.eai.portal.config;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

/**
 * @author kul_sudhakar
 *
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		logger.info("beforeSpringSecurityFilterChain >>>>>>>");
		insertFilters(servletContext, new MultipartFilter());
	}
	
}
