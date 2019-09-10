package th.co.baiwa.buckwaframework.accesscontrol.vo;

public class RoleOperationVo {
	private Long roleOperationId;
	private Long roleId;
	private Long operationId;
	private String operationCode;
	private String operationDesc;
	private String isDeleted;

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

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

}
