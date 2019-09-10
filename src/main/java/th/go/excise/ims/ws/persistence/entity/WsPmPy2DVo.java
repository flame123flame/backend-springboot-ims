package th.go.excise.ims.ws.persistence.entity;

import java.time.LocalDate;

public class WsPmPy2DVo {
	private Long pmPy2DSeq;
	private String offCode;
	private String formCode;
	private Integer divSeq;
	private String divName;
	private String jobName;
	private String processBy;
	private String processPosition;
	private LocalDate processDate;
	private Integer py2TopicSeq;
	private String py2TopicName;
	private String py2Topic1Main;
	private String py2Topic2Ctl;
	private String py2Topic3Assess;
	private String py2Topic4Risk;
	private String py2Topic5Improve;
	private String py2Topic6Owner;
	private String py2Topic7Remark;

	/* custom */
	private String processDateStr;
	private String py2AuditResult;

	public Long getPmPy2DSeq() {
		return pmPy2DSeq;
	}

	public void setPmPy2DSeq(Long pmPy2DSeq) {
		this.pmPy2DSeq = pmPy2DSeq;
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

	public Integer getDivSeq() {
		return divSeq;
	}

	public void setDivSeq(Integer divSeq) {
		this.divSeq = divSeq;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getProcessBy() {
		return processBy;
	}

	public void setProcessBy(String processBy) {
		this.processBy = processBy;
	}

	public String getProcessPosition() {
		return processPosition;
	}

	public void setProcessPosition(String processPosition) {
		this.processPosition = processPosition;
	}

	public LocalDate getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDate processDate) {
		this.processDate = processDate;
	}

	public Integer getPy2TopicSeq() {
		return py2TopicSeq;
	}

	public void setPy2TopicSeq(Integer py2TopicSeq) {
		this.py2TopicSeq = py2TopicSeq;
	}

	public String getPy2TopicName() {
		return py2TopicName;
	}

	public void setPy2TopicName(String py2TopicName) {
		this.py2TopicName = py2TopicName;
	}

	public String getPy2Topic1Main() {
		return py2Topic1Main;
	}

	public void setPy2Topic1Main(String py2Topic1Main) {
		this.py2Topic1Main = py2Topic1Main;
	}

	public String getPy2Topic2Ctl() {
		return py2Topic2Ctl;
	}

	public void setPy2Topic2Ctl(String py2Topic2Ctl) {
		this.py2Topic2Ctl = py2Topic2Ctl;
	}

	public String getPy2Topic3Assess() {
		return py2Topic3Assess;
	}

	public void setPy2Topic3Assess(String py2Topic3Assess) {
		this.py2Topic3Assess = py2Topic3Assess;
	}

	public String getPy2Topic4Risk() {
		return py2Topic4Risk;
	}

	public void setPy2Topic4Risk(String py2Topic4Risk) {
		this.py2Topic4Risk = py2Topic4Risk;
	}

	public String getPy2Topic5Improve() {
		return py2Topic5Improve;
	}

	public void setPy2Topic5Improve(String py2Topic5Improve) {
		this.py2Topic5Improve = py2Topic5Improve;
	}

	public String getPy2Topic6Owner() {
		return py2Topic6Owner;
	}

	public void setPy2Topic6Owner(String py2Topic6Owner) {
		this.py2Topic6Owner = py2Topic6Owner;
	}

	public String getPy2Topic7Remark() {
		return py2Topic7Remark;
	}

	public void setPy2Topic7Remark(String py2Topic7Remark) {
		this.py2Topic7Remark = py2Topic7Remark;
	}

	public String getProcessDateStr() {
		return processDateStr;
	}

	public void setProcessDateStr(String processDateStr) {
		this.processDateStr = processDateStr;
	}

	public String getPy2AuditResult() {
		return py2AuditResult;
	}

	public void setPy2AuditResult(String py2AuditResult) {
		this.py2AuditResult = py2AuditResult;
	}

}
