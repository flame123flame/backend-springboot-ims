package th.go.excise.ims.ws.client.pcc.inquirybank.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.go.excise.ims.ws.client.pcc.common.model.BaseModel;

public class Bank extends BaseModel {

	@SerializedName("BANK_CODE")
	@Expose
	private String bankCode;

	@SerializedName("BANK_NAME")
	@Expose
	private String bankName;

	@SerializedName("SHORT_NAME")
	@Expose
	private String shortName;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}