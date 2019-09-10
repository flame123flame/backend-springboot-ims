package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Menu;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface MenuRepository extends CommonJpaCrudRepository<Menu, Long>, MenuRepositoryCustom {

}
