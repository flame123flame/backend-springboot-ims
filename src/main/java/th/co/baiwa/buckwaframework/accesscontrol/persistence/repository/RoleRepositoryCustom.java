package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleFormVo;

public interface RoleRepositoryCustom {

	public Integer countByCriteria(RoleFormVo role);

	public List<Role> findByCriteria(RoleFormVo role);
}
