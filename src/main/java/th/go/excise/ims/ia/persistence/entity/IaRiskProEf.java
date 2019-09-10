
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_RISK_PRO_EF")
public class IaRiskProEf
    extends BaseEntity
{
	private static final long serialVersionUID = 5665016835222277664L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_PRO_EF_GEN")
    @SequenceGenerator(name = "IA_RISK_PRO_EF_GEN", sequenceName = "IA_RISK_PRO_EF_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PROJECT_ID")
    private String projectId;
    @Column(name = "PROJECT_YEAR")
    private String projectYear;
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "PROJECT_TYPE_NAME")
    private String projectTypeName;
    @Column(name = "OWNER_OFFICE_ID")
    private String ownerOfficeId;
    @Column(name = "OWNER_OFFICE_NAME")
    private String ownerOfficeName;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getOwnerOfficeId() {
        return ownerOfficeId;
    }

    public void setOwnerOfficeId(String ownerOfficeId) {
        this.ownerOfficeId = ownerOfficeId;
    }

    public String getOwnerOfficeName() {
        return ownerOfficeName;
    }

    public void setOwnerOfficeName(String ownerOfficeName) {
        this.ownerOfficeName = ownerOfficeName;
    }

}
