package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class IaAuditIncD3Vo {

	private Long iaAuditIncD3Id;
	private String auditIncNo;
	private String taxCode;
	private String taxName;
	private BigDecimal amount;
	private BigDecimal countReceipt;
	private String auditCheck;
	private String remark;

	public Long getIaAuditIncD3Id() {
		return iaAuditIncD3Id;
	}

	public void setIaAuditIncD3Id(Long iaAuditIncD3Id) {
		this.iaAuditIncD3Id = iaAuditIncD3Id;
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

	public String getAuditIncNo() {
		return auditIncNo;
	}

	public void setAuditIncNo(String auditIncNo) {
		this.auditIncNo = auditIncNo;
	}

}
