package th.go.excise.ims.ws.client.pcc.inquirydutygroup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.go.excise.ims.ws.client.pcc.common.model.BaseModel;

public class DutyGroup extends BaseModel {

	@SerializedName("GROUP_ID")
	@Expose
	private String groupId;

	@SerializedName("GROUP_NAME")
	@Expose
	private String groupName;

	@SerializedName("GROUP_STATUS")
	@Expose
	private String groupStatus;

	@SerializedName("SUP_GROUP_ID")
	@Expose
	private String supGroupId;

	@SerializedName("REG_STATUS")
	@Expose
	private String regStatus;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getSupGroupId() {
		return supGroupId;
	}

	public void setSupGroupId(String supGroupId) {
		this.supGroupId = supGroupId;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

}
