package com.bms.eai.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.bms.eai.portal.config.idm.CustomAuthenticationManager;
import com.bms.eai.portal.constants.PageConstants;

/**
 * @author kul_sudhakar
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class BmsSecurityConfig extends WebSecurityConfigurerAdapter {

	/*@Autowired
	private ApplicationContext applicationContext;*/
	
	@Override
	public void configure(WebSecurity web) throws Exception	{
		//web.ignoring().antMatchers("/*.css","/*.js","/*.png","/*.jpg","/*.jpeg");
		//web.ignoring().antMatchers("/css/**", "/js/**", "/images/**","/resources/**");
		web.ignoring().antMatchers("/images/**","/resources/**");
	}
	
	@Bean
	public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
    	UsernamePasswordAuthenticationFilter upaf = new UsernamePasswordAuthenticationFilter();
    	upaf.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(PageConstants.LOGIN, "POST"));
    	upaf.setAuthenticationManager(authenticationManagerBean());
    	upaf.setAuthenticationFailureHandler(customAuthenticationFailureHandler());
    	//upaf.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
    	upaf.setAuthenticationSuccessHandler(authSuccessHandler());
    	upaf.setUsernameParameter("username");
    	upaf.setPasswordParameter("password");
    	upaf.setAllowSessionCreation(false);
    	return upaf;
    };
    
    @Bean 
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return new CustomAuthenticationManager();
    }
    
    @Bean
    public SimpleUrlAuthenticationFailureHandler customAuthenticationFailureHandler() {
    	SimpleUrlAuthenticationFailureHandler suafh = new SimpleUrlAuthenticationFailureHandler();
    	suafh.setDefaultFailureUrl(PageConstants.LOGIN);
    	return suafh;
    }
    
    /*@Bean
    public SimpleUrlAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
    	SimpleUrlAuthenticationSuccessHandler suash =  new SimpleUrlAuthenticationSuccessHandler();
    	suash.setDefaultTargetUrl(PageConstants.ROOT);
    	return suash;
    }*/
    
    @Bean
    public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
    	return new LoginUrlAuthenticationEntryPoint(PageConstants.LOGIN);
    }
    
    @Bean
   	public LoginSuccessHandler authSuccessHandler() {
   		return new LoginSuccessHandler();
   	}
    
    @Bean
   	public LogoutHandler logoutHandler() {
   		return new LogoutHandler();
   	}
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		//auth.userDetailsService(this.userDetailService).passwordEncoder(getPasswordEncoder());
		//auth.userDetailsService(this.userDetailService);
	}
	
	@Bean
    public BasicAuthenticationFilter basicAuthenticationFilter() throws Exception {
    	return new BasicAuthenticationFilter(authenticationManagerBean(), authenticationEntryPoint());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		RequestMatcher matcher = new AntPathRequestMatcher("/login");
		DelegatingRequestMatcherHeaderWriter headerWriter =	new DelegatingRequestMatcherHeaderWriter(matcher,new XFrameOptionsHeaderWriter());
		 
		http
			.csrf()
			//.csrfTokenRepository(csrfTokenRepository())
				.disable()
			//.and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
				.headers()
				.referrerPolicy(ReferrerPolicy.SAME_ORIGIN)
				.and()
				.xssProtection().block(true)
			.and()
				.frameOptions().disable()
				.cacheControl().disable()
				.contentTypeOptions().disable()
				.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN))
				.addHeaderWriter(new StaticHeadersWriter("X-Custom-Security-Header","BMS"))
				.addHeaderWriter(headerWriter)
				.httpStrictTransportSecurity().includeSubDomains(true)
		.and()
	    .and()	    	
	        	.authorizeRequests()//.anyRequest().hasAnyRole("ROLE_USER","ROLE_ADMIN")
		        // UNAUTHENTICATED USER
	        	.antMatchers(PageConstants.ROOT).anonymous()
		        .antMatchers(PageConstants.LOGIN).anonymous()
		        .antMatchers(PageConstants.FRGT_PWORD).anonymous()
		        .anyRequest().authenticated()
        .and()
        	// This is where we configure our login form.
	        .formLogin()
	            .loginPage(PageConstants.LOGIN)				// login-page: the page that contains the login screen
	            .loginProcessingUrl(PageConstants.LOGIN)	// login-processing-url: this is the URL to which the login form should be submitted
	            .defaultSuccessUrl(PageConstants.HOME)		// default-target-url: the URL to which the user will be redirected if they login successfully
	            .successHandler(authSuccessHandler())		// success-url: Handler to redirected the user to any specific page
	            .failureUrl(PageConstants.LOGIN_ERR)		// authentication-failure-url: the URL to which the user will be redirected if they fail login
	            .usernameParameter("username")				// username-parameter: the name of the request parameter which contains the username
	            .passwordParameter("password")				// password-parameter: the name of the request parameter which contains the password
	            .permitAll() 
	   .and().sessionManagement()	   	   
	   			.invalidSessionUrl(PageConstants.LOGIN)
	   			.sessionCreationPolicy(SessionCreationPolicy.NEVER)
	   			.sessionFixation().migrateSession()
	   			.maximumSessions(1)								// session-management/concurrency-control@max-sessions
	   			.maxSessionsPreventsLogin(true)					// session-management/concurrency-control@error-if-maximum-exceeded
	   			.expiredUrl(PageConstants.LOGIN)				// session-management/concurrency-control@expired-url
	   			.sessionRegistry(sessionRegistry())
	   .and()
	   .and()
	        .logout()
	        	.logoutRequestMatcher(new AntPathRequestMatcher(PageConstants.LOGOUT))
	        	.logoutSuccessHandler(logoutHandler())
	        	.deleteCookies("JSESSIONID","XSRF-TOKEN")
	            .invalidateHttpSession(true)
       .and().addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
       .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
       //.accessDeniedPage(PageConstants.ERROR)
	    ;
	}
	
	@Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }
	
	/*@Bean
	 public CsrfTokenRepository csrfTokenRepository() {
		 HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		 repository.setHeaderName("X-XSRF-TOKEN");
		 return repository;
	} */
	
}
