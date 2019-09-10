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
@Table(name = "ADM_USER_ROLE")
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = -2074283299394102269L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_USER_ROLE_GEN")
	@SequenceGenerator(name = "ADM_USER_ROLE_GEN", sequenceName = "ADM_USER_ROLE_SEQ", allocationSize = 1)
	@Column(name = "USER_ROLE_ID")
	private Long userRoleId;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "ROLE_ID")
	private Long roleId;

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
