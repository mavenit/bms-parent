package com.bms.eai.property.be.boot.config;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.SpringHandlerInstantiator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bms.eai.lib.DateUtils;
import com.bms.eai.lib.payload.mixin.JsonMixIn;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;

/**
 * @author kul_sudhakar
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes","deprecation" })
@Configuration
public class PropertySpringConfig extends WebMvcConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${bms.json.mix.in.base.package}")
	private String jsonMixInBasePackage;
	
	@Autowired
	private ApplicationContext appCtx;
	
	/*@Bean()
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages");
		messageBundle.setDefaultEncoding("UTF-8");
		messageBundle.setUseCodeAsDefaultMessage(true);
		return messageBundle;
	}*/

	@Bean
	public HandlerInstantiator handlerInstantiator() {
	   return new SpringHandlerInstantiator(appCtx.getAutowireCapableBeanFactory());
	}
	
	/*@Bean
	public LocaleResolver localResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.ENGLISH);
		return slr;
	}*/
	
	/*@Bean
	public ErrorMessageSourceHelper errorMessageSourceHelper() {
		ErrorMessageSourceHelper errorMessageSourceHelper = new ErrorMessageSourceHelper();
		return errorMessageSourceHelper;
	}*/
	
	@Bean
	public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, false);
        objectMapper.setHandlerInstantiator(handlerInstantiator());
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.DATE_FORMAT));
        objectMapper.setTimeZone(TimeZone.getDefault());
        Map<Class, Class> entityMixInMappingConfigs = getEntityMixInMappingConfigs();
        entityMixInMappingConfigs.forEach(
                (entityTarget, mixInSource) -> {
                    logger.info("[JSON] Entity: {} mapped to mixIn: {}", entityTarget.getName(), mixInSource.getName());
                    objectMapper.addMixIn(entityTarget, mixInSource);
                });
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
	   }
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods(HttpMethod.GET.name(),
                HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(), HttpMethod.PUT.name(),
                HttpMethod.PATCH.name());
        logger.info("CORSEnablerConfig enabled.");
    }
	 
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	   converters.add(customJackson2HttpMessageConverter());
	}
	
	private Map<Class, Class> getEntityMixInMappingConfigs() {
        try {
            Map<Class, Class> entityMixInMappingConfigs = new HashMap<>();
            ClassPathScanningCandidateComponentProvider scanner =
                    new ClassPathScanningCandidateComponentProvider(false) {
                        @Override
                        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                            return true;
                        }
                    };
            scanner.addIncludeFilter(new AnnotationTypeFilter(JsonMixIn.class));
            for(BeanDefinition bd : scanner.findCandidateComponents(jsonMixInBasePackage)) {
                Class mixInSource;
                mixInSource = Class.forName(bd.getBeanClassName());
				JsonMixIn annotation = (JsonMixIn) mixInSource.getAnnotation(JsonMixIn.class);
                Class entityTarget = annotation.value();
                entityMixInMappingConfigs.put(entityTarget, mixInSource);
            }
            return entityMixInMappingConfigs;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
	  }
}
