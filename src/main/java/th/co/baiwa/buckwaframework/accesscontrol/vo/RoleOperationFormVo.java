package th.co.baiwa.buckwaframework.accesscontrol.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class RoleOperationFormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6884853110297388133L;
	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
