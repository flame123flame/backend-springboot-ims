package th.co.baiwa.buckwaframework.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.URL;
import th.co.baiwa.buckwaframework.security.rest.entrypoint.RestAuthenticationEntryPoint;
import th.co.baiwa.buckwaframework.security.rest.handler.RestAuthenticationSuccessHandler;
import th.co.baiwa.buckwaframework.security.rest.handler.RestLogoutHandler;
import th.co.baiwa.buckwaframework.security.rest.handler.RestLogoutSuccessHandler;

@Configuration
@Order(1)
public class WebSecurityRestAuthenConfigurationAdapter extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET,
			"/api/preferences/**"
		);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/mobile-api/**").permitAll()
			.and()
			.antMatcher("/api/**")
				.authorizeRequests().anyRequest()
				.hasAnyAuthority(ROLE.USER)
			.and()
			.formLogin()
				.loginProcessingUrl(URL.LOGIN_REST).permitAll()
				.successHandler(restAuthenticationSuccessHandler())
				.failureHandler(restAuthenticationFailureHandler())
				.usernameParameter(SecurityConstants.USERNAME_PARAM)
				.passwordParameter(SecurityConstants.PASSWORD_PARAM)
			.and()
			.logout()
				.permitAll()
				.addLogoutHandler(restLogoutHandler())
				.logoutRequestMatcher(new AntPathRequestMatcher(URL.LOGIN_REST, HttpMethod.DELETE.toString()))
				.logoutSuccessHandler(restLogoutSuccessHandler())
			.and()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
			.and()
			.requestCache().requestCache(new NullRequestCache())
			.and()
			.sessionManagement()
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry());
		
		http.headers().frameOptions().sameOrigin();
		
		http.csrf().disable();
	}
	
	@Bean
	public RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
		return new RestAuthenticationSuccessHandler();
	}
	
	@Bean
	public SimpleUrlAuthenticationFailureHandler restAuthenticationFailureHandler() {
		return new SimpleUrlAuthenticationFailureHandler();
	}
	
	@Bean
	public RestLogoutHandler restLogoutHandler() {
		return new RestLogoutHandler();
	}
	
	@Bean
	public RestLogoutSuccessHandler restLogoutSuccessHandler() {
		return new RestLogoutSuccessHandler();
	}
	
	@Bean
	public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
		return new RestAuthenticationEntryPoint();
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
}
