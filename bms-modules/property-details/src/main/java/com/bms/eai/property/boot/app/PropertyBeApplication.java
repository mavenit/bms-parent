package com.bms.eai.property.boot.app;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.bms.eai.common.model.config.PropertyModelConfig;
import com.bms.eai.property.boot.config.PropertySpringConfig;

/**
 * @author kul_sudhakar
 *
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.bms.eai")
@EnableJpaRepositories(basePackages = {"com.bms.eai.property.model.repository","com.bms.eai.common.model.repository"})
@EntityScan(basePackages = {"com.bms.eai.property.model.entity","com.bms.eai.common.model.entity"})
@SpringBootApplication
@Profile("dev")
public class PropertyBeApplication extends SpringBootServletInitializer {

	private Logger logger = LoggerFactory.getLogger(PropertyBeApplication.class);

	 public static void main(String[] args) {
	        SpringApplication.run(PropertyBeApplication.class, args);
	        System.out.println("=============================================================");
	  }
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		logger.info("[Main PROPERTY War Application Name :{}]",new Object[] {builder.application().getMainApplicationClass()});
		return builder.sources(PropertyBeApplication.class,PropertySpringConfig.class,PropertyModelConfig.class);
	}

	@PreDestroy
	public void destroy() {
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				logger.info("Unregistering jdbc driver: {}", driver);
			} catch (SQLException e) {
				logger.error("Error unregistering jdbc driver {}", driver);
				logger.error(e.getMessage(), e);
			}
		}
	}
	
}
