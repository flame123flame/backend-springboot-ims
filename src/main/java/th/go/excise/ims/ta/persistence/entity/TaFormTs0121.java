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
@Table(name = "TA_FORM_TS0121")
public class TaFormTs0121 extends BaseEntity {

	private static final long serialVersionUID = 7140752273253460324L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0121_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0121_GEN", sequenceName = "TA_FORM_TS0121_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0121_ID")
	private Long formTs0121Id;
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
	@Column(name = "OFFICER_SEND_FULL_NAME1")
	private String officerSendFullName1;
	@Column(name = "OFFICER_SEND_POSITION1")
	private String officerSendPosition1;
	@Column(name = "OFFICER_RECEIVE_FULL_NAME1")
	private String officerReceiveFullName1;
	@Column(name = "OFFICER_RECEIVE_POSITION1")
	private String officerReceivePosition1;
	@Column(name = "OFFICE_NAME")
	private String officeName;
	@Column(name = "DOC_DATE")
	private Date docDate;
	@Column(name = "COMD_DESC")
	private String comdDesc;
	@Column(name = "COMD_DATE")
	private Date comdDate;
	@Column(name = "OFFICER_SEND_FULL_NAME2")
	private String officerSendFullName2;
	@Column(name = "FACTORY_NAME2")
	private String factoryName2;
	@Column(name = "OFFICER_RECEIVE_FULL_NAME2")
	private String officerReceiveFullName2;
	@Column(name = "OFFICER_SEND_FULL_NAME3")
	private String officerSendFullName3;
	@Column(name = "OFFICER_RECEIVE_FULL_NAME3")
	private String officerReceiveFullName3;
	@Column(name = "FACTORY_NAME3")
	private String factoryName3;
	@Column(name = "DOC1_NUM")
	private String doc1Num;
	@Column(name = "DOC_ACCT1_NUM")
	private String docAcct1Num;
	@Column(name = "DOC_ACCT1_NO")
	private String docAcct1No;
	@Column(name = "DOC_ACCT2_NUM")
	private String docAcct2Num;
	@Column(name = "DOC_ACCT2_NO")
	private String docAcct2No;
	@Column(name = "DOC_OTHER")
	private String docOther;
	@Column(name = "SIGN_OFFICER_FULL_NAME1")
	private String signOfficerFullName1;
	@Column(name = "SIGN_OFFICER_FULL_NAME2")
	private String signOfficerFullName2;
	@Column(name = "SIGN_WITNESS_FULL_NAME1")
	private String signWitnessFullName1;
	@Column(name = "SIGN_WITNESS_FULL_NAME2")
	private String signWitnessFullName2;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0121Id() {
		return formTs0121Id;
	}

	public void setFormTs0121Id(Long formTs0121Id) {
		this.formTs0121Id = formTs0121Id;
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

	public String getOfficerSendFullName1() {
		return officerSendFullName1;
	}

	public void setOfficerSendFullName1(String officerSendFullName1) {
		this.officerSendFullName1 = officerSendFullName1;
	}

	public String getOfficerSendPosition1() {
		return officerSendPosition1;
	}

	public void setOfficerSendPosition1(String officerSendPosition1) {
		this.officerSendPosition1 = officerSendPosition1;
	}

	public String getOfficerReceiveFullName1() {
		return officerReceiveFullName1;
	}

	public void setOfficerReceiveFullName1(String officerReceiveFullName1) {
		this.officerReceiveFullName1 = officerReceiveFullName1;
	}

	public String getOfficerReceivePosition1() {
		return officerReceivePosition1;
	}

	public void setOfficerReceivePosition1(String officerReceivePosition1) {
		this.officerReceivePosition1 = officerReceivePosition1;
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

	public String getComdDesc() {
		return comdDesc;
	}

	public void setComdDesc(String comdDesc) {
		this.comdDesc = comdDesc;
	}

	public Date getComdDate() {
		return comdDate;
	}

	public void setComdDate(Date comdDate) {
		this.comdDate = comdDate;
	}

	public String getOfficerSendFullName2() {
		return officerSendFullName2;
	}

	public void setOfficerSendFullName2(String officerSendFullName2) {
		this.officerSendFullName2 = officerSendFullName2;
	}

	public String getFactoryName2() {
		return factoryName2;
	}

	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
	}

	public String getOfficerReceiveFullName2() {
		return officerReceiveFullName2;
	}

	public void setOfficerReceiveFullName2(String officerReceiveFullName2) {
		this.officerReceiveFullName2 = officerReceiveFullName2;
	}

	public String getOfficerSendFullName3() {
		return officerSendFullName3;
	}

	public void setOfficerSendFullName3(String officerSendFullName3) {
		this.officerSendFullName3 = officerSendFullName3;
	}

	public String getOfficerReceiveFullName3() {
		return officerReceiveFullName3;
	}

	public void setOfficerReceiveFullName3(String officerReceiveFullName3) {
		this.officerReceiveFullName3 = officerReceiveFullName3;
	}

	public String getFactoryName3() {
		return factoryName3;
	}

	public void setFactoryName3(String factoryName3) {
		this.factoryName3 = factoryName3;
	}

	public String getDoc1Num() {
		return doc1Num;
	}

	public void setDoc1Num(String doc1Num) {
		this.doc1Num = doc1Num;
	}

	public String getDocAcct1Num() {
		return docAcct1Num;
	}

	public void setDocAcct1Num(String docAcct1Num) {
		this.docAcct1Num = docAcct1Num;
	}

	public String getDocAcct1No() {
		return docAcct1No;
	}

	public void setDocAcct1No(String docAcct1No) {
		this.docAcct1No = docAcct1No;
	}

	public String getDocAcct2Num() {
		return docAcct2Num;
	}

	public void setDocAcct2Num(String docAcct2Num) {
		this.docAcct2Num = docAcct2Num;
	}

	public String getDocAcct2No() {
		return docAcct2No;
	}

	public void setDocAcct2No(String docAcct2No) {
		this.docAcct2No = docAcct2No;
	}

	public String getDocOther() {
		return docOther;
	}

	public void setDocOther(String docOther) {
		this.docOther = docOther;
	}

	public String getSignOfficerFullName1() {
		return signOfficerFullName1;
	}

	public void setSignOfficerFullName1(String signOfficerFullName1) {
		this.signOfficerFullName1 = signOfficerFullName1;
	}

	public String getSignOfficerFullName2() {
		return signOfficerFullName2;
	}

	public void setSignOfficerFullName2(String signOfficerFullName2) {
		this.signOfficerFullName2 = signOfficerFullName2;
	}

	public String getSignWitnessFullName1() {
		return signWitnessFullName1;
	}

	public void setSignWitnessFullName1(String signWitnessFullName1) {
		this.signWitnessFullName1 = signWitnessFullName1;
	}

	public String getSignWitnessFullName2() {
		return signWitnessFullName2;
	}

	public void setSignWitnessFullName2(String signWitnessFullName2) {
		this.signWitnessFullName2 = signWitnessFullName2;
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
