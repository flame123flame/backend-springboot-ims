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
@Table(name = "TA_FORM_TS0116")
public class TaFormTs0116 extends BaseEntity {

	private static final long serialVersionUID = -39668813174972492L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_FORM_TS0116_GEN")
	@SequenceGenerator(name = "TA_FORM_TS0116_GEN", sequenceName = "TA_FORM_TS0116_SEQ", allocationSize = 1)
	@Column(name = "FORM_TS0116_ID")
	private Long formTs0116Id;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "BUDGET_YEAR")
	private String budgetYear;
	@Column(name = "FORM_TS_NUMBER")
	private String formTsNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "DOC_TEXT")
	private String docText;
	@Column(name = "DOC_DEAR")
	private String docDear;
	@Column(name = "FACTORY_NAME1")
	private String factoryName1;
	@Column(name = "FACTORY_TYPE")
	private String factoryType;
	@Column(name = "FACTORY_NAME2")
	private String factoryName2;
	@Column(name = "REQUEST_DATE")
	private Date requestDate;
	@Column(name = "REQUEST_TYPE_EXCEPT")
	private String requestTypeExcept;
	@Column(name = "REQUEST_TYPE_REDUCE")
	private String requestTypeReduce;
	@Column(name = "REQUEST_TYPE_FINE_AMT")
	private String requestTypeFineAmt;
	@Column(name = "REQUEST_TYPE_EXTRA_AMT")
	private String requestTypeExtraAmt;
	@Column(name = "REQUEST_REASON")
	private String requestReason;
	@Column(name = "REQUEST_DESC")
	private String requestDesc;
	@Column(name = "FINE_NO_FLAG")
	private String fineNoFlag;
	@Column(name = "FINE_EXCEPT_AMT_FLAG")
	private String fineExceptAmtFlag;
	@Column(name = "FIND_REDUCE_AMT_FLAG")
	private String findReduceAmtFlag;
	@Column(name = "FINE_PERCENT")
	private BigDecimal finePercent;
	@Column(name = "EXTRA_NO_FLAG")
	private String extraNoFlag;
	@Column(name = "EXTRA_REDUCE_AMT_FLAG")
	private String extraReduceAmtFlag;
	@Column(name = "EXTRA_PERCENT")
	private BigDecimal extraPercent;
	@Column(name = "BEFORE_TAX_AMT")
	private BigDecimal beforeTaxAmt;
	@Column(name = "BEFORE_FINE_PERCENT")
	private BigDecimal beforeFinePercent;
	@Column(name = "BEFORE_FINE_AMT")
	private BigDecimal beforeFineAmt;
	@Column(name = "BEFORE_EXTRA_AMT")
	private BigDecimal beforeExtraAmt;
	@Column(name = "BEFORE_MOI_AMT")
	private BigDecimal beforeMoiAmt;
	@Column(name = "BEFORE_SUM_AMT")
	private BigDecimal beforeSumAmt;
	@Column(name = "AFTER_TAX_AMT")
	private BigDecimal afterTaxAmt;
	@Column(name = "AFTER_FINE_PERCENT")
	private BigDecimal afterFinePercent;
	@Column(name = "AFTER_FINE_AMT")
	private BigDecimal afterFineAmt;
	@Column(name = "AFTER_EXTRA_AMT")
	private BigDecimal afterExtraAmt;
	@Column(name = "AFTER_MOI_AMT")
	private BigDecimal afterMoiAmt;
	@Column(name = "AFTER_SUM_AMT")
	private BigDecimal afterSumAmt;
	@Column(name = "SIGN_OFFICER_FULL_NAME")
	private String signOfficerFullName;
	@Column(name = "SIGN_OFFICER_POSITION")
	private String signOfficerPosition;
	@Column(name = "HEAD_OFFICER_COMMENT")
	private String headOfficerComment;
	@Column(name = "SIGN_HEAD_OFFICER_FULL_NAME")
	private String signHeadOfficerFullName;
	@Column(name = "SIGN_HEAD_OFFICER_POSITION")
	private String signHeadOfficerPosition;
	@Column(name = "SIGN_HEAD_OFFICER_DATE")
	private Date signHeadOfficerDate;
	@Column(name = "APPROVER_COMMENT")
	private String approverComment;
	@Column(name = "SIGN_APPROVER_FULL_NAME")
	private String signApproverFullName;
	@Column(name = "SIGN_APPROVER_POSITION")
	private String signApproverPosition;
	@Column(name = "SIGN_APPROVER_DATE")
	private Date signApproverDate;
	@Column(name = "AUDIT_PLAN_CODE")
	private String auditPlanCode;

	public Long getFormTs0116Id() {
		return formTs0116Id;
	}

	public void setFormTs0116Id(Long formTs0116Id) {
		this.formTs0116Id = formTs0116Id;
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

	public String getDocText() {
		return docText;
	}

	public void setDocText(String docText) {
		this.docText = docText;
	}

	public String getDocDear() {
		return docDear;
	}

	public void setDocDear(String docDear) {
		this.docDear = docDear;
	}

	public String getFactoryName1() {
		return factoryName1;
	}

	public void setFactoryName1(String factoryName1) {
		this.factoryName1 = factoryName1;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryName2() {
		return factoryName2;
	}

	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getRequestTypeExcept() {
		return requestTypeExcept;
	}

	public void setRequestTypeExcept(String requestTypeExcept) {
		this.requestTypeExcept = requestTypeExcept;
	}

	public String getRequestTypeReduce() {
		return requestTypeReduce;
	}

	public void setRequestTypeReduce(String requestTypeReduce) {
		this.requestTypeReduce = requestTypeReduce;
	}

	public String getRequestTypeFineAmt() {
		return requestTypeFineAmt;
	}

	public void setRequestTypeFineAmt(String requestTypeFineAmt) {
		this.requestTypeFineAmt = requestTypeFineAmt;
	}

	public String getRequestTypeExtraAmt() {
		return requestTypeExtraAmt;
	}

	public void setRequestTypeExtraAmt(String requestTypeExtraAmt) {
		this.requestTypeExtraAmt = requestTypeExtraAmt;
	}

	public String getRequestReason() {
		return requestReason;
	}

	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}

	public String getRequestDesc() {
		return requestDesc;
	}

	public void setRequestDesc(String requestDesc) {
		this.requestDesc = requestDesc;
	}

	public String getFineNoFlag() {
		return fineNoFlag;
	}

	public void setFineNoFlag(String fineNoFlag) {
		this.fineNoFlag = fineNoFlag;
	}

	public String getFineExceptAmtFlag() {
		return fineExceptAmtFlag;
	}

	public void setFineExceptAmtFlag(String fineExceptAmtFlag) {
		this.fineExceptAmtFlag = fineExceptAmtFlag;
	}

	public String getFindReduceAmtFlag() {
		return findReduceAmtFlag;
	}

	public void setFindReduceAmtFlag(String findReduceAmtFlag) {
		this.findReduceAmtFlag = findReduceAmtFlag;
	}

	public BigDecimal getFinePercent() {
		return finePercent;
	}

	public void setFinePercent(BigDecimal finePercent) {
		this.finePercent = finePercent;
	}

	public String getExtraNoFlag() {
		return extraNoFlag;
	}

	public void setExtraNoFlag(String extraNoFlag) {
		this.extraNoFlag = extraNoFlag;
	}

	public String getExtraReduceAmtFlag() {
		return extraReduceAmtFlag;
	}

	public void setExtraReduceAmtFlag(String extraReduceAmtFlag) {
		this.extraReduceAmtFlag = extraReduceAmtFlag;
	}

	public BigDecimal getExtraPercent() {
		return extraPercent;
	}

	public void setExtraPercent(BigDecimal extraPercent) {
		this.extraPercent = extraPercent;
	}

	public BigDecimal getBeforeTaxAmt() {
		return beforeTaxAmt;
	}

	public void setBeforeTaxAmt(BigDecimal beforeTaxAmt) {
		this.beforeTaxAmt = beforeTaxAmt;
	}

	public BigDecimal getBeforeFinePercent() {
		return beforeFinePercent;
	}

	public void setBeforeFinePercent(BigDecimal beforeFinePercent) {
		this.beforeFinePercent = beforeFinePercent;
	}

	public BigDecimal getBeforeFineAmt() {
		return beforeFineAmt;
	}

	public void setBeforeFineAmt(BigDecimal beforeFineAmt) {
		this.beforeFineAmt = beforeFineAmt;
	}

	public BigDecimal getBeforeExtraAmt() {
		return beforeExtraAmt;
	}

	public void setBeforeExtraAmt(BigDecimal beforeExtraAmt) {
		this.beforeExtraAmt = beforeExtraAmt;
	}

	public BigDecimal getBeforeMoiAmt() {
		return beforeMoiAmt;
	}

	public void setBeforeMoiAmt(BigDecimal beforeMoiAmt) {
		this.beforeMoiAmt = beforeMoiAmt;
	}

	public BigDecimal getBeforeSumAmt() {
		return beforeSumAmt;
	}

	public void setBeforeSumAmt(BigDecimal beforeSumAmt) {
		this.beforeSumAmt = beforeSumAmt;
	}

	public BigDecimal getAfterTaxAmt() {
		return afterTaxAmt;
	}

	public void setAfterTaxAmt(BigDecimal afterTaxAmt) {
		this.afterTaxAmt = afterTaxAmt;
	}

	public BigDecimal getAfterFinePercent() {
		return afterFinePercent;
	}

	public void setAfterFinePercent(BigDecimal afterFinePercent) {
		this.afterFinePercent = afterFinePercent;
	}

	public BigDecimal getAfterFineAmt() {
		return afterFineAmt;
	}

	public void setAfterFineAmt(BigDecimal afterFineAmt) {
		this.afterFineAmt = afterFineAmt;
	}

	public BigDecimal getAfterExtraAmt() {
		return afterExtraAmt;
	}

	public void setAfterExtraAmt(BigDecimal afterExtraAmt) {
		this.afterExtraAmt = afterExtraAmt;
	}

	public BigDecimal getAfterMoiAmt() {
		return afterMoiAmt;
	}

	public void setAfterMoiAmt(BigDecimal afterMoiAmt) {
		this.afterMoiAmt = afterMoiAmt;
	}

	public BigDecimal getAfterSumAmt() {
		return afterSumAmt;
	}

	public void setAfterSumAmt(BigDecimal afterSumAmt) {
		this.afterSumAmt = afterSumAmt;
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

	public String getSignHeadOfficerPosition() {
		return signHeadOfficerPosition;
	}

	public void setSignHeadOfficerPosition(String signHeadOfficerPosition) {
		this.signHeadOfficerPosition = signHeadOfficerPosition;
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

	public String getSignApproverFullName() {
		return signApproverFullName;
	}

	public void setSignApproverFullName(String signApproverFullName) {
		this.signApproverFullName = signApproverFullName;
	}

	public String getSignApproverPosition() {
		return signApproverPosition;
	}

	public void setSignApproverPosition(String signApproverPosition) {
		this.signApproverPosition = signApproverPosition;
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
