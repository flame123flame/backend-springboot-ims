package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface RoleRepository extends CommonJpaCrudRepository<Role, Long>, RoleRepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.roleCode = :roleCode")
	public Role findByRoleCode(@Param("roleCode") String roleCode);

}
