
package th.go.excise.ims.ia.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingH;

public interface IaEmpWorkingHRepository extends CommonJpaCrudRepository<IaEmpWorkingH, Long>, IaEmpWorkingHRepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG
			+ "' and e.iaEmpWorkingHSeq = :iaEmpWorkingHSeq")
	public IaEmpWorkingH findByIaEmpWorkingHSeq(@Param("iaEmpWorkingHSeq") Long iaEmpWorkingHSeq);
}
