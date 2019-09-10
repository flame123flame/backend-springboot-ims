package th.go.excise.ims.ws.client.pcc.incfri8040.model;

import java.math.BigDecimal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Income {

	@SerializedName("SumTaxAmount")
	@Expose
	private BigDecimal sumTaxAmount;

	@SerializedName("ForecastAmount")
	@Expose
	private String forecastAmount;

	@SerializedName("OfficeReceiveCode")
	@Expose
	private String officeReceiveCode;

	@SerializedName("OfficeName")
	@Expose
	private String officeName;

	public BigDecimal getSumTaxAmount() {
		return sumTaxAmount;
	}

	public void setSumTaxAmount(BigDecimal sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}

	public String getForecastAmount() {
		return forecastAmount;
	}

	public void setForecastAmount(String forecastAmount) {
		this.forecastAmount = forecastAmount;
	}

	public String getOfficeReceiveCode() {
		return officeReceiveCode;
	}

	public void setOfficeReceiveCode(String officeReceiveCode) {
		this.officeReceiveCode = officeReceiveCode;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

}
