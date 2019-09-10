package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;

public class AuditTxinsurD1Vo {
	/* entity */
	private Long iaAuditTxinsurD1Id;
	private String auditTxinsurNo;
	private String officeCode;
	private String newRegId;
	private BigDecimal resultSeq;
	private Date bankGuaranteeDate;
	private String bankGuaranteeNo;
	private BigDecimal bankGuaranteeAmt;
	private String bankGuaranteeResult;
	private Date cashGuaranteeDate;
	private String cashReceiptNo;
	private BigDecimal cashGuaranteeAmt;
	private String cashGuaranteeResult;

	/* custom */
	private String cashGuaranteeDateStr;
	private String bankGuaranteeDateStr;
	private Boolean bankGuaranteeResultBL;
	private Boolean cashGuaranteeResultBL;

	public Long getIaAuditTxinsurD1Id() {
		return iaAuditTxinsurD1Id;
	}

	public void setIaAuditTxinsurD1Id(Long iaAuditTxinsurD1Id) {
		this.iaAuditTxinsurD1Id = iaAuditTxinsurD1Id;
	}

	public String getAuditTxinsurNo() {
		return auditTxinsurNo;
	}

	public void setAuditTxinsurNo(String auditTxinsurNo) {
		this.auditTxinsurNo = auditTxinsurNo;
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

	public BigDecimal getResultSeq() {
		return resultSeq;
	}

	public void setResultSeq(BigDecimal resultSeq) {
		this.resultSeq = resultSeq;
	}

	public Date getBankGuaranteeDate() {
		return bankGuaranteeDate;
	}

	public void setBankGuaranteeDate(Date bankGuaranteeDate) {
		this.bankGuaranteeDate = bankGuaranteeDate;
	}

	public String getBankGuaranteeNo() {
		return bankGuaranteeNo;
	}

	public void setBankGuaranteeNo(String bankGuaranteeNo) {
		this.bankGuaranteeNo = bankGuaranteeNo;
	}

	public BigDecimal getBankGuaranteeAmt() {
		return bankGuaranteeAmt;
	}

	public void setBankGuaranteeAmt(BigDecimal bankGuaranteeAmt) {
		this.bankGuaranteeAmt = bankGuaranteeAmt;
	}

	public String getBankGuaranteeResult() {
		return bankGuaranteeResult;
	}

	public void setBankGuaranteeResult(String bankGuaranteeResult) {
		this.bankGuaranteeResult = bankGuaranteeResult;
	}

	public Date getCashGuaranteeDate() {
		return cashGuaranteeDate;
	}

	public void setCashGuaranteeDate(Date cashGuaranteeDate) {
		this.cashGuaranteeDate = cashGuaranteeDate;
	}

	public String getCashReceiptNo() {
		return cashReceiptNo;
	}

	public void setCashReceiptNo(String cashReceiptNo) {
		this.cashReceiptNo = cashReceiptNo;
	}

	public BigDecimal getCashGuaranteeAmt() {
		return cashGuaranteeAmt;
	}

	public void setCashGuaranteeAmt(BigDecimal cashGuaranteeAmt) {
		this.cashGuaranteeAmt = cashGuaranteeAmt;
	}

	public String getCashGuaranteeResult() {
		return cashGuaranteeResult;
	}

	public void setCashGuaranteeResult(String cashGuaranteeResult) {
		this.cashGuaranteeResult = cashGuaranteeResult;
	}

	public String getCashGuaranteeDateStr() {
		return cashGuaranteeDateStr;
	}

	public void setCashGuaranteeDateStr(String cashGuaranteeDateStr) {
		this.cashGuaranteeDateStr = cashGuaranteeDateStr;
	}

	public String getBankGuaranteeDateStr() {
		return bankGuaranteeDateStr;
	}

	public void setBankGuaranteeDateStr(String bankGuaranteeDateStr) {
		this.bankGuaranteeDateStr = bankGuaranteeDateStr;
	}

	public Boolean getBankGuaranteeResultBL() {
		return bankGuaranteeResultBL;
	}

	public void setBankGuaranteeResultBL(Boolean bankGuaranteeResultBL) {
		this.bankGuaranteeResultBL = bankGuaranteeResultBL;
	}

	public Boolean getCashGuaranteeResultBL() {
		return cashGuaranteeResultBL;
	}

	public void setCashGuaranteeResultBL(Boolean cashGuaranteeResultBL) {
		this.cashGuaranteeResultBL = cashGuaranteeResultBL;
	}

}
