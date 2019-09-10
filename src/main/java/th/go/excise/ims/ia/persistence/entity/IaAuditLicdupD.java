
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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_AUDIT_LICDUP_D")
public class IaAuditLicdupD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5039499202625132608L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LICDUP_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_LICDUP_D_GEN", sequenceName = "IA_AUDIT_LICDUP_D_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_LICDUP_D_SEQ")
	private Long auditLicdupDSeq;
	@Column(name = "AUDIT_LICDUP_NO")
	private String auditLicdupNo;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_FULLNAME")
	private String cusFullname;
	@Column(name = "LIC_TYPE")
	private String licType;
	@Column(name = "RUN_CHECK")
	private Integer runCheck;
	@Column(name = "LIC_NO")
	private String licNo;
	@Column(name = "LIC_DATE")
	private Date licDate;
	@Column(name = "PRINT_COUNT")
	private BigDecimal printCount;

	public Long getAuditLicdupDSeq() {
		return auditLicdupDSeq;
	}

	public void setAuditLicdupDSeq(Long auditLicdupDSeq) {
		this.auditLicdupDSeq = auditLicdupDSeq;
	}

	public String getAuditLicdupNo() {
		return auditLicdupNo;
	}

	public void setAuditLicdupNo(String auditLicdupNo) {
		this.auditLicdupNo = auditLicdupNo;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusFullname() {
		return cusFullname;
	}

	public void setCusFullname(String cusFullname) {
		this.cusFullname = cusFullname;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public Integer getRunCheck() {
		return runCheck;
	}

	public void setRunCheck(Integer runCheck) {
		this.runCheck = runCheck;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public Date getLicDate() {
		return licDate;
	}

	public void setLicDate(Date licDate) {
		this.licDate = licDate;
	}

	public BigDecimal getPrintCount() {
		return printCount;
	}

	public void setPrintCount(BigDecimal printCount) {
		this.printCount = printCount;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
