package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0903FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8616942994981862758L;
	private String budgetType;
	private String endDate;
	private String offcode;
	private String startDate;

	public String getBudgetType() {
		return budgetType;
	}

	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
