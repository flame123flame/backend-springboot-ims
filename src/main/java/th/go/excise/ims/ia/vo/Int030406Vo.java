package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaRiskCheckPeriod;

public class Int030406Vo {
	private IaRiskCheckPeriod iaRiskCheckPeriod;
	private IntCalculateCriteriaVo intCalculateCriteriaVo;
	private String dateFrom;
	private String dateTo;
	
	/* ExciseDepartmentVo */
	private ExciseDepartmentVo exciseDepartmentVo;
	

	public IaRiskCheckPeriod getIaRiskCheckPeriod() {
		return iaRiskCheckPeriod;
	}

	public void setIaRiskCheckPeriod(IaRiskCheckPeriod iaRiskCheckPeriod) {
		this.iaRiskCheckPeriod = iaRiskCheckPeriod;
	}

	public IntCalculateCriteriaVo getIntCalculateCriteriaVo() {
		return intCalculateCriteriaVo;
	}

	public void setIntCalculateCriteriaVo(IntCalculateCriteriaVo intCalculateCriteriaVo) {
		this.intCalculateCriteriaVo = intCalculateCriteriaVo;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

}
