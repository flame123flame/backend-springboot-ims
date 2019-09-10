package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_WORKSHEET_COND_SUB_CAP")
public class TaWorksheetCondSubCapital extends BaseEntity {

	private static final long serialVersionUID = 6113664532857101577L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_SUB_CAP_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_SUB_CAP_GEN", sequenceName = "TA_WORKSHEET_COND_SUB_CAP_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_SUB_CAP_ID")
	private Long worksheetCondSubCapId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "DUTY_CODE")
	private String dutyCode;
	@Column(name = "HUGE_CAPITAL_AMOUNT")
	private BigDecimal hugeCapitalAmount;
	@Column(name = "LARGE_CAPITAL_AMOUNT")
	private BigDecimal largeCapitalAmount;
	@Column(name = "MEDIUM_CAPITAL_AMOUNT")
	private BigDecimal mediumCapitalAmount;
	@Column(name = "SMALL_CAPITAL_AMOUNT")
	private BigDecimal smallCapitalAmount;

	public Long getWorksheetCondSubCapId() {
		return worksheetCondSubCapId;
	}

	public void setWorksheetCondSubCapId(Long worksheetCondSubCapId) {
		this.worksheetCondSubCapId = worksheetCondSubCapId;
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

	public BigDecimal getHugeCapitalAmount() {
		return hugeCapitalAmount;
	}

	public void setHugeCapitalAmount(BigDecimal hugeCapitalAmount) {
		this.hugeCapitalAmount = hugeCapitalAmount;
	}

	public BigDecimal getLargeCapitalAmount() {
		return largeCapitalAmount;
	}

	public void setLargeCapitalAmount(BigDecimal largeCapitalAmount) {
		this.largeCapitalAmount = largeCapitalAmount;
	}

	public BigDecimal getMediumCapitalAmount() {
		return mediumCapitalAmount;
	}

	public void setMediumCapitalAmount(BigDecimal mediumCapitalAmount) {
		this.mediumCapitalAmount = mediumCapitalAmount;
	}

	public BigDecimal getSmallCapitalAmount() {
		return smallCapitalAmount;
	}

	public void setSmallCapitalAmount(BigDecimal smallCapitalAmount) {
		this.smallCapitalAmount = smallCapitalAmount;
	}

}
