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
@Table(name = "ADM_USER_OPERATION")
public class UserOperation extends BaseEntity {

	private static final long serialVersionUID = -2960598659593577527L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_USER_OPERATION_GEN")
	@SequenceGenerator(name = "ADM_USER_OPERATION_GEN", sequenceName = "ADM_USER_OPERATION_SEQ", allocationSize = 1)
	@Column(name = "USER_OPERATION_ID")
	private Long userOperationId;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "OPERATION_ID")
	private Long operationId;

	public Long getUserOperationId() {
		return userOperationId;
	}

	public void setUserOperationId(Long userOperationId) {
		this.userOperationId = userOperationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

}
