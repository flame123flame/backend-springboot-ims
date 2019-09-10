package th.co.baiwa.buckwaframework.accesscontrol.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class UserFormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 52036244727799867L;
	private String username;
	private String enabled;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

}
