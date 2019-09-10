package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class IaRiskProEfTaskVo {
    private BigDecimal id;
    private BigDecimal peId;
    private String projectId;
    private String taskId;
    private String taskDescriptionText;
    
    private List<IaRiskProEfKpiVo> IaRiskProEfKpiVo;

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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskDescriptionText() {
		return taskDescriptionText;
	}

	public void setTaskDescriptionText(String taskDescriptionText) {
		this.taskDescriptionText = taskDescriptionText;
	}

	public List<IaRiskProEfKpiVo> getIaRiskProEfKpiVo() {
		return IaRiskProEfKpiVo;
	}

	public void setIaRiskProEfKpiVo(List<IaRiskProEfKpiVo> iaRiskProEfKpiVo) {
		IaRiskProEfKpiVo = iaRiskProEfKpiVo;
	}
 
    
}
