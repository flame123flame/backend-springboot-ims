package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.WsPmAssessDVo;
import th.go.excise.ims.ws.persistence.entity.WsPmAssessD;

public interface WsPmAssessDRepositoryCustom {
	public void batchMerge(List<WsPmAssessD> pmAssessDList);

	public List<WsPmAssessDVo> filterWsPaAssessD(String offCode, String formCode);
}
