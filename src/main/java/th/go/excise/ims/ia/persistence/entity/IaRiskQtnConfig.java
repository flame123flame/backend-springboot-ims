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
@Table(name = "IA_RISK_QTN_CONFIG")
public class IaRiskQtnConfig extends BaseEntity {

	private static final long serialVersionUID = -1185997833610340235L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_QTN_CONFIG_GEN")
	@SequenceGenerator(name = "IA_RISK_QTN_CONFIG_GEN", sequenceName = "IA_RISK_QTN_CONFIG_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_QTN_HDR")
	private BigDecimal idQtnHdr;
	@Column(name = "LOW")
	private String low;
	@Column(name = "LOW_START")
	private BigDecimal lowStart;
	@Column(name = "LOW_END")
	private BigDecimal lowEnd;
	@Column(name = "LOW_RATING")
	private BigDecimal lowRating;
	@Column(name = "LOW_COLOR")
	private String lowColor;
	@Column(name = "LOW_CONDITION")
	private String lowCondition;
	@Column(name = "MEDIUM")
	private String medium;
	@Column(name = "MEDIUM_START")
	private BigDecimal mediumStart;
	@Column(name = "MEDIUM_END")
	private BigDecimal mediumEnd;
	@Column(name = "MEDIUM_RATING")
	private BigDecimal mediumRating;
	@Column(name = "MEDIUM_COLOR")
	private String mediumColor;
	@Column(name = "MEDIUM_CONDITION")
	private String mediumCondition;
	@Column(name = "HIGH")
	private String high;
	@Column(name = "HIGH_START")
	private BigDecimal highStart;
	@Column(name = "HIGH_END")
	private BigDecimal highEnd;
	@Column(name = "HIGH_RATING")
	private BigDecimal highRating;
	@Column(name = "HIGH_COLOR")
	private String highColor;
	@Column(name = "HIGH_CONDITION")
	private String highCondition;
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

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdQtnHdr() {
		return idQtnHdr;
	}

	public void setIdQtnHdr(BigDecimal idQtnHdr) {
		this.idQtnHdr = idQtnHdr;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public BigDecimal getLowStart() {
		return lowStart;
	}

	public void setLowStart(BigDecimal lowStart) {
		this.lowStart = lowStart;
	}

	public BigDecimal getLowEnd() {
		return lowEnd;
	}

	public void setLowEnd(BigDecimal lowEnd) {
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

	public BigDecimal getMediumStart() {
		return mediumStart;
	}

	public void setMediumStart(BigDecimal mediumStart) {
		this.mediumStart = mediumStart;
	}

	public BigDecimal getMediumEnd() {
		return mediumEnd;
	}

	public void setMediumEnd(BigDecimal mediumEnd) {
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

	public BigDecimal getHighStart() {
		return highStart;
	}

	public void setHighStart(BigDecimal highStart) {
		this.highStart = highStart;
	}

	public BigDecimal getHighEnd() {
		return highEnd;
	}

	public void setHighEnd(BigDecimal highEnd) {
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

}
