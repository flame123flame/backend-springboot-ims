package th.go.excise.ims.ta.vo;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;

public class MasCondSubRiskFormVo {

	private String budgetYear;
	private List<TaMasCondSubRisk> riskList;
	
	public String getBudgetYear() {
		return budgetYear;
	}
	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}
	public List<TaMasCondSubRisk> getRiskList() {
		return riskList;
	}
	public void setRiskList(List<TaMasCondSubRisk> riskList) {
		this.riskList = riskList;
	}
	
}
