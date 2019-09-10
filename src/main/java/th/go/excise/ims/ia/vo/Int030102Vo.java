package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;

public class Int030102Vo {
	private IaRiskFactorsMaster iaRiskFactorsMaster;
	private IaRiskFactorsConfig iaRiskFactorsConfig;
	private String createdDateDesc;
	private String updateDateDesc;
	private BigDecimal idMaster;
	private BigDecimal idConfig;
	private BigDecimal idFactors;

	public IaRiskFactorsMaster getIaRiskFactorsMaster() {
		return iaRiskFactorsMaster;
	}

	public void setIaRiskFactorsMaster(IaRiskFactorsMaster iaRiskFactorsMaster) {
		this.iaRiskFactorsMaster = iaRiskFactorsMaster;
	}

	public IaRiskFactorsConfig getIaRiskFactorsConfig() {
		return iaRiskFactorsConfig;
	}

	public void setIaRiskFactorsConfig(IaRiskFactorsConfig iaRiskFactorsConfig) {
		this.iaRiskFactorsConfig = iaRiskFactorsConfig;
	}

	public String getCreatedDateDesc() {
		return createdDateDesc;
	}

	public void setCreatedDateDesc(String createdDateDesc) {
		this.createdDateDesc = createdDateDesc;
	}

	public String getUpdateDateDesc() {
		return updateDateDesc;
	}

	public void setUpdateDateDesc(String updateDateDesc) {
		this.updateDateDesc = updateDateDesc;
	}

	public BigDecimal getIdMaster() {
		return idMaster;
	}

	public void setIdMaster(BigDecimal idMaster) {
		this.idMaster = idMaster;
	}

	public BigDecimal getIdConfig() {
		return idConfig;
	}

	public void setIdConfig(BigDecimal idConfig) {
		this.idConfig = idConfig;
	}

	public BigDecimal getIdFactors() {
		return idFactors;
	}

	public void setIdFactors(BigDecimal idFactors) {
		this.idFactors = idFactors;
	}

}
