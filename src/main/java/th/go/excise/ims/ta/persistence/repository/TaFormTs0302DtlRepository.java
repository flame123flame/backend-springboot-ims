package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0302Dtl;

public interface TaFormTs0302DtlRepository extends CommonJpaCrudRepository<TaFormTs0302Dtl, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
	public List<TaFormTs0302Dtl> findByFormTsNumber(@Param("formTsNumber") String formTsNumber);

}
