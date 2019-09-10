package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_MAS_COND_SUB_NO_AUDIT")
public class TaMasCondSubNoAudit extends BaseEntity {

    private static final long serialVersionUID = 6302647090149581141L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_SUB_NO_AUDIT_GEN")
    @SequenceGenerator(name = "TA_MAS_COND_SUB_NO_AUDIT_GEN", sequenceName = "TA_MAS_COND_SUB_NO_AUDIT_SEQ", allocationSize = 1)
    @Column(name = "MAS_COND_SUB_NO_AUDIT_ID")
    private Long masCondSubNoAuditId;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "NO_TAX_AUDIT_YEAR_NUM")
    private Integer noTaxAuditYearNum;
    @Column(name = "OFFICE_CODE")
    private String officeCode;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Long getMasCondSubNoAuditId() {
        return masCondSubNoAuditId;
    }

    public void setMasCondSubNoAuditId(Long masCondSubNoAuditId) {
        this.masCondSubNoAuditId = masCondSubNoAuditId;
    }

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public Integer getNoTaxAuditYearNum() {
        return noTaxAuditYearNum;
    }

    public void setNoTaxAuditYearNum(Integer noTaxAuditYearNum) {
        this.noTaxAuditYearNum = noTaxAuditYearNum;
    }

}
