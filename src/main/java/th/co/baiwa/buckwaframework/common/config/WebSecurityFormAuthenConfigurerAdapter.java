package th.co.baiwa.buckwaframework.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.URL;

@Configuration
@Order(2)
public class WebSecurityFormAuthenConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
			// For Angular -- Start
			"/inline.bundle.js",
			"/polyfills.bundle.js",
			"/scripts.bundle.js",
			"/styles.bundle.js",
			"/vendor.bundle.js",
			"/main.bundle.js",
			// For Angular -- End
			// For Swagger2 -- Start
			"/v2/api-docs/**",
			"/swagger.json",
			"/swagger-ui.html"
			// For Swagger2 -- End
		);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.antMatcher("/backend/**")
				.authorizeRequests().anyRequest()
				.hasAnyAuthority(ROLE.USER, ROLE.ADMIN)
			.and()
			.formLogin()
				.loginPage(URL.LOGIN_WEB).permitAll()
				.defaultSuccessUrl(URL.LOGIN_WEB_SUCCESS)
				.failureUrl(URL.LOGIN_WEB_FAILURE)
				.usernameParameter(SecurityConstants.USERNAME_PARAM)
				.passwordParameter(SecurityConstants.PASSWORD_PARAM)
			.and()
			.logout()
				.permitAll()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout.htm"))
			.and()
			.exceptionHandling().accessDeniedPage("/403.htm")
			.and()
			.sessionManagement()
				.invalidSessionUrl(SecurityConstants.URL.LOGIN_WEB)
				.maximumSessions(1)
				.sessionRegistry(sessionRegistry());
		
		http.csrf().disable();
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
}
