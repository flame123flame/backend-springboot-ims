package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface UserRepository extends CommonJpaCrudRepository<User, Long>, UserRepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.username = :username")
	public User findByUsername(@Param("username") String username);

}
