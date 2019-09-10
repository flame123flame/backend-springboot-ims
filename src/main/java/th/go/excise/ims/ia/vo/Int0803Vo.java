package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int0803Vo {
	private List<Int0803TableVo> experimentalBudgetList;
	private List<Int0803Footer> sumExperimentalBudget;
	private List<Int0803TableVo> depositsReportList;
	private List<Int0803Footer> sumDepositsReport;
	private List<Int0803Footer> difference;

	public List<Int0803TableVo> getExperimentalBudgetList() {
		return experimentalBudgetList;
	}

	public void setExperimentalBudgetList(List<Int0803TableVo> experimentalBudgetList) {
		this.experimentalBudgetList = experimentalBudgetList;
	}

	public List<Int0803Footer> getSumExperimentalBudget() {
		return sumExperimentalBudget;
	}

	public void setSumExperimentalBudget(List<Int0803Footer> sumExperimentalBudget) {
		this.sumExperimentalBudget = sumExperimentalBudget;
	}

	public List<Int0803TableVo> getDepositsReportList() {
		return depositsReportList;
	}

	public void setDepositsReportList(List<Int0803TableVo> depositsReportList) {
		this.depositsReportList = depositsReportList;
	}

	public List<Int0803Footer> getSumDepositsReport() {
		return sumDepositsReport;
	}

	public void setSumDepositsReport(List<Int0803Footer> sumDepositsReport) {
		this.sumDepositsReport = sumDepositsReport;
	}

	public List<Int0803Footer> getDifference() {
		return difference;
	}

	public void setDifference(List<Int0803Footer> difference) {
		this.difference = difference;
	}

}