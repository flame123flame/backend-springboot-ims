package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.ta.persistence.entity.TaFormTs01141;

public interface TaFormTs01141Repository extends CommonTaFormTsRepository<TaFormTs01141, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber order by e.formTsNumber")
	public List<TaFormTs01141> findByFormTsNumber(@Param("formTsNumber") String formTsNumber);
	
	@Query("select distinct new java.lang.String(e.formTsNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode order by e.formTsNumber desc")
	public List<String> findFormTsNumberByOfficeCode(@Param("officeCode") String officeCode);
	
	@Query("select distinct new java.lang.String(e.formTsNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPlanCode = :auditPlanCode order by e.formTsNumber desc")
	public List<String> findFormTsNumberByAuditPlanCode(@Param("auditPlanCode") String auditPlanCode);
	
}
