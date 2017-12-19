package com.bms.eai.common.model.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import com.bms.eai.common.model.core.AuditorAwareImpl;
import com.bms.eai.common.model.core.EntityManagerGlobalFilter;

/**
 * @author kul_sudhakar
 *
 */
@Configuration
@EnableAspectJAutoProxy
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class PropertyModelConfig {

	@Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }
	
	@Bean
    public EntityManagerGlobalFilter entityManagerGlobalFilter() {
        return new EntityManagerGlobalFilter();
    }
	
	@Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
	
}
