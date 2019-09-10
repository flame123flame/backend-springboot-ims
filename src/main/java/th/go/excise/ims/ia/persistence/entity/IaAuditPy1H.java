
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
@Table(name = "IA_AUDIT_PY1_H")
public class IaAuditPy1H
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PY1_H_GEN")
    @SequenceGenerator(name = "IA_AUDIT_PY1_H_GEN", sequenceName = "IA_AUDIT_PY1_H_SEQ", allocationSize = 1)
    @Column(name = "IA_AUDIT_PY1_H_ID")
    private Long iaAuditPy1HId;
    @Column(name = "AUDIT_PY1_NO")
    private String auditPy1No;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "BUGGET_YEAR")
    private String buggetYear;
    @Column(name = "OVERALL_RESULES")
    private String overallResules;
    @Column(name = "AUDIT_RESULT")
    private String auditResult;
    @Column(name = "CONDITION_TEXT")
    private String conditionText;
    @Column(name = "CRITERIA_TEXT")
    private String criteriaText;
    
    public Long getIaAuditPy1HId() {
        return iaAuditPy1HId;
    }

    public void setIaAuditPy1HId(Long iaAuditPy1HId) {
        this.iaAuditPy1HId = iaAuditPy1HId;
    }

    public String getAuditPy1No() {
        return auditPy1No;
    }

    public void setAuditPy1No(String auditPy1No) {
        this.auditPy1No = auditPy1No;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getBuggetYear() {
        return buggetYear;
    }

    public void setBuggetYear(String buggetYear) {
        this.buggetYear = buggetYear;
    }

    public String getOverallResules() {
        return overallResules;
    }

    public void setOverallResules(String overallResules) {
        this.overallResules = overallResules;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
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
    
}
