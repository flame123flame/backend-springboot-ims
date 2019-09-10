package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanMas;

public interface TaPlanMasRepository extends CommonJpaCrudRepository<TaPlanMas, Long>,TaPlanMasRepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear and e.officeCode = :officeCode")
	public List<TaPlanMas> findByBudgetYearAndOfficeCode(@Param("budgetYear") String budgetYear, @Param("officeCode") String officeCode);

}
