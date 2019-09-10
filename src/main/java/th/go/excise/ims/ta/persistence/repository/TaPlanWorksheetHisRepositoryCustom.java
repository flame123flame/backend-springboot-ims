package th.go.excise.ims.ta.persistence.repository;

import java.util.List;
import java.util.Map;

public interface TaPlanWorksheetHisRepositoryCustom {

	public Map<String, String> findAuditPlanCodeByOfficeCodeAndBudgetYearList(String officeCode, List<String> budgetYearList);

	public Map<String, String> findMaxTaxAuditYear();

	public Map<String, List<String>> findAuditPlanCodeByNewRegId(List<String> newRegIdList, List<String> budgetYearList);

}
