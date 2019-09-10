package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0501Vo {

	private String edPersonSeq;
	private String edLogin;
	private String edPersonName;
	private String edPositionName;
	private BigDecimal allowancesDay;
	private BigDecimal accomFeePackages;

	public String getEdPersonSeq() {
		return edPersonSeq;
	}

	public void setEdPersonSeq(String edPersonSeq) {
		this.edPersonSeq = edPersonSeq;
	}

	public String getEdLogin() {
		return edLogin;
	}

	public void setEdLogin(String edLogin) {
		this.edLogin = edLogin;
	}

	public String getEdPersonName() {
		return edPersonName;
	}

	public void setEdPersonName(String edPersonName) {
		this.edPersonName = edPersonName;
	}

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

	public BigDecimal getAccomFeePackages() {
		return accomFeePackages;
	}

	public void setAccomFeePackages(BigDecimal accomFeePackages) {
		this.accomFeePackages = accomFeePackages;
	}

}
