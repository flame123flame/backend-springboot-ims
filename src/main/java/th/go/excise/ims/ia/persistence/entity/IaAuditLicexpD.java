
package th.go.excise.ims.ia.persistence.entity;

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
@Table(name = "IA_AUDIT_LICEXP_D")
public class IaAuditLicexpD extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2028693038201870497L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LICEXP_D_GEN")
	@SequenceGenerator(name = "IA_AUDIT_LICEXP_D_GEN", sequenceName = "IA_AUDIT_LICEXP_D_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_LICEXP_SEQ")
	private Long auditLicexpSeq;
	@Column(name = "AUDIT_LICEXP_NO")
	private String auditLicexpNo;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "CUS_FULL_NAME")
	private String cusFullName;
	@Column(name = "FAC_FULL_NAME")
	private String facFullName;
	@Column(name = "LIC_TYPE")
	private String licType;
	@Column(name = "LIC_NO")
	private String licNo;
	@Column(name = "LIC_DATE")
	private Date licDate;
	@Column(name = "EXP_DATE")
	private Date expDate;
	@Column(name = "LIC_NO_NEW")
	private String licNoNew;
	@Column(name = "LIC_DATE_NEW")
	private Date licDateNew;

	public Long getAuditLicexpSeq() {
		return auditLicexpSeq;
	}

	public void setAuditLicexpSeq(Long auditLicexpSeq) {
		this.auditLicexpSeq = auditLicexpSeq;
	}

	public String getAuditLicexpNo() {
		return auditLicexpNo;
	}

	public void setAuditLicexpNo(String auditLicexpNo) {
		this.auditLicexpNo = auditLicexpNo;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCusFullName() {
		return cusFullName;
	}

	public void setCusFullName(String cusFullName) {
		this.cusFullName = cusFullName;
	}

	public String getFacFullName() {
		return facFullName;
	}

	public void setFacFullName(String facFullName) {
		this.facFullName = facFullName;
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

	public Date getLicDate() {
		return licDate;
	}

	public void setLicDate(Date licDate) {
		this.licDate = licDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public String getLicNoNew() {
		return licNoNew;
	}

	public void setLicNoNew(String licNoNew) {
		this.licNoNew = licNoNew;
	}

	public Date getLicDateNew() {
		return licDateNew;
	}

	public void setLicDateNew(Date licDateNew) {
		this.licDateNew = licDateNew;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
