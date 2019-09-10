package th.go.excise.ims.oa.vo;

import java.math.BigDecimal;

public class Oa0703TaxpayVo {
	private String newRegId;
	private String taxYear;
	private BigDecimal sumTaxAmount;

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(String taxYear) {
		this.taxYear = taxYear;
	}

	public BigDecimal getSumTaxAmount() {
		return sumTaxAmount;
	}

	public void setSumTaxAmount(BigDecimal sumTaxAmount) {
		this.sumTaxAmount = sumTaxAmount;
	}

}
