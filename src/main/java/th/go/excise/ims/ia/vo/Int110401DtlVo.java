package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Int110401DtlVo {
	private BigDecimal id;
	private BigDecimal idFollowRecommendHdr;
	private String followNotifyBookNumber;
	private String followNotifyDateStr;
	private String daedlinesStartStr;
	private String daedlinesEndStr;
	private String resultNotifyBookNumber;
	private String resultNotifyDateStr;
	private String followReportBookNumber;
	private String followReportDateStr;
	private Date followReportDate;
	private Date resultNotifyDate;
	private Date followNotifyDate;
	private BigDecimal timeNotify;
	
	private Boolean flagUpdate;

	public BigDecimal getIdFollowRecommendHdr() {
		return idFollowRecommendHdr;
	}

	public void setIdFollowRecommendHdr(BigDecimal idFollowRecommendHdr) {
		this.idFollowRecommendHdr = idFollowRecommendHdr;
	}

	public String getFollowNotifyBookNumber() {
		return followNotifyBookNumber;
	}

	public void setFollowNotifyBookNumber(String followNotifyBookNumber) {
		this.followNotifyBookNumber = followNotifyBookNumber;
	}

	public String getFollowNotifyDateStr() {
		return followNotifyDateStr;
	}

	public void setFollowNotifyDateStr(String followNotifyDateStr) {
		this.followNotifyDateStr = followNotifyDateStr;
	}

	public String getResultNotifyBookNumber() {
		return resultNotifyBookNumber;
	}

	public void setResultNotifyBookNumber(String resultNotifyBookNumber) {
		this.resultNotifyBookNumber = resultNotifyBookNumber;
	}

	public String getResultNotifyDateStr() {
		return resultNotifyDateStr;
	}

	public void setResultNotifyDateStr(String resultNotifyDateStr) {
		this.resultNotifyDateStr = resultNotifyDateStr;
	}

	public String getFollowReportBookNumber() {
		return followReportBookNumber;
	}

	public void setFollowReportBookNumber(String followReportBookNumber) {
		this.followReportBookNumber = followReportBookNumber;
	}

	public String getFollowReportDateStr() {
		return followReportDateStr;
	}

	public void setFollowReportDateStr(String followReportDateStr) {
		this.followReportDateStr = followReportDateStr;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Date getFollowReportDate() {
		return followReportDate;
	}

	public void setFollowReportDate(Date followReportDate) {
		this.followReportDate = followReportDate;
	}

	public Date getResultNotifyDate() {
		return resultNotifyDate;
	}

	public void setResultNotifyDate(Date resultNotifyDate) {
		this.resultNotifyDate = resultNotifyDate;
	}

	public Date getFollowNotifyDate() {
		return followNotifyDate;
	}

	public void setFollowNotifyDate(Date followNotifyDate) {
		this.followNotifyDate = followNotifyDate;
	}

	public BigDecimal getTimeNotify() {
		return timeNotify;
	}

	public void setTimeNotify(BigDecimal timeNotify) {
		this.timeNotify = timeNotify;
	}

	public String getDaedlinesStartStr() {
		return daedlinesStartStr;
	}

	public void setDaedlinesStartStr(String daedlinesStartStr) {
		this.daedlinesStartStr = daedlinesStartStr;
	}

	public String getDaedlinesEndStr() {
		return daedlinesEndStr;
	}

	public void setDaedlinesEndStr(String daedlinesEndStr) {
		this.daedlinesEndStr = daedlinesEndStr;
	}

	public Boolean getFlagUpdate() {
		return flagUpdate;
	}

	public void setFlagUpdate(Boolean flagUpdate) {
		this.flagUpdate = flagUpdate;
	}

}
