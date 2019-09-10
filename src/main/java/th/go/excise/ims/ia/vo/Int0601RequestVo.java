package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0601RequestVo extends DataTableRequest {

	private static final long serialVersionUID = -1376109444188102181L;
	
	private String officeReceive;
	private String receiptDateFrom;
	private String receiptDateTo;
	private String taxCode;

	public String getOfficeReceive() {
		return officeReceive;
	}

	public void setOfficeReceive(String officeReceive) {
		this.officeReceive = officeReceive;
	}

	public String getReceiptDateFrom() {
		return receiptDateFrom;
	}

	public void setReceiptDateFrom(String receiptDateFrom) {
		this.receiptDateFrom = receiptDateFrom;
	}

	public String getReceiptDateTo() {
		return receiptDateTo;
	}

	public void setReceiptDateTo(String receiptDateTo) {
		this.receiptDateTo = receiptDateTo;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

}
