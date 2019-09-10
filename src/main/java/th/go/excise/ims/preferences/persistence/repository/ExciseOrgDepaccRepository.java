
package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgDepacc;

public interface ExciseOrgDepaccRepository
    extends CommonJpaCrudRepository<ExciseOrgDepacc, Long>
{
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode")
	public List<ExciseOrgDepacc> listOrg (@Param("officeCode") String officeCode);
	
}
