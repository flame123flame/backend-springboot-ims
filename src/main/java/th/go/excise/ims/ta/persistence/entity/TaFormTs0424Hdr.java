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
@Table(name = "TA_FORM_TS0424_HDR")
public class TaFormTs0424Hdr extends BaseEntity {

	private static final long serialVersionUID = -8373797976660939445L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0424_HDR_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0424_HDR_GEN", sequenceName = "TA_FORM_TS0424_HDR_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0424_HDR_ID")
	private Long formTs0424HdrId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "AUDIT_MONTH_START")
	private String auditMonthStart;
	@Column(name = "AUDIT_MONTH_END")
	private String auditMonthEnd;
	@Column(name = "AUDIT_YEAR")
	private String auditYear;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0424HdrId() {
		return formTs0424HdrId;
	}

	public void setFormTs0424HdrId(Long formTs0424HdrId) {
		this.formTs0424HdrId = formTs0424HdrId;
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

	public String getFormTsNumber() {
		return formTsNumber;
	}

	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getAuditMonthStart() {
		return auditMonthStart;
	}

	public void setAuditMonthStart(String auditMonthStart) {
		this.auditMonthStart = auditMonthStart;
	}

	public String getAuditMonthEnd() {
		return auditMonthEnd;
	}

	public void setAuditMonthEnd(String auditMonthEnd) {
		this.auditMonthEnd = auditMonthEnd;
	}

	public String getAuditYear() {
		return auditYear;
	}

	public void setAuditYear(String auditYear) {
		this.auditYear = auditYear;
	}

	public String getAuditPlanCode() {
		return auditPlanCode;
	}

	public void setAuditPlanCode(String auditPlanCode) {
		this.auditPlanCode = auditPlanCode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
