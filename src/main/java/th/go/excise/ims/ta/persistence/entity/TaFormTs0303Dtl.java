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
@Table(name = "TA_FORM_TS0303_DTL")
public class TaFormTs0303Dtl extends BaseEntity {

	private static final long serialVersionUID = -9197567592991434357L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0303_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0303_DTL_GEN", sequenceName = "TA_FORM_TS0303_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0303_DTL_ID")
	private Long formTs0303DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "OWNER_FULL_NAME")
	private String ownerFullName;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "FACTORY_TYPE_TEXT")
	private String factoryTypeText;
	@Column(name = "REQ_DOC_NO")
	private String reqDocNo;
	@Column(name = "REQ_DOC_DATE")
	private Date reqDocDate;
	@Column(name = "INFORM_DOC_NO")
	private String informDocNo;
	@Column(name = "INFORM_DOC_DATE")
	private Date informDocDate;
	@Column(name = "CALL_DOC_NO")
	private String callDocNo;
	@Column(name = "CALL_DOC_DATE")
	private Date callDocDate;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
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

	public Long getFormTs0303DtlId() {
		return formTs0303DtlId;
	}

	public void setFormTs0303DtlId(Long formTs0303DtlId) {
		this.formTs0303DtlId = formTs0303DtlId;
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

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public String getReqDocNo() {
		return reqDocNo;
	}

	public void setReqDocNo(String reqDocNo) {
		this.reqDocNo = reqDocNo;
	}

	public Date getReqDocDate() {
		return reqDocDate;
	}

	public void setReqDocDate(Date reqDocDate) {
		this.reqDocDate = reqDocDate;
	}

	public String getInformDocNo() {
		return informDocNo;
	}

	public void setInformDocNo(String informDocNo) {
		this.informDocNo = informDocNo;
	}

	public Date getInformDocDate() {
		return informDocDate;
	}

	public void setInformDocDate(Date informDocDate) {
		this.informDocDate = informDocDate;
	}

	public String getCallDocNo() {
		return callDocNo;
	}

	public void setCallDocNo(String callDocNo) {
		this.callDocNo = callDocNo;
	}

	public Date getCallDocDate() {
		return callDocDate;
	}

	public void setCallDocDate(Date callDocDate) {
		this.callDocDate = callDocDate;
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
