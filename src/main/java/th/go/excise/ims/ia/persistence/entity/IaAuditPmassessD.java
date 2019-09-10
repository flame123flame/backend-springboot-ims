
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_PMASSESS_D")
public class IaAuditPmassessD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8156573913018319179L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PMASSESS_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_PMASSESS_D_GEN", sequenceName = "IA_AUDIT_PMASSESS_D_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_PM_D_ID")
	private BigDecimal auditPmDId;
	@Column(name = "AUDIT_PM_ASSESS_NO")
	private String auditPmAssessNo;
	@Column(name = "AUDIT_RESULT")
	private String auditResult;
	@Column(name = "PM_ASSESS_D_SEQ")
	private BigDecimal pmAssessDSeq;
	@Column(name = "OFF_CODE")
	private String offCode;
	@Column(name = "FORM_CODE")
	private String formCode;
	@Column(name = "TOPIC_LEVEL")
	private BigDecimal topicLevel;
	@Column(name = "TOPIC_CODE")
	private String topicCode;
	@Column(name = "TOPIC_NAME")
	private String topicName;
	@Column(name = "TOPIC_ANSWER")
	private String topicAnswer;

	public BigDecimal getAuditPmDId() {
		return auditPmDId;
	}

	public void setAuditPmDId(BigDecimal auditPmDId) {
		this.auditPmDId = auditPmDId;
	}

	public String getAuditPmAssessNo() {
		return auditPmAssessNo;
	}

	public void setAuditPmAssessNo(String auditPmAssessNo) {
		this.auditPmAssessNo = auditPmAssessNo;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public BigDecimal getPmAssessDSeq() {
		return pmAssessDSeq;
	}

	public void setPmAssessDSeq(BigDecimal pmAssessDSeq) {
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

	public BigDecimal getTopicLevel() {
		return topicLevel;
	}

	public void setTopicLevel(BigDecimal topicLevel) {
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
