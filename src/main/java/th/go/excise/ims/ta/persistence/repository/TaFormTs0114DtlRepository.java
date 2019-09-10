package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0114Dtl;

public interface TaFormTs0114DtlRepository extends CommonJpaCrudRepository<TaFormTs0114Dtl, Long> {

    @Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
    public List<TaFormTs0114Dtl> findByFormTsNumber(@Param("formTsNumber") String formTsNumber);

}
