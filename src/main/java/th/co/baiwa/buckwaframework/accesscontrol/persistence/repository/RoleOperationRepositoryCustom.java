package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationVo;

public interface RoleOperationRepositoryCustom {

	Integer countByCriteria(RoleOperationFormVo request);

	List<RoleOperationVo> findByCriteria(RoleOperationFormVo request);

	List<RoleOperationVo> findById(RoleOperationFormVo request);

}
