package th.go.excise.ims.ta.vo;

import java.time.LocalDate;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class BasicAnalysisTaxFilingVo extends DataTableRequest {

	private static final long serialVersionUID = 9133141236206101098L;

	private String taxMonth;
	private String taxSubmissionDate;
	private String anaTaxSubmissionDate;
	private String resultTaxSubmission;
	// Extra
	private LocalDate taxMonthLocalDate;
	private LocalDate taxSubmissionLocalDate;
	private LocalDate anaTaxSubmissionLocalDate;

	public String getTaxMonth() {
		return taxMonth;
	}

	public void setTaxMonth(String taxMonth) {
		this.taxMonth = taxMonth;
	}

	public String getTaxSubmissionDate() {
		return taxSubmissionDate;
	}

	public void setTaxSubmissionDate(String taxSubmissionDate) {
		this.taxSubmissionDate = taxSubmissionDate;
	}

	public String getAnaTaxSubmissionDate() {
		return anaTaxSubmissionDate;
	}

	public void setAnaTaxSubmissionDate(String anaTaxSubmissionDate) {
		this.anaTaxSubmissionDate = anaTaxSubmissionDate;
	}

	public LocalDate getTaxMonthLocalDate() {
		return taxMonthLocalDate;
	}

	public void setTaxMonthLocalDate(LocalDate taxMonthLocalDate) {
		this.taxMonthLocalDate = taxMonthLocalDate;
	}

	public String getResultTaxSubmission() {
		return resultTaxSubmission;
	}

	public void setResultTaxSubmission(String resultTaxSubmission) {
		this.resultTaxSubmission = resultTaxSubmission;
	}

	public LocalDate getTaxSubmissionLocalDate() {
		return taxSubmissionLocalDate;
	}

	public void setTaxSubmissionLocalDate(LocalDate taxSubmissionLocalDate) {
		this.taxSubmissionLocalDate = taxSubmissionLocalDate;
	}

	public LocalDate getAnaTaxSubmissionLocalDate() {
		return anaTaxSubmissionLocalDate;
	}

	public void setAnaTaxSubmissionLocalDate(LocalDate anaTaxSubmissionLocalDate) {
		this.anaTaxSubmissionLocalDate = anaTaxSubmissionLocalDate;
	}

}
