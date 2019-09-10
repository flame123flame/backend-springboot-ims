package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;

public interface TaMasCondSubCapitalRepository extends CommonJpaCrudRepository<TaMasCondSubCapital, Long> {
	
	public List<TaMasCondSubCapital> findByBudgetYear(String budgetYear);
	public TaMasCondSubCapital findByBudgetYearAndDutyCode(String budgetYear, String dutyCode);
	public List<TaMasCondSubCapital> findByOfficeCodeAndBudgetYear(String officeCode, String budgetYear);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear and e.dutyCode = :dutyCode")
	public TaMasCondSubCapital findByOfficeCodeAndBudgetYearAndDutyCode(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear, @Param("dutyCode") String dutyCode);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear and e.dutyCode is '0000'")
	public TaMasCondSubCapital findByOfficeCodeAndBudgetYearTotal(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear and e.dutyCode is not '0000'")
	public List<TaMasCondSubCapital> findByOfficeCodeAndBudgetYearNotTotal(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);
}
