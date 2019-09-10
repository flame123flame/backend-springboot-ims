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
@Table(name = "TA_FORM_TS0119")
public class TaFormTs0119 extends BaseEntity {

	private static final long serialVersionUID = -4476086214789584137L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0119_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0119_GEN", sequenceName = "TA_FORM_TS0119_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0119_ID")
	private Long formTs0119Id;
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
	@Column(name = "DOC_TEXT1")
	private String docText1;
	@Column(name = "DOC_TEXT2")
	private String docText2;
	@Column(name = "DOC_TEXT3")
	private String docText3;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "COMPANY_NAME")
	private String companyName;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
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
	@Column(name = "FOLLOW_TYPE_FLAG1")
	private String followTypeFlag1;
	@Column(name = "REF_BOOK_NUMBER")
	private String refBookNumber;
	@Column(name = "REF_BOOK_DATE")
	private Date refBookDate;
	@Column(name = "OFFICE_NAME1")
	private String officeName1;
	@Column(name = "FOLLOW_TYPE_FLAG2")
	private String followTypeFlag2;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "OFFICE_NAME2")
	private String officeName2;
	@Column(name = "OFFICE_PHONE")
	private String officePhone;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0119Id() {
		return formTs0119Id;
	}

	public void setFormTs0119Id(Long formTs0119Id) {
		this.formTs0119Id = formTs0119Id;
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

	public String getDocText1() {
		return docText1;
	}

	public void setDocText1(String docText1) {
		this.docText1 = docText1;
	}

	public String getDocText2() {
		return docText2;
	}

	public void setDocText2(String docText2) {
		this.docText2 = docText2;
	}

	public String getDocText3() {
		return docText3;
	}

	public void setDocText3(String docText3) {
		this.docText3 = docText3;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getFollowTypeFlag1() {
		return followTypeFlag1;
	}

	public void setFollowTypeFlag1(String followTypeFlag1) {
		this.followTypeFlag1 = followTypeFlag1;
	}

	public String getRefBookNumber() {
		return refBookNumber;
	}

	public void setRefBookNumber(String refBookNumber) {
		this.refBookNumber = refBookNumber;
	}

	public Date getRefBookDate() {
		return refBookDate;
	}

	public void setRefBookDate(Date refBookDate) {
		this.refBookDate = refBookDate;
	}

	public String getOfficeName1() {
		return officeName1;
	}

	public void setOfficeName1(String officeName1) {
		this.officeName1 = officeName1;
	}

	public String getFollowTypeFlag2() {
		return followTypeFlag2;
	}

	public void setFollowTypeFlag2(String followTypeFlag2) {
		this.followTypeFlag2 = followTypeFlag2;
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
