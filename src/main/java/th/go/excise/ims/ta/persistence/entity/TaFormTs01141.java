package th.go.excise.ims.ta.persistence.entity;

import java.util.Date;
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
@Table(name = "TA_FORM_TS01141")
public class TaFormTs01141 extends BaseEntity {

	private static final long serialVersionUID = 8887246252640425970L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS01141_GEN")
	@SequenceGenerator(name = "TA_FORM_TS01141_GEN", sequenceName = "TA_FORM_TS01141_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS01141_ID")
	private Long formTs01141Id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "PAGE_NO")
	private String pageNo;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FACTORY_TYPE_TEXT")
	private String factoryTypeText;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "AUDIT_DESC")
	private String auditDesc;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs01141Id() {
		return formTs01141Id;
	}

	public void setFormTs01141Id(Long formTs01141Id) {
		this.formTs01141Id = formTs01141Id;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocDear() {
		return docDear;
	}

	public void setDocDear(String docDear) {
		this.docDear = docDear;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public Date getAuditDateStart() {
		return auditDateStart;
	}

	public void setAuditDateStart(Date auditDateStart) {
		this.auditDateStart = auditDateStart;
	}

	public Date getAuditDateEnd() {
		return auditDateEnd;
	}

	public void setAuditDateEnd(Date auditDateEnd) {
		this.auditDateEnd = auditDateEnd;
	}

	public String getAuditDesc() {
		return auditDesc;
	}

	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
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
