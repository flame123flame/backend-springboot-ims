package th.go.excise.ims.ia.vo;

import java.time.LocalDate;
import java.util.List;

public class WsPmAssessHVo {
	/* entity */
	private Long pmAssessHSeq;
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
	private LocalDate processDate;
	private String pmaAuditResult;
	private String pmaAuditEvident;
	private String pmaAuditSuggestion;

	/* custom */
	private String processDateStr;
	private List<WsPmAssessDVo> detail;

	public Long getPmAssessHSeq() {
		return pmAssessHSeq;
	}

	public void setPmAssessHSeq(Long pmAssessHSeq) {
		this.pmAssessHSeq = pmAssessHSeq;
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

	public LocalDate getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDate processDate) {
		this.processDate = processDate;
	}

	public String getProcessDateStr() {
		return processDateStr;
	}

	public void setProcessDateStr(String processDateStr) {
		this.processDateStr = processDateStr;
	}

	public List<WsPmAssessDVo> getDetail() {
		return detail;
	}

	public void setDetail(List<WsPmAssessDVo> detail) {
		this.detail = detail;
	}

	public String getPmaAuditResult() {
		return pmaAuditResult;
	}

	public void setPmaAuditResult(String pmaAuditResult) {
		this.pmaAuditResult = pmaAuditResult;
	}

	public String getPmaAuditEvident() {
		return pmaAuditEvident;
	}

	public void setPmaAuditEvident(String pmaAuditEvident) {
		this.pmaAuditEvident = pmaAuditEvident;
	}

	public String getPmaAuditSuggestion() {
		return pmaAuditSuggestion;
	}

	public void setPmaAuditSuggestion(String pmaAuditSuggestion) {
		this.pmaAuditSuggestion = pmaAuditSuggestion;
	}

}