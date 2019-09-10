package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubRisk;

public interface TaWorksheetCondSubRiskRepository extends CommonJpaCrudRepository<TaWorksheetCondSubRisk, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber and e.dutyCode = :dutyCode")
	public TaWorksheetCondSubRisk findByAnalysisNumberAndDutyCode(@Param("analysisNumber") String analysisNumber, @Param("dutyCode") String dutyCode);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber")
	public List<TaWorksheetCondSubRisk> findByAnalysisNumber(@Param("analysisNumber") String analysisNumber);
	
}
