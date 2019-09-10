
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "IA_AUDIT_INC_GF")
public class IaAuditIncGf
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_GF_GEN")
    @SequenceGenerator(name = "IA_AUDIT_INC_GF_GEN", sequenceName = "IA_AUDIT_INC_GF_SEQ", allocationSize = 1)
    @Column(name = "AUDIT_INC_GF_ID")
    private BigDecimal auditIncGfId;
    @Column(name = "DEPT_DISB")
    private String deptDisb;
    @Column(name = "PERIOD_MONTH")
    private String periodMonth;
    @Column(name = "PERIOD_YEAR")
    private String periodYear;
    @Column(name = "INCOME_CODE")
    private String incomeCode;
    @Column(name = "NET_TAX_AMT")
    private BigDecimal netTaxAmt;
    @Column(name = "GL_ACC_NO")
    private String glAccNo;

    public BigDecimal getAuditIncGfId() {
        return auditIncGfId;
    }

    public void setAuditIncGfId(BigDecimal auditIncGfId) {
        this.auditIncGfId = auditIncGfId;
    }

    public String getDeptDisb() {
        return deptDisb;
    }

    public void setDeptDisb(String deptDisb) {
        this.deptDisb = deptDisb;
    }

    public String getPeriodMonth() {
        return periodMonth;
    }

    public void setPeriodMonth(String periodMonth) {
        this.periodMonth = periodMonth;
    }

    public String getPeriodYear() {
        return periodYear;
    }

    public void setPeriodYear(String periodYear) {
        this.periodYear = periodYear;
    }

    public String getIncomeCode() {
        return incomeCode;
    }

    public void setIncomeCode(String incomeCode) {
        this.incomeCode = incomeCode;
    }

    public BigDecimal getNetTaxAmt() {
        return netTaxAmt;
    }

    public void setNetTaxAmt(BigDecimal netTaxAmt) {
        this.netTaxAmt = netTaxAmt;
    }

    public String getGlAccNo() {
        return glAccNo;
    }

    public void setGlAccNo(String glAccNo) {
        this.glAccNo = glAccNo;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
