
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
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "OA_ALCOHOL_DISTIL")
public class OaAlcoholDistil
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_DISTIL_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_DISTIL_GEN", sequenceName = "OA_ALCOHOL_DISTIL_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_DISTIL_ID")
    private BigDecimal oaAlcoholDistilId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;
    @Column(name = "DISTIL_DATE")
    private Date distilDate;
    @Column(name = "DISTIL_DEGREE")
    private BigDecimal distilDegree;
    @Column(name = "DISTIL_VOLUME")
    private BigDecimal distilVolume;
    @Column(name = "REMARK")
    private String remark;

    public BigDecimal getOaAlcoholDistilId() {
        return oaAlcoholDistilId;
    }

    public void setOaAlcoholDistilId(BigDecimal oaAlcoholDistilId) {
        this.oaAlcoholDistilId = oaAlcoholDistilId;
    }

    public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public Date getDistilDate() {
        return distilDate;
    }

    public void setDistilDate(Date distilDate) {
        this.distilDate = distilDate;
    }

    public BigDecimal getDistilDegree() {
        return distilDegree;
    }

    public void setDistilDegree(BigDecimal distilDegree) {
        this.distilDegree = distilDegree;
    }

    public BigDecimal getDistilVolume() {
        return distilVolume;
    }

    public void setDistilVolume(BigDecimal distilVolume) {
        this.distilVolume = distilVolume;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
