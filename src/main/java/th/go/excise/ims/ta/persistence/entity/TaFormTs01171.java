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
@Table(name = "TA_FORM_TS01171")
public class TaFormTs01171 extends BaseEntity {

	private static final long serialVersionUID = -6889489624313225388L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS01171_GEN")
	@SequenceGenerator(name = "TA_FORM_TS01171_GEN", sequenceName = "TA_FORM_TS01171_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS01171_ID")
	private Long formTs01171Id;
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
	@Column(name = "FACTORY_NAME")
	private String factoryName;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
	@Column(name = "FAC_ADDR_NO")
	private String facAddrNo;
	@Column(name = "FAC_MOO_NO")
	private String facMooNo;
	@Column(name = "FAC_SOI_NAME")
	private String facSoiName;
	@Column(name = "FAC_THN_NAME")
	private String facThnName;
	@Column(name = "FAC_TAMBOLNAME")
	private String facTambolName;
	@Column(name = "FAC_AMPHUR_NAME")
	private String facAmphurName;
	@Column(name = "FAC_PROVINCE_NAME")
	private String facProvinceName;
	@Column(name = "FAC_ZIP_CODE")
	private String facZipCode;
	@Column(name = "BOOK_TYPE")
	private String bookType;
	@Column(name = "REF_BOOK_NUMBER1")
	private String refBookNumber1;
	@Column(name = "REF_BOOK_NUMBER2")
	private String refBookNumber2;
	@Column(name = "REF_DOC_DATE")
	private Date refDocDate;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
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
	@Column(name = "OFFFICE_PHONE")
	private String offficePhone;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs01171Id() {
		return formTs01171Id;
	}

	public void setFormTs01171Id(Long formTs01171Id) {
		this.formTs01171Id = formTs01171Id;
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

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
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

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
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

	public String getOffficePhone() {
		return offficePhone;
	}

	public void setOffficePhone(String offficePhone) {
		this.offficePhone = offficePhone;
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
