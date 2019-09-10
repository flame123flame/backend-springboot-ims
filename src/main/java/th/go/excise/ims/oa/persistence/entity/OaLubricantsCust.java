
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
@Table(name = "OA_LUBRICANTS_CUST")
public class OaLubricantsCust
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_LUBRICANTS_CUST_GEN")
    @SequenceGenerator(name = "OA_LUBRICANTS_CUST_GEN", sequenceName = "OA_LUBRICANTS_CUST_SEQ", allocationSize = 1)
    @Column(name = "OA_LUBRICANTS_CUST_ID")
    private BigDecimal oaLubricantsCustId;
    @Column(name = "OA_LUBRICANTS_ID")
    private BigDecimal oaLubricantsId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "MOBILE")
    private String mobile;

    public BigDecimal getOaLubricantsCustId() {
        return oaLubricantsCustId;
    }

    public void setOaLubricantsCustId(BigDecimal oaLubricantsCustId) {
        this.oaLubricantsCustId = oaLubricantsCustId;
    }

    public BigDecimal getOaLubricantsId() {
        return oaLubricantsId;
    }

    public void setOaLubricantsId(BigDecimal oaLubricantsId) {
        this.oaLubricantsId = oaLubricantsId;
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
