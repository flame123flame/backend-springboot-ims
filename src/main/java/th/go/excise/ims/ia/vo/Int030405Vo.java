package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;

public class Int030405Vo {
	private String systemCode;
	private String systemName;
	private List<IaRiskSystemUnworking> iaRiskSystemUnworking;
	private IntCalculateCriteriaVo intCalculateCriteriaVo;
	private String sumCountAll;
	private String sumCountNormal;
	private String sumCountError;

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public List<IaRiskSystemUnworking> getIaRiskSystemUnworking() {
		return iaRiskSystemUnworking;
	}

	public void setIaRiskSystemUnworking(List<IaRiskSystemUnworking> iaRiskSystemUnworking) {
		this.iaRiskSystemUnworking = iaRiskSystemUnworking;
	}

	public IntCalculateCriteriaVo getIntCalculateCriteriaVo() {
		return intCalculateCriteriaVo;
	}

	public void setIntCalculateCriteriaVo(IntCalculateCriteriaVo intCalculateCriteriaVo) {
		this.intCalculateCriteriaVo = intCalculateCriteriaVo;
	}

	public String getSumCountAll() {
		return sumCountAll;
	}

	public void setSumCountAll(String sumCountAll) {
		this.sumCountAll = sumCountAll;
	}

	public String getSumCountNormal() {
		return sumCountNormal;
	}

	public void setSumCountNormal(String sumCountNormal) {
		this.sumCountNormal = sumCountNormal;
	}

	public String getSumCountError() {
		return sumCountError;
	}

	public void setSumCountError(String sumCountError) {
		this.sumCountError = sumCountError;
	}

}
