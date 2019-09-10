package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "TA_FORM_TS0118_HDR")
public class TaFormTs0118Hdr extends BaseEntity {

	private static final long serialVersionUID = -3477798711765117100L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0118_HDR_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0118_HDR_GEN", sequenceName = "TA_FORM_TS0118_HDR_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0118_HDR_ID")
	private Long formTs0118HdrId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "BOOK_NUMBER1")
	private String bookNumber1;
	@Column(name = "BOOK_NUMBER2")
	private String bookNumber2;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "OWNER_FULL_NAME")
	private String ownerFullName;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FACTORY_ADDRESS")
	private String factoryAddress;
	@Column(name = "COMPANY_ADDRESS")
	private String companyAddress;
	@Column(name = "LAW_SECTION")
	private String lawSection;
	@Column(name = "LAW_GROUP")
	private String lawGroup;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "SUM_ALL_TAX_AMT")
	private BigDecimal sumAllTaxAmt;
	@Column(name = "SUM_ALL_TAX_TEXT")
	private String sumAllTaxText;
	@Column(name = "OFFICE_NAME")
	private String officeName;
	@Column(name = "TABLE_HEADER_DUTY_TYPE")
	private String tableHeaderDutyType;
	@Column(name = "TABLE_HEADER_UNIT")
	private String tableHeaderUnit;
	@Column(name = "REASON_TEXT")
	private String reasonText;
	@Column(name = "SIGN_OFFICER_FULL_NAME1")
	private String signOfficerFullName1;
	@Column(name = "SIGN_OFFICER_DATE1")
	private Date signOfficerDate1;
	@Column(name = "SIGN_OFFICER_FULL_NAME2")
	private String signOfficerFullName2;
	@Column(name = "SIGN_OFFICER_DATE2")
	private Date signOfficerDate2;
	@Column(name = "EXTRA_MONEY_DATE")
	private Date extraMoneyDate;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0118HdrId() {
		return formTs0118HdrId;
	}

	public void setFormTs0118HdrId(Long formTs0118HdrId) {
		this.formTs0118HdrId = formTs0118HdrId;
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

	public String getBookNumber1() {
		return bookNumber1;
	}

	public void setBookNumber1(String bookNumber1) {
		this.bookNumber1 = bookNumber1;
	}

	public String getBookNumber2() {
		return bookNumber2;
	}

	public void setBookNumber2(String bookNumber2) {
		this.bookNumber2 = bookNumber2;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
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

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getLawSection() {
		return lawSection;
	}

	public void setLawSection(String lawSection) {
		this.lawSection = lawSection;
	}

	public String getLawGroup() {
		return lawGroup;
	}

	public void setLawGroup(String lawGroup) {
		this.lawGroup = lawGroup;
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

	public BigDecimal getSumAllTaxAmt() {
		return sumAllTaxAmt;
	}

	public void setSumAllTaxAmt(BigDecimal sumAllTaxAmt) {
		this.sumAllTaxAmt = sumAllTaxAmt;
	}

	public String getSumAllTaxText() {
		return sumAllTaxText;
	}

	public void setSumAllTaxText(String sumAllTaxText) {
		this.sumAllTaxText = sumAllTaxText;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getTableHeaderDutyType() {
		return tableHeaderDutyType;
	}

	public void setTableHeaderDutyType(String tableHeaderDutyType) {
		this.tableHeaderDutyType = tableHeaderDutyType;
	}

	public String getTableHeaderUnit() {
		return tableHeaderUnit;
	}

	public void setTableHeaderUnit(String tableHeaderUnit) {
		this.tableHeaderUnit = tableHeaderUnit;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
	}

	public String getSignOfficerFullName1() {
		return signOfficerFullName1;
	}

	public void setSignOfficerFullName1(String signOfficerFullName1) {
		this.signOfficerFullName1 = signOfficerFullName1;
	}

	public Date getSignOfficerDate1() {
		return signOfficerDate1;
	}

	public void setSignOfficerDate1(Date signOfficerDate1) {
		this.signOfficerDate1 = signOfficerDate1;
	}

	public String getSignOfficerFullName2() {
		return signOfficerFullName2;
	}

	public void setSignOfficerFullName2(String signOfficerFullName2) {
		this.signOfficerFullName2 = signOfficerFullName2;
	}

	public Date getSignOfficerDate2() {
		return signOfficerDate2;
	}

	public void setSignOfficerDate2(Date signOfficerDate2) {
		this.signOfficerDate2 = signOfficerDate2;
	}

	public Date getExtraMoneyDate() {
		return extraMoneyDate;
	}

	public void setExtraMoneyDate(Date extraMoneyDate) {
		this.extraMoneyDate = extraMoneyDate;
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
