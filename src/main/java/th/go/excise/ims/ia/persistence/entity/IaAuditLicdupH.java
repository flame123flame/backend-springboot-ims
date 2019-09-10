
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
@Table(name = "IA_AUDIT_LICDUP_H")
public class IaAuditLicdupH
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5990722632022271026L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LICDUP_H_GEN")
    @SequenceGenerator(name = "IA_AUDIT_LICDUP_H_GEN", sequenceName = "IA_AUDIT_LICDUP_H_SEQ", allocationSize = 1)
    @Column(name = "AUDIT_LICDUP_SEQ")
    private Long auditLicdupSeq;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "LIC_DATE_FROM")
    private Date licDateFrom;
    @Column(name = "LIC_DATE_TO")
    private Date licDateTo;
    @Column(name = "AUDIT_LICDUP_NO")
    private String auditLicdupNo;
    @Column(name = "AUDIT_FLAG")
    private String auditFlag;
    @Column(name = "CONDITION_TEXT")
    private String conditionText;
    @Column(name = "CRITERIA_TEXT")
    private String criteriaText;

    public Long getAuditLicdupSeq() {
        return auditLicdupSeq;
    }

    public void setAuditLicdupSeq(Long auditLicdupSeq) {
        this.auditLicdupSeq = auditLicdupSeq;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Date getLicDateFrom() {
        return licDateFrom;
    }

    public void setLicDateFrom(Date licDateFrom) {
        this.licDateFrom = licDateFrom;
    }

    public Date getLicDateTo() {
        return licDateTo;
    }

    public void setLicDateTo(Date licDateTo) {
        this.licDateTo = licDateTo;
    }

    public String getAuditLicdupNo() {
        return auditLicdupNo;
    }

    public void setAuditLicdupNo(String auditLicdupNo) {
        this.auditLicdupNo = auditLicdupNo;
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
