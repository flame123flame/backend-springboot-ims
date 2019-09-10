
package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExcisePersonInfo1;

public interface ExcisePersonInfo1Repository
    extends CommonJpaCrudRepository<ExcisePersonInfo1, Long>
{
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.personLogin = :personLogin")
	public List<ExcisePersonInfo1> listChild (@Param("personLogin") String personLogin);
}
