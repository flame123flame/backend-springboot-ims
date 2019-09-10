
package th.go.excise.ims.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_WORKING_H")
public class IaAuditWorkingH
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1263714843976585885L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_WORKING_H_GEN")
    @SequenceGenerator(name = "IA_AUDIT_WORKING_H_GEN", sequenceName = "IA_AUDIT_WORKING_H_SEQ", allocationSize = 1)
    @Column(name = "IA_AUDIT_WORKING_H_ID")
    private Long iaAuditWorkingHId;
    @Column(name = "AUDIT_WORKING_NO")
    private String auditWorkingNo;
    @Column(name = "AU_OFFICE_CODE")
    private String auOfficeCode;
    @Column(name = "AU_WORKING_MONTH")
    private String auWorkingMonth;
    @Column(name = "AU_PETITION_NO")
    private String auPetitionNo;
    @Column(name = "AU_DEPT_NAME")
    private String auDeptName;
    @Column(name = "WORKING_AUDIT_FLAG")
    private String workingAuditFlag;
    @Column(name = "WORKING_CONDITION_TEXT")
    private String workingConditionText;
    @Column(name = "WORKING_CRITERIA_TEXT")
    private String workingCriteriaText;

    public Long getIaAuditWorkingHId() {
        return iaAuditWorkingHId;
    }

    public void setIaAuditWorkingHId(Long iaAuditWorkingHId) {
        this.iaAuditWorkingHId = iaAuditWorkingHId;
    }

    public String getAuditWorkingNo() {
        return auditWorkingNo;
    }

    public void setAuditWorkingNo(String auditWorkingNo) {
        this.auditWorkingNo = auditWorkingNo;
    }

    public String getAuOfficeCode() {
        return auOfficeCode;
    }

    public void setAuOfficeCode(String auOfficeCode) {
        this.auOfficeCode = auOfficeCode;
    }

    public String getAuWorkingMonth() {
        return auWorkingMonth;
    }

    public void setAuWorkingMonth(String auWorkingMonth) {
        this.auWorkingMonth = auWorkingMonth;
    }

    public String getAuPetitionNo() {
        return auPetitionNo;
    }

    public void setAuPetitionNo(String auPetitionNo) {
        this.auPetitionNo = auPetitionNo;
    }

    public String getAuDeptName() {
        return auDeptName;
    }

    public void setAuDeptName(String auDeptName) {
        this.auDeptName = auDeptName;
    }

    public String getWorkingAuditFlag() {
        return workingAuditFlag;
    }

    public void setWorkingAuditFlag(String workingAuditFlag) {
        this.workingAuditFlag = workingAuditFlag;
    }

    public String getWorkingConditionText() {
        return workingConditionText;
    }

    public void setWorkingConditionText(String workingConditionText) {
        this.workingConditionText = workingConditionText;
    }

    public String getWorkingCriteriaText() {
        return workingCriteriaText;
    }

    public void setWorkingCriteriaText(String workingCriteriaText) {
        this.workingCriteriaText = workingCriteriaText;
    }

}
