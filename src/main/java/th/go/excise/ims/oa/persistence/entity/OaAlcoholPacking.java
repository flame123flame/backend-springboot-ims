
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
@Table(name = "OA_ALCOHOL_PACKING")
public class OaAlcoholPacking
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_PACKING_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_PACKING_GEN", sequenceName = "OA_ALCOHOL_PACKING_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_PACKING_ID")
    private BigDecimal oaAlcoholPackingId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACH_SIZE")
    private BigDecimal achSize;
    @Column(name = "DEGREE_28")
    private BigDecimal degree28;
    @Column(name = "DEGREE_30")
    private BigDecimal degree30;
    @Column(name = "DEGREE_35")
    private BigDecimal degree35;
    @Column(name = "DEGREE_40")
    private BigDecimal degree40;
    @Column(name = "REMARK")
    private String remark;

    public BigDecimal getOaAlcoholPackingId() {
        return oaAlcoholPackingId;
    }

    public void setOaAlcoholPackingId(BigDecimal oaAlcoholPackingId) {
        this.oaAlcoholPackingId = oaAlcoholPackingId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAchSize() {
		return achSize;
	}

	public void setAchSize(BigDecimal achSize) {
		this.achSize = achSize;
	}

	public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public BigDecimal getDegree28() {
		return degree28;
	}

	public void setDegree28(BigDecimal degree28) {
		this.degree28 = degree28;
	}

	public BigDecimal getDegree30() {
		return degree30;
	}

	public void setDegree30(BigDecimal degree30) {
		this.degree30 = degree30;
	}

	public BigDecimal getDegree35() {
		return degree35;
	}

	public void setDegree35(BigDecimal degree35) {
		this.degree35 = degree35;
	}

	public BigDecimal getDegree40() {
		return degree40;
	}

	public void setDegree40(BigDecimal degree40) {
		this.degree40 = degree40;
	}

}
