
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
@Table(name = "IA_AUDIT_LIC_D2")
public class IaAuditLicD2 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5118308278589549783L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_LIC_D2_GEN")
	@SequenceGenerator(name = "IA_AUDIT_LIC_D2_GEN", sequenceName = "IA_AUDIT_LIC_D2_SEQ", allocationSize = 1)
	@Column(name = "AUDIT_LIC_D2_SEQ")
	private Long auditLicD2Seq;
	@Column(name = "AUDIT_LIC_NO")
	private String auditLicNo;
	@Column(name = "TAX_CODE")
	private String taxCode;
	@Column(name = "LIC_NAME")
	private String licName;
	@Column(name = "LIC_PRICE")
	private BigDecimal licPrice;
	@Column(name = "LIC_COUNT")
	private Long licCount;
	@Column(name = "AUDIT_CHECK")
	private String auditCheck;
	@Column(name = "LIC_T2_REMARK")
	private String licT2Remark;

	

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}



	public Long getAuditLicD2Seq() {
		return auditLicD2Seq;
	}



	public void setAuditLicD2Seq(Long auditLicD2Seq) {
		this.auditLicD2Seq = auditLicD2Seq;
	}



	public String getAuditLicNo() {
		return auditLicNo;
	}



	public void setAuditLicNo(String auditLicNo) {
		this.auditLicNo = auditLicNo;
	}



	public String getTaxCode() {
		return taxCode;
	}



	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}



	public String getLicName() {
		return licName;
	}



	public void setLicName(String licName) {
		this.licName = licName;
	}



	public BigDecimal getLicPrice() {
		return licPrice;
	}



	public void setLicPrice(BigDecimal licPrice) {
		this.licPrice = licPrice;
	}



	public Long getLicCount() {
		return licCount;
	}



	public void setLicCount(Long licCount) {
		this.licCount = licCount;
	}



	public String getAuditCheck() {
		return auditCheck;
	}



	public void setAuditCheck(String auditCheck) {
		this.auditCheck = auditCheck;
	}



	public String getLicT2Remark() {
		return licT2Remark;
	}



	public void setLicT2Remark(String licT2Remark) {
		this.licT2Remark = licT2Remark;
	}

}
