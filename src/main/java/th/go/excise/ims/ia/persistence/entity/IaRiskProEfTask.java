
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
@Table(name = "IA_RISK_PRO_EF_TASK")
public class IaRiskProEfTask extends BaseEntity {
	private static final long serialVersionUID = -5116126237735985745L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_PRO_EF_TASK_GEN")
	@SequenceGenerator(name = "IA_RISK_PRO_EF_TASK_GEN", sequenceName = "IA_RISK_PRO_EF_TASK_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "PE_ID")
	private BigDecimal peId;
	@Column(name = "PROJECT_ID")
	private String projectId;
	@Column(name = "TASK_ID")
	private String taskId;
	@Column(name = "TASK_DESCRIPTION_TEXT")
	private String taskDescriptionText;

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

}
