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
@Table(name = "TA_FORM_TS0102")
public class TaFormTs0102 extends BaseEntity {

	private static final long serialVersionUID = -935326164451840813L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0102_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0102_GEN", sequenceName = "TA_FORM_TS0102_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0102_ID")
	private Long formTs0102Id;
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
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "DOC_FROM")
	private String docFrom;
	@Column(name = "DOC_TEXT1")
	private String docText1;
	@Column(name = "COMPANY_TYPE")
	private String companyType;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FACTORY_ADDRESS")
	private String factoryAddress;
	@Column(name = "FACTORY_TYPE_TEXT")
	private String factoryTypeText;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "AUDIT_CASE")
	private String auditCase;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "SIGN_OFFICER_DATE")
	private Date signOfficerDate;
	@Column(name = "REG_DEAR")
	private String regDear;
	@Column(name = "REG_TEXT")
	private String regText;
	@Column(name = "SIGN_REG_FULL_NAME")
	private String signRegFullName;
	@Column(name = "SIGN_REG_POSITION")
	private String signRegPosition;
	@Column(name = "SIGN_REG_DATE")
	private Date signRegDate;
	@Column(name = "COMD_TYPE_FLAG")
	private String comdTypeFlag;
	@Column(name = "SIGN_COMD_FULL_NAME")
	private String signComdFullName;
	@Column(name = "SIGN_COMD_POSITION")
	private String signComdPosition;
	@Column(name = "SIGN_COMD_DATE")
	private Date signComdDate;
	@Column(name = "FINANCE_BOOK_NUMBER1")
	private String financeBookNumber1;
	@Column(name = "FINANCE_BOOK_NUMBER2")
	private String financeBookNumber2;
	@Column(name = "FINANCE_DEAR")
	private String financeDear;
	@Column(name = "SIGN_FINANCE_FULL_NAME")
	private String signFinanceFullName;
	@Column(name = "SIGN_FINANCE_POSITION")
	private String signFinancePosition;
	@Column(name = "SIGN_FINANCE_DATE")
	private Date signFinanceDate;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0102Id() {
		return formTs0102Id;
	}

	public void setFormTs0102Id(Long formTs0102Id) {
		this.formTs0102Id = formTs0102Id;
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

	public String getDocDear() {
		return docDear;
	}

	public void setDocDear(String docDear) {
		this.docDear = docDear;
	}

	public String getDocFrom() {
		return docFrom;
	}

	public void setDocFrom(String docFrom) {
		this.docFrom = docFrom;
	}

	public String getDocText1() {
		return docText1;
	}

	public void setDocText1(String docText1) {
		this.docText1 = docText1;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
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

	public String getAuditCase() {
		return auditCase;
	}

	public void setAuditCase(String auditCase) {
		this.auditCase = auditCase;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

	public String getSignOfficerPosition() {
		return signOfficerPosition;
	}

	public void setSignOfficerPosition(String signOfficerPosition) {
		this.signOfficerPosition = signOfficerPosition;
	}

	public Date getSignOfficerDate() {
		return signOfficerDate;
	}

	public void setSignOfficerDate(Date signOfficerDate) {
		this.signOfficerDate = signOfficerDate;
	}

	public String getRegDear() {
		return regDear;
	}

	public void setRegDear(String regDear) {
		this.regDear = regDear;
	}

	public String getRegText() {
		return regText;
	}

	public void setRegText(String regText) {
		this.regText = regText;
	}

	public String getSignRegFullName() {
		return signRegFullName;
	}

	public void setSignRegFullName(String signRegFullName) {
		this.signRegFullName = signRegFullName;
	}

	public String getSignRegPosition() {
		return signRegPosition;
	}

	public void setSignRegPosition(String signRegPosition) {
		this.signRegPosition = signRegPosition;
	}

	public Date getSignRegDate() {
		return signRegDate;
	}

	public void setSignRegDate(Date signRegDate) {
		this.signRegDate = signRegDate;
	}

	public String getComdTypeFlag() {
		return comdTypeFlag;
	}

	public void setComdTypeFlag(String comdTypeFlag) {
		this.comdTypeFlag = comdTypeFlag;
	}

	public String getSignComdFullName() {
		return signComdFullName;
	}

	public void setSignComdFullName(String signComdFullName) {
		this.signComdFullName = signComdFullName;
	}

	public String getSignComdPosition() {
		return signComdPosition;
	}

	public void setSignComdPosition(String signComdPosition) {
		this.signComdPosition = signComdPosition;
	}

	public Date getSignComdDate() {
		return signComdDate;
	}

	public void setSignComdDate(Date signComdDate) {
		this.signComdDate = signComdDate;
	}

	public String getFinanceBookNumber1() {
		return financeBookNumber1;
	}

	public void setFinanceBookNumber1(String financeBookNumber1) {
		this.financeBookNumber1 = financeBookNumber1;
	}

	public String getFinanceBookNumber2() {
		return financeBookNumber2;
	}

	public void setFinanceBookNumber2(String financeBookNumber2) {
		this.financeBookNumber2 = financeBookNumber2;
	}

	public String getFinanceDear() {
		return financeDear;
	}

	public void setFinanceDear(String financeDear) {
		this.financeDear = financeDear;
	}

	public String getSignFinanceFullName() {
		return signFinanceFullName;
	}

	public void setSignFinanceFullName(String signFinanceFullName) {
		this.signFinanceFullName = signFinanceFullName;
	}

	public String getSignFinancePosition() {
		return signFinancePosition;
	}

	public void setSignFinancePosition(String signFinancePosition) {
		this.signFinancePosition = signFinancePosition;
	}

	public Date getSignFinanceDate() {
		return signFinanceDate;
	}

	public void setSignFinanceDate(Date signFinanceDate) {
		this.signFinanceDate = signFinanceDate;
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
