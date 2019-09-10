
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_LIC_D1")
public class IaAuditLicD1 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955096941724105831L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LIC_D1_GEN")
	@SequenceGenerator(name = "IA_AUDIT_LIC_D1_GEN", sequenceName = "IA_AUDIT_LIC_D1_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_LIC_D1_SEQ")
	private Long auditLicD1Seq;
	@Column(name = "AUDIT_LIC_NO")
	private String auditLicNo;
	@Column(name = "LIC_TYPE")
	private String licType;
	@Column(name = "LIC_NO")
	private String licNo;
	@Column(name = "RUN_CHECK")
	private BigDecimal runCheck;
	@Column(name = "LIC_DATE")
	private Date licDate;
	@Column(name = "SEND_DATE")
	private Date sendDate;
	@Column(name = "LIC_NAME")
	private String licName;
	@Column(name = "INC_CODE")
	private String incCode;
	@Column(name = "LIC_PRICE")
	private BigDecimal licPrice;
	@Column(name = "LIC_FEE")
	private BigDecimal licFee;
	@Column(name = "LIC_INTERIOR")
	private BigDecimal licInterior;
	@Column(name = "LIC_REMARK")
	private String licRemark;

	@Column(name = "SEQ_NO")
	private Integer seqNo;

	@Column(name = "ACTION_FLAG")
	private String actionFlag;

	public Long getAuditLicD1Seq() {
		return auditLicD1Seq;
	}

	public void setAuditLicD1Seq(Long auditLicD1Seq) {
		this.auditLicD1Seq = auditLicD1Seq;
	}

	public String getAuditLicNo() {
		return auditLicNo;
	}

	public void setAuditLicNo(String auditLicNo) {
		this.auditLicNo = auditLicNo;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public BigDecimal getRunCheck() {
		return runCheck;
	}

	public void setRunCheck(BigDecimal runCheck) {
		this.runCheck = runCheck;
	}

	public Date getLicDate() {
		return licDate;
	}

	public void setLicDate(Date licDate) {
		this.licDate = licDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getLicName() {
		return licName;
	}

	public void setLicName(String licName) {
		this.licName = licName;
	}

	public String getIncCode() {
		return incCode;
	}

	public void setIncCode(String incCode) {
		this.incCode = incCode;
	}

	public BigDecimal getLicPrice() {
		return licPrice;
	}

	public void setLicPrice(BigDecimal licPrice) {
		this.licPrice = licPrice;
	}

	public BigDecimal getLicFee() {
		return licFee;
	}

	public void setLicFee(BigDecimal licFee) {
		this.licFee = licFee;
	}

	public BigDecimal getLicInterior() {
		return licInterior;
	}

	public void setLicInterior(BigDecimal licInterior) {
		this.licInterior = licInterior;
	}

	public String getLicRemark() {
		return licRemark;
	}

	public void setLicRemark(String licRemark) {
		this.licRemark = licRemark;
	}

	public Integer getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	public String getActionFlag() {
		return actionFlag;
	}

	public void setActionFlag(String actionFlag) {
		this.actionFlag = actionFlag;
	}

}
