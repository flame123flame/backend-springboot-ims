package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TaFormTS0116Vo extends AbstractTaFormTsVo {

	private String docText;
	private String docDear;
	private String factoryName1;
	private String factoryName2;
	private String factoryType;
	private String newRegId;
	private Date requestDate;
	private String requestTypeExcept;
	private String requestTypeReduce;
	private String requestTypeFineAmt;
	private String requestTypeExtraAmt;
	private String requestReason;
	private String requestDesc;
	private String fineNoFlag;
	private String fineExceptAmtFlag;
	private String fineReduceAmtFlag;
	private BigDecimal finePercent;
	private String extraNoFlag;
	private String extraReduceAmtFlag;
	private BigDecimal extraPercent;
	private BigDecimal beforeTaxAmt;
	private BigDecimal beforeFinePercent;
	private BigDecimal beforeFineAmt;
	private BigDecimal beforeExtraAmt;
	private BigDecimal beforeMoiAmt;
	private BigDecimal beforeSumAmt;
	private BigDecimal afterTaxAmt;
	private BigDecimal afterFinePercent;
	private BigDecimal afterFineAmt;
	private BigDecimal afterExtraAmt;
	private BigDecimal afterMoiAmt;
	private BigDecimal afterSumAmt;
	private String signOfficerFullName;
	private String signOfficerPosition;
	private String headOfficerComment;
	private String signHeadOfficerFullName;
	private String signHeadOfficerPosition;
	private Date signHeadOfficerDate;
	private String approverComment;
	private String signApproverFullName;
	private String signApproverPosition;
	private Date signApproverDate;

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

	public String getFactoryName2() {
		return factoryName2;
	}

	public void setFactoryName2(String factoryName2) {
		this.factoryName2 = factoryName2;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
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

	public String getFineReduceAmtFlag() {
		return fineReduceAmtFlag;
	}

	public void setFineReduceAmtFlag(String fineReduceAmtFlag) {
		this.fineReduceAmtFlag = fineReduceAmtFlag;
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

}
