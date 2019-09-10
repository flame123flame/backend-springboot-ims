
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurD1;

public interface IaAuditTxinsurD1Repository extends CommonJpaCrudRepository<IaAuditTxinsurD1, Long> {

	BigDecimal countByAuditTxinsurNo(String auditTxinsurNo);

	IaAuditTxinsurD1 findByAuditTxinsurNoAndNewRegIdAndIsDeleted(String auditTxinsurNo, String newRegId, String isDeleted);

}
