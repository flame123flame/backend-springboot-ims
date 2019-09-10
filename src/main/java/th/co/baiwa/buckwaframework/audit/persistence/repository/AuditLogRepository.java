package th.co.baiwa.buckwaframework.audit.persistence.repository;

import th.co.baiwa.buckwaframework.audit.persistence.entity.AuditLog;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface AuditLogRepository extends CommonJpaCrudRepository<AuditLog, Long> {

}
