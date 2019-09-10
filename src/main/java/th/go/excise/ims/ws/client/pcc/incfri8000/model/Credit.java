package th.go.excise.ims.ws.client.pcc.incfri8000.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credit {

	@SerializedName("ApproveNo")
	@Expose
	private String approveNo;

	@SerializedName("ApproveDate")
	@Expose
	private String approveDate;

	public String getApproveNo() {
		return approveNo;
	}

	public void setApproveNo(String approveNo) {
		this.approveNo = approveNo;
	}

	public String getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}

}
