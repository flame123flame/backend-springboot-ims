package th.co.baiwa.buckwaframework.security.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserBean extends UserDetails {
	
	public Long getUserId();
	
	public String getUsername();
	
	public String getUserThaiId();
	public String getUserThaiName();
	public String getUserThaiSurname();
	public String getUserEngName();
	public String getUserEngSurname();
	public String getTitle();
	public String getEmail();
	public String getCnName();
	public String getTelephoneNo();
	public String getOfficeCode();
	public String getAccessAttr();
	public String getSubdeptCode();
	public String getSubdeptLevel();
	
	public Collection<GrantedAuthority> getAuthorities();
	
}
