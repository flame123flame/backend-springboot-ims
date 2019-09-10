
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
@Table(name = "IA_AUDIT_INC_D2")
public class IaAuditIncD2 extends BaseEntity {

	private static final long serialVersionUID = 4529881034909841262L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_AUDIT_INC_D2_GEN")
	@SequenceGenerator(name = "IA_AUDIT_INC_D2_GEN", sequenceName = "IA_AUDIT_INC_D2_SEQ", allocationSize = 1)
	@Column(name = "IA_AUDIT_INC_D2_ID")
	private Long iaAuditIncD2Id;
	@Column(name = "AUDIT_INC_NO")
	private String auditIncNo;
	@Column(name = "RECEIPT_DATE")
	private Date receiptDate;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "PRINT_PER_DAY")
	private BigDecimal printPerDay;
	@Column(name = "AUDIT_CHECK")
	private String auditCheck;
	@Column(name = "REMARK")
	private String remark;

	public Long getIaAuditIncD2Id() {
		return iaAuditIncD2Id;
	}

	public void setIaAuditIncD2Id(Long iaAuditIncD2Id) {
		this.iaAuditIncD2Id = iaAuditIncD2Id;
	}

	public String getAuditIncNo() {
		return auditIncNo;
	}

	public void setAuditIncNo(String auditIncNo) {
		this.auditIncNo = auditIncNo;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrintPerDay() {
		return printPerDay;
	}

	public void setPrintPerDay(BigDecimal printPerDay) {
		this.printPerDay = printPerDay;
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

}
