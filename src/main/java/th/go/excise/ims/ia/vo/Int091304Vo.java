package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int091304Vo {
	private String budgetYear;
	private BigDecimal totalDepartment;
	private List<Int091304Quarter> quarter;

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public List<Int091304Quarter> getQuarter() {
		return quarter;
	}

	public void setQuarter(List<Int091304Quarter> quarter) {
		this.quarter = quarter;
	}

	public BigDecimal getTotalDepartment() {
		return totalDepartment;
	}

	public void setTotalDepartment(BigDecimal totalDepartment) {
		this.totalDepartment = totalDepartment;
	}

}
