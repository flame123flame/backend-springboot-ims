package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class IaAuditPmassessDVo {
	private BigDecimal auditPmDId;
	private String auditPmAssessNo;
	private String auditResult;
	private BigDecimal pmAssessDSeq;
	private String offCode;
	private String formCode;
	private BigDecimal topicLevel;
	private String topicCode;
	private String topicName;
	private String topicAnswer;

	public BigDecimal getAuditPmDId() {
		return auditPmDId;
	}

	public void setAuditPmDId(BigDecimal auditPmDId) {
		this.auditPmDId = auditPmDId;
	}

	public String getAuditPmAssessNo() {
		return auditPmAssessNo;
	}

	public void setAuditPmAssessNo(String auditPmAssessNo) {
		this.auditPmAssessNo = auditPmAssessNo;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public BigDecimal getPmAssessDSeq() {
		return pmAssessDSeq;
	}

	public void setPmAssessDSeq(BigDecimal pmAssessDSeq) {
		this.pmAssessDSeq = pmAssessDSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public BigDecimal getTopicLevel() {
		return topicLevel;
	}

	public void setTopicLevel(BigDecimal topicLevel) {
		this.topicLevel = topicLevel;
	}

	public String getTopicCode() {
		return topicCode;
	}

	public void setTopicCode(String topicCode) {
		this.topicCode = topicCode;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicAnswer() {
		return topicAnswer;
	}

	public void setTopicAnswer(String topicAnswer) {
		this.topicAnswer = topicAnswer;
	}

}