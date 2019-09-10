package th.go.excise.ims.ws.client.pcc.common.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PccResponseHeader<T> {

	@SerializedName(value = "ResponseCode", alternate = {"responseCode"})
	@Expose
	private String responseCode;

	@SerializedName(value = "ResponseMessage", alternate = {"responseMessage"})
	@Expose
	private String responseMessage;

	@SerializedName("ResponseData")
	@Expose
	private T responseData;

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

	public T getResponseData() {
		return responseData;
	}

	public void setResponseData(T responseData) {
		this.responseData = responseData;
	}

}
