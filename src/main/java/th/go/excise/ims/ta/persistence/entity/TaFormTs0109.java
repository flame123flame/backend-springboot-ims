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
@Table(name = "TA_FORM_TS0109")
public class TaFormTs0109 extends BaseEntity {

	private static final long serialVersionUID = -3087467903441581239L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0109_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0109_GEN", sequenceName = "TA_FORM_TS0109_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0109_ID")
	private Long formTs0109Id;
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
	@Column(name = "COMD_PLACE")
	private String comdPlace;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "EVIDENCE_REASON")
	private String evidenceReason;
	@Column(name = "DOC_TEXT1")
	private String docText1;
	@Column(name = "DOC_TEXT2")
	private String docText2;
	@Column(name = "DOC_TEXT3")
	private String docText3;
	@Column(name = "HEAD_OFFICER_FULL_NAME")
	private String headOfficerFullName;
	@Column(name = "HEAD_OFFICER_POSITION")
	private String headOfficerPosition;
	@Column(name = "OFFICER_TEXT")
	private String officerText;
	@Column(name = "SEARCH_PLACE")
	private String searchPlace;
	@Column(name = "SEARCH_DATE")
	private Date searchDate;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0109Id() {
		return formTs0109Id;
	}

	public void setFormTs0109Id(Long formTs0109Id) {
		this.formTs0109Id = formTs0109Id;
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

	public String getComdPlace() {
		return comdPlace;
	}

	public void setComdPlace(String comdPlace) {
		this.comdPlace = comdPlace;
	}

	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getEvidenceReason() {
		return evidenceReason;
	}

	public void setEvidenceReason(String evidenceReason) {
		this.evidenceReason = evidenceReason;
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

	public String getHeadOfficerFullName() {
		return headOfficerFullName;
	}

	public void setHeadOfficerFullName(String headOfficerFullName) {
		this.headOfficerFullName = headOfficerFullName;
	}

	public String getHeadOfficerPosition() {
		return headOfficerPosition;
	}

	public void setHeadOfficerPosition(String headOfficerPosition) {
		this.headOfficerPosition = headOfficerPosition;
	}

	public String getOfficerText() {
		return officerText;
	}

	public void setOfficerText(String officerText) {
		this.officerText = officerText;
	}

	public String getSearchPlace() {
		return searchPlace;
	}

	public void setSearchPlace(String searchPlace) {
		this.searchPlace = searchPlace;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
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
