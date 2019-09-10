package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public interface TaWorksheetHdrRepository extends CommonJpaCrudRepository<TaWorksheetHdr, Long>, TaWorksheetHdrRepositoryCustom {

	@Query("select new java.lang.String(e.analysisNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear and e.worksheetStatus = :worksheetStatus order by e.analysisNumber desc")
	public List<String> findAllAnalysisNumberByOfficeCodeAndBudgetYearAndWorksheetStatus(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear, @Param("worksheetStatus") String worksheetStatus);

	@Query("select new java.lang.String(e.analysisNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear order by e.analysisNumber desc")
	public List<String> findAllAnalysisNumberByOfficeCodeAndBudgetYear(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber")
	public TaWorksheetHdr findByAnalysisNumber(@Param("analysisNumber") String analysisNumber);
	
	// This method for Clear Data
	@Modifying
	@Query("update #{#entityName} e set e.worksheetStatus = :worksheetStatus where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear")
	public void updateWorksheetStatusByBudgetYear(@Param("worksheetStatus") String worksheetStatus, @Param("budgetYear") String budgetYear);
	
	public List<TaWorksheetHdr> findByOfficeCodeAndBudgetYearOrderByCreatedDateDesc(String officeCode, String budgetYear);
	
	
	@Query("select DISTINCT new java.lang.String(e.budgetYear) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.budgetYear desc")
	public List<String> findBudgetYearDistinctOrderByBudgetYearDesc();
	
	
	@Query(value ="SELECT * FROM TA_WORKSHEET_HDR WHERE WORKSHEET_STATUS = ?1 AND IS_DELETED = '" + FLAG.N_FLAG + "' ORDER BY WORKSHEET_HDR_ID DESC" ,nativeQuery = true)
	public List<TaWorksheetHdr> findWorksheetHdrByStatusOrderById(String status);
	
	public List<TaWorksheetHdr> findByOfficeCodeAndBudgetYearAndWorksheetStatusOrderByCreatedDateDesc(String officeCode, String budgetYear, String worksheetStatus);
}
