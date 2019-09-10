
package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_ORG_DEPACC")
public class ExciseOrgDepacc
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -794709423740515325L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_ORG_DEPACC_GEN")
    @SequenceGenerator(name = "EXCISE_ORG_DEPACC_GEN", sequenceName = "EXCISE_ORG_DEPACC_SEQ", allocationSize = 1)
    @Column(name = "ORG_DEPACC_ID")
    private Long orgDepaccId;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "GF_OWNER_DEPOSIT")
    private String gfOwnerDeposit;
    @Column(name = "GF_DEPOSIT_CODE")
    private String gfDepositCode;

    public Long getOrgDepaccId() {
        return orgDepaccId;
    }

    public void setOrgDepaccId(Long orgDepaccId) {
        this.orgDepaccId = orgDepaccId;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getGfOwnerDeposit() {
        return gfOwnerDeposit;
    }

    public void setGfOwnerDeposit(String gfOwnerDeposit) {
        this.gfOwnerDeposit = gfOwnerDeposit;
    }

    public String getGfDepositCode() {
        return gfDepositCode;
    }

    public void setGfDepositCode(String gfDepositCode) {
        this.gfDepositCode = gfDepositCode;
    }

}
