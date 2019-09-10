
package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "ADM_ROLE_OPERATION")
public class RoleOperation extends BaseEntity {

	private static final long serialVersionUID = -5739058332539367317L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_ROLE_OPERATION_GEN")
	@SequenceGenerator(name = "ADM_ROLE_OPERATION_GEN", sequenceName = "ADM_ROLE_OPERATION_SEQ", allocationSize = 1)
	@Column(name = "ROLE_OPERATION_ID")
	private Long roleOperationId;
	
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	@Column(name = "OPERATION_ID")
	private Long operationId;

	public Long getRoleOperationId() {
		return roleOperationId;
	}

	public void setRoleOperationId(Long roleOperationId) {
		this.roleOperationId = roleOperationId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
