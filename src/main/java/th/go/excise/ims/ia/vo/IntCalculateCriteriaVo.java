package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;

public class IntCalculateCriteriaVo {

	private BigDecimal dataCal;
	private BigDecimal riskRate;
	private String translatingRisk;
	private String color;
	private String codeColor;
	private BigDecimal percent;
	
	

	public BigDecimal getDataCal() {
		return dataCal;
	}

	public void setDataCal(BigDecimal dataCal) {
		this.dataCal = dataCal;
	}

	public BigDecimal getRiskRate() {
		return riskRate;
	}

	public void setRiskRate(BigDecimal riskRate) {
		this.riskRate = riskRate;
	}

	public String getTranslatingRisk() {
		return translatingRisk;
	}

	public void setTranslatingRisk(String translatingRisk) {
		this.translatingRisk = translatingRisk;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCodeColor() {
		return codeColor;
	}

	public void setCodeColor(String codeColor) {
		this.codeColor = codeColor;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}
	
	

}
