package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDisb;

public class Int0610Vo {
	private List<Int0610TabVo> tab;
	private String officeCode;
	private String incMonthFrom;
	private String incYearFrom;
	private String incMonthTo;
	private String incYearTo;

	/* exciseOrgDisb */
	private ExciseOrgDisb exciseOrgDisb;

	public List<Int0610TabVo> getTab() {
		return tab;
	}

	public void setTab(List<Int0610TabVo> tab) {
		this.tab = tab;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getIncMonthFrom() {
		return incMonthFrom;
	}

	public void setIncMonthFrom(String incMonthFrom) {
		this.incMonthFrom = incMonthFrom;
	}

	public String getIncYearFrom() {
		return incYearFrom;
	}

	public void setIncYearFrom(String incYearFrom) {
		this.incYearFrom = incYearFrom;
	}

	public String getIncMonthTo() {
		return incMonthTo;
	}

	public void setIncMonthTo(String incMonthTo) {
		this.incMonthTo = incMonthTo;
	}

	public String getIncYearTo() {
		return incYearTo;
	}

	public void setIncYearTo(String incYearTo) {
		this.incYearTo = incYearTo;
	}

	public ExciseOrgDisb getExciseOrgDisb() {
		return exciseOrgDisb;
	}

	public void setExciseOrgDisb(ExciseOrgDisb exciseOrgDisb) {
		this.exciseOrgDisb = exciseOrgDisb;
	}

}