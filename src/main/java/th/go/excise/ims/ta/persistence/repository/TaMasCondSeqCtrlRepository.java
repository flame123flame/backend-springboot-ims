package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSeqCtrl;

public interface TaMasCondSeqCtrlRepository extends CommonJpaCrudRepository<TaMasCondSeqCtrl, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear")
	public TaMasCondSeqCtrl findByOfficeCodeAndBudgetYear(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);
	
}
