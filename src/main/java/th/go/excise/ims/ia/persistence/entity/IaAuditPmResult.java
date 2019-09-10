
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_AUDIT_PM_RESULT")
public class IaAuditPmResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1658901086826157525L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PM_RESULT_GEN")
	@SequenceGenerator(name = "IA_AUDIT_PM_RESULT_GEN", sequenceName = "IA_AUDIT_PM_RESULT_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_PMRESULT_SEQ")
	private Long auditPmresultSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "AUDIT_DATE_FROM")
	private Date auditDateFrom;
	@Column(name = "AUDIT_DATE_TO")
	private Date auditDateTo;
	@Column(name = "AUDIT_PMRESULT_NO")
	private String auditPmresultNo;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "AUDIT_PMASSESS_NO")
	private String auditPmassessNo;
	@Column(name = "AUDIT_PMQT_NO")
	private String auditPmqtNo;
	@Column(name = "AUDIT_PY1_NO")
	private String auditPy1No;
	@Column(name = "AUDIT_PY2_NO")
	private String auditPy2No;
	@Column(name = "AUDIT_PMCOMMIT_NO")
	private String auditPmcommitNo;
	@Column(name = "DEP_AUDITING_SUGGESTION")
	private String depAuditingSuggestion;
	@Column(name = "AUDIT_SUMMARY")
	private String auditSummary;
	@Column(name = "AUDIT_SUGGESTION")
	private String auditSuggestion;
	@Column(name = "PERSON_AUDITY")
	private String personAudity;
	@Column(name = "PERSON_AUDITY_POSITION")
	private String personAudityPosition;
	@Column(name = "AUDITER1")
	private String auditer1;
	@Column(name = "AUDITER1_AUDITY_POSITION")
	private String auditer1AudityPosition;
	@Column(name = "AUDITER2")
	private String auditer2;
	@Column(name = "AUDITER2_AUDITY_POSITION")
	private String auditer2AudityPosition;

	public Long getAuditPmresultSeq() {
		return auditPmresultSeq;
	}

	public void setAuditPmresultSeq(Long auditPmresultSeq) {
		this.auditPmresultSeq = auditPmresultSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Date getAuditDateFrom() {
		return auditDateFrom;
	}

	public void setAuditDateFrom(Date auditDateFrom) {
		this.auditDateFrom = auditDateFrom;
	}

	public Date getAuditDateTo() {
		return auditDateTo;
	}

	public void setAuditDateTo(Date auditDateTo) {
		this.auditDateTo = auditDateTo;
	}

	public String getAuditPmresultNo() {
		return auditPmresultNo;
	}

	public void setAuditPmresultNo(String auditPmresultNo) {
		this.auditPmresultNo = auditPmresultNo;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getAuditPmassessNo() {
		return auditPmassessNo;
	}

	public void setAuditPmassessNo(String auditPmassessNo) {
		this.auditPmassessNo = auditPmassessNo;
	}

	public String getAuditPmqtNo() {
		return auditPmqtNo;
	}

	public void setAuditPmqtNo(String auditPmqtNo) {
		this.auditPmqtNo = auditPmqtNo;
	}

	public String getAuditPy1No() {
		return auditPy1No;
	}

	public void setAuditPy1No(String auditPy1No) {
		this.auditPy1No = auditPy1No;
	}

	public String getAuditPy2No() {
		return auditPy2No;
	}

	public void setAuditPy2No(String auditPy2No) {
		this.auditPy2No = auditPy2No;
	}

	public String getAuditPmcommitNo() {
		return auditPmcommitNo;
	}

	public void setAuditPmcommitNo(String auditPmcommitNo) {
		this.auditPmcommitNo = auditPmcommitNo;
	}

	public String getDepAuditingSuggestion() {
		return depAuditingSuggestion;
	}

	public void setDepAuditingSuggestion(String depAuditingSuggestion) {
		this.depAuditingSuggestion = depAuditingSuggestion;
	}

	public String getAuditSummary() {
		return auditSummary;
	}

	public void setAuditSummary(String auditSummary) {
		this.auditSummary = auditSummary;
	}

	public String getAuditSuggestion() {
		return auditSuggestion;
	}

	public void setAuditSuggestion(String auditSuggestion) {
		this.auditSuggestion = auditSuggestion;
	}

	public String getPersonAudity() {
		return personAudity;
	}

	public void setPersonAudity(String personAudity) {
		this.personAudity = personAudity;
	}

	public String getPersonAudityPosition() {
		return personAudityPosition;
	}

	public void setPersonAudityPosition(String personAudityPosition) {
		this.personAudityPosition = personAudityPosition;
	}

	public String getAuditer1() {
		return auditer1;
	}

	public void setAuditer1(String auditer1) {
		this.auditer1 = auditer1;
	}

	public String getAuditer1AudityPosition() {
		return auditer1AudityPosition;
	}

	public void setAuditer1AudityPosition(String auditer1AudityPosition) {
		this.auditer1AudityPosition = auditer1AudityPosition;
	}

	public String getAuditer2() {
		return auditer2;
	}

	public void setAuditer2(String auditer2) {
		this.auditer2 = auditer2;
	}

	public String getAuditer2AudityPosition() {
		return auditer2AudityPosition;
	}

	public void setAuditer2AudityPosition(String auditer2AudityPosition) {
		this.auditer2AudityPosition = auditer2AudityPosition;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
