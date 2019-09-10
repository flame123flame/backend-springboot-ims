package th.go.excise.ims.ta.vo;

public class ProductPaperBalanceMaterialVo {

	private String materialDesc;
	private String balanceByAccountQty;
	private String balanceByStockQty;
	private String balanceByCountQty;
	private String maxDiffQty1;
	private String maxDiffQty2;

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public String getBalanceByAccountQty() {
		return balanceByAccountQty;
	}

	public void setBalanceByAccountQty(String balanceByAccountQty) {
		this.balanceByAccountQty = balanceByAccountQty;
	}

	public String getBalanceByStockQty() {
		return balanceByStockQty;
	}

	public void setBalanceByStockQty(String balanceByStockQty) {
		this.balanceByStockQty = balanceByStockQty;
	}

	public String getBalanceByCountQty() {
		return balanceByCountQty;
	}

	public void setBalanceByCountQty(String balanceByCountQty) {
		this.balanceByCountQty = balanceByCountQty;
	}

	public String getMaxDiffQty1() {
		return maxDiffQty1;
	}

	public void setMaxDiffQty1(String maxDiffQty1) {
		this.maxDiffQty1 = maxDiffQty1;
	}

	public String getMaxDiffQty2() {
		return maxDiffQty2;
	}

	public void setMaxDiffQty2(String maxDiffQty2) {
		this.maxDiffQty2 = maxDiffQty2;
	}

}
