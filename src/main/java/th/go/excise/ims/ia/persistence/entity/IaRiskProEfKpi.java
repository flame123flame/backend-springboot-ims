
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
@Table(name = "IA_RISK_PRO_EF_KPI")
public class IaRiskProEfKpi
    extends BaseEntity
{

	private static final long serialVersionUID = 1280237892918426498L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_PRO_EF_KPI_GEN")
    @SequenceGenerator(name = "IA_RISK_PRO_EF_KPI_GEN", sequenceName = "IA_RISK_PRO_EF_KPI_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PE_ID")
    private BigDecimal peId;
    @Column(name = "PROJECT_ID")
    private String projectId;
    @Column(name = "TS_ID")
    private BigDecimal tsId;
    @Column(name = "TASK_ID")
    private String taskId;
    @Column(name = "KPI_ID")
    private String kpiId;
    @Column(name = "KPI_CODE")
    private String kpiCode;
    @Column(name = "KPI_NAME")
    private String kpiName;
    @Column(name = "KPI_ACTIVITY_CODE")
    private String kpiActivityCode;
    @Column(name = "KPI_ACTIVITY_NAME")
    private String kpiActivityName;
    @Column(name = "KPI_TARGET_AMOUNT")
    private BigDecimal kpiTargetAmount;
    @Column(name = "KPI_EXPENSE_ACTUAL_AMOUNT")
    private BigDecimal kpiExpenseActualAmount;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getPeId() {
        return peId;
    }

    public void setPeId(BigDecimal peId) {
        this.peId = peId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getTsId() {
        return tsId;
    }

    public void setTsId(BigDecimal tsId) {
        this.tsId = tsId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getKpiId() {
        return kpiId;
    }

    public void setKpiId(String kpiId) {
        this.kpiId = kpiId;
    }

    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getKpiActivityCode() {
        return kpiActivityCode;
    }

    public void setKpiActivityCode(String kpiActivityCode) {
        this.kpiActivityCode = kpiActivityCode;
    }

    public String getKpiActivityName() {
        return kpiActivityName;
    }

    public void setKpiActivityName(String kpiActivityName) {
        this.kpiActivityName = kpiActivityName;
    }

    public BigDecimal getKpiTargetAmount() {
        return kpiTargetAmount;
    }

    public void setKpiTargetAmount(BigDecimal kpiTargetAmount) {
        this.kpiTargetAmount = kpiTargetAmount;
    }

    public BigDecimal getKpiExpenseActualAmount() {
        return kpiExpenseActualAmount;
    }

    public void setKpiExpenseActualAmount(BigDecimal kpiExpenseActualAmount) {
        this.kpiExpenseActualAmount = kpiExpenseActualAmount;
    }

}
