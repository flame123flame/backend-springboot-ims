package th.go.excise.ims.ws.vo;

import java.math.BigDecimal;

public class WsIncfri8000MVo {

	private String regId;
	private String newRegId;
	private String dutyGroupId;
	private BigDecimal sumTaxAmount;

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getDutyGroupId() {
		return dutyGroupId;
	}

	public void setDutyGroupId(String dutyGroupId) {
		this.dutyGroupId = dutyGroupId;
	}

	public BigDecimal getSumTaxAmount() {
		return sumTaxAmount;
	}

	public void setSumTaxAmount(BigDecimal sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}

}
