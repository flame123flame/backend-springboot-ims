package th.go.excise.ims.ws.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessH;

public interface WsPmAssessHRepository extends CommonJpaCrudRepository<WsPmAssessH, Long>, WsPmAssessHRepositoryCustom {
	
}
