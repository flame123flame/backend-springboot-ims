package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0203FormVo extends DataTableRequest {

	private static final long serialVersionUID = -7911786230141138301L;

	private String budgetYear;
	private String startDate;
	private String endDate;
	private String createdBy;
	private String nameQtn;

	public String getNameQtn() {
		return nameQtn;
	}

	public void setNameQtn(String nameQtn) {
		this.nameQtn = nameQtn;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
