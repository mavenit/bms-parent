package com.bms.eai.portal.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kul_sudhakar
 *
 */
@SpringBootApplication
@ComponentScan("com.bms.eai")
public class BMSPortalApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BMSPortalApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BMSPortalApp.class);
	}
	
}
