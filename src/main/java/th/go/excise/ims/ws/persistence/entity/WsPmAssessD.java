package th.go.excise.ims.ws.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "WS_PM_ASSESS_D")
public class WsPmAssessD extends BaseEntity {

	private static final long serialVersionUID = 9069137448418733141L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_PM_ASSESS_D_GEN")
	@SequenceGenerator(name = "WS_PM_ASSESS_D_GEN", sequenceName = "WS_PM_ASSESS_D_SEQ", allocationSize = 1)
	@Column(name = "PM_ASSESS_D_SEQ")
	private Long pmAssessDSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "TOPIC_LEVEL")
	private String topicLevel;
	@Column(name = "TOPIC_CODE")
	private String topicCode;
	@Column(name = "TOPIC_NAME")
	private String topicName;
	@Column(name = "TOPIC_ANSWER")
	private String topicAnswer;

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

}
