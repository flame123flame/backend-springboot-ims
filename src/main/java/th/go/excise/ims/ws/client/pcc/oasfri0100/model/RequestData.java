package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

	@SerializedName("regId")
	@Expose
	private String regId;

	@SerializedName("taxYear")
	@Expose
	private String taxYear;

	@SerializedName("taxMonth")
	@Expose
	private String taxMonth;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

}
