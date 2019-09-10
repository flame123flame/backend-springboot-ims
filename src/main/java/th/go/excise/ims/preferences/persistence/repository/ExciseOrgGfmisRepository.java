
package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaEstimateExpH;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;

public interface ExciseOrgGfmisRepository extends CommonJpaCrudRepository<ExciseOrgGfmis, String> {
	
	@Modifying
	@Query(value = "SELECT * FROM EXCISE_ORG_GFMIS WHERE GF_DISBURSE_UNIT = GF_COST_CENTER AND IS_DELETED = '" + FLAG.N_FLAG + "' ORDER BY GF_EXCISE_CODE", nativeQuery = true)
	public List<ExciseOrgGfmis> findGfDisburseUnitAndName();
	
	@Query(value = " SELECT * FROM EXCISE_ORG_GFMIS WHERE IS_DELETED = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public List<ExciseOrgGfmis> listData();
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.gfExciseCode = :gfExciseCode")
	public ExciseOrgGfmis findByGfExciseCode(@Param("gfExciseCode") String gfExciseCode);
	
}
