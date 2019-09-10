package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseSubdept;

public interface ExciseSubdeptRepository extends CommonJpaCrudRepository<ExciseSubdept, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and offCode = :offCode")
	public List<ExciseSubdept> findByOfficeCode(@Param("offCode") String offCode);
	
//	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.audit_select_flag = '" + FLAG.Y_FLAG + "' and  e.subdept_code IS NULL ")
//	public List<ExciseSubdept> findByAuditSelectFlag();
	
	@Modifying
	@Query(value = "select e.* from excise_subdept e where e.IS_DELETED = '" + FLAG.N_FLAG + "' and e.audit_select_flag = '" + FLAG.Y_FLAG + "' and  e.subdept_code IS NULL", nativeQuery = true)
	public List<ExciseSubdept>  findByAuditSelectFlag();
	
}
