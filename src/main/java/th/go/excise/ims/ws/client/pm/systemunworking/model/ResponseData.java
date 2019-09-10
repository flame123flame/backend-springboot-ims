package th.go.excise.ims.ws.client.pm.systemunworking.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

	@SerializedName("statusCode")
	@Expose
	private String statusCode;

	@SerializedName("statusDesc")
	@Expose
	private String statusDesc;

	@SerializedName("data")
	@Expose
	private List<Data> data;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

}