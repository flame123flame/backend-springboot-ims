package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class IaAuditPmQtHVo {
	private BigDecimal auditPmqtHId;
	private String auditPmqtNo;
	private String buggetYear;
	private BigDecimal pmQtHSeq;
	private String offCode;
	private String offName;
	private String formYear;
	private String formCode;
	private String formName;
	private String formRound;
	private String formStatus;
	private String formStatusDesc;
	private String summary;
	private String processBy;
	private String processPosition;
	private Date processDate;
	private String qtAuditResult;
	private String qtAuditEvident;
	private String qtAuditSuggestion;

	/* custom */
	private String processDateStr;
	private List<IaAuditPmQtDVo> detail;

	public BigDecimal getAuditPmqtHId() {
		return auditPmqtHId;
	}

	public void setAuditPmqtHId(BigDecimal auditPmqtHId) {
		this.auditPmqtHId = auditPmqtHId;
	}

	public String getAuditPmqtNo() {
		return auditPmqtNo;
	}

	public void setAuditPmqtNo(String auditPmqtNo) {
		this.auditPmqtNo = auditPmqtNo;
	}

	public String getBuggetYear() {
		return buggetYear;
	}

	public void setBuggetYear(String buggetYear) {
		this.buggetYear = buggetYear;
	}

	public BigDecimal getPmQtHSeq() {
		return pmQtHSeq;
	}

	public void setPmQtHSeq(BigDecimal pmQtHSeq) {
		this.pmQtHSeq = pmQtHSeq;
	}

	public String getOffCode() {
		return offCode;
	}

	public void setOffCode(String offCode) {
		this.offCode = offCode;
	}

	public String getOffName() {
		return offName;
	}

	public void setOffName(String offName) {
		this.offName = offName;
	}

	public String getFormYear() {
		return formYear;
	}

	public void setFormYear(String formYear) {
		this.formYear = formYear;
	}

	public String getFormCode() {
		return formCode;
	}

	public void setFormCode(String formCode) {
		this.formCode = formCode;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getFormRound() {
		return formRound;
	}

	public void setFormRound(String formRound) {
		this.formRound = formRound;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

	public String getFormStatusDesc() {
		return formStatusDesc;
	}

	public void setFormStatusDesc(String formStatusDesc) {
		this.formStatusDesc = formStatusDesc;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getQtAuditResult() {
		return qtAuditResult;
	}

	public void setQtAuditResult(String qtAuditResult) {
		this.qtAuditResult = qtAuditResult;
	}

	public String getQtAuditEvident() {
		return qtAuditEvident;
	}

	public void setQtAuditEvident(String qtAuditEvident) {
		this.qtAuditEvident = qtAuditEvident;
	}

	public String getQtAuditSuggestion() {
		return qtAuditSuggestion;
	}

	public void setQtAuditSuggestion(String qtAuditSuggestion) {
		this.qtAuditSuggestion = qtAuditSuggestion;
	}

	public String getProcessDateStr() {
		return processDateStr;
	}

	public void setProcessDateStr(String processDateStr) {
		this.processDateStr = processDateStr;
	}

	public List<IaAuditPmQtDVo> getDetail() {
		return detail;
	}

	public void setDetail(List<IaAuditPmQtDVo> detail) {
		this.detail = detail;
	}

}
