
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
@Table(name = "IA_AUDIT_INC_GFD")
public class IaAuditIncGfd extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4331897222362413817L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_GFD_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_GFD_GEN", sequenceName = "IA_AUDIT_INC_GFD_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_INC_GFD_ID")
	private BigDecimal iaAuditIncGfdId;
	@Column(name = "AUDIT_INC_GF_NO")
	private String auditIncGfNo;
	@Column(name = "DISB_DEPT")
	private String disbDept;
	@Column(name = "DISBURSE_UNIT")
	private String disburseUnit;
	@Column(name = "INCOME_CODE")
	private String incomeCode;
	@Column(name = "INC_NET_TAX_AMT")
	private BigDecimal incNetTaxAmt;
	@Column(name = "GL_ACC_NO")
	private String glAccNo;
	@Column(name = "GL_NET_TAX_AMT")
	private BigDecimal glNetTaxAmt;
	@Column(name = "INC_GFD_SEQ")
	private BigDecimal incGfdSeq;

	public BigDecimal getIaAuditIncGfdId() {
		return iaAuditIncGfdId;
	}

	public void setIaAuditIncGfdId(BigDecimal iaAuditIncGfdId) {
		this.iaAuditIncGfdId = iaAuditIncGfdId;
	}

	public String getAuditIncGfNo() {
		return auditIncGfNo;
	}

	public void setAuditIncGfNo(String auditIncGfNo) {
		this.auditIncGfNo = auditIncGfNo;
	}

	public String getDisbDept() {
		return disbDept;
	}

	public void setDisbDept(String disbDept) {
		this.disbDept = disbDept;
	}

	public String getDisburseUnit() {
		return disburseUnit;
	}

	public void setDisburseUnit(String disburseUnit) {
		this.disburseUnit = disburseUnit;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public BigDecimal getIncNetTaxAmt() {
		return incNetTaxAmt;
	}

	public void setIncNetTaxAmt(BigDecimal incNetTaxAmt) {
		this.incNetTaxAmt = incNetTaxAmt;
	}

	public String getGlAccNo() {
		return glAccNo;
	}

	public void setGlAccNo(String glAccNo) {
		this.glAccNo = glAccNo;
	}

	public BigDecimal getGlNetTaxAmt() {
		return glNetTaxAmt;
	}

	public void setGlNetTaxAmt(BigDecimal glNetTaxAmt) {
		this.glNetTaxAmt = glNetTaxAmt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public BigDecimal getIncGfdSeq() {
		return incGfdSeq;
	}

	public void setIncGfdSeq(BigDecimal incGfdSeq) {
		this.incGfdSeq = incGfdSeq;
	}

}
