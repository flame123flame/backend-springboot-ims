
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
@Table(name = "OA_ALCOHOL_FERMENT")
public class OaAlcoholFerment
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_FERMENT_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_FERMENT_GEN", sequenceName = "OA_ALCOHOL_FERMENT_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_FERMENT_ID")
    private BigDecimal oaAlcoholFermentId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;
    @Column(name = "TANK_NUMBER")
    private BigDecimal tankNumber;
    @Column(name = "QUANTITY_TANK")
    private BigDecimal quantityTank;
    @Column(name = "NET_QUANTITY")
    private BigDecimal netQuantity;
    @Column(name = "REMARK")
    private String remark;

    public BigDecimal getOaAlcoholFermentId() {
        return oaAlcoholFermentId;
    }

    public void setOaAlcoholFermentId(BigDecimal oaAlcoholFermentId) {
        this.oaAlcoholFermentId = oaAlcoholFermentId;
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

    public BigDecimal getTankNumber() {
        return tankNumber;
    }

    public void setTankNumber(BigDecimal tankNumber) {
        this.tankNumber = tankNumber;
    }

    public BigDecimal getQuantityTank() {
        return quantityTank;
    }

    public void setQuantityTank(BigDecimal quantityTank) {
        this.quantityTank = quantityTank;
    }

    public BigDecimal getNetQuantity() {
        return netQuantity;
    }

    public void setNetQuantity(BigDecimal netQuantity) {
        this.netQuantity = netQuantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
