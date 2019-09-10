package th.go.excise.ims.ta.vo;

public class ServicePaperPricePerUnitVo {

	private String goodsDesc; // รายการ
	private String invoicePrice; // ราคาตามใบกำกับภาษี
	private String informPrice; // ราคาบริการตามแบบแจ้ง
	private String auditPrice; // จากการตรวจสอบ
	private String goodsPrice; // ราคาที่ยื่นชำระภาษี
	private String diffPrice; // ผลต่าง

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(String invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public String getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(String informPrice) {
		this.informPrice = informPrice;
	}

	public String getAuditPrice() {
		return auditPrice;
	}

	public void setAuditPrice(String auditPrice) {
		this.auditPrice = auditPrice;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getDiffPrice() {
		return diffPrice;
	}

	public void setDiffPrice(String diffPrice) {
		this.diffPrice = diffPrice;
	}

}
