package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;

public interface TaMasCondSubRiskRepository extends CommonJpaCrudRepository<TaMasCondSubRisk, Long> {

	public List<TaMasCondSubRisk> findByBudgetYear(String budgetYear);
	public TaMasCondSubRisk findByBudgetYearAndDutyCode(String budgetYear, String dutyCode);
	List<TaMasCondSubRisk> findByBudgetYearAndOfficeCode(String budgetYear, String officeCode);
	TaMasCondSubRisk findByBudgetYearAndDutyCodeAndOfficeCode(String budgetYear, String dutyCode,String officeCode);
}
