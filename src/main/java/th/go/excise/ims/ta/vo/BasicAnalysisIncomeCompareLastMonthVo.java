package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisIncomeCompareLastMonthVo extends DataTableRequest {

	private static final long serialVersionUID = -3604910343019283741L;

	private String taxMonth;
	private String incomeAmt;
	private String diffIncomeAmt;
	private String diffIncomePnt;

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public String getIncomeAmt() {
		return incomeAmt;
	}

	public void setIncomeAmt(String incomeAmt) {
		this.incomeAmt = incomeAmt;
	}

	public String getDiffIncomeAmt() {
		return diffIncomeAmt;
	}

	public void setDiffIncomeAmt(String diffIncomeAmt) {
		this.diffIncomeAmt = diffIncomeAmt;
	}

	public String getDiffIncomePnt() {
		return diffIncomePnt;
	}

	public void setDiffIncomePnt(String diffIncomePnt) {
		this.diffIncomePnt = diffIncomePnt;
	}

}
