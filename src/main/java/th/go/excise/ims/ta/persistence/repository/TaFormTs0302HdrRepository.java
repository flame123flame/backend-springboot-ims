package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0302Hdr;

public interface TaFormTs0302HdrRepository extends CommonTaFormTsRepository<TaFormTs0302Hdr, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
	public TaFormTs0302Hdr findByFormTsNumber(@Param("formTsNumber") String formTsNumber);
	
}
