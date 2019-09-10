package th.co.baiwa.buckwaframework.security.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;

public class UserLoginUtils {

	public static UserBean getCurrentUserBean() {
		UserBean userBean = null;
		
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal instanceof UserBean) {
				userBean = (UserBean) principal;
			} else if (principal instanceof User) {
				// UnitTest @WithMockUser
				User user = (User) principal;
				userBean = new UserDetails(user.getUsername(), "", user.getAuthorities());
			} else {
				// "anonymous" user
				String username = principal.toString();
				userBean = new UserDetails(username, "", AuthorityUtils.NO_AUTHORITIES);
			}
		} else {
			String username = "NO LOGIN";
			userBean = new UserDetails(username, "", AuthorityUtils.NO_AUTHORITIES);
		}
		
		return userBean;
	}

	public static String getCurrentUsername() {
		return UserLoginUtils.getCurrentUserBean().getUsername();
	}
	
	public static List<String> getGrantedAuthorityList() {
		Collection<GrantedAuthority> grantedAuthorityList = UserLoginUtils.getCurrentUserBean().getAuthorities();
		
		List<String> authorityList = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
			authorityList.add(grantedAuthority.getAuthority());
		}
		
		return authorityList;
	}
	
}
