package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleVo;

public interface UserRoleRepositoryCustom {
	Integer countByCriteria(UserRoleFormVo request);

	List<UserRoleVo> findByCriteria(UserRoleFormVo request);

	List<UserRoleVo> findById(UserRoleFormVo request);
}
