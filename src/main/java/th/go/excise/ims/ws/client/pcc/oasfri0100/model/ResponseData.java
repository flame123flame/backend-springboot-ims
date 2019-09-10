package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

	@SerializedName(value = "ResponseCode", alternate = { "responseCode" })
	@Expose
	private String responseCode;

	@SerializedName(value = "ResponseMessage", alternate = { "responseMessage" })
	@Expose
	private String responseMessage;

	@SerializedName(value = "ResponseData", alternate = { "responseData" })
	@Expose
	private ResponseData2 responseData;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public ResponseData2 getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData2 responseData) {
		this.responseData = responseData;
	}

}
