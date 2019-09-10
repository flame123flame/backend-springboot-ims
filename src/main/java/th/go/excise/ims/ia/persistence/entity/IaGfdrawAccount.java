
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
@Table(name = "IA_GFDRAW_ACCOUNT")
public class IaGfdrawAccount extends BaseEntity {

	private static final long serialVersionUID = 3152957676776086885L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_GFDRAW_ACCOUNT_GEN")
	@SequenceGenerator(name = "IA_GFDRAW_ACCOUNT_GEN", sequenceName = "IA_GFDRAW_ACCOUNT_SEQ", allocationSize = 1)
	@Column(name = "GFTRIAL_BALANCE_SEQ")
	private Long gftrialBalanceSeq;
	@Column(name = "GFUPLOAD_H_ID")
	private Long gfuploadHId;
	@Column(name = "DEPT_DISB")
	private String deptDisb;
	@Column(name = "PERIOD_FROM")
	private Date periodFrom;
	@Column(name = "PERIOD_TO")
	private Date periodTo;
	@Column(name = "REP_DATE")
	private Date repDate;
	@Column(name = "REP_TYPE")
	private String repType;
	@Column(name = "RECORD_DATE")
	private Date recordDate;
	@Column(name = "RECODE_APPROVE_DATE")
	private Date recodeApproveDate;
	@Column(name = "TYPE")
	private String type;
	@Column(name = "DOC_NO")
	private String docNo;
	@Column(name = "SELLER_NAME")
	private String sellerName;
	@Column(name = "SELLER_BOOK_BANK")
	private String sellerBookBank;
	@Column(name = "REFERENCE_CODE")
	private String referenceCode;
	@Column(name = "BUDGET_CODE")
	private String budgetCode;
	@Column(name = "DISB_AMT")
	private BigDecimal disbAmt;
	@Column(name = "TAX_AMT")
	private BigDecimal taxAmt;
	@Column(name = "MULCT_AMT")
	private BigDecimal mulctAmt;
	@Column(name = "FEE_AMT")
	private BigDecimal feeAmt;
	@Column(name = "NET_AMT")
	private BigDecimal netAmt;

	public Long getGftrialBalanceSeq() {
		return gftrialBalanceSeq;
	}

	public void setGftrialBalanceSeq(Long gftrialBalanceSeq) {
		this.gftrialBalanceSeq = gftrialBalanceSeq;
	}

	public Long getGfuploadHId() {
		return gfuploadHId;
	}

	public void setGfuploadHId(Long gfuploadHId) {
		this.gfuploadHId = gfuploadHId;
	}

	public Date getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Date periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Date getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Date periodTo) {
		this.periodTo = periodTo;
	}

	public Date getRepDate() {
		return repDate;
	}

	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}

	public String getRepType() {
		return repType;
	}

	public void setRepType(String repType) {
		this.repType = repType;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getRecodeApproveDate() {
		return recodeApproveDate;
	}

	public void setRecodeApproveDate(Date recodeApproveDate) {
		this.recodeApproveDate = recodeApproveDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerBookBank() {
		return sellerBookBank;
	}

	public void setSellerBookBank(String sellerBookBank) {
		this.sellerBookBank = sellerBookBank;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public BigDecimal getDisbAmt() {
		return disbAmt;
	}

	public void setDisbAmt(BigDecimal disbAmt) {
		this.disbAmt = disbAmt;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getMulctAmt() {
		return mulctAmt;
	}

	public void setMulctAmt(BigDecimal mulctAmt) {
		this.mulctAmt = mulctAmt;
	}

	public BigDecimal getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt) {
		this.feeAmt = feeAmt;
	}

	public BigDecimal getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(BigDecimal netAmt) {
		this.netAmt = netAmt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getDeptDisb() {
		return deptDisb;
	}

	public void setDeptDisb(String deptDisb) {
		this.deptDisb = deptDisb;
	}

}
