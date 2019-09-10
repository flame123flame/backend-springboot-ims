package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "TA_MAS_COND_MAIN_DTL")
public class TaMasCondMainDtl extends BaseEntity {

	private static final long serialVersionUID = -3675070910474302216L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_MAS_COND_MAIN_DTL_GEN")
	@SequenceGenerator(name = "TA_MAS_COND_MAIN_DTL_GEN", sequenceName = "TA_MAS_COND_MAIN_DTL_SEQ", allocationSize = 1)
	@Column(name = "MAS_COND_MAIN_DTL_ID")
	private Long masCondMainDtlId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "COND_NUMBER")
	private String condNumber;
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

	public Long getMasCondMainDtlId() {
		return masCondMainDtlId;
	}

	public void setMasCondMainDtlId(Long masCondMainDtlId) {
		this.masCondMainDtlId = masCondMainDtlId;
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

	public String getCondNumber() {
		return condNumber;
	}

	public void setCondNumber(String condNumber) {
		this.condNumber = condNumber;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
