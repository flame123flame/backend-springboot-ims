package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.UserRole;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface UserRoleRepository extends CommonJpaCrudRepository<UserRole, Long>, UserRoleRepositoryCustom {

}
