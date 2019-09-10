package th.go.excise.ims.ws.client.pcc.regfri4000.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegDuty {

	@SerializedName("GroupId")
	@Expose
	private String groupId;

	@SerializedName("GroupName")
	@Expose
	private String groupName;

	@SerializedName("RegDate")
	@Expose
	private String regDate;

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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
