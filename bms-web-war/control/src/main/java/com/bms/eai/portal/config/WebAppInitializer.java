package com.bms.eai.portal.config;

import javax.servlet.MultipartConfigElement;
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
	
	private String TMP_FOLDER = "/temp/"; 
    private int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		System.out.println("Initializing System");

		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.setConfigLocation(CONFIG_LOCATION);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		servletContext.addListener(new SessionListener());
		ServletRegistration.Dynamic servlet = servletContext.addServlet("portal-dispatcher", dispatcherServlet);

		servlet.setLoadOnStartup(1);
		
		/* MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER, 
		          MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2);
		 servlet.setMultipartConfig(multipartConfigElement);*/
		
		servlet.setAsyncSupported(true);
		servlet.addMapping("/");
	}
	
}
