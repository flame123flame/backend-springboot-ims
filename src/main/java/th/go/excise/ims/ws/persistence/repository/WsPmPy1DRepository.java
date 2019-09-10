package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;

public interface WsPmPy1DRepository extends CommonJpaCrudRepository<WsPmPy1D, Long>, WsPmPy1DRepositoryCustom {

	@Query(value = "Select DTL.* from WS_PM_PY1_D DTL  WHERE DTL.FORM_CODE = ?1 AND DTL.OFF_CODE = ?2 AND DTL.Is_Deleted = 'N' ORDER BY DTL.TOPIC_NAME ASC ", nativeQuery = true)
	public List<WsPmPy1D> findByFormCodeByOffCode(String formCode, String offCode);

}
