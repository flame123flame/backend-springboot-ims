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
@Table(name = "TA_FORM_TS0120")
public class TaFormTs0120 extends BaseEntity {

	private static final long serialVersionUID = -3525211553185916200L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0120_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0120_GEN", sequenceName = "TA_FORM_TS0120_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0120_ID")
	private Long formTs0120Id;
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
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "BOOK_NUMBER1")
	private String bookNumber1;
	@Column(name = "BOOK_DATE")
	private Date bookDate;
	@Column(name = "FACTORY_NAME2")
	private String factoryName2;
	@Column(name = "AUDIT_DATE_START")
	private Date auditDateStart;
	@Column(name = "AUDIT_DATE_END")
	private Date auditDateEnd;
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
	@Column(name = "EXPAND_REASON")
	private String expandReason;
	@Column(name = "EXPAND_FLAG")
	private String expandFlag;
	@Column(name = "EXPAND_NO")
	private String expandNo;
	@Column(name = "EXPAND_DATE_OLD")
	private Date expandDateOld;
	@Column(name = "EXPAND_DATE_NEW")
	private Date expandDateNew;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_DATE")
	private Date signOfficerDate;
	@Column(name = "HEAD_OFFICER_COMMENT")
	private String headOfficerComment;
	@Column(name = "SIGN_HEAD_OFFICER_FULL_NAME")
	private String signHeadOfficerFullName;
	@Column(name = "SIGN_HEAD_OFFICER_DATE")
	private Date signHeadOfficerDate;
	@Column(name = "APPROVER_COMMENT")
	private String approverComment;
	@Column(name = "APPROVE_FLAG")
	private String approveFlag;
	@Column(name = "SIGN_APPROVER_FULL_NAME")
	private String signApproverFullName;
	@Column(name = "SIGN_APPROVER_DATE")
	private Date signApproverDate;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0120Id() {
		return formTs0120Id;
	}

	public void setFormTs0120Id(Long formTs0120Id) {
		this.formTs0120Id = formTs0120Id;
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

	public String getDocDear() {
		return docDear;
	}

	public void setDocDear(String docDear) {
		this.docDear = docDear;
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

	public String getFactoryName2() {
		return factoryName2;
	}

	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
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

	public String getExpandReason() {
		return expandReason;
	}

	public void setExpandReason(String expandReason) {
		this.expandReason = expandReason;
	}

	public String getExpandFlag() {
		return expandFlag;
	}

	public void setExpandFlag(String expandFlag) {
		this.expandFlag = expandFlag;
	}

	public String getExpandNo() {
		return expandNo;
	}

	public void setExpandNo(String expandNo) {
		this.expandNo = expandNo;
	}

	public Date getExpandDateOld() {
		return expandDateOld;
	}

	public void setExpandDateOld(Date expandDateOld) {
		this.expandDateOld = expandDateOld;
	}

	public Date getExpandDateNew() {
		return expandDateNew;
	}

	public void setExpandDateNew(Date expandDateNew) {
		this.expandDateNew = expandDateNew;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

	public Date getSignOfficerDate() {
		return signOfficerDate;
	}

	public void setSignOfficerDate(Date signOfficerDate) {
		this.signOfficerDate = signOfficerDate;
	}

	public String getHeadOfficerComment() {
		return headOfficerComment;
	}

	public void setHeadOfficerComment(String headOfficerComment) {
		this.headOfficerComment = headOfficerComment;
	}

	public String getSignHeadOfficerFullName() {
		return signHeadOfficerFullName;
	}

	public void setSignHeadOfficerFullName(String signHeadOfficerFullName) {
		this.signHeadOfficerFullName = signHeadOfficerFullName;
	}

	public Date getSignHeadOfficerDate() {
		return signHeadOfficerDate;
	}

	public void setSignHeadOfficerDate(Date signHeadOfficerDate) {
		this.signHeadOfficerDate = signHeadOfficerDate;
	}

	public String getApproverComment() {
		return approverComment;
	}

	public void setApproverComment(String approverComment) {
		this.approverComment = approverComment;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

	public String getSignApproverFullName() {
		return signApproverFullName;
	}

	public void setSignApproverFullName(String signApproverFullName) {
		this.signApproverFullName = signApproverFullName;
	}

	public Date getSignApproverDate() {
		return signApproverDate;
	}

	public void setSignApproverDate(Date signApproverDate) {
		this.signApproverDate = signApproverDate;
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
