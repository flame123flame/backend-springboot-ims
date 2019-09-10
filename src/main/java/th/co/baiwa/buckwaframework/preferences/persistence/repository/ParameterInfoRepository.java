package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaPagingAndSortingRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;

public interface ParameterInfoRepository extends CommonJpaPagingAndSortingRepository<ParameterInfo, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paramGroupCode = :paramGroupCode order by e.sortingOrder")
	public List<ParameterInfo> findByParamGroupCode(@Param("paramGroupCode") String paramGroupCode);
	
	ParameterInfo findByParamGroupCodeAndParamCode(String paramGroupCode, String paramCode);
}
