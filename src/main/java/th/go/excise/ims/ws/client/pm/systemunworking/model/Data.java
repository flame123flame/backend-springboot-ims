package th.go.excise.ims.ws.client.pm.systemunworking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("systemCode")
	@Expose
	private String systemCode;

	@SerializedName("systemName")
	@Expose
	private String systemName;

	@SerializedName("countAll")
	@Expose
	private String countAll;

	@SerializedName("countError")
	@Expose
	private String countError;

	@SerializedName("errorDetail")
	@Expose
	private String errorDetail;

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getCountAll() {
		return countAll;
	}

	public void setCountAll(String countAll) {
		this.countAll = countAll;
	}

	public String getCountError() {
		return countError;
	}

	public void setCountError(String countError) {
		this.countError = countError;
	}

	public String getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

}
