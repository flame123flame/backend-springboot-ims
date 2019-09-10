package th.go.excise.ims.ws.client.pcc.exciseuserinformation.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestExciseUserInformation {

	@SerializedName("UserId")
	@Expose
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
