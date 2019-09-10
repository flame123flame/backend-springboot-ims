
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
@Table(name = "OA_ACH_CUSTOMER_LICEN_DTL")
public class OaAchCustomerLicenDtl
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ACH_CUSTOMER_LICEN_DTL_GEN")
    @SequenceGenerator(name = "OA_ACH_CUSTOMER_LICEN_DTL_GEN", sequenceName = "OA_ACH_CUSTOMER_LICEN_DTL_SEQ", allocationSize = 1)
    @Column(name = "OA_CUSLICENSE_DTL_ID")
    private BigDecimal oaCuslicenseDtlId;
    @Column(name = "OA_CUSLICENSE_ID")
    private BigDecimal oaCuslicenseId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "LICENSE_NO")
    private String licenseNo;

    public BigDecimal getOaCuslicenseDtlId() {
        return oaCuslicenseDtlId;
    }

    public void setOaCuslicenseDtlId(BigDecimal oaCuslicenseDtlId) {
        this.oaCuslicenseDtlId = oaCuslicenseDtlId;
    }

    public BigDecimal getOaCuslicenseId() {
        return oaCuslicenseId;
    }

    public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
        this.oaCuslicenseId = oaCuslicenseId;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

}
