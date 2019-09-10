package th.go.excise.ims.ws.client.pcc.exciseuserinformation.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseExciseUserInformation {

	@SerializedName("Nid")
	@Expose
	private String nid;
	
	@SerializedName("FirstName")
	@Expose
	private String firstName;
	
	@SerializedName("SurName")
	@Expose
	private String surName;
	
	@SerializedName("Position")
	@Expose
	private String position;
	
	@SerializedName("Email")
	@Expose
	private String email;
	
	@SerializedName("AccessPermit")
	@Expose
	private String accessPermit;
	
	@SerializedName("Offcode")
	@Expose
	private String offcode;
	
	@SerializedName("Role")
	@Expose
	private List<Role> role = null;

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessPermit() {
		return accessPermit;
	}

	public void setAccessPermit(String accessPermit) {
		this.accessPermit = accessPermit;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
}
