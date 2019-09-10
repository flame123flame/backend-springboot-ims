package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisIncomeCompareLastYearVo extends DataTableRequest {

	private static final long serialVersionUID = 915242216213450730L;

	private String taxMonth;
	private String incomeCurrentYearAmt;
	private String incomeLastYear1Amt;
	private String diffIncomeLastYear1Amt;
	private String diffIncomeLastYear1Pnt;
	private String incomeLastYear2Amt;
	private String diffIncomeLastYear2Amt;
	private String diffIncomeLastYear2Pnt;
	private String incomeLastYear3Amt;
	private String diffIncomeLastYear3Amt;
	private String diffIncomeLastYear3Pnt;

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public String getIncomeCurrentYearAmt() {
		return incomeCurrentYearAmt;
	}

	public void setIncomeCurrentYearAmt(String incomeCurrentYearAmt) {
		this.incomeCurrentYearAmt = incomeCurrentYearAmt;
	}

	public String getIncomeLastYear1Amt() {
		return incomeLastYear1Amt;
	}

	public void setIncomeLastYear1Amt(String incomeLastYear1Amt) {
		this.incomeLastYear1Amt = incomeLastYear1Amt;
	}

	public String getDiffIncomeLastYear1Amt() {
		return diffIncomeLastYear1Amt;
	}

	public void setDiffIncomeLastYear1Amt(String diffIncomeLastYear1Amt) {
		this.diffIncomeLastYear1Amt = diffIncomeLastYear1Amt;
	}

	public String getDiffIncomeLastYear1Pnt() {
		return diffIncomeLastYear1Pnt;
	}

	public void setDiffIncomeLastYear1Pnt(String diffIncomeLastYear1Pnt) {
		this.diffIncomeLastYear1Pnt = diffIncomeLastYear1Pnt;
	}

	public String getIncomeLastYear2Amt() {
		return incomeLastYear2Amt;
	}

	public void setIncomeLastYear2Amt(String incomeLastYear2Amt) {
		this.incomeLastYear2Amt = incomeLastYear2Amt;
	}

	public String getDiffIncomeLastYear2Amt() {
		return diffIncomeLastYear2Amt;
	}

	public void setDiffIncomeLastYear2Amt(String diffIncomeLastYear2Amt) {
		this.diffIncomeLastYear2Amt = diffIncomeLastYear2Amt;
	}

	public String getDiffIncomeLastYear2Pnt() {
		return diffIncomeLastYear2Pnt;
	}

	public void setDiffIncomeLastYear2Pnt(String diffIncomeLastYear2Pnt) {
		this.diffIncomeLastYear2Pnt = diffIncomeLastYear2Pnt;
	}

	public String getIncomeLastYear3Amt() {
		return incomeLastYear3Amt;
	}

	public void setIncomeLastYear3Amt(String incomeLastYear3Amt) {
		this.incomeLastYear3Amt = incomeLastYear3Amt;
	}

	public String getDiffIncomeLastYear3Amt() {
		return diffIncomeLastYear3Amt;
	}

	public void setDiffIncomeLastYear3Amt(String diffIncomeLastYear3Amt) {
		this.diffIncomeLastYear3Amt = diffIncomeLastYear3Amt;
	}

	public String getDiffIncomeLastYear3Pnt() {
		return diffIncomeLastYear3Pnt;
	}

	public void setDiffIncomeLastYear3Pnt(String diffIncomeLastYear3Pnt) {
		this.diffIncomeLastYear3Pnt = diffIncomeLastYear3Pnt;
	}

}
