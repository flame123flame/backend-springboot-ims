package th.co.baiwa.buckwaframework.security.provider;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.security.persistence.entity.UserAttempt;
import th.co.baiwa.buckwaframework.security.service.UserAttemptService;

@Component("jdbcAuthenticationProvider")
public class JdbcAuthenticationProvider extends DaoAuthenticationProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcAuthenticationProvider.class);
	
	private final UserAttemptService userAttemptService;
	
	@Autowired
	public JdbcAuthenticationProvider(
			@Qualifier("userDetailsService") UserDetailsService userDetailsService,
			@Qualifier("passwordEncoder") PasswordEncoder passwordEncoder,
			UserAttemptService userAttemptService) {
		super.setUserDetailsService(userDetailsService);
		super.setPasswordEncoder(passwordEncoder);
		this.userAttemptService = userAttemptService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		logger.info("authenticate");
		
		try {
			
			Authentication auth = super.authenticate(authentication);
			
			// if reach here, means login success, else exception will be thrown
			// reset the ADM_USER_ATTEMPT.ATTEMPTS
			userAttemptService.resetFailAttempt(authentication.getName());
			
			return auth;
			
		} catch (BadCredentialsException e) {
			
			// invalid login, update to ADM_USER_ATTEMPT.ATTEMPTS
			userAttemptService.updateFailAttempt(authentication.getName());
			
			logger.error(e.getMessage(), e);
			throw e;
			
		} catch (LockedException e) {
			
			// this user is locked!
			String error = "";
			UserAttempt userAttempt = userAttemptService.getUserAttemptByUsername(authentication.getName());
			if (userAttempt != null) {
				LocalDateTime lastAttempts = userAttempt.getLastModified();
				error = "User account is locked! <br><br>Username : " + authentication.getName() + "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}
			
			logger.error(e.getMessage(), e);
			throw new LockedException(error);
		}
		
	}
	
}
