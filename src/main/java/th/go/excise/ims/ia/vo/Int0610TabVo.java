package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int0610TabVo {
	private List<Int0610SumVo> summary;
	private String accNo;
	private String accName;
	private BigDecimal carryForward;
	private BigDecimal difference;

	public List<Int0610SumVo> getSummary() {
		return summary;
	}

	public void setSummary(List<Int0610SumVo> summary) {
		this.summary = summary;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public BigDecimal getDifference() {
		return difference;
	}

	public void setDifference(BigDecimal difference) {
		this.difference = difference;
	}
}
