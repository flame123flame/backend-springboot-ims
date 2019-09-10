package th.go.excise.ims.ia.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;

public class Int0301Vo extends DataTableRequest {

	private IaRiskFactors iaRiskFactors;
	private IaRiskFactorsConfig iaRiskFactorsConfig;
	private String createdDateDesc;
	private String updateDateDesc;
	private String statusScreenDesc;
	private List<String> datalistdynamic;

	public IaRiskFactors getIaRiskFactors() {
		return iaRiskFactors;
	}

	public void setIaRiskFactors(IaRiskFactors iaRiskFactors) {
		this.iaRiskFactors = iaRiskFactors;
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

	public String getStatusScreenDesc() {
		return statusScreenDesc;
	}

	public void setStatusScreenDesc(String statusScreenDesc) {
		this.statusScreenDesc = statusScreenDesc;
	}

	public List<String> getDatalistdynamic() {
		return datalistdynamic;
	}

	public void setDatalistdynamic(List<String> datalistdynamic) {
		this.datalistdynamic = datalistdynamic;
	}

}
