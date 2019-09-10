package th.co.baiwa.buckwaframework.security.domain;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User implements UserBean {

	private static final long serialVersionUID = 2637807472705815470L;

	private Long userId;
	// Add More Information about USER here.

	private String userThaiId;
	private String userThaiName;
	private String userThaiSurname;
	private String userEngName;
	private String userEngSurname;
	private String title;
	private String email;
	private String cnName;
	private String telephoneNo;
	private String officeCode;
	private String accessAttr;
	private List<String> authorityList;
	// Excise Person
	private String subdeptCode;
	private String subdeptLevel;

	// Constructor
	public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	// Constructor
	public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	// ==================================================
	// Getter & Setter Method
	// ==================================================
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserThaiId() {
		return userThaiId;
	}

	public void setUserThaiId(String userThaiId) {
		this.userThaiId = userThaiId;
	}

	public String getUserThaiName() {
		return userThaiName;
	}

	public void setUserThaiName(String userThaiName) {
		this.userThaiName = userThaiName;
	}

	public String getUserThaiSurname() {
		return userThaiSurname;
	}

	public void setUserThaiSurname(String userThaiSurname) {
		this.userThaiSurname = userThaiSurname;
	}

	public String getUserEngName() {
		return userEngName;
	}

	public void setUserEngName(String userEngName) {
		this.userEngName = userEngName;
	}

	public String getUserEngSurname() {
		return userEngSurname;
	}

	public void setUserEngSurname(String userEngSurname) {
		this.userEngSurname = userEngSurname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getAccessAttr() {
		return accessAttr;
	}

	public void setAccessAttr(String accessAttr) {
		this.accessAttr = accessAttr;
	}

	public List<String> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<String> authorityList) {
		this.authorityList = authorityList;
	}

	public String getSubdeptCode() {
		return subdeptCode;
	}

	public void setSubdeptCode(String subdeptCode) {
		this.subdeptCode = subdeptCode;
	}

	public String getSubdeptLevel() {
		return subdeptLevel;
	}

	public void setSubdeptLevel(String subdeptLevel) {
		this.subdeptLevel = subdeptLevel;
	}

}
