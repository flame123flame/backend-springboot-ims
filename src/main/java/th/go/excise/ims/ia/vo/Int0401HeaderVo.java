package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;

public class Int0401HeaderVo {
	private String name;
	private String dataCal;
	private BigDecimal percent;
	private IaRiskFactors iaRiskFactors;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataCal() {
		return dataCal;
	}

	public void setDataCal(String dataCal) {
		this.dataCal = dataCal;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public IaRiskFactors getIaRiskFactors() {
		return iaRiskFactors;
	}

	public void setIaRiskFactors(IaRiskFactors iaRiskFactors) {
		this.iaRiskFactors = iaRiskFactors;
	}
	
	

}
