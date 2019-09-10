package th.go.excise.ims.ws.client.pcc.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseModel {

	@SerializedName("BEGIN_DATE")
	@Expose
	private String beginDate;

	@SerializedName("UPD_USERID")
	@Expose
	private String updUserId;

	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getUpdUserId() {
		return updUserId;
	}

	public void setUpdUserId(String updUserId) {
		this.updUserId = updUserId;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
}
