package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster2;

public class Int0305Vo {
	private IaRiskFactorsMaster2 iaRiskFactorsMaster2;
	private IaRiskFactorsConfig iaRiskFactorsConfig;
	private String createdDateDesc;
	private String updateDateDesc;
	private BigDecimal idMaster;
	private BigDecimal idConfig;
	private BigDecimal idFactors;

	public IaRiskFactorsMaster2 getIaRiskFactorsMaster2() {
		return iaRiskFactorsMaster2;
	}

	public void setIaRiskFactorsMaster2(IaRiskFactorsMaster2 iaRiskFactorsMaster2) {
		this.iaRiskFactorsMaster2 = iaRiskFactorsMaster2;
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
