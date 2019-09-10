package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserFormVo;

public interface UserRepositoryCustom {

	public Integer countByCriteria(UserFormVo userFormVo);

	public List<User> findByCriteria(UserFormVo userFormVo);

}
