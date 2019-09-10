package th.go.excise.ims.ws.client.pcc.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PccRequestHeader<T> {

	@SerializedName("SystemId")
	@Expose
	private String systemId;

	@SerializedName("UserName")
	@Expose
	private String userName;

	@SerializedName("Password")
	@Expose
	private String password;

	@SerializedName("IpAddress")
	@Expose
	private String ipAddress;

	@SerializedName("RequestData")
	@Expose
	private T requestData;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public T getRequestData() {
		return requestData;
	}

	public void setRequestData(T requestData) {
		this.requestData = requestData;
	}

}
