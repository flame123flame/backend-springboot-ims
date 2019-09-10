package th.go.excise.ims.bi.persistence.entity;

import java.math.BigDecimal;

public class TempFactory {

	private String facId;
	private String cusId;
	private String factoryName;
	private BigDecimal capital;
	private String empTot;
	private String facAddrseq;
	private String cusAddrseq;
	private String facSeq;
	private String cusSeq;

	public String getFacId() {
		return facId;
	}

	public void setFacId(String facId) {
		this.facId = facId;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public String getEmpTot() {
		return empTot;
	}

	public void setEmpTot(String empTot) {
		this.empTot = empTot;
	}

	public String getFacAddrseq() {
		return facAddrseq;
	}

	public void setFacAddrseq(String facAddrseq) {
		this.facAddrseq = facAddrseq;
	}

	public String getCusAddrseq() {
		return cusAddrseq;
	}

	public void setCusAddrseq(String cusAddrseq) {
		this.cusAddrseq = cusAddrseq;
	}

	public String getFacSeq() {
		return facSeq;
	}

	public void setFacSeq(String facSeq) {
		this.facSeq = facSeq;
	}

	public String getCusSeq() {
		return cusSeq;
	}

	public void setCusSeq(String cusSeq) {
		this.cusSeq = cusSeq;
	}

}
