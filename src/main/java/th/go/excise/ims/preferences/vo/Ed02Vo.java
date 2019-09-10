package th.go.excise.ims.preferences.vo;

import java.math.BigDecimal;

public class Ed02Vo {

	private BigDecimal edPositionSeq;
	private String edPositionName;
	private BigDecimal allowancesDay;
	private BigDecimal allowancesHalfDay;
	private BigDecimal accomFeeSingle;
	private BigDecimal accomFeeDouble;
	private BigDecimal accomFeePackages;

	

	public String getEdPositionName() {
		return edPositionName;
	}

	public void setEdPositionName(String edPositionName) {
		this.edPositionName = edPositionName;
	}

	public BigDecimal getAllowancesDay() {
		return allowancesDay;
	}

	public void setAllowancesDay(BigDecimal allowancesDay) {
		this.allowancesDay = allowancesDay;
	}

	public BigDecimal getAllowancesHalfDay() {
		return allowancesHalfDay;
	}

	public void setAllowancesHalfDay(BigDecimal allowancesHalfDay) {
		this.allowancesHalfDay = allowancesHalfDay;
	}

	public BigDecimal getAccomFeeSingle() {
		return accomFeeSingle;
	}

	public void setAccomFeeSingle(BigDecimal accomFeeSingle) {
		this.accomFeeSingle = accomFeeSingle;
	}

	public BigDecimal getAccomFeeDouble() {
		return accomFeeDouble;
	}

	public void setAccomFeeDouble(BigDecimal accomFeeDouble) {
		this.accomFeeDouble = accomFeeDouble;
	}

	public BigDecimal getAccomFeePackages() {
		return accomFeePackages;
	}

	public void setAccomFeePackages(BigDecimal accomFeePackages) {
		this.accomFeePackages = accomFeePackages;
	}

	public BigDecimal getEdPositionSeq() {
		return edPositionSeq;
	}

	public void setEdPositionSeq(BigDecimal edPositionSeq) {
		this.edPositionSeq = edPositionSeq;
	}

}
