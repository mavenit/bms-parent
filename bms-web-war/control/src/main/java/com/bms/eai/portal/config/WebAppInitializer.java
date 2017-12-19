package com.bms.eai.portal.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author kul_sudhakar
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {

	private static final String CONFIG_LOCATION = "com.bms.eai.portal.config";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println("Initializing System");

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.setConfigLocation(CONFIG_LOCATION);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		servletContext.addListener(new SessionListener());
		ServletRegistration.Dynamic servlet = servletContext.addServlet("portal-dispatcher", dispatcherServlet);

		servlet.setLoadOnStartup(1);
		servlet.setAsyncSupported(true);
		servlet.addMapping("/");
	}
	
}
