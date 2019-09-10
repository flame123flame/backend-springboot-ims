
package th.go.excise.ims.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;

public interface IaAuditTxinsurHRepository
    extends CommonJpaCrudRepository<IaAuditTxinsurH, Long>
{
	
	IaAuditTxinsurH findByAuditTxinsurNo(String auditTxinsurNo);


}
