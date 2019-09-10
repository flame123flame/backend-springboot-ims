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
@Table(name = "WS_PM_PY1_D")
public class WsPmPy1D extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 506608583377558071L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WS_PM_PY1_D_GEN")
	@SequenceGenerator(name = "WS_PM_PY1_D_GEN", sequenceName = "WS_PM_PY1_D_SEQ", allocationSize = 1)
	@Column(name = "PM_PY1_D_SEQ")
	private Long pmPy1DSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "TOPIC_CODE")
	private String topicCode;
	@Column(name = "TOPIC_NAME")
	private String topicName;
	@Column(name = "TOPIC_DESC")
	private String topicDesc;
	@Column(name = "TOPIC_ANSWER")
	private String topicAnswer;

	public Long getPmPy1DSeq() {
		return pmPy1DSeq;
	}

	public void setPmPy1DSeq(Long pmPy1DSeq) {
		this.pmPy1DSeq = pmPy1DSeq;
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

	public String getTopicDesc() {
		return topicDesc;
	}

	public void setTopicDesc(String topicDesc) {
		this.topicDesc = topicDesc;
	}

	public String getTopicAnswer() {
		return topicAnswer;
	}

	public void setTopicAnswer(String topicAnswer) {
		this.topicAnswer = topicAnswer;
	}

}
