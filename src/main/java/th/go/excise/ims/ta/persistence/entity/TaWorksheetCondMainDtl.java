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
@Table(name = "TA_WORKSHEET_COND_MAIN_DTL")
public class TaWorksheetCondMainDtl extends BaseEntity {

	private static final long serialVersionUID = 6018625077163491064L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_MAIN_DTL_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_MAIN_DTL_GEN", sequenceName = "TA_WORKSHEET_COND_MAIN_DTL_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_MAIN_DTL_ID")
	private Long worksheetCondMainDtlId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "COND_GROUP")
	private String condGroup;
	@Column(name = "TAX_FREQ_TYPE")
	private String taxFreqType;
	@Column(name = "TAX_MONTH_START")
	private Integer taxMonthStart;
	@Column(name = "TAX_MONTH_END")
	private Integer taxMonthEnd;
	@Column(name = "RANGE_TYPE_START")
	private String rangeTypeStart;
	@Column(name = "RANGE_START")
	private BigDecimal rangeStart;
	@Column(name = "RANGE_TYPE_END")
	private String rangeTypeEnd;
	@Column(name = "RANGE_END")
	private BigDecimal rangeEnd;
	@Column(name = "RISK_LEVEL")
	private String riskLevel;
	@Column(name = "COND_TYPE")
	private String condType;
	@Column(name = "COND_DTL_DESC")
	private String condDtlDesc;

	public Long getWorksheetCondMainDtlId() {
		return worksheetCondMainDtlId;
	}

	public void setWorksheetCondMainDtlId(Long worksheetCondMainDtlId) {
		this.worksheetCondMainDtlId = worksheetCondMainDtlId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getCondGroup() {
		return condGroup;
	}

	public void setCondGroup(String condGroup) {
		this.condGroup = condGroup;
	}

	public String getTaxFreqType() {
		return taxFreqType;
	}

	public void setTaxFreqType(String taxFreqType) {
		this.taxFreqType = taxFreqType;
	}

	public Integer getTaxMonthStart() {
		return taxMonthStart;
	}

	public void setTaxMonthStart(Integer taxMonthStart) {
		this.taxMonthStart = taxMonthStart;
	}

	public Integer getTaxMonthEnd() {
		return taxMonthEnd;
	}

	public void setTaxMonthEnd(Integer taxMonthEnd) {
		this.taxMonthEnd = taxMonthEnd;
	}

	public String getRangeTypeStart() {
		return rangeTypeStart;
	}

	public void setRangeTypeStart(String rangeTypeStart) {
		this.rangeTypeStart = rangeTypeStart;
	}

	public BigDecimal getRangeStart() {
		return rangeStart;
	}

	public void setRangeStart(BigDecimal rangeStart) {
		this.rangeStart = rangeStart;
	}

	public String getRangeTypeEnd() {
		return rangeTypeEnd;
	}

	public void setRangeTypeEnd(String rangeTypeEnd) {
		this.rangeTypeEnd = rangeTypeEnd;
	}

	public BigDecimal getRangeEnd() {
		return rangeEnd;
	}

	public void setRangeEnd(BigDecimal rangeEnd) {
		this.rangeEnd = rangeEnd;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getCondType() {
		return condType;
	}

	public void setCondType(String condType) {
		this.condType = condType;
	}

	public String getCondDtlDesc() {
		return condDtlDesc;
	}

	public void setCondDtlDesc(String condDtlDesc) {
		this.condDtlDesc = condDtlDesc;
	}

}
