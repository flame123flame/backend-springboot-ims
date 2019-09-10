package th.go.excise.ims.preferences.persistence.entity;

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
@Table(name = "EXCISE_POSITION")
public class ExcisePosition extends BaseEntity {

	private static final long serialVersionUID = 7382008618718656380L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_POSITION_GEN")
	@SequenceGenerator(name = "EXCISE_POSITION_GEN", sequenceName = "EXCISE_POSITION_SEQ", allocationSize = 1)
	@Column(name = "ED_POSITION_SEQ")
	private Long edPositionSeq;
	@Column(name = "ED_POSITION_NAME")
	private String edPositionName;
	@Column(name = "ALLOWANCES_DAY")
	private BigDecimal allowancesDay;
	@Column(name = "ALLOWANCES_HALF_DAY")
	private BigDecimal allowancesHalfDay;
	@Column(name = "ACCOM_FEE_SINGLE")
	private BigDecimal accomFeeSingle;
	@Column(name = "ACCOM_FEE_DOUBLE")
	private BigDecimal accomFeeDouble;
	@Column(name = "ACCOM_FEE_PACKAGES")
	private BigDecimal accomFeePackages;

	public String getEdPositionName() {
		return edPositionName;
	}

	public void setEdPositionName(String edPositionName) {
		this.edPositionName = edPositionName;
	}

	public BigDecimal getAllowancesDay() {
		return allowancesDay;
	}

	public void setAllowancesDay(BigDecimal allowancesDay) {
		this.allowancesDay = allowancesDay;
	}

	public BigDecimal getAllowancesHalfDay() {
		return allowancesHalfDay;
	}

	public void setAllowancesHalfDay(BigDecimal allowancesHalfDay) {
		this.allowancesHalfDay = allowancesHalfDay;
	}

	public BigDecimal getAccomFeeSingle() {
		return accomFeeSingle;
	}

	public void setAccomFeeSingle(BigDecimal accomFeeSingle) {
		this.accomFeeSingle = accomFeeSingle;
	}

	public BigDecimal getAccomFeeDouble() {
		return accomFeeDouble;
	}

	public void setAccomFeeDouble(BigDecimal accomFeeDouble) {
		this.accomFeeDouble = accomFeeDouble;
	}

	public BigDecimal getAccomFeePackages() {
		return accomFeePackages;
	}

	public void setAccomFeePackages(BigDecimal accomFeePackages) {
		this.accomFeePackages = accomFeePackages;
	}

	public Long getEdPositionSeq() {
		return edPositionSeq;
	}

	public void setEdPositionSeq(Long edPositionSeq) {
		this.edPositionSeq = edPositionSeq;
	}

}
