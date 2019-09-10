package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0114Hdr;

public interface TaFormTs0114HdrRepository extends CommonTaFormTsRepository<TaFormTs0114Hdr, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
	public TaFormTs0114Hdr findByFormTsNumber(@Param("formTsNumber") String formTsNumber);
	
}
