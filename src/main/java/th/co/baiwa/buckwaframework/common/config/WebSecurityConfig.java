package th.co.baiwa.buckwaframework.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Value("${spring.profiles.active}")
	private String profileActive;
	
	@Autowired
	@Qualifier("customAuthenticationProvider")
	private AuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	@Qualifier("wsAuthenticationProvider")
	private AuthenticationProvider wsAuthenticationProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		if (PROFILE.PROD.equals(profileActive)) {
			auth.authenticationProvider(wsAuthenticationProvider);
		} else {
			auth.authenticationProvider(customAuthenticationProvider);
		}
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	
}