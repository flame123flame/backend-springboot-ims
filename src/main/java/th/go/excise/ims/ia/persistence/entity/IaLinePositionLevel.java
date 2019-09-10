
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_LINE_POSITION_LEVEL")
public class IaLinePositionLevel extends BaseEntity {

	private static final long serialVersionUID = -1834519399140813002L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_LINE_POSITION_LEVEL_GEN")
	@SequenceGenerator(name = "IA_LINE_POSITION_LEVEL_GEN", sequenceName = "IA_LINE_POSITION_LEVEL_SEQ", allocationSize = 1)
	@Column(name = "LINE_POSITION_LEVEL_ID")
	private Long linePositionLevelId;
	@Column(name = "LINE_POSITION_LEVEL")
	private String linePositionLevel;
	@Column(name = "LINE_POSITION_LEVEL_NAME")
	private String linePositionLevelName;
	@Column(name = "LINE_POSITION_LEVEL_ENGNAME")
	private String linePositionLevelEngname;
	@Column(name = "LINE_POSITION_LEVEL_NAME_ABBR")
	private String linePositionLevelNameAbbr;
	@Column(name = "LINE_POSITION_FIRST")
	private String linePositionFirst;
	@Column(name = "LINE_POSITION_END")
	private String linePositionEnd;
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

	public Long getLinePositionLevelId() {
		return linePositionLevelId;
	}

	public void setLinePositionLevelId(Long linePositionLevelId) {
		this.linePositionLevelId = linePositionLevelId;
	}

	public String getLinePositionLevel() {
		return linePositionLevel;
	}

	public void setLinePositionLevel(String linePositionLevel) {
		this.linePositionLevel = linePositionLevel;
	}

	public String getLinePositionLevelName() {
		return linePositionLevelName;
	}

	public void setLinePositionLevelName(String linePositionLevelName) {
		this.linePositionLevelName = linePositionLevelName;
	}

	public String getLinePositionLevelEngname() {
		return linePositionLevelEngname;
	}

	public void setLinePositionLevelEngname(String linePositionLevelEngname) {
		this.linePositionLevelEngname = linePositionLevelEngname;
	}

	public String getLinePositionLevelNameAbbr() {
		return linePositionLevelNameAbbr;
	}

	public void setLinePositionLevelNameAbbr(String linePositionLevelNameAbbr) {
		this.linePositionLevelNameAbbr = linePositionLevelNameAbbr;
	}

	public String getLinePositionFirst() {
		return linePositionFirst;
	}

	public void setLinePositionFirst(String linePositionFirst) {
		this.linePositionFirst = linePositionFirst;
	}

	public String getLinePositionEnd() {
		return linePositionEnd;
	}

	public void setLinePositionEnd(String linePositionEnd) {
		this.linePositionEnd = linePositionEnd;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
