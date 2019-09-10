package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetStep;

public interface TaPlanWorksheetStepRepository extends CommonJpaCrudRepository<TaPlanWorksheetStep, BigDecimal>, TaPlanWorksheetStepRepositoryCustom {
	
	@Query("select e from #{#entityName} e where  e.formTsNumber = :formTsNumber")
	public TaPlanWorksheetStep findByFormTsNumber(@Param("formTsNumber") String formTsNumber);
	
}
