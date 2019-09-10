package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.go.excise.ims.ia.persistence.entity.IaRiskBudgetProject;

public class Int030403Vo extends DataTableRequest {
	
	private IaRiskBudgetProject iaRiskBudgetProject;
	private BigDecimal expenseBudgetAmountAll;
	private IntCalculateCriteriaVo intCalculateCriteriaVo;

	public IaRiskBudgetProject getIaRiskBudgetProject() {
		return iaRiskBudgetProject;
	}

	public void setIaRiskBudgetProject(IaRiskBudgetProject iaRiskBudgetProject) {
		this.iaRiskBudgetProject = iaRiskBudgetProject;
	}

	public BigDecimal getExpenseBudgetAmountAll() {
		return expenseBudgetAmountAll;
	}

	public void setExpenseBudgetAmountAll(BigDecimal expenseBudgetAmountAll) {
		this.expenseBudgetAmountAll = expenseBudgetAmountAll;
	}

	public IntCalculateCriteriaVo getIntCalculateCriteriaVo() {
		return intCalculateCriteriaVo;
	}

	public void setIntCalculateCriteriaVo(IntCalculateCriteriaVo intCalculateCriteriaVo) {
		this.intCalculateCriteriaVo = intCalculateCriteriaVo;
	}

}
