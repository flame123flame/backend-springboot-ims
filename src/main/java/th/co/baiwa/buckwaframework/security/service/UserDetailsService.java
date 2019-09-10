package th.co.baiwa.buckwaframework.security.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.UserRepository;
import th.co.baiwa.buckwaframework.common.util.BooleanToStringConverter;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;
import th.co.baiwa.buckwaframework.security.persistence.repository.UserDetailsRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
	
	private UserRepository userRepository;
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	public UserDetailsService(
			UserRepository userRepository,
			UserDetailsRepository userDetailsRepository) {
		this.userRepository = userRepository;
		this.userDetailsRepository = userDetailsRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("loadUserByUsername username={}", username);
		
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(MessageFormat.format("Username {0} not found", username));
		}
		
		// Initial Granted Authority
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		// Add Role
		grantedAuthorityList.addAll(userDetailsRepository.findGrantedRoleByUserId(user.getUserId()));
		// Add Operation
		grantedAuthorityList.addAll(userDetailsRepository.findGrantedOperationByUserId(user.getUserId()));
		
		UserDetails userDetails = new UserDetails(
			user.getUsername(),
			user.getPassword(),
			BooleanToStringConverter.convertToBeanAttribute(user.getEnabled()),
			BooleanToStringConverter.convertToBeanAttribute(user.getAccountNonExpired()),
			BooleanToStringConverter.convertToBeanAttribute(user.getCredentialsNonExpired()),
			BooleanToStringConverter.convertToBeanAttribute(user.getAccountNonLocked()),
			grantedAuthorityList
		);
		userDetails.setUserId(user.getUserId());
		
		return userDetails;
	}
	
}
