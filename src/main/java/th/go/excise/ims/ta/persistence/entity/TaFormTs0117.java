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
@Table(name = "TA_FORM_TS0117")
public class TaFormTs0117 extends BaseEntity {

	private static final long serialVersionUID = -4422884365499541603L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0117_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0117_GEN", sequenceName = "TA_FORM_TS0117_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0117_ID")
	private Long formTs0117Id;
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
	@Column(name = "DOC_TOPIC")
	private String docTopic;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "REF_BOOK_NUMBER1")
	private String refBookNumber1;
	@Column(name = "REF_BOOK_NUMBER2")
	private String refBookNumber2;
	@Column(name = "REF_DOC_DATE")
	private Date refDocDate;
	@Column(name = "AUDIT_DATE")
	private Date auditDate;
	@Column(name = "CALL_BOOK_NUMBER1")
	private String callBookNumber1;
	@Column(name = "CALL_BOOK_NUMBER2")
	private String callBookNumber2;
	@Column(name = "CALL_BOOK_DATE")
	private Date callBookDate;
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
	@Column(name = "FAC_MOO_NO")
	private String facMooNo;
	@Column(name = "FAC_SOI_NAME")
	private String facSoiName;
	@Column(name = "FAC_THN_NAME")
	private String facThnName;
	@Column(name = "FAC_TAMBOL_NAME")
	private String facTambolName;
	@Column(name = "FAC_AMPHUR_NAME")
	private String facAmphurName;
	@Column(name = "FAC_PROVINCE_NAME")
	private String facProvinceName;
	@Column(name = "FAC_ZIP_CODE")
	private String facZipCode;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_POSITION")
	private String officerPosition;
	@Column(name = "TAX_FORM_DATE_START")
	private Date taxFormDateStart;
	@Column(name = "TAX_FORM_DATE_END")
	private Date taxFormDateEnd;
	@Column(name = "TESTIMONY_DATE")
	private Date testimonyDate;
	@Column(name = "FACT_DESC")
	private String factDesc;
	@Column(name = "LAW_DESC")
	private String lawDesc;
	@Column(name = "FACTORY_NAME2")
	private String factoryName2;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "FINE_AMT")
	private BigDecimal fineAmt;
	@Column(name = "EXTRA_AMT")
	private BigDecimal extraAmt;
	@Column(name = "EXCISE_TAX_AMT")
	private BigDecimal exciseTaxAmt;
	@Column(name = "MOI_AMT")
	private BigDecimal moiAmt;
	@Column(name = "SUM_ALL_TAX_AMT")
	private BigDecimal sumAllTaxAmt;
	@Column(name = "EXTRA_DATE")
	private Date extraDate;
	@Column(name = "PAYMENT_DEST")
	private String paymentDest;
	@Column(name = "PAYMENT_EXCISE_TAX_AMT")
	private BigDecimal paymentExciseTaxAmt;
	@Column(name = "PAYMENT_DATE")
	private Date paymentDate;
	@Column(name = "OFFICE_DEST")
	private String officeDest;
	@Column(name = "OFFICE_DATE")
	private Date officeDate;
	@Column(name = "OFFICE_TIME")
	private String officeTime;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "OFFICE_NAME")
	private String officeName;
	@Column(name = "OFFICE_PHONE")
	private String officePhone;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0117Id() {
		return formTs0117Id;
	}

	public void setFormTs0117Id(Long formTs0117Id) {
		this.formTs0117Id = formTs0117Id;
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

	public String getDocTopic() {
		return docTopic;
	}

	public void setDocTopic(String docTopic) {
		this.docTopic = docTopic;
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

	public String getRefBookNumber1() {
		return refBookNumber1;
	}

	public void setRefBookNumber1(String refBookNumber1) {
		this.refBookNumber1 = refBookNumber1;
	}

	public String getRefBookNumber2() {
		return refBookNumber2;
	}

	public void setRefBookNumber2(String refBookNumber2) {
		this.refBookNumber2 = refBookNumber2;
	}

	public Date getRefDocDate() {
		return refDocDate;
	}

	public void setRefDocDate(Date refDocDate) {
		this.refDocDate = refDocDate;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getCallBookNumber1() {
		return callBookNumber1;
	}

	public void setCallBookNumber1(String callBookNumber1) {
		this.callBookNumber1 = callBookNumber1;
	}

	public String getCallBookNumber2() {
		return callBookNumber2;
	}

	public void setCallBookNumber2(String callBookNumber2) {
		this.callBookNumber2 = callBookNumber2;
	}

	public Date getCallBookDate() {
		return callBookDate;
	}

	public void setCallBookDate(Date callBookDate) {
		this.callBookDate = callBookDate;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFacAddrNo() {
		return facAddrNo;
	}

	public void setFacAddrNo(String facAddrNo) {
		this.facAddrNo = facAddrNo;
	}

	public String getFacMooNo() {
		return facMooNo;
	}

	public void setFacMooNo(String facMooNo) {
		this.facMooNo = facMooNo;
	}

	public String getFacSoiName() {
		return facSoiName;
	}

	public void setFacSoiName(String facSoiName) {
		this.facSoiName = facSoiName;
	}

	public String getFacThnName() {
		return facThnName;
	}

	public void setFacThnName(String facThnName) {
		this.facThnName = facThnName;
	}

	public String getFacTambolName() {
		return facTambolName;
	}

	public void setFacTambolName(String facTambolName) {
		this.facTambolName = facTambolName;
	}

	public String getFacAmphurName() {
		return facAmphurName;
	}

	public void setFacAmphurName(String facAmphurName) {
		this.facAmphurName = facAmphurName;
	}

	public String getFacProvinceName() {
		return facProvinceName;
	}

	public void setFacProvinceName(String facProvinceName) {
		this.facProvinceName = facProvinceName;
	}

	public String getFacZipCode() {
		return facZipCode;
	}

	public void setFacZipCode(String facZipCode) {
		this.facZipCode = facZipCode;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public String getOfficerPosition() {
		return officerPosition;
	}

	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}

	public Date getTaxFormDateStart() {
		return taxFormDateStart;
	}

	public void setTaxFormDateStart(Date taxFormDateStart) {
		this.taxFormDateStart = taxFormDateStart;
	}

	public Date getTaxFormDateEnd() {
		return taxFormDateEnd;
	}

	public void setTaxFormDateEnd(Date taxFormDateEnd) {
		this.taxFormDateEnd = taxFormDateEnd;
	}

	public Date getTestimonyDate() {
		return testimonyDate;
	}

	public void setTestimonyDate(Date testimonyDate) {
		this.testimonyDate = testimonyDate;
	}

	public String getFactDesc() {
		return factDesc;
	}

	public void setFactDesc(String factDesc) {
		this.factDesc = factDesc;
	}

	public String getLawDesc() {
		return lawDesc;
	}

	public void setLawDesc(String lawDesc) {
		this.lawDesc = lawDesc;
	}

	public String getFactoryName2() {
		return factoryName2;
	}

	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getFineAmt() {
		return fineAmt;
	}

	public void setFineAmt(BigDecimal fineAmt) {
		this.fineAmt = fineAmt;
	}

	public BigDecimal getExtraAmt() {
		return extraAmt;
	}

	public void setExtraAmt(BigDecimal extraAmt) {
		this.extraAmt = extraAmt;
	}

	public BigDecimal getExciseTaxAmt() {
		return exciseTaxAmt;
	}

	public void setExciseTaxAmt(BigDecimal exciseTaxAmt) {
		this.exciseTaxAmt = exciseTaxAmt;
	}

	public BigDecimal getMoiAmt() {
		return moiAmt;
	}

	public void setMoiAmt(BigDecimal moiAmt) {
		this.moiAmt = moiAmt;
	}

	public BigDecimal getSumAllTaxAmt() {
		return sumAllTaxAmt;
	}

	public void setSumAllTaxAmt(BigDecimal sumAllTaxAmt) {
		this.sumAllTaxAmt = sumAllTaxAmt;
	}

	public Date getExtraDate() {
		return extraDate;
	}

	public void setExtraDate(Date extraDate) {
		this.extraDate = extraDate;
	}

	public String getPaymentDest() {
		return paymentDest;
	}

	public void setPaymentDest(String paymentDest) {
		this.paymentDest = paymentDest;
	}

	public BigDecimal getPaymentExciseTaxAmt() {
		return paymentExciseTaxAmt;
	}

	public void setPaymentExciseTaxAmt(BigDecimal paymentExciseTaxAmt) {
		this.paymentExciseTaxAmt = paymentExciseTaxAmt;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getOfficeDest() {
		return officeDest;
	}

	public void setOfficeDest(String officeDest) {
		this.officeDest = officeDest;
	}

	public Date getOfficeDate() {
		return officeDate;
	}

	public void setOfficeDate(Date officeDate) {
		this.officeDate = officeDate;
	}

	public String getOfficeTime() {
		return officeTime;
	}

	public void setOfficeTime(String officeTime) {
		this.officeTime = officeTime;
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

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getHeadOfficerFullName() {
		return headOfficerFullName;
	}

	public void setHeadOfficerFullName(String headOfficerFullName) {
		this.headOfficerFullName = headOfficerFullName;
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
