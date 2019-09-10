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
@Table(name = "ADM_OPERATION")
public class Operation extends BaseEntity {

	private static final long serialVersionUID = -5628927546154517795L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADM_OPERATION_GEN")
	@SequenceGenerator(name = "ADM_OPERATION_GEN", sequenceName = "ADM_OPERATION_SEQ", allocationSize = 1)
	@Column(name = "OPERATION_ID")
	private Long operationId;

	@Column(name = "OPERATION_CODE")
	private String operationCode;

	@Column(name = "OPERATION_DESC")
	private String operationDesc;

	@Column(name = "PARENT_ID")
	private Long parentId;

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
