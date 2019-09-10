package th.go.excise.ims.ws.client.pcc.exciseuserinformation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Role {

	@SerializedName("SystemId")
	@Expose
	private String systemId;
	
	@SerializedName("RoleId")
	@Expose
	private String roleId;
	
	@SerializedName("RoleName")
	@Expose
	private String roleName;
	

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
