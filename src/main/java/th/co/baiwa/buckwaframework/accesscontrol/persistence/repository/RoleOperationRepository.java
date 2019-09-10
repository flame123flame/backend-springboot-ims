package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.RoleOperation;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface RoleOperationRepository extends CommonJpaCrudRepository<RoleOperation, Long>,RoleOperationRepositoryCustom {

}
                       