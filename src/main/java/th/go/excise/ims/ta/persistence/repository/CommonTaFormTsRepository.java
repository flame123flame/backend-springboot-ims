package th.go.excise.ims.ta.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

@NoRepositoryBean
public interface CommonTaFormTsRepository<T extends BaseEntity, ID extends Serializable> extends CommonJpaCrudRepository<T, ID> {
	
	@Query("select new java.lang.String(e.formTsNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode order by e.formTsNumber desc")
	public List<String> findFormTsNumberByOfficeCode(@Param("officeCode") String officeCode);
	
	@Query("select new java.lang.String(e.formTsNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPlanCode = :auditPlanCode order by e.formTsNumber desc")
	public List<String> findFormTsNumberByAuditPlanCode(@Param("auditPlanCode") String auditPlanCode);
	
}
