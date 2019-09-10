package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TaFormTS01142DtlVo {

	private String formTs01142DtlId;
	private String recNo;
	private Date recDate;
	private String dutyTypeText;
	private BigDecimal valueFromAudit;
	private BigDecimal taxRate;
	private BigDecimal auditTaxAmt;
	private BigDecimal paidTaxAmt;
	private BigDecimal addTaxAmt;
	private BigDecimal addFineAmt;
	private BigDecimal addExtraAmt;
	private BigDecimal addSumTaxAmt;
	private BigDecimal addMoiAmt;
	private BigDecimal addSumAllTaxAmt;
	private String addMonthNum;

	public String getFormTs01142DtlId() {
		return formTs01142DtlId;
	}

	public void setFormTs01142DtlId(String formTs01142DtlId) {
		this.formTs01142DtlId = formTs01142DtlId;
	}

	public String getRecNo() {
		return recNo;
	}

	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}

	public Date getRecDate() {
		return recDate;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	public String getDutyTypeText() {
		return dutyTypeText;
	}

	public void setDutyTypeText(String dutyTypeText) {
		this.dutyTypeText = dutyTypeText;
	}

	public BigDecimal getValueFromAudit() {
		return valueFromAudit;
	}

	public void setValueFromAudit(BigDecimal valueFromAudit) {
		this.valueFromAudit = valueFromAudit;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getAuditTaxAmt() {
		return auditTaxAmt;
	}

	public void setAuditTaxAmt(BigDecimal auditTaxAmt) {
		this.auditTaxAmt = auditTaxAmt;
	}

	public BigDecimal getPaidTaxAmt() {
		return paidTaxAmt;
	}

	public void setPaidTaxAmt(BigDecimal paidTaxAmt) {
		this.paidTaxAmt = paidTaxAmt;
	}

	public BigDecimal getAddTaxAmt() {
		return addTaxAmt;
	}

	public void setAddTaxAmt(BigDecimal addTaxAmt) {
		this.addTaxAmt = addTaxAmt;
	}

	public BigDecimal getAddFineAmt() {
		return addFineAmt;
	}

	public void setAddFineAmt(BigDecimal addFineAmt) {
		this.addFineAmt = addFineAmt;
	}

	public BigDecimal getAddExtraAmt() {
		return addExtraAmt;
	}

	public void setAddExtraAmt(BigDecimal addExtraAmt) {
		this.addExtraAmt = addExtraAmt;
	}

	public BigDecimal getAddSumTaxAmt() {
		return addSumTaxAmt;
	}

	public void setAddSumTaxAmt(BigDecimal addSumTaxAmt) {
		this.addSumTaxAmt = addSumTaxAmt;
	}

	public BigDecimal getAddMoiAmt() {
		return addMoiAmt;
	}

	public void setAddMoiAmt(BigDecimal addMoiAmt) {
		this.addMoiAmt = addMoiAmt;
	}

	public BigDecimal getAddSumAllTaxAmt() {
		return addSumAllTaxAmt;
	}

	public void setAddSumAllTaxAmt(BigDecimal addSumAllTaxAmt) {
		this.addSumAllTaxAmt = addSumAllTaxAmt;
	}

	public String getAddMonthNum() {
		return addMonthNum;
	}

	public void setAddMonthNum(String addMonthNum) {
		this.addMonthNum = addMonthNum;
	}

}
