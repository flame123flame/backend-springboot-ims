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
@Table(name = "TA_FORM_TS01142_HDR")
public class TaFormTs01142Hdr extends BaseEntity {

	private static final long serialVersionUID = 2852044961472558084L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS01142_HDR_GEN")
	@SequenceGenerator(name = "TA_FORM_TS01142_HDR_GEN", sequenceName = "TA_FORM_TS01142_HDR_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS01142_HDR_ID")
	private Long formTs01142HdrId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "OWNER_FULL_NAME")
	private String ownerFullName;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "DUTY_TYPE_TEXT")
	private String dutyTypeText;
	@Column(name = "EXTRA_AMT_DATE")
	private Date extraAmtDate;
	@Column(name = "SIGN_OWNER_FULL_NAME")
	private String signOwnerFullName;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs01142HdrId() {
		return formTs01142HdrId;
	}

	public void setFormTs01142HdrId(Long formTs01142HdrId) {
		this.formTs01142HdrId = formTs01142HdrId;
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

	public String getOwnerFullName() {
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
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

	public String getDutyTypeText() {
		return dutyTypeText;
	}

	public void setDutyTypeText(String dutyTypeText) {
		this.dutyTypeText = dutyTypeText;
	}

	public Date getExtraAmtDate() {
		return extraAmtDate;
	}

	public void setExtraAmtDate(Date extraAmtDate) {
		this.extraAmtDate = extraAmtDate;
	}

	public String getSignOwnerFullName() {
		return signOwnerFullName;
	}

	public void setSignOwnerFullName(String signOwnerFullName) {
		this.signOwnerFullName = signOwnerFullName;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
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
