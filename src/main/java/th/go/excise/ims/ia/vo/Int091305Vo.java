package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int091305Vo {
	
	private String budgetYear;
	private String ubillTypeStr;
	private String ubillType;
	List<Int091305QuarterVo> quarter;

	public String getUbillTypeStr() {
		return ubillTypeStr;
	}

	public void setUbillTypeStr(String ubillTypeStr) {
		this.ubillTypeStr = ubillTypeStr;
	}

	public String getUbillType() {
		return ubillType;
	}

	public void setUbillType(String ubillType) {
		this.ubillType = ubillType;
	}

	public List<Int091305QuarterVo> getQuarter() {
		return quarter;
	}

	public void setQuarter(List<Int091305QuarterVo> quarter) {
		this.quarter = quarter;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

}
