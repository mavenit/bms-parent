package com.bms.eai.module.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author kul_sudhakar
 *
 */
@Configuration
public class ClientSdkApiConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
	
}
