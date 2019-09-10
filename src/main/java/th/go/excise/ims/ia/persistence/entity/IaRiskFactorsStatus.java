
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
@Table(name = "IA_RISK_FACTORS_STATUS")
public class IaRiskFactorsStatus
    extends BaseEntity
{

	private static final long serialVersionUID = -4501100247978052817L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_FACTORS_STATUS_GEN")
    @SequenceGenerator(name = "IA_RISK_FACTORS_STATUS_GEN", sequenceName = "IA_RISK_FACTORS_STATUS_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ID_MASTER")
    private BigDecimal idMaster;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "INSPECTION_WORK")
    private BigDecimal inspectionWork;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(BigDecimal idMaster) {
        this.idMaster = idMaster;
    }

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getInspectionWork() {
        return inspectionWork;
    }

    public void setInspectionWork(BigDecimal inspectionWork) {
        this.inspectionWork = inspectionWork;
    }

}
