
package th.go.excise.ims.oa.persistence.entity;

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
@Table(name = "OA_ALCOHOL")
public class OaAlcohol
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_GEN", sequenceName = "OA_ALCOHOL_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "OA_PLAN_ID")
    private BigDecimal oaPlanId;
    @Column(name = "LICENSE_NO")
    private String licenseNo;
    @Column(name = "LICENSE_ID")
    private BigDecimal licenseId;

    public BigDecimal getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(BigDecimal licenseId) {
		this.licenseId = licenseId;
	}

	public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public BigDecimal getOaPlanId() {
        return oaPlanId;
    }

    public void setOaPlanId(BigDecimal oaPlanId) {
        this.oaPlanId = oaPlanId;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

}
