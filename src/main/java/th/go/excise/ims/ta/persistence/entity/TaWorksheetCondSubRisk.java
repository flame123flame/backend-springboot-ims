package th.go.excise.ims.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_WORKSHEET_COND_SUB_RISK")
public class TaWorksheetCondSubRisk extends BaseEntity {

	private static final long serialVersionUID = 2627276247839170156L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_SUB_RISK_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_SUB_RISK_GEN", sequenceName = "TA_WORKSHEET_COND_SUB_RISK_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_SUB_RISK_ID")
	private Long worksheetCondSubRiskId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "DUTY_CODE")
	private String dutyCode;
	@Column(name = "RISK_LEVEL")
	private String riskLevel;

	public Long getWorksheetCondSubRiskId() {
		return worksheetCondSubRiskId;
	}

	public void setWorksheetCondSubRiskId(Long worksheetCondSubRiskId) {
		this.worksheetCondSubRiskId = worksheetCondSubRiskId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getDutyCode() {
		return dutyCode;
	}

	public void setDutyCode(String dutyCode) {
		this.dutyCode = dutyCode;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

}
