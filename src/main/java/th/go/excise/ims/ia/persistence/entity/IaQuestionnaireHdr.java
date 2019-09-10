
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "IA_QUESTIONNAIRE_HDR")
public class IaQuestionnaireHdr extends BaseEntity {

	private static final long serialVersionUID = 2034486539682880770L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QUESTIONNAIRE_HDR_GEN")
	@SequenceGenerator(name = "IA_QUESTIONNAIRE_HDR_GEN", sequenceName = "IA_QUESTIONNAIRE_HDR_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "QTN_HEADER_NAME")
	private String qtnHeaderName;
	@Column(name = "NOTE")
	private String note;
	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "END_DATE")
	private LocalDate endDate;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "CONCLUDE")
	private String conclude;
	@Column(name = "TO_DEPARTMENT")
	private String toDepartment;
	@Column(name = "USAGE_PATTERNS")
	private String usagePatterns;
	@Column(name = "FACTOR_LEVEL")
	private String factorLevel;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getBudgetYear() {
		return budgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		this.budgetYear = budgetYear;
	}

	public String getQtnHeaderName() {
		return qtnHeaderName;
	}

	public void setQtnHeaderName(String qtnHeaderName) {
		this.qtnHeaderName = qtnHeaderName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConclude() {
		return conclude;
	}

	public void setConclude(String conclude) {
		this.conclude = conclude;
	}

	public String getToDepartment() {
		return toDepartment;
	}

	public void setToDepartment(String toDepartment) {
		this.toDepartment = toDepartment;
	}

	public String getUsagePatterns() {
		return usagePatterns;
	}

	public void setUsagePatterns(String usagePatterns) {
		this.usagePatterns = usagePatterns;
	}

	public String getFactorLevel() {
		return factorLevel;
	}

	public void setFactorLevel(String factorLevel) {
		this.factorLevel = factorLevel;
	}

}
