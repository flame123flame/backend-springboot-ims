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
@Table(name = "TA_FORM_TS0105")
public class TaFormTs0105 extends BaseEntity {

	private static final long serialVersionUID = -3232278165379031602L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0105_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0105_GEN", sequenceName = "TA_FORM_TS0105_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0105_ID")
	private Long formTs0105Id;
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
	@Column(name = "OFFICE_NAME")
	private String officeName;
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
	@Column(name = "REF_DOC_SEND")
	private String refDocSend;
	@Column(name = "CASE_DATE")
	private Date caseDate;
	@Column(name = "CASE_TIME")
	private String caseTime;
	@Column(name = "DEST_TEXT")
	private String destText;
	@Column(name = "DEST_DATE")
	private Date destDate;
	@Column(name = "DEST_TIME")
	private String destTime;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "OTHER_TEXT")
	private String otherText;
	@Column(name = "OTHER_PHONE")
	private String otherPhone;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0105Id() {
		return formTs0105Id;
	}

	public void setFormTs0105Id(Long formTs0105Id) {
		this.formTs0105Id = formTs0105Id;
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

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
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

	public String getRefDocSend() {
		return refDocSend;
	}

	public void setRefDocSend(String refDocSend) {
		this.refDocSend = refDocSend;
	}

	public Date getCaseDate() {
		return caseDate;
	}

	public void setCaseDate(Date caseDate) {
		this.caseDate = caseDate;
	}

	public String getCaseTime() {
		return caseTime;
	}

	public void setCaseTime(String caseTime) {
		this.caseTime = caseTime;
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

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
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
