package th.co.baiwa.buckwaframework.accesscontrol.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class RoleFormVo extends DataTableRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3421108865484414981L;
	private String roleCode;
	private String roleDesc;

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
