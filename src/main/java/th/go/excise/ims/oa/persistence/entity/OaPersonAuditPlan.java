
package th.go.excise.ims.oa.persistence.entity;

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
@Table(name = "OA_PERSON_AUDIT_PLAN")
public class OaPersonAuditPlan
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PERSON_AUDIT_PLAN_GEN")
    @SequenceGenerator(name = "OA_PERSON_AUDIT_PLAN_GEN", sequenceName = "OA_PERSON_AUDIT_PLAN_SEQ", allocationSize = 1)
    @Column(name = "OA_PERSON_AUDIT_PLAN_ID")
    private BigDecimal oaPersonAuditPlanId;
    @Column(name = "OA_PERSON_ID")
    private BigDecimal oaPersonId;
    @Column(name = "OA_PLAN_ID")
    private BigDecimal oaPlanId;
    @Column(name = "USER_ID")
    private String userId;

    public BigDecimal getOaPersonAuditPlanId() {
        return oaPersonAuditPlanId;
    }

    public void setOaPersonAuditPlanId(BigDecimal oaPersonAuditPlanId) {
        this.oaPersonAuditPlanId = oaPersonAuditPlanId;
    }

    public BigDecimal getOaPersonId() {
        return oaPersonId;
    }

    public void setOaPersonId(BigDecimal oaPersonId) {
        this.oaPersonId = oaPersonId;
    }

    public BigDecimal getOaPlanId() {
        return oaPlanId;
    }

    public void setOaPlanId(BigDecimal oaPlanId) {
        this.oaPlanId = oaPlanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
