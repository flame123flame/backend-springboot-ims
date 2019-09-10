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
@Table(name = "TA_FORM_TS0114_HDR")
public class TaFormTs0114Hdr extends BaseEntity {

	private static final long serialVersionUID = -5098437264902500282L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0114_HDR_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0114_HDR_GEN", sequenceName = "TA_FORM_TS0114_HDR_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0114_HDR_ID")
	private Long formTs0114HdrId;
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
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
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
	@Column(name = "FAC_TEL_NO")
	private String facTelNo;
	@Column(name = "FACTORY_TYPE_TEXT")
	private String factoryTypeText;
	@Column(name = "OFFICER_FULL_NAME")
	private String officerFullName;
	@Column(name = "OFFICER_DEPT")
	private String officerDept;
	@Column(name = "AUDIT_DATE")
	private Date auditDate;
	@Column(name = "BOOK_NUMBER1")
	private String bookNumber1;
	@Column(name = "BOOK_DATE")
	private Date bookDate;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
	@Column(name = "AUDIT_SUM_MONTH")
	private String auditSumMonth;
	@Column(name = "AUDIT_SUM_DAY")
	private String auditSumDay;
	@Column(name = "AUDIT_BOOK_TYPE")
	private String auditBookType;
	@Column(name = "AUDIT_BOOK_TYPE_OTHER")
	private String auditBookTypeOther;
	@Column(name = "AUDIT_BOOK_NUMBER")
	private String auditBookNumber;
	@Column(name = "AUDIT_BOOK_DATE")
	private Date auditBookDate;
	@Column(name = "DOC_NUM")
	private String docNum;
	@Column(name = "DOC1_NUM")
	private String doc1Num;
	@Column(name = "DOC1_DATE")
	private Date doc1Date;
	@Column(name = "DOC2_NUM")
	private String doc2Num;
	@Column(name = "DOC2_DATE")
	private Date doc2Date;
	@Column(name = "DOC3_NUM")
	private String doc3Num;
	@Column(name = "DOC3_DATE")
	private Date doc3Date;
	@Column(name = "DOC4_NUM")
	private String doc4Num;
	@Column(name = "DOC5_NUM")
	private String doc5Num;
	@Column(name = "DOC6_NUM")
	private String doc6Num;
	@Column(name = "DOC7_NUM")
	private String doc7Num;
	@Column(name = "DOC8_NUM")
	private String doc8Num;
	@Column(name = "DOC9_NUM")
	private String doc9Num;
	@Column(name = "ASS_REASON")
	private String assReason;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0114HdrId() {
		return formTs0114HdrId;
	}

	public void setFormTs0114HdrId(Long formTs0114HdrId) {
		this.formTs0114HdrId = formTs0114HdrId;
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

	public String getFacAddrNo() {
		return facAddrNo;
	}

	public void setFacAddrNo(String facAddrNo) {
		this.facAddrNo = facAddrNo;
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

	public String getFacTelNo() {
		return facTelNo;
	}

	public void setFacTelNo(String facTelNo) {
		this.facTelNo = facTelNo;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public String getOfficerFullName() {
		return officerFullName;
	}

	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}

	public String getOfficerDept() {
		return officerDept;
	}

	public void setOfficerDept(String officerDept) {
		this.officerDept = officerDept;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getBookNumber1() {
		return bookNumber1;
	}

	public void setBookNumber1(String bookNumber1) {
		this.bookNumber1 = bookNumber1;
	}

	public Date getBookDate() {
		return bookDate;
	}

	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
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

	public String getAuditSumMonth() {
		return auditSumMonth;
	}

	public void setAuditSumMonth(String auditSumMonth) {
		this.auditSumMonth = auditSumMonth;
	}

	public String getAuditSumDay() {
		return auditSumDay;
	}

	public void setAuditSumDay(String auditSumDay) {
		this.auditSumDay = auditSumDay;
	}

	public String getAuditBookType() {
		return auditBookType;
	}

	public void setAuditBookType(String auditBookType) {
		this.auditBookType = auditBookType;
	}

	public String getAuditBookTypeOther() {
		return auditBookTypeOther;
	}

	public void setAuditBookTypeOther(String auditBookTypeOther) {
		this.auditBookTypeOther = auditBookTypeOther;
	}

	public String getAuditBookNumber() {
		return auditBookNumber;
	}

	public void setAuditBookNumber(String auditBookNumber) {
		this.auditBookNumber = auditBookNumber;
	}

	public Date getAuditBookDate() {
		return auditBookDate;
	}

	public void setAuditBookDate(Date auditBookDate) {
		this.auditBookDate = auditBookDate;
	}

	public String getDocNum() {
		return docNum;
	}

	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}

	public String getDoc1Num() {
		return doc1Num;
	}

	public void setDoc1Num(String doc1Num) {
		this.doc1Num = doc1Num;
	}

	public Date getDoc1Date() {
		return doc1Date;
	}

	public void setDoc1Date(Date doc1Date) {
		this.doc1Date = doc1Date;
	}

	public String getDoc2Num() {
		return doc2Num;
	}

	public void setDoc2Num(String doc2Num) {
		this.doc2Num = doc2Num;
	}

	public Date getDoc2Date() {
		return doc2Date;
	}

	public void setDoc2Date(Date doc2Date) {
		this.doc2Date = doc2Date;
	}

	public String getDoc3Num() {
		return doc3Num;
	}

	public void setDoc3Num(String doc3Num) {
		this.doc3Num = doc3Num;
	}

	public Date getDoc3Date() {
		return doc3Date;
	}

	public void setDoc3Date(Date doc3Date) {
		this.doc3Date = doc3Date;
	}

	public String getDoc4Num() {
		return doc4Num;
	}

	public void setDoc4Num(String doc4Num) {
		this.doc4Num = doc4Num;
	}

	public String getDoc5Num() {
		return doc5Num;
	}

	public void setDoc5Num(String doc5Num) {
		this.doc5Num = doc5Num;
	}

	public String getDoc6Num() {
		return doc6Num;
	}

	public void setDoc6Num(String doc6Num) {
		this.doc6Num = doc6Num;
	}

	public String getDoc7Num() {
		return doc7Num;
	}

	public void setDoc7Num(String doc7Num) {
		this.doc7Num = doc7Num;
	}

	public String getDoc8Num() {
		return doc8Num;
	}

	public void setDoc8Num(String doc8Num) {
		this.doc8Num = doc8Num;
	}

	public String getDoc9Num() {
		return doc9Num;
	}

	public void setDoc9Num(String doc9Num) {
		this.doc9Num = doc9Num;
	}

	public String getAssReason() {
		return assReason;
	}

	public void setAssReason(String assReason) {
		this.assReason = assReason;
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
