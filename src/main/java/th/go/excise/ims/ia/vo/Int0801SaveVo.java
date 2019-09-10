package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int0801SaveVo {
	private String deptDisb;
	private String gfExciseName;
	private String officeCode;
	private String period;
	private String periodYear;
	private String gftbFlag;
	private String gftbConditionText;
	private String gftbCreteriaText;
	private List<Int0801Tabs> tabs;

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPeriodYear() {
		return periodYear;
	}

	public void setPeriodYear(String periodYear) {
		this.periodYear = periodYear;
	}

	public String getGftbFlag() {
		return gftbFlag;
	}

	public void setGftbFlag(String gftbFlag) {
		this.gftbFlag = gftbFlag;
	}

	public String getGftbConditionText() {
		return gftbConditionText;
	}

	public void setGftbConditionText(String gftbConditionText) {
		this.gftbConditionText = gftbConditionText;
	}

	public String getGftbCreteriaText() {
		return gftbCreteriaText;
	}

	public void setGftbCreteriaText(String gftbCreteriaText) {
		this.gftbCreteriaText = gftbCreteriaText;
	}

	public List<Int0801Tabs> getTabs() {
		return tabs;
	}

	public void setTabs(List<Int0801Tabs> tabs) {
		this.tabs = tabs;
	}

	public String getGfExciseName() {
		return gfExciseName;
	}

	public void setGfExciseName(String gfExciseName) {
		this.gfExciseName = gfExciseName;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

}