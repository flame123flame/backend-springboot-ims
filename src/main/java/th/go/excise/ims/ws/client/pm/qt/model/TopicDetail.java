package th.go.excise.ims.ws.client.pm.qt.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicDetail {

	@SerializedName("topicLevel")
	@Expose
	private String topicLevel;

	@SerializedName("topicCode")
	@Expose
	private String topicCode;

	@SerializedName("topicName")
	@Expose
	private String topicName;

	@SerializedName("topicAnswer")
	@Expose
	private String topicAnswer;

	@SerializedName("topicResult")
	@Expose
	private String topicResult;

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

	public String getTopicResult() {
		return topicResult;
	}

	public void setTopicResult(String topicResult) {
		this.topicResult = topicResult;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
