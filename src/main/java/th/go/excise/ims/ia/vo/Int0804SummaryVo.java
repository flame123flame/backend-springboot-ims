package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0804SummaryVo {
	private String dateDefault;
	private String gfExciseCode;
	private String sumCarryForward;
	private BigDecimal sumOfMonth;
	private BigDecimal totalMonths;

	public String getDateDefault() {
		return dateDefault;
	}

	public void setDateDefault(String dateDefault) {
		this.dateDefault = dateDefault;
	}

	public String getGfExciseCode() {
		return gfExciseCode;
	}

	public void setGfExciseCode(String gfExciseCode) {
		this.gfExciseCode = gfExciseCode;
	}

	public String getSumCarryForward() {
		return sumCarryForward;
	}

	public void setSumCarryForward(String sumCarryForward) {
		this.sumCarryForward = sumCarryForward;
	}

	public BigDecimal getSumOfMonth() {
		return sumOfMonth;
	}

	public void setSumOfMonth(BigDecimal sumOfMonth) {
		this.sumOfMonth = sumOfMonth;
	}

	public BigDecimal getTotalMonths() {
		return totalMonths;
	}

	public void setTotalMonths(BigDecimal totalMonths) {
		this.totalMonths = totalMonths;
	}

}
