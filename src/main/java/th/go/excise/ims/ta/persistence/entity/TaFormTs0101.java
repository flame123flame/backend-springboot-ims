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
@Table(name = "TA_FORM_TS0101")
public class TaFormTs0101 extends BaseEntity {

	private static final long serialVersionUID = -493405425298185645L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0101_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0101_GEN", sequenceName = "TA_FORM_TS0101_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0101_ID")
	private Long formTs0101Id;
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
	@Column(name = "FACTORY_TYPE_TEXT")
	private String factoryTypeText;
	@Column(name = "FACTORY_ADDRESS")
	private String factoryAddress;
	@Column(name = "ANALYSIS_DATE_START")
	private Date analysisDateStart;
	@Column(name = "ANALYSIS_DATE_END")
	private Date analysisDateEnd;
	@Column(name = "ANALYSIS_DATA1")
	private String analysisData1;
	@Column(name = "ANALYSIS_DATA2")
	private String analysisData2;
	@Column(name = "ANALYSIS_DATA3")
	private String analysisData3;
	@Column(name = "ANALYSIS_DATA4")
	private String analysisData4;
	@Column(name = "ANALYSIS_DATA5")
	private String analysisData5;
	@Column(name = "ANALYSIS_RESULT_DEAR")
	private String analysisResultDear;
	@Column(name = "ANALYSIS_RESULT_TEXT")
	private String analysisResultText;
	@Column(name = "CALL_AUDIT_FLAG")
	private String callAuditFlag;
	@Column(name = "OTHER_TEXT")
	private String otherText;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_SUP_OFFICER_FULL_NAME")
	private String signSupOfficerFullName;
	@Column(name = "SIGN_OFFICER_DATE")
	private Date signOfficerDate;
	@Column(name = "APPROVED_FLAG")
	private String approvedFlag;
	@Column(name = "SIGN_APPR_OFFICER_FULL_NAME")
	private String signApprOfficerFullName;
	@Column(name = "SIGN_APPR_OFFICER_POSITION")
	private String signApprOfficerPosition;
	@Column(name = "SIGN_APPR_DATE")
	private Date signApprDate;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0101Id() {
		return formTs0101Id;
	}

	public void setFormTs0101Id(Long formTs0101Id) {
		this.formTs0101Id = formTs0101Id;
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

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

	public String getFactoryAddress() {
		return factoryAddress;
	}

	public void setFactoryAddress(String factoryAddress) {
		this.factoryAddress = factoryAddress;
	}

	public Date getAnalysisDateStart() {
		return analysisDateStart;
	}

	public void setAnalysisDateStart(Date analysisDateStart) {
		this.analysisDateStart = analysisDateStart;
	}

	public Date getAnalysisDateEnd() {
		return analysisDateEnd;
	}

	public void setAnalysisDateEnd(Date analysisDateEnd) {
		this.analysisDateEnd = analysisDateEnd;
	}

	public String getAnalysisData1() {
		return analysisData1;
	}

	public void setAnalysisData1(String analysisData1) {
		this.analysisData1 = analysisData1;
	}

	public String getAnalysisData2() {
		return analysisData2;
	}

	public void setAnalysisData2(String analysisData2) {
		this.analysisData2 = analysisData2;
	}

	public String getAnalysisData3() {
		return analysisData3;
	}

	public void setAnalysisData3(String analysisData3) {
		this.analysisData3 = analysisData3;
	}

	public String getAnalysisData4() {
		return analysisData4;
	}

	public void setAnalysisData4(String analysisData4) {
		this.analysisData4 = analysisData4;
	}

	public String getAnalysisData5() {
		return analysisData5;
	}

	public void setAnalysisData5(String analysisData5) {
		this.analysisData5 = analysisData5;
	}

	public String getAnalysisResultDear() {
		return analysisResultDear;
	}

	public void setAnalysisResultDear(String analysisResultDear) {
		this.analysisResultDear = analysisResultDear;
	}

	public String getAnalysisResultText() {
		return analysisResultText;
	}

	public void setAnalysisResultText(String analysisResultText) {
		this.analysisResultText = analysisResultText;
	}

	public String getCallAuditFlag() {
		return callAuditFlag;
	}

	public void setCallAuditFlag(String callAuditFlag) {
		this.callAuditFlag = callAuditFlag;
	}

	public String getOtherText() {
		return otherText;
	}

	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}

	public String getSignOfficerFullName() {
		return signOfficerFullName;
	}

	public void setSignOfficerFullName(String signOfficerFullName) {
		this.signOfficerFullName = signOfficerFullName;
	}

	public String getSignSupOfficerFullName() {
		return signSupOfficerFullName;
	}

	public void setSignSupOfficerFullName(String signSupOfficerFullName) {
		this.signSupOfficerFullName = signSupOfficerFullName;
	}

	public Date getSignOfficerDate() {
		return signOfficerDate;
	}

	public void setSignOfficerDate(Date signOfficerDate) {
		this.signOfficerDate = signOfficerDate;
	}

	public String getApprovedFlag() {
		return approvedFlag;
	}

	public void setApprovedFlag(String approvedFlag) {
		this.approvedFlag = approvedFlag;
	}

	public String getSignApprOfficerFullName() {
		return signApprOfficerFullName;
	}

	public void setSignApprOfficerFullName(String signApprOfficerFullName) {
		this.signApprOfficerFullName = signApprOfficerFullName;
	}

	public String getSignApprOfficerPosition() {
		return signApprOfficerPosition;
	}

	public void setSignApprOfficerPosition(String signApprOfficerPosition) {
		this.signApprOfficerPosition = signApprOfficerPosition;
	}

	public Date getSignApprDate() {
		return signApprDate;
	}

	public void setSignApprDate(Date signApprDate) {
		this.signApprDate = signApprDate;
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
