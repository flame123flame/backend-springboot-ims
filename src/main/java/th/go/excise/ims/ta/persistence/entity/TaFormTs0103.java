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
@Table(name = "TA_FORM_TS0103")
public class TaFormTs0103 extends BaseEntity {

	private static final long serialVersionUID = -447820556948635296L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0103_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0103_GEN", sequenceName = "TA_FORM_TS0103_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0103_ID")
	private Long formTs0103Id;
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
	@Column(name = "OFFICE_NAME1")
	private String officeName1;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
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
	@Column(name = "COMP_ADDR_NO")
	private String compAddrNo;
	@Column(name = "COMP_SOI_NAME")
	private String compSoiName;
	@Column(name = "COMP_THN_NAME")
	private String compThnName;
	@Column(name = "COMP_TAMBOL_NAME")
	private String compTambolName;
	@Column(name = "COMP_AMPHUR_NAME")
	private String compAmphurName;
	@Column(name = "COMP_PROVINCE_NAME")
	private String compProvinceName;
	@Column(name = "COMP_ZIP_CODE")
	private String compZipCode;
	@Column(name = "REASON_TEXT")
	private String reasonText;
	@Column(name = "LAW_SECTION")
	private String lawSection;
	@Column(name = "LAW_GROUP")
	private String lawGroup;
	@Column(name = "DEST_TEXT")
	private String destText;
	@Column(name = "DEST_DATE")
	private Date destDate;
	@Column(name = "DEST_TIME")
	private String destTime;
	@Column(name = "DEST_DOC_DESC")
	private String destDocDesc;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "OFFICE_NAME2")
	private String officeName2;
	@Column(name = "OFFICE_PHONE")
	private String officePhone;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0103Id() {
		return formTs0103Id;
	}

	public void setFormTs0103Id(Long formTs0103Id) {
		this.formTs0103Id = formTs0103Id;
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

	public String getOfficeName1() {
		return officeName1;
	}

	public void setOfficeName1(String officeName1) {
		this.officeName1 = officeName1;
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

	public String getCompAddrNo() {
		return compAddrNo;
	}

	public void setCompAddrNo(String compAddrNo) {
		this.compAddrNo = compAddrNo;
	}

	public String getCompSoiName() {
		return compSoiName;
	}

	public void setCompSoiName(String compSoiName) {
		this.compSoiName = compSoiName;
	}

	public String getCompThnName() {
		return compThnName;
	}

	public void setCompThnName(String compThnName) {
		this.compThnName = compThnName;
	}

	public String getCompTambolName() {
		return compTambolName;
	}

	public void setCompTambolName(String compTambolName) {
		this.compTambolName = compTambolName;
	}

	public String getCompAmphurName() {
		return compAmphurName;
	}

	public void setCompAmphurName(String compAmphurName) {
		this.compAmphurName = compAmphurName;
	}

	public String getCompProvinceName() {
		return compProvinceName;
	}

	public void setCompProvinceName(String compProvinceName) {
		this.compProvinceName = compProvinceName;
	}

	public String getCompZipCode() {
		return compZipCode;
	}

	public void setCompZipCode(String compZipCode) {
		this.compZipCode = compZipCode;
	}

	public String getReasonText() {
		return reasonText;
	}

	public void setReasonText(String reasonText) {
		this.reasonText = reasonText;
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

	public String getDestText() {
		return destText;
	}

	public void setDestText(String destText) {
		this.destText = destText;
	}

	public Date getDestDate() {
		return destDate;
	}

	public void setDestDate(Date destDate) {
		this.destDate = destDate;
	}

	public String getDestTime() {
		return destTime;
	}

	public void setDestTime(String destTime) {
		this.destTime = destTime;
	}

	public String getDestDocDesc() {
		return destDocDesc;
	}

	public void setDestDocDesc(String destDocDesc) {
		this.destDocDesc = destDocDesc;
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

	public String getOfficeName2() {
		return officeName2;
	}

	public void setOfficeName2(String officeName2) {
		this.officeName2 = officeName2;
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
