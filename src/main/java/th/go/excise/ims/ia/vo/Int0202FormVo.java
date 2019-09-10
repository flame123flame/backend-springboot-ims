package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0202FormVo extends DataTableRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1024769386710020511L;
	private String budgetYear;
	private String startDate;
	private String endDate;
	private String createdBy;
	private String officeCode;
	private String qtnName;
	
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
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getQtnName() {
		return qtnName;
	}
	public void setQtnName(String qtnName) {
		this.qtnName = qtnName;
	}
}
