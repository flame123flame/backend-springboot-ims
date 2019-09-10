package th.go.excise.ims.ia.vo;

import java.time.LocalDate;
import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmQtD;

public class WsPmQtHVo {
	private Long pmQtHSeq;
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

	/* custom */

	private String processDateStr;
	private List<WsPmQtDVo> detail;

	public Long getPmQtHSeq() {
		return pmQtHSeq;
	}

	public void setPmQtHSeq(Long pmQtHSeq) {
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

	public List<WsPmQtDVo> getDetail() {
		return detail;
	}

	public void setDetail(List<WsPmQtDVo> detail) {
		this.detail = detail;
	}

}
