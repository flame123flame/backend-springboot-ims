
package th.go.excise.ims.preferences.persistence.entity;

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
@Table(name = "EXCISE_ORG_DISB")
public class ExciseOrgDisb
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_ORG_DISB_GEN")
    @SequenceGenerator(name = "EXCISE_ORG_DISB_GEN", sequenceName = "EXCISE_ORG_DISB_SEQ", allocationSize = 1)
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "EXCISE_NAME")
    private String exciseName;
    @Column(name = "EXCISE_NAME_ABBR")
    private String exciseNameAbbr;
    @Column(name = "DISBURSE_UNIT")
    private String disburseUnit;

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getExciseName() {
        return exciseName;
    }

    public void setExciseName(String exciseName) {
        this.exciseName = exciseName;
    }

    public String getExciseNameAbbr() {
        return exciseNameAbbr;
    }

    public void setExciseNameAbbr(String exciseNameAbbr) {
        this.exciseNameAbbr = exciseNameAbbr;
    }

    public String getDisburseUnit() {
        return disburseUnit;
    }

    public void setDisburseUnit(String disburseUnit) {
        this.disburseUnit = disburseUnit;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
