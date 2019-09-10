package th.go.excise.ims.ia.vo;

import java.util.List;

public class Int1304Vo {

	private List<IaAuditPmQtHVo> header;
	private ExciseDepartmentVo exciseDepartmentVo;
	private String BudgetYear;

	public List<IaAuditPmQtHVo> getHeader() {
		return header;
	}

	public void setHeader(List<IaAuditPmQtHVo> header) {
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
