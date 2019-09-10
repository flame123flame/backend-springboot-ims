
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbH;

public interface IaAuditGftbHRepository extends CommonJpaCrudRepository<IaAuditGftbH, BigDecimal> {
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditGftbNo")
	public List<IaAuditGftbH> findAllOrderByAuditGftbNo();
	
	public IaAuditGftbH findByAuditGftbNoAndIsDeletedOrderByAuditGftbNo(String auditGftbNo, String isDeleted);
}
