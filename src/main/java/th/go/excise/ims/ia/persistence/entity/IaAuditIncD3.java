
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
@Table(name = "IA_AUDIT_INC_D3")
public class IaAuditIncD3 extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666829259595173981L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_D3_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_D3_GEN", sequenceName = "IA_AUDIT_INC_D3_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_INC_D3_ID")
	private Long iaAuditIncD3Id;
	@Column(name = "AUDIT_INC_NO")
	private String auditIncNo;
	@Column(name = "TAX_CODE")
	private String taxCode;
	@Column(name = "TAX_NAME")
	private String taxName;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "COUNT_RECEIPT")
	private BigDecimal countReceipt;
	@Column(name = "AUDIT_CHECK")
	private String auditCheck;
	@Column(name = "REMARK")
	private String remark;

	public Long getIaAuditIncD3Id() {
		return iaAuditIncD3Id;
	}

	public void setIaAuditIncD3Id(Long iaAuditIncD3Id) {
		this.iaAuditIncD3Id = iaAuditIncD3Id;
	}

	public String getAuditIncNo() {
		return auditIncNo;
	}

	public void setAuditIncNo(String auditIncNo) {
		this.auditIncNo = auditIncNo;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCountReceipt() {
		return countReceipt;
	}

	public void setCountReceipt(BigDecimal countReceipt) {
		this.countReceipt = countReceipt;
	}

	public String getAuditCheck() {
		return auditCheck;
	}

	public void setAuditCheck(String auditCheck) {
		this.auditCheck = auditCheck;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
