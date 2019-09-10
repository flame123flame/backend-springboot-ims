package th.go.excise.ims.ta.vo;

public class ProductPaperReduceTaxVo {

	private String materialDesc;
	private String taxReduceAmt;
	private String taxReduceQty;
	private String taxReducePerUnitAmt;
	private String billNo;
	private String billTaxAmt;
	private String billTaxQty;
	private String billTaxPerUnit;
	private String diffTaxReduceAmt;

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getTaxReduceAmt() {
		return taxReduceAmt;
	}

	public void setTaxReduceAmt(String taxReduceAmt) {
		this.taxReduceAmt = taxReduceAmt;
	}

	public String getTaxReduceQty() {
		return taxReduceQty;
	}

	public void setTaxReduceQty(String taxReduceQty) {
		this.taxReduceQty = taxReduceQty;
	}

	public String getTaxReducePerUnitAmt() {
		return taxReducePerUnitAmt;
	}

	public void setTaxReducePerUnitAmt(String taxReducePerUnitAmt) {
		this.taxReducePerUnitAmt = taxReducePerUnitAmt;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillTaxAmt() {
		return billTaxAmt;
	}

	public void setBillTaxAmt(String billTaxAmt) {
		this.billTaxAmt = billTaxAmt;
	}

	public String getBillTaxQty() {
		return billTaxQty;
	}

	public void setBillTaxQty(String billTaxQty) {
		this.billTaxQty = billTaxQty;
	}

	public String getBillTaxPerUnit() {
		return billTaxPerUnit;
	}

	public void setBillTaxPerUnit(String billTaxPerUnit) {
		this.billTaxPerUnit = billTaxPerUnit;
	}

	public String getDiffTaxReduceAmt() {
		return diffTaxReduceAmt;
	}

	public void setDiffTaxReduceAmt(String diffTaxReduceAmt) {
		this.diffTaxReduceAmt = diffTaxReduceAmt;
	}

}
