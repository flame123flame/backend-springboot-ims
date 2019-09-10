
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_AUDIT_PMCOMMIT_H")
public class IaAuditPmcommitH extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5090830893989305374L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PMCOMMIT_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_PMCOMMIT_H_GEN", sequenceName = "IA_AUDIT_PMCOMMIT_H_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_PMCOMMIT_ID")
	private Long auditPmcommitId;
	@Column(name = "AUDIT_PMCOMMIT_NO")
	private String auditPmcommitNo;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "URL_LINK")
	private String urlLink;
	@Column(name = "AUDIT_FLAG")
	private String auditFlag;
	@Column(name = "CONDITION_TEXT")
	private String conditionText;
	@Column(name = "CRITERIA_TEXT")
	private String criteriaText;

	public Long getAuditPmcommitId() {
		return auditPmcommitId;
	}

	public void setAuditPmcommitId(Long auditPmcommitId) {
		this.auditPmcommitId = auditPmcommitId;
	}

	public String getAuditPmcommitNo() {
		return auditPmcommitNo;
	}

	public void setAuditPmcommitNo(String auditPmcommitNo) {
		this.auditPmcommitNo = auditPmcommitNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getConditionText() {
		return conditionText;
	}

	public void setConditionText(String conditionText) {
		this.conditionText = conditionText;
	}

	public String getCriteriaText() {
		return criteriaText;
	}

	public void setCriteriaText(String criteriaText) {
		this.criteriaText = criteriaText;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
