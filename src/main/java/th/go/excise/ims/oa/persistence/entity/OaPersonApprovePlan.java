
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
@Table(name = "OA_PERSON_APPROVE_PLAN")
public class OaPersonApprovePlan
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_PERSON_APPROVE_PLAN_GEN")
    @SequenceGenerator(name = "OA_PERSON_APPROVE_PLAN_GEN", sequenceName = "OA_PERSON_APPROVE_PLAN_SEQ", allocationSize = 1)
    @Column(name = "OA_PERSON_APPROVE_PLAN_ID")
    private BigDecimal oaPersonApprovePlanId;
    @Column(name = "OA_PERSON_ID")
    private BigDecimal oaPersonId;
    @Column(name = "OA_PALN_ID")
    private BigDecimal oaPalnId;
    @Column(name = "USER_ID")
    private String userId;

    public BigDecimal getOaPersonApprovePlanId() {
        return oaPersonApprovePlanId;
    }

    public void setOaPersonApprovePlanId(BigDecimal oaPersonApprovePlanId) {
        this.oaPersonApprovePlanId = oaPersonApprovePlanId;
    }

    public BigDecimal getOaPersonId() {
        return oaPersonId;
    }

    public void setOaPersonId(BigDecimal oaPersonId) {
        this.oaPersonId = oaPersonId;
    }

    public BigDecimal getOaPalnId() {
        return oaPalnId;
    }

    public void setOaPalnId(BigDecimal oaPalnId) {
        this.oaPalnId = oaPalnId;
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
