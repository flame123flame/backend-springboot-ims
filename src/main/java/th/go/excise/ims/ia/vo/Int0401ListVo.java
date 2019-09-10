package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0401ListVo {
	private BigDecimal riskRate;
	private String riskText;
	private String riskCode;

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public BigDecimal getRiskRate() {
		return riskRate;
	}

	public void setRiskRate(BigDecimal riskRate) {
		this.riskRate = riskRate;
	}

	public String getRiskText() {
		return riskText;
	}

	public void setRiskText(String riskText) {
		this.riskText = riskText;
	}
	
}
