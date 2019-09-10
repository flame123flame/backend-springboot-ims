
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
@Table(name = "OA_HYDROCARB_CUST")
public class OaHydrocarbCust
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_HYDROCARB_CUST_GEN")
    @SequenceGenerator(name = "OA_HYDROCARB_CUST_GEN", sequenceName = "OA_HYDROCARB_CUST_SEQ", allocationSize = 1)
    @Column(name = "OA_HYDROCARB_CUST_ID")
    private BigDecimal oaHydrocarbCustId;
    @Column(name = "OA_HYDROCARB_ID")
    private BigDecimal oaHydrocarbId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "MOBILE")
    private String mobile;

    public BigDecimal getOaHydrocarbCustId() {
        return oaHydrocarbCustId;
    }

    public void setOaHydrocarbCustId(BigDecimal oaHydrocarbCustId) {
        this.oaHydrocarbCustId = oaHydrocarbCustId;
    }

    public BigDecimal getOaHydrocarbId() {
        return oaHydrocarbId;
    }

    public void setOaHydrocarbId(BigDecimal oaHydrocarbId) {
        this.oaHydrocarbId = oaHydrocarbId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
