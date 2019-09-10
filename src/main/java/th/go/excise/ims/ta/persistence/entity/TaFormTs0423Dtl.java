package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_FORM_TS0423_DTL")
public class TaFormTs0423Dtl extends BaseEntity {

	private static final long serialVersionUID = 533720700998188033L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0423_DTL_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0423_DTL_GEN", sequenceName = "TA_FORM_TS0423_DTL_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0423_DTL_ID")
	private Long formTs0423DtlId;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "REC_NO")
	private String recNo;
	@Column(name = "OPERATOR_OFFICE_NAME")
	private String operatorOfficeName;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "REQ_DOC_DATE")
	private Date reqDocDate;
	@Column(name = "INFORM_DOC_DATE")
	private Date informDocDate;
	@Column(name = "AUDIT_REASON")
	private String auditReason;
	@Column(name = "AUDIT_RESULT")
	private String auditResult;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;

	public Long getFormTs0423DtlId() {
		return formTs0423DtlId;
	}

	public void setFormTs0423DtlId(Long formTs0423DtlId) {
		this.formTs0423DtlId = formTs0423DtlId;
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

	public String getOperatorOfficeName() {
		return operatorOfficeName;
	}

	public void setOperatorOfficeName(String operatorOfficeName) {
		this.operatorOfficeName = operatorOfficeName;
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

	public Date getReqDocDate() {
		return reqDocDate;
	}

	public void setReqDocDate(Date reqDocDate) {
		this.reqDocDate = reqDocDate;
	}

	public Date getInformDocDate() {
		return informDocDate;
	}

	public void setInformDocDate(Date informDocDate) {
		this.informDocDate = informDocDate;
	}

	public String getAuditReason() {
		return auditReason;
	}

	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
