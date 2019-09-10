package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int1301Vo {
	
	private List<IaAuditPmassessHVo> header;
	private ExciseDepartmentVo exciseDepartmentVo;
	private String BudgetYear;

	public List<IaAuditPmassessHVo> getHeader() {
		return header;
	}

	public void setHeader(List<IaAuditPmassessHVo> header) {
		this.header = header;
	}

	public ExciseDepartmentVo getExciseDepartmentVo() {
		return exciseDepartmentVo;
	}

	public void setExciseDepartmentVo(ExciseDepartmentVo exciseDepartmentVo) {
		this.exciseDepartmentVo = exciseDepartmentVo;
	}

	public String getBudgetYear() {
		return BudgetYear;
	}

	public void setBudgetYear(String budgetYear) {
		BudgetYear = budgetYear;
	}

}
