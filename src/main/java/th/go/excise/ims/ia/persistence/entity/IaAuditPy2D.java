
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
@Table(name = "IA_AUDIT_PY2_D")
public class IaAuditPy2D extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6571828523183254920L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_PY2_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_PY2_D_GEN", sequenceName = "IA_AUDIT_PY2_D_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_PY2_D_ID")
	private BigDecimal auditPy2DId;
	@Column(name = "AUDIT_PY2_NO")
	private String auditPy2No;
	@Column(name = "PY2_AUDIT_RESULT")
	private String py2AuditResult;
	@Column(name = "PY2_TOPIC_SEQ")
	private BigDecimal py2TopicSeq;
	@Column(name = "PY2_TOPIC_NAME")
	private String py2TopicName;
	@Column(name = "PY2_TOPIC1_MAIN")
	private String py2Topic1Main;
	@Column(name = "PY2_TOPIC2_CTL")
	private String py2Topic2Ctl;
	@Column(name = "PY2_TOPIC3_ASSESS")
	private String py2Topic3Assess;
	@Column(name = "PY2_TOPIC4_RISK")
	private String py2Topic4Risk;
	@Column(name = "PY2_TOPIC5_IMPROVE")
	private String py2Topic5Improve;
	@Column(name = "PY2_TOPIC6_OWNER")
	private String py2Topic6Owner;
	@Column(name = "PY2_TOPIC7_REMARK")
	private String py2Topic7Remark;
	@Column(name = "PM_PY2_D_SEQ")
	private BigDecimal pmPy2DSeq;

	public BigDecimal getAuditPy2DId() {
		return auditPy2DId;
	}

	public void setAuditPy2DId(BigDecimal auditPy2DId) {
		this.auditPy2DId = auditPy2DId;
	}

	public String getAuditPy2No() {
		return auditPy2No;
	}

	public void setAuditPy2No(String auditPy2No) {
		this.auditPy2No = auditPy2No;
	}

	public String getPy2AuditResult() {
		return py2AuditResult;
	}

	public void setPy2AuditResult(String py2AuditResult) {
		this.py2AuditResult = py2AuditResult;
	}

	public BigDecimal getPy2TopicSeq() {
		return py2TopicSeq;
	}

	public void setPy2TopicSeq(BigDecimal py2TopicSeq) {
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public BigDecimal getPmPy2DSeq() {
		return pmPy2DSeq;
	}

	public void setPmPy2DSeq(BigDecimal pmPy2DSeq) {
		this.pmPy2DSeq = pmPy2DSeq;
	}

}
