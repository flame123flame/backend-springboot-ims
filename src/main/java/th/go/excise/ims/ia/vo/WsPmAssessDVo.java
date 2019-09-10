package th.go.excise.ims.ia.vo;

public class WsPmAssessDVo {
	/* entity */
	private Long pmAssessDSeq;
	private String offCode;
	private String formCode;
	private String topicLevel;
	private String topicCode;
	private String topicName;
	private String topicAnswer;

	/* custom */
	private String auditResult;

	public Long getPmAssessDSeq() {
		return pmAssessDSeq;
	}

	public void setPmAssessDSeq(Long pmAssessDSeq) {
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

	public String getTopicLevel() {
		return topicLevel;
	}

	public void setTopicLevel(String topicLevel) {
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

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

}
