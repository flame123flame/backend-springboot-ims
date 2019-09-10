
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
@Table(name = "IA_AUDIT_LICEXP_H")
public class IaAuditLicexpH extends BaseEntity {

	private static final long serialVersionUID = 6026944100840987701L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LICEXP_H_GEN")
	@SequenceGenerator(name = "IA_AUDIT_LICEXP_H_GEN", sequenceName = "IA_AUDIT_LICEXP_H_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_LICEXP_SEQ")
	private Long auditLicexpSeq;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "LICEXP_DATE_FROM")
	private Date licexpDateFrom;
	@Column(name = "LICEXP_DATE_TO")
	private Date licexpDateTo;
	@Column(name = "AUDIT_LICEXP_NO")
	private String auditLicexpNo;
	@Column(name = "AUDIT_FLAG")
	private String auditFlag;
	@Column(name = "CONDITION_TEXT")
	private String conditionText;
	@Column(name = "CRITERIA_TEXT")
	private String criteriaText;

	public Long getAuditLicexpSeq() {
		return auditLicexpSeq;
	}

	public void setAuditLicexpSeq(Long auditLicexpSeq) {
		this.auditLicexpSeq = auditLicexpSeq;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Date getLicexpDateFrom() {
		return licexpDateFrom;
	}

	public void setLicexpDateFrom(Date licexpDateFrom) {
		this.licexpDateFrom = licexpDateFrom;
	}

	public Date getLicexpDateTo() {
		return licexpDateTo;
	}

	public void setLicexpDateTo(Date licexpDateTo) {
		this.licexpDateTo = licexpDateTo;
	}

	public String getAuditLicexpNo() {
		return auditLicexpNo;
	}

	public void setAuditLicexpNo(String auditLicexpNo) {
		this.auditLicexpNo = auditLicexpNo;
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
