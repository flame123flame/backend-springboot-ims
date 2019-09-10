
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_RISK_FACTORS_CONFIG")
public class IaRiskFactorsConfig extends BaseEntity {
	private static final long serialVersionUID = 7188721507455629630L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_FACTORS_CONFIG_GEN")
	@SequenceGenerator(name = "IA_RISK_FACTORS_CONFIG_GEN", sequenceName = "IA_RISK_FACTORS_CONFIG_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_FACTORS")
	private BigDecimal idFactors;
	@Column(name = "FACTORS_LEVEL")
	private BigDecimal factorsLevel;
	@Column(name = "START_DATE")
	private Date startDate;
	@Column(name = "END_DATE")
	private Date endDate;
	@Column(name = "INFO_USED_RISK")
	private String infoUsedRisk;
	@Column(name = "INFO_USED_RISK_DESC")
	private String infoUsedRiskDesc;
	@Column(name = "VERYLOW")
	private String verylow;
	@Column(name = "VERYLOW_START")
	private String verylowStart;
	@Column(name = "VERYLOW_END")
	private String verylowEnd;
	@Column(name = "VERYLOW_RATING")
	private BigDecimal verylowRating;
	@Column(name = "VERYLOW_COLOR")
	private String verylowColor;
	@Column(name = "VERYLOW_CONDITION")
	private String verylowCondition;
	@Column(name = "LOW")
	private String low;
	@Column(name = "LOW_START")
	private String lowStart;
	@Column(name = "LOW_END")
	private String lowEnd;
	@Column(name = "LOW_RATING")
	private BigDecimal lowRating;
	@Column(name = "LOW_COLOR")
	private String lowColor;
	@Column(name = "LOW_CONDITION")
	private String lowCondition;
	@Column(name = "MEDIUM")
	private String medium;
	@Column(name = "MEDIUM_START")
	private String mediumStart;
	@Column(name = "MEDIUM_END")
	private String mediumEnd;
	@Column(name = "MEDIUM_RATING")
	private BigDecimal mediumRating;
	@Column(name = "MEDIUM_COLOR")
	private String mediumColor;
	@Column(name = "MEDIUM_CONDITION")
	private String mediumCondition;
	@Column(name = "HIGH")
	private String high;
	@Column(name = "HIGH_START")
	private String highStart;
	@Column(name = "HIGH_END")
	private String highEnd;
	@Column(name = "HIGH_RATING")
	private BigDecimal highRating;
	@Column(name = "HIGH_COLOR")
	private String highColor;
	@Column(name = "HIGH_CONDITION")
	private String highCondition;
	@Column(name = "VERYHIGH")
	private String veryhigh;
	@Column(name = "VERYHIGH_START")
	private String veryhighStart;
	@Column(name = "VERYHIGH_END")
	private String veryhighEnd;
	@Column(name = "VERYHIGH_RATING")
	private BigDecimal veryhighRating;
	@Column(name = "VERYHIGH_COLOR")
	private String veryhighColor;
	@Column(name = "VERYHIGH_CONDITION")
	private String veryhighCondition;
	@Column(name = "RISK_UNIT")
	private String riskUnit;
	@Column(name = "PERCENT")
	private BigDecimal percent;
	@Column(name = "RISK_INDICATORS")
	private String riskIndicators;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdFactors() {
		return idFactors;
	}

	public void setIdFactors(BigDecimal idFactors) {
		this.idFactors = idFactors;
	}

	public BigDecimal getFactorsLevel() {
		return factorsLevel;
	}

