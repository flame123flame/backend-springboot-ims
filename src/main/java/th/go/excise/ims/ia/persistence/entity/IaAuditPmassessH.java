
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_PMASSESS_H")
public class IaAuditPmassessH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1397251097158783252L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PMASSESS_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_PMASSESS_H_GEN", sequenceName = "IA_AUDIT_PMASSESS_H_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_PMASSESS_H_ID")
	private Long auditPmassessHId;
	@Column(name = "AUDIT_PMASSESS_NO")
	private String auditPmassessNo;
	@Column(name = "PM_ASSESS_H_SEQ")
	private BigDecimal pmAssessHSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "OFF_NAME")
	private String offName;
	@Column(name = "FORM_YEAR")
	private String formYear;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "FORM_NAME")
	private String formName;
	@Column(name = "FORM_ROUND")
	private String formRound;
	@Column(name = "FORM_STATUS")
	private String formStatus;
	@Column(name = "FORM_STATUS_DESC")
	private String formStatusDesc;
	@Column(name = "SUMMARY")
	private String summary;
	@Column(name = "PROCESS_BY")
	private String processBy;
	@Column(name = "PROCESS_POSITION")
	private String processPosition;
	@Column(name = "PROCESS_DATE")
	private Date processDate;
	@Column(name = "PMA_AUDIT_RESULT")
	private String pmaAuditResult;
	@Column(name = "PMA_AUDIT_EVIDENT")
	private String pmaAuditEvident;
	@Column(name = "PMA_AUDIT_SUGGESTION")
	private String pmaAuditSuggestion;

	public Long getAuditPmassessHId() {
		return auditPmassessHId;
	}

	public void setAuditPmassessHId(Long auditPmassessHId) {
		this.auditPmassessHId = auditPmassessHId;
	}

	public String getAuditPmassessNo() {
		return auditPmassessNo;
	}

	public void setAuditPmassessNo(String auditPmassessNo) {
		this.auditPmassessNo = auditPmassessNo;
	}

	public BigDecimal getPmAssessHSeq() {
		return pmAssessHSeq;
	}

	public void setPmAssessHSeq(BigDecimal pmAssessHSeq) {
		this.pmAssessHSeq = pmAssessHSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getFormYear() {
		return formYear;
	}

	public void setFormYear(String formYear) {
		this.formYear = formYear;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormRound() {
		return formRound;
	}

	public void setFormRound(String formRound) {
		this.formRound = formRound;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormStatusDesc() {
		return formStatusDesc;
	}

	public void setFormStatusDesc(String formStatusDesc) {
		this.formStatusDesc = formStatusDesc;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getProcessBy() {
		return processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public String getProcessPosition() {
		return processPosition;
	}

	public void setProcessPosition(String processPosition) {
		this.processPosition = processPosition;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getPmaAuditResult() {
		return pmaAuditResult;
	}

	public void setPmaAuditResult(String pmaAuditResult) {
		this.pmaAuditResult = pmaAuditResult;
	}

	public String getPmaAuditEvident() {
		return pmaAuditEvident;
	}

	public void setPmaAuditEvident(String pmaAuditEvident) {
		this.pmaAuditEvident = pmaAuditEvident;
	}

	public String getPmaAuditSuggestion() {
		return pmaAuditSuggestion;
	}

	public void setPmaAuditSuggestion(String pmaAuditSuggestion) {
		this.pmaAuditSuggestion = pmaAuditSuggestion;
	}

}
