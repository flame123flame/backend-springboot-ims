package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.List;

public class Int12070101SaveFormVo {
	private Long rentHouseId;
	private String name;
	private String position;
	private String affiliation;
	private String paymentCost;
	private String paymentFor;
	private String period;
	private BigDecimal refReceipts;
	private BigDecimal billAmount;
	private BigDecimal salary;
	private String requestNo;
	private BigDecimal notOver;
	private String periodWithdraw;
	private BigDecimal totalMonth;
	private BigDecimal totalWithdraw;
	private String receipts;
	private String status;
	private String periodWithdrawTo;
	private Long iaDisReqId;
	private String form6005No;
	private List<Int12070101D1Vo> receiptsRH;
	
	public Long getRentHouseId() {
		return rentHouseId;
	}

	public void setRentHouseId(Long rentHouseId) {
		this.rentHouseId = rentHouseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getPaymentCost() {
		return paymentCost;
	}

	public void setPaymentCost(String paymentCost) {
		this.paymentCost = paymentCost;
	}

	public String getPaymentFor() {
		return paymentFor;
	}

	public void setPaymentFor(String paymentFor) {
		this.paymentFor = paymentFor;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public BigDecimal getRefReceipts() {
		return refReceipts;
	}

	public void setRefReceipts(BigDecimal refReceipts) {
		this.refReceipts = refReceipts;
	}

	public BigDecimal getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(BigDecimal billAmount) {
		this.billAmount = billAmount;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getRequestNo() {
		return requestNo;
	}

	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}

	public BigDecimal getNotOver() {
		return notOver;
	}

	public void setNotOver(BigDecimal notOver) {
		this.notOver = notOver;
	}

	public String getPeriodWithdraw() {
		return periodWithdraw;
	}

	public void setPeriodWithdraw(String periodWithdraw) {
		this.periodWithdraw = periodWithdraw;
	}

	public BigDecimal getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(BigDecimal totalMonth) {
		this.totalMonth = totalMonth;
	}

	public BigDecimal getTotalWithdraw() {
		return totalWithdraw;
	}

	public void setTotalWithdraw(BigDecimal totalWithdraw) {
		this.totalWithdraw = totalWithdraw;
	}

	public String getReceipts() {
		return receipts;
	}

	public void setReceipts(String receipts) {
		this.receipts = receipts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPeriodWithdrawTo() {
		return periodWithdrawTo;
	}

	public void setPeriodWithdrawTo(String periodWithdrawTo) {
		this.periodWithdrawTo = periodWithdrawTo;
	}

	public Long getIaDisReqId() {
		return iaDisReqId;
	}

	public void setIaDisReqId(Long iaDisReqId) {
		this.iaDisReqId = iaDisReqId;
	}

	public List<Int12070101D1Vo> getReceiptsRH() {
		return receiptsRH;
	}

	public void setReceiptsRH(List<Int12070101D1Vo> receiptsRH) {
		this.receiptsRH = receiptsRH;
	}

	public String getForm6005No() {
		return form6005No;
	}

	public void setForm6005No(String form6005No) {
		this.form6005No = form6005No;
	}

}