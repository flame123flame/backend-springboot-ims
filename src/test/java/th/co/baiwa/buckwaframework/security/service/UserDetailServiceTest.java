package th.co.baiwa.buckwaframework.security.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;

@Service("userDetailService")
public class UserDetailServiceTest implements org.springframework.security.core.userdetails.UserDetailsService {

	static Map<String, UserDetails> DB_BASED_USER_MAPPING;

	static {
		DB_BASED_USER_MAPPING = new LinkedHashMap<>();
	}

	@PostConstruct
	public void initialUser() {
		// Admin
		UserDetails userDetailsAdmin = new UserDetails("admin", "password", getGrantedAuthorities(
			ROLE.USER,
			ROLE.ADMIN,
			ROLE.TA_ADM,
			ROLE.IA_ADM,
			ROLE.OA_ADM
		));
		userDetailsAdmin.setOfficeCode("000000");
		DB_BASED_USER_MAPPING.put("admin", userDetailsAdmin);
		
		// ta001401
		UserDetails userDetailsTa001401 = new UserDetails("ta001401", "password", getGrantedAuthorities(ROLE.USER, ROLE.TA_PLAN));
		userDetailsTa001401.setOfficeCode("001401");
		DB_BASED_USER_MAPPING.put("ta001401", userDetailsTa001401);
		
		// ta001402
		UserDetails userDetailsTa001402 = new UserDetails("ta001402", "password", getGrantedAuthorities(ROLE.USER, ROLE.TA_SEL));
		userDetailsTa001402.setOfficeCode("001402");
		DB_BASED_USER_MAPPING.put("ta001402", userDetailsTa001402);
		
		// ta001403
		UserDetails userDetailsTa001403 = new UserDetails("ta001403", "password", getGrantedAuthorities(ROLE.USER, ROLE.TA_SEL));
		userDetailsTa001403.setOfficeCode("001403");
		DB_BASED_USER_MAPPING.put("ta001403", userDetailsTa001403);
	}

	private static List<GrantedAuthority> getGrantedAuthorities(String... roles) {
		ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (DB_BASED_USER_MAPPING.containsKey(username)) {
			return DB_BASED_USER_MAPPING.get(username);
		}
		throw new UsernameNotFoundException("User " + username + " cannot be found");
	}

}
