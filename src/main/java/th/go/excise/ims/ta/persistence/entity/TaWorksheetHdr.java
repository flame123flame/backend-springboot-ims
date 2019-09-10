package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_WORKSHEET_HDR")
public class TaWorksheetHdr extends BaseEntity {

	private static final long serialVersionUID = 5296877673202764295L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_HDR_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_HDR_GEN", sequenceName = "TA_WORKSHEET_HDR_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_HDR_ID")
	private Long worksheetHdrId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "WORKSHEET_STATUS")
	private String worksheetStatus;
	@Column(name = "COND_SUB_CAPITAL_FLAG")
	private String condSubCapitalFlag;
	@Column(name = "COND_SUB_RISK_FLAG")
	private String condSubRiskFlag;
	@Column(name = "COND_SUB_NO_AUDIT_FLAG")
	private String condSubNoAuditFlag;

	public Long getWorksheetHdrId() {
		return worksheetHdrId;
	}

	public void setWorksheetHdrId(Long worksheetHdrId) {
		this.worksheetHdrId = worksheetHdrId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getWorksheetStatus() {
		return worksheetStatus;
	}

	public void setWorksheetStatus(String worksheetStatus) {
		this.worksheetStatus = worksheetStatus;
	}

	public String getCondSubCapitalFlag() {
		return condSubCapitalFlag;
	}

	public void setCondSubCapitalFlag(String condSubCapitalFlag) {
		this.condSubCapitalFlag = condSubCapitalFlag;
	}

	public String getCondSubRiskFlag() {
		return condSubRiskFlag;
	}

	public void setCondSubRiskFlag(String condSubRiskFlag) {
		this.condSubRiskFlag = condSubRiskFlag;
	}

	public String getCondSubNoAuditFlag() {
		return condSubNoAuditFlag;
	}

	public void setCondSubNoAuditFlag(String condSubNoAuditFlag) {
		this.condSubNoAuditFlag = condSubNoAuditFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
