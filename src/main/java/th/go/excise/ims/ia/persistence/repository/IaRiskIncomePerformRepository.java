package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskIncomePerform;

public interface IaRiskIncomePerformRepository
extends CommonJpaCrudRepository<IaRiskIncomePerform, BigDecimal>
{
	List<IaRiskIncomePerform> findByBudgetYear(String budgetYear);
}
