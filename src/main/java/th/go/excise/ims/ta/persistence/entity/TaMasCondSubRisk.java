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
@Table(name = "TA_MAS_COND_SUB_RISK")
public class TaMasCondSubRisk extends BaseEntity {

    private static final long serialVersionUID = -1616600631095300535L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_SUB_RISK_GEN")
    @SequenceGenerator(name = "TA_MAS_COND_SUB_RISK_GEN", sequenceName = "TA_MAS_COND_SUB_RISK_SEQ", allocationSize = 1)
    @Column(name = "MAS_COND_SUB_RISK_ID")
    private Long masCondSubRiskId;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "DUTY_CODE")
    private String dutyCode;
    @Column(name = "RISK_LEVEL")
    private String riskLevel;
    @Column(name = "OFFICE_CODE")
    private String officeCode;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Long getMasCondSubRiskId() {
        return masCondSubRiskId;
    }

    public void setMasCondSubRiskId(Long masCondSubRiskId) {
        this.masCondSubRiskId = masCondSubRiskId;
    }

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

}
