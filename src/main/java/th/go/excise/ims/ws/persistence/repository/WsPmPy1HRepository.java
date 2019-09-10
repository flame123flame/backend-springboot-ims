package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessH;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;

public interface WsPmPy1HRepository extends CommonJpaCrudRepository<WsPmPy1H, Long>, WsPmPy1HRepositoryCustom {
	
	@Query(value = "Select HDR.* from WS_PM_PY1_H HDR  WHERE HDR.OFF_CODE = ?1 AND HDR.FORM_YEAR = ?2 AND HDR.Is_Deleted = 'N' ", nativeQuery = true)
	public WsPmPy1H findByOffCodeByFormYear(String offCode, String formYear);
	
}
