package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_MAS_COND_SUB_CAPITAL")
public class TaMasCondSubCapital extends BaseEntity {

    private static final long serialVersionUID = -7452436042292157536L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_SUB_CAPITAL_GEN")
    @SequenceGenerator(name = "TA_MAS_COND_SUB_CAPITAL_GEN", sequenceName = "TA_MAS_COND_SUB_CAPITAL_SEQ", allocationSize = 1)
    @Column(name = "MAS_COND_SUB_CAPITAL_ID")
    private Long masCondSubCapitalId;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "DUTY_CODE")
    private String dutyCode;
    @Column(name = "HUGE_CAPITAL_AMOUNT")
    private BigDecimal hugeCapitalAmount;
    @Column(name = "LARGE_CAPITAL_AMOUNT")
    private BigDecimal largeCapitalAmount;
    @Column(name = "MEDIUM_CAPITAL_AMOUNT")
    private BigDecimal mediumCapitalAmount;
    @Column(name = "SMALL_CAPITAL_AMOUNT")
    private BigDecimal smallCapitalAmount;
    @Column(name = "OFFICE_CODE")
    private String officeCode;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public Long getMasCondSubCapitalId() {
        return masCondSubCapitalId;
    }

    public void setMasCondSubCapitalId(Long masCondSubCapitalId) {
        this.masCondSubCapitalId = masCondSubCapitalId;
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

    public BigDecimal getHugeCapitalAmount() {
        return hugeCapitalAmount;
    }

    public void setHugeCapitalAmount(BigDecimal hugeCapitalAmount) {
        this.hugeCapitalAmount = hugeCapitalAmount;
    }

    public BigDecimal getLargeCapitalAmount() {
        return largeCapitalAmount;
    }

    public void setLargeCapitalAmount(BigDecimal largeCapitalAmount) {
        this.largeCapitalAmount = largeCapitalAmount;
    }

    public BigDecimal getMediumCapitalAmount() {
        return mediumCapitalAmount;
    }

    public void setMediumCapitalAmount(BigDecimal mediumCapitalAmount) {
        this.mediumCapitalAmount = mediumCapitalAmount;
    }

    public BigDecimal getSmallCapitalAmount() {
        return smallCapitalAmount;
    }

    public void setSmallCapitalAmount(BigDecimal smallCapitalAmount) {
        this.smallCapitalAmount = smallCapitalAmount;
    }

}
