package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class Int0602ResultTab2Vo {

	private String taxCode;
	private String licName;
	private BigDecimal licPrice;
	private Long licCount;
	
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getLicName() {
		return licName;
	}
	public void setLicName(String licName) {
		this.licName = licName;
	}
	public BigDecimal getLicPrice() {
		return licPrice;
	}
	public void setLicPrice(BigDecimal licPrice) {
		this.licPrice = licPrice;
	}
	public Long getLicCount() {
		return licCount;
	}
	public void setLicCount(Long licCount) {
		this.licCount = licCount;
	}
}
