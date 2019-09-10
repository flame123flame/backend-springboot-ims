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
@Table(name = "ADM_ROLE")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -3833918994291979045L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_ROLE_GEN")
	@SequenceGenerator(name = "ADM_ROLE_GEN", sequenceName = "ADM_ROLE_SEQ", allocationSize = 1)
	@Column(name = "ROLE_ID")
	private Long roleId;

	@Column(name = "ROLE_CODE")
	private String roleCode;

	@Column(name = "ROLE_DESC")
	private String roleDesc;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
