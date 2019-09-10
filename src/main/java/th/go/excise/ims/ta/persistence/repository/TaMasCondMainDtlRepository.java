package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;

public interface TaMasCondMainDtlRepository extends CommonJpaCrudRepository<TaMasCondMainDtl, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear order by e.condGroup")
	public List<TaMasCondMainDtl> findByBudgetYear(@Param("budgetYear") String budgetYear);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear and e.condNumber = :condNumber and e.condType = :condType and e.officeCode = :officeCode order by e.condGroup")
	public List<TaMasCondMainDtl> findByBudgetYearAndCondNumberAndCondTypeAndOfficeCode(@Param("budgetYear") String budgetYear, @Param("condNumber") String condNumber, @Param("condType") String condType, @Param("officeCode") String officeCode);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear and e.condNumber = :condNumber and e.condType = :condType order by e.condGroup")
	public List<TaMasCondMainDtl> findByBudgetYearAndCondNumberAndCondType(@Param("budgetYear") String budgetYear, @Param("condNumber") String condNumber, @Param("condType") String condType);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.condNumber = :condNumber order by e.condGroup")
	public List<TaMasCondMainDtl> findByCondNumber(@Param("condNumber") String condNumber);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear and e.condNumber = :condNumber order by e.condGroup")
	public List<TaMasCondMainDtl> findByBudgetYearAndCondNumber(@Param("budgetYear") String budgetYear, @Param("condNumber") String condNumber);
	
}
