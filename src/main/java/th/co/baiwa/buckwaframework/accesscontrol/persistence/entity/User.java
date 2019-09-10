package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "ADM_USER")
public class User extends BaseEntity {

	private static final long serialVersionUID = 8469283616859080045L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_USER_GEN")
	@SequenceGenerator(name = "ADM_USER_GEN", sequenceName = "ADM_USER_SEQ", allocationSize = 1)
	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ENABLED")
	private String enabled;

	@Column(name = "ACCOUNT_NON_EXPIRED")
	private String accountNonExpired;

	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private String credentialsNonExpired;

	@Column(name = "ACCOUNT_NON_LOCKED")
	private String accountNonLocked;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(String accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public String getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(String credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(String accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

}
