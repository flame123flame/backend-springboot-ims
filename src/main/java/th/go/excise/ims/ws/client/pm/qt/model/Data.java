package th.go.excise.ims.ws.client.pm.qt.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("offCode")
	@Expose
	private String offCode;

	@SerializedName("offName")
	@Expose
	private String offName;

	@SerializedName("formYear")
	@Expose
	private String formYear;

	@SerializedName("formCode")
	@Expose
	private String formCode;

	@SerializedName("formName")
	@Expose
	private String formName;

	@SerializedName("formRound")
	@Expose
	private String formRound;

	@SerializedName("formStatus")
	@Expose
	private String formStatus;

	@SerializedName("formStatusDesc")
	@Expose
	private String formStatusDesc;

	@SerializedName("summary")
	@Expose
	private String summary;

	@SerializedName("processBy")
	@Expose
	private String processBy;

	@SerializedName("processPosition")
	@Expose
	private String processPosition;

	@SerializedName("processDate")
	@Expose
	private String processDate;

	@SerializedName("updateDate")
	@Expose
	private String updateDate;

	@SerializedName("topicDetail")
	@Expose
	private List<TopicDetail> topicDetail;

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

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public List<TopicDetail> getTopicDetail() {
		return topicDetail;
	}

	public void setTopicDetail(List<TopicDetail> topicDetail) {
		this.topicDetail = topicDetail;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
