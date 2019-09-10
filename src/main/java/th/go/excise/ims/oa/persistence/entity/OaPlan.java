
package th.go.excise.ims.oa.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "OA_PLAN")
public class OaPlan
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PLAN_GEN")
    @SequenceGenerator(name = "OA_PLAN_GEN", sequenceName = "OA_PLAN_SEQ", allocationSize = 1)
    @Column(name = "OA_PLAN_ID")
    private BigDecimal oaPlanId;
    @Column(name = "OA_LICENSE_PLAN_ID")
    private BigDecimal oaLicensePlanId;
    @Column(name = "OA_PERSON_AUDIT_PLAN_ID")
    private BigDecimal oaPersonAuditPlanId;
    @Column(name = "OA_PERSON_APPROV_PLAN_ID")
    private BigDecimal oaPersonApprovPlanId;
    @Column(name = "AUDIT_START")
    private Date auditStart;
    @Column(name = "AUDIT_END")
    private Date auditEnd;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "FISCOL_YEAR")
    private String fiscolYear;
    @Column(name = "REMARK")
    private String remark;

    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getOaPlanId() {
        return oaPlanId;
    }

    public void setOaPlanId(BigDecimal oaPlanId) {
        this.oaPlanId = oaPlanId;
    }

    public BigDecimal getOaLicensePlanId() {
        return oaLicensePlanId;
    }

    public void setOaLicensePlanId(BigDecimal oaLicensePlanId) {
        this.oaLicensePlanId = oaLicensePlanId;
    }

    public BigDecimal getOaPersonAuditPlanId() {
        return oaPersonAuditPlanId;
    }

    public void setOaPersonAuditPlanId(BigDecimal oaPersonAuditPlanId) {
        this.oaPersonAuditPlanId = oaPersonAuditPlanId;
    }

    public BigDecimal getOaPersonApprovPlanId() {
        return oaPersonApprovPlanId;
    }

    public void setOaPersonApprovPlanId(BigDecimal oaPersonApprovPlanId) {
        this.oaPersonApprovPlanId = oaPersonApprovPlanId;
    }

    public Date getAuditStart() {
        return auditStart;
    }

    public void setAuditStart(Date auditStart) {
        this.auditStart = auditStart;
    }

    public Date getAuditEnd() {
        return auditEnd;
    }

    public void setAuditEnd(Date auditEnd) {
        this.auditEnd = auditEnd;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFiscolYear() {
        return fiscolYear;
    }

    public void setFiscolYear(String fiscolYear) {
        this.fiscolYear = fiscolYear;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
