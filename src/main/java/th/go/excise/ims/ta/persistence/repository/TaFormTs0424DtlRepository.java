package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0424Dtl;

public interface TaFormTs0424DtlRepository extends CommonJpaCrudRepository<TaFormTs0424Dtl, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
	public List<TaFormTs0424Dtl> findByFormTsNumber(@Param("formTsNumber") String formTsNumber);

}
