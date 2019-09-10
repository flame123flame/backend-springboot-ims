package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_FORM_TS01142_DTL")
public class TaFormTs01142Dtl extends BaseEntity {

	private static final long serialVersionUID = 7828954413674269793L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS01142_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS01142_DTL_GEN", sequenceName = "TA_FORM_TS01142_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS01142_DTL_ID")
	private Long formTs01142DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "REC_DATE")
	private Date recDate;
	@Column(name = "DUTY_TYPE_TEXT")
	private String dutyTypeText;
	@Column(name = "VALUE_FROM_AUDIT")
	private BigDecimal valueFromAudit;
	@Column(name = "TAX_RATE")
	private BigDecimal taxRate;
	@Column(name = "AUDIT_TAX_AMT")
	private BigDecimal auditTaxAmt;
	@Column(name = "PAID_TAX_AMT")
	private BigDecimal paidTaxAmt;
	@Column(name = "ADD_TAX_AMT")
	private BigDecimal addTaxAmt;
	@Column(name = "ADD_FINE_AMT")
	private BigDecimal addFineAmt;
	@Column(name = "ADD_EXTRA_AMT")
	private BigDecimal addExtraAmt;
	@Column(name = "ADD_SUM_TAX_AMT")
	private BigDecimal addSumTaxAmt;
	@Column(name = "ADD_MOI_AMT")
	private BigDecimal addMoiAmt;
	@Column(name = "ADD_SUM_ALL_TAX_AMT")
	private BigDecimal addSumAllTaxAmt;
	@Column(name = "ADD_MONTH_NUM")
	private String addMonthNum;

	public Long getFormTs01142DtlId() {
		return formTs01142DtlId;
	}

	public void setFormTs01142DtlId(Long formTs01142DtlId) {
		this.formTs01142DtlId = formTs01142DtlId;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
