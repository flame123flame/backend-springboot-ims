package th.go.excise.ims.ia.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;

public interface IaEmpWorkingDtlRepository extends CommonJpaCrudRepository<IaEmpWorkingDtl, Long>, IaEmpWorkingDtlRepositoryCustom {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.iaEmpWorkingDtlSeq = :iaEmpWorkingDtlSeq")
	public IaEmpWorkingDtl findByIaEmpWorkingDtlSeq(@Param("iaEmpWorkingDtlSeq") Long iaEmpWorkingDtlSeq);
}