	public void setFactorsLevel(BigDecimal factorsLevel) {
		this.factorsLevel = factorsLevel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getInfoUsedRisk() {
		return infoUsedRisk;
	}

	public void setInfoUsedRisk(String infoUsedRisk) {
		this.infoUsedRisk = infoUsedRisk;
	}

	public String getInfoUsedRiskDesc() {
		return infoUsedRiskDesc;
	}

	public void setInfoUsedRiskDesc(String infoUsedRiskDesc) {
		this.infoUsedRiskDesc = infoUsedRiskDesc;
	}

	public String getVerylow() {
		return verylow;
	}

	public void setVerylow(String verylow) {
		this.verylow = verylow;
	}

	public String getVerylowStart() {
		return verylowStart;
	}

	public void setVerylowStart(String verylowStart) {
		this.verylowStart = verylowStart;
	}

	public String getVerylowEnd() {
		return verylowEnd;
	}

	public void setVerylowEnd(String verylowEnd) {
		this.verylowEnd = verylowEnd;
	}

	public BigDecimal getVerylowRating() {
		return verylowRating;
	}

	public void setVerylowRating(BigDecimal verylowRating) {
		this.verylowRating = verylowRating;
	}

	public String getVerylowColor() {
		return verylowColor;
	}

	public void setVerylowColor(String verylowColor) {
		this.verylowColor = verylowColor;
	}

	public String getVerylowCondition() {
		return verylowCondition;
	}

	public void setVerylowCondition(String verylowCondition) {
		this.verylowCondition = verylowCondition;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getLowStart() {
		return lowStart;
	}

	public void setLowStart(String lowStart) {
		this.lowStart = lowStart;
	}

	public String getLowEnd() {
		return lowEnd;
	}

	public void setLowEnd(String lowEnd) {
		this.lowEnd = lowEnd;
	}

	public BigDecimal getLowRating() {
		return lowRating;
	}

	public void setLowRating(BigDecimal lowRating) {
		this.lowRating = lowRating;
	}

	public String getLowColor() {
		return lowColor;
	}

	public void setLowColor(String lowColor) {
		this.lowColor = lowColor;
	}

	public String getLowCondition() {
		return lowCondition;
	}

	public void setLowCondition(String lowCondition) {
		this.lowCondition = lowCondition;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getMediumStart() {
		return mediumStart;
	}

	public void setMediumStart(String mediumStart) {
		this.mediumStart = mediumStart;
	}

	public String getMediumEnd() {
		return mediumEnd;
	}

	public void setMediumEnd(String mediumEnd) {
		this.mediumEnd = mediumEnd;
	}

	public BigDecimal getMediumRating() {
		return mediumRating;
	}

	public void setMediumRating(BigDecimal mediumRating) {
		this.mediumRating = mediumRating;
	}

	public String getMediumColor() {
		return mediumColor;
	}

	public void setMediumColor(String mediumColor) {
		this.mediumColor = mediumColor;
	}

	public String getMediumCondition() {
		return mediumCondition;
	}

	public void setMediumCondition(String mediumCondition) {
		this.mediumCondition = mediumCondition;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getHighStart() {
		return highStart;
	}

	public void setHighStart(String highStart) {
		this.highStart = highStart;
	}

	public String getHighEnd() {
		return highEnd;
	}

	public void setHighEnd(String highEnd) {
		this.highEnd = highEnd;
	}

	public BigDecimal getHighRating() {
		return highRating;
	}

	public void setHighRating(BigDecimal highRating) {
		this.highRating = highRating;
	}

	public String getHighColor() {
		return highColor;
	}

	public void setHighColor(String highColor) {
		this.highColor = highColor;
	}

	public String getHighCondition() {
		return highCondition;
	}

	public void setHighCondition(String highCondition) {
		this.highCondition = highCondition;
	}

	public String getVeryhigh() {
		return veryhigh;
	}

	public void setVeryhigh(String veryhigh) {
		this.veryhigh = veryhigh;
	}

	public String getVeryhighStart() {
		return veryhighStart;
	}

	public void setVeryhighStart(String veryhighStart) {
		this.veryhighStart = veryhighStart;
	}

	public String getVeryhighEnd() {
		return veryhighEnd;
	}

	public void setVeryhighEnd(String veryhighEnd) {
		this.veryhighEnd = veryhighEnd;
	}

	public BigDecimal getVeryhighRating() {
		return veryhighRating;
	}

	public void setVeryhighRating(BigDecimal veryhighRating) {
		this.veryhighRating = veryhighRating;
	}

	public String getVeryhighColor() {
		return veryhighColor;
	}

	public void setVeryhighColor(String veryhighColor) {
		this.veryhighColor = veryhighColor;
	}

	public String getVeryhighCondition() {
		return veryhighCondition;
	}

	public void setVeryhighCondition(String veryhighCondition) {
		this.veryhighCondition = veryhighCondition;
	}

	public String getRiskUnit() {
		return riskUnit;
	}

	public void setRiskUnit(String riskUnit) {
		this.riskUnit = riskUnit;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public String getRiskIndicators() {
		return riskIndicators;
	}

	public void setRiskIndicators(String riskIndicators) {
		this.riskIndicators = riskIndicators;
	}

}
