
package th.go.excise.ims.preferences.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExcisePersonInfo;

public interface ExcisePersonInfoRepository extends CommonJpaCrudRepository<ExcisePersonInfo, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.personLogin = :personLogin")
	public ExcisePersonInfo dataHead (@Param("personLogin") String personLogin);
	
}
