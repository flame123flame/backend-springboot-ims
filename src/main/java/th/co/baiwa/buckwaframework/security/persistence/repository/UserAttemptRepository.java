package th.co.baiwa.buckwaframework.security.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import th.co.baiwa.buckwaframework.security.persistence.entity.UserAttempt;

public interface UserAttemptRepository extends CrudRepository<UserAttempt, Long> {

	public UserAttempt findByUsername(String username);

}
