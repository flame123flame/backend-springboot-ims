package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessH;

public interface WsPmAssessHRepositoryCustom {
	public void batchMerge(List<WsPmAssessH> pmAssessHList);

	public List<WsPmAssessHVo> filterWsPaAssess(Int1301Filter request);
}
