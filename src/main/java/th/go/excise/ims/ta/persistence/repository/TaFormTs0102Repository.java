package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0102;

public interface TaFormTs0102Repository extends CommonTaFormTsRepository<TaFormTs0102, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
	public TaFormTs0102 findByFormTsNumber(@Param("formTsNumber") String formTsNumber);
	
}
