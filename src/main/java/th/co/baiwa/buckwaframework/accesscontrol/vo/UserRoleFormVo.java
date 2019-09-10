package th.co.baiwa.buckwaframework.accesscontrol.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class UserRoleFormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4365919420782028056L;
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
