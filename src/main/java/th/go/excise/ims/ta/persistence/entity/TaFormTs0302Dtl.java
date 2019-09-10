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
@Table(name = "TA_FORM_TS0302_DTL")
public class TaFormTs0302Dtl extends BaseEntity {

	private static final long serialVersionUID = -8041159692877958730L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0302_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0302_DTL_GEN", sequenceName = "TA_FORM_TS0302_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0302_DTL_ID")
	private Long formTs0302DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "AUDIT_NO")
	private String auditNo;
	@Column(name = "OPERATOR_OFFICE_NAME")
	private String operatorOfficeName;
	@Column(name = "OPERATOR_FULL_NAME")
	private String operatorFullName;
	@Column(name = "REF_DOC_NO")
	private String refDocNo;
	@Column(name = "REF_DOC_DATE")
	private Date refDocDate;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "AUDIT_STATUS")
	private String auditStatus;
	@Column(name = "AUDIT_STATUS_DATE")
	private Date auditStatusDate;
	@Column(name = "RESULT_DOC_NO")
	private String resultDocNo;
	@Column(name = "RESULT_DOC_DATE")
	private Date resultDocDate;
	@Column(name = "RESULT_TAX_AMT")
	private BigDecimal resultTaxAmt;
	@Column(name = "RESULT_FINE_AMT")
	private BigDecimal resultFineAmt;
	@Column(name = "RESULT_EXTRA_AMT")
	private BigDecimal resultExtraAmt;
	@Column(name = "RESULT_MOI_AMT")
	private BigDecimal resultMoiAmt;
	@Column(name = "RESULT_NET_TAX_AMT")
	private BigDecimal resultNetTaxAmt;
	@Column(name = "ASSESSMENT_AMT")
	private BigDecimal assessmentAmt;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_DATE")
	private Date officerDate;
	@Column(name = "OFFICER_COMMENT")
	private String officerComment;

	public Long getFormTs0302DtlId() {
		return formTs0302DtlId;
	}

	public void setFormTs0302DtlId(Long formTs0302DtlId) {
		this.formTs0302DtlId = formTs0302DtlId;
	}

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(String auditNo) {
		this.auditNo = auditNo;
	}

	public String getOperatorOfficeName() {
		return operatorOfficeName;
	}

	public void setOperatorOfficeName(String operatorOfficeName) {
		this.operatorOfficeName = operatorOfficeName;
	}

	public String getOperatorFullName() {
		return operatorFullName;
	}

	public void setOperatorFullName(String operatorFullName) {
		this.operatorFullName = operatorFullName;
	}

	public String getRefDocNo() {
		return refDocNo;
	}

	public void setRefDocNo(String refDocNo) {
		this.refDocNo = refDocNo;
	}

	public Date getRefDocDate() {
		return refDocDate;
	}

	public void setRefDocDate(Date refDocDate) {
		this.refDocDate = refDocDate;
	}

	public Date getAuditDateStart() {
		return auditDateStart;
	}

	public void setAuditDateStart(Date auditDateStart) {
		this.auditDateStart = auditDateStart;
	}

	public Date getAuditDateEnd() {
		return auditDateEnd;
	}

	public void setAuditDateEnd(Date auditDateEnd) {
		this.auditDateEnd = auditDateEnd;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getAuditStatusDate() {
		return auditStatusDate;
	}

	public void setAuditStatusDate(Date auditStatusDate) {
		this.auditStatusDate = auditStatusDate;
	}

	public String getResultDocNo() {
		return resultDocNo;
	}

	public void setResultDocNo(String resultDocNo) {
		this.resultDocNo = resultDocNo;
	}

	public Date getResultDocDate() {
		return resultDocDate;
	}

	public void setResultDocDate(Date resultDocDate) {
		this.resultDocDate = resultDocDate;
	}

	public BigDecimal getResultTaxAmt() {
		return resultTaxAmt;
	}

	public void setResultTaxAmt(BigDecimal resultTaxAmt) {
		this.resultTaxAmt = resultTaxAmt;
	}

	public BigDecimal getResultFineAmt() {
		return resultFineAmt;
	}

	public void setResultFineAmt(BigDecimal resultFineAmt) {
		this.resultFineAmt = resultFineAmt;
	}

	public BigDecimal getResultExtraAmt() {
		return resultExtraAmt;
	}

	public void setResultExtraAmt(BigDecimal resultExtraAmt) {
		this.resultExtraAmt = resultExtraAmt;
	}

	public BigDecimal getResultMoiAmt() {
		return resultMoiAmt;
	}

	public void setResultMoiAmt(BigDecimal resultMoiAmt) {
		this.resultMoiAmt = resultMoiAmt;
	}

	public BigDecimal getResultNetTaxAmt() {
		return resultNetTaxAmt;
	}

	public void setResultNetTaxAmt(BigDecimal resultNetTaxAmt) {
		this.resultNetTaxAmt = resultNetTaxAmt;
	}

	public BigDecimal getAssessmentAmt() {
		return assessmentAmt;
	}

	public void setAssessmentAmt(BigDecimal assessmentAmt) {
		this.assessmentAmt = assessmentAmt;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public Date getOfficerDate() {
		return officerDate;
	}

	public void setOfficerDate(Date officerDate) {
		this.officerDate = officerDate;
	}

	public String getOfficerComment() {
		return officerComment;
	}

	public void setOfficerComment(String officerComment) {
		this.officerComment = officerComment;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
