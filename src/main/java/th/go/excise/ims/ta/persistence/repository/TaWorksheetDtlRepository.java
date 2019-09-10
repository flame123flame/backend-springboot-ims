package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;

public interface TaWorksheetDtlRepository extends CommonJpaCrudRepository<TaWorksheetDtl, Long>, TaWorksheetDtlRepositoryCustom {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber and e.newRegId = :newRegId")
	public TaWorksheetDtl findByAnalysisNumberAndNewRegId(@Param("analysisNumber") String analysisNumber, @Param("newRegId")  String newRegId);
	
	@Query("select new java.lang.String(e.newRegId) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber")
	public List<String> findNewRegIdByAnalysisNumber(@Param("analysisNumber") String analysisNumber);

}
