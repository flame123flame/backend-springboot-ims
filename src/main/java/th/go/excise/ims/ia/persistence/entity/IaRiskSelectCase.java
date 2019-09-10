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
@Table(name = "IA_RISK_SELECT_CASE")
public class IaRiskSelectCase extends BaseEntity {

	private static final long serialVersionUID = 1126425384322194149L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_SELECT_CASE_GEN")
	@SequenceGenerator(name = "IA_RISK_SELECT_CASE_GEN", sequenceName = "IA_RISK_SELECT_CASE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "PROJECT")
	private String project;
	@Column(name = "INSPECTION_WORK")
	private BigDecimal inspectionWork;
	@Column(name = "EXCISE_CODE")
	private String exciseCode;
	@Column(name = "SECTOR")
	private String sector;
	@Column(name = "AREA")
	private String area;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "PROJECT_CODE")
	private String projectCode;
	@Column(name = "SYSTEM_CODE")
	private String systemCode;
	@Column(name = "SYSTEM_NAME")
	private String systemName;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public BigDecimal getInspectionWork() {
		return inspectionWork;
	}

	public void setInspectionWork(BigDecimal inspectionWork) {
		this.inspectionWork = inspectionWork;
	}

	public String getExciseCode() {
		return exciseCode;
	}

	public void setExciseCode(String exciseCode) {
		this.exciseCode = exciseCode;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

}
