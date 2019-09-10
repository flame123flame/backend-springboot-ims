package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class IaRiskProEfVo {

	private BigDecimal id;
	private String projectId;
	private String projectYear;
	private String projectName;
	private String projectTypeName;
	private String ownerOfficeId;
	private String ownerOfficeName;

	private BigDecimal countKpi;
	private BigDecimal averageData;

	private List<IaRiskProEfTaskVo> iaRiskProEfTaskVo;

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

	public List<IaRiskProEfTaskVo> getIaRiskProEfTaskVo() {
		return iaRiskProEfTaskVo;
	}

	public void setIaRiskProEfTaskVo(List<IaRiskProEfTaskVo> iaRiskProEfTaskVo) {
		this.iaRiskProEfTaskVo = iaRiskProEfTaskVo;
	}

	public BigDecimal getCountKpi() {
		return countKpi;
	}

	public void setCountKpi(BigDecimal countKpi) {
		this.countKpi = countKpi;
	}

	public BigDecimal getAverageData() {
		return averageData;
	}

	public void setAverageData(BigDecimal averageData) {
		this.averageData = averageData;
	}

}
