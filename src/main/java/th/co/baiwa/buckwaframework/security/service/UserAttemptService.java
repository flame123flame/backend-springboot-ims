package th.co.baiwa.buckwaframework.security.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.UserRepository;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.SYSTEM_CONFIG;
import th.co.baiwa.buckwaframework.security.persistence.entity.UserAttempt;
import th.co.baiwa.buckwaframework.security.persistence.repository.UserAttemptRepository;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;

@Service
public class UserAttemptService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAttemptService.class);
	
	private final UserAttemptRepository userAttemptRepository;
	private final UserRepository userRepository;
	
	@Autowired
	public UserAttemptService(
			UserAttemptRepository userAttemptRepository,
			UserRepository userRepository) {
		this.userAttemptRepository = userAttemptRepository;
		this.userRepository = userRepository;
	}
	
	public void resetFailAttempt(String username) {
		logger.info("resetFailAttempt username={}", username);
		
		UserAttempt userAttempt = userAttemptRepository.findByUsername(username);
		if (userAttempt == null) {
			userAttempt = new UserAttempt(); 
			userAttempt.setUsername(username);
			userAttempt.setAttempts(0);
			userAttempt.setLastModified(LocalDateTime.now());
			userAttemptRepository.save(userAttempt);
		} else {
			userAttempt.setAttempts(0);
			userAttempt.setLastModified(LocalDateTime.now());
			userAttemptRepository.save(userAttempt);
		}
	}
	
	@Transactional
	public void updateFailAttempt(String username) {
		logger.info("updateFailAttempt username={}", username);
		
		UserAttempt userAttempt = userAttemptRepository.findByUsername(username);
		if (userAttempt == null) {
			userAttempt = new UserAttempt();
			userAttempt.setUsername(username);
			userAttempt.setAttempts(1);
			userAttempt.setLastModified(LocalDateTime.now());
			userAttemptRepository.save(userAttempt);
		} else {
			userAttempt.setAttempts(userAttempt.getAttempts() + 1);
			userAttempt.setLastModified(LocalDateTime.now());
			userAttemptRepository.save(userAttempt);
			
			ParamInfo maxLoginAttempts = ApplicationCache.getParamInfoByCode(PARAM_GROUP.SYSTEM_CONFIG, SYSTEM_CONFIG.LOGIN_ATTEMPTS);
			if (FLAG.Y_FLAG.equals(maxLoginAttempts.getValue1()) && userAttempt.getAttempts() + 1 >= Integer.parseInt(maxLoginAttempts.getValue2())) {
				// Locked User
				User user = userRepository.findByUsername(username);
				user.setAccountNonLocked(FLAG.N_FLAG);
				userRepository.save(user);
				
				// Throw Exception
				throw new LockedException("User Account is locked!");
			}
		}
	}
	
	public UserAttempt getUserAttemptByUsername(String username) {
		logger.info("getUserAttemptByUsername username={}", username);
		return userAttemptRepository.findByUsername(username);
	}
	
}
