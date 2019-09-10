package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;

public interface TaMasCondSubNoAuditRepository extends CommonJpaCrudRepository<TaMasCondSubNoAudit, Long> {

	public TaMasCondSubNoAudit findByBudgetYearAndOfficeCode(String budgetYear, String officeCode);
}
