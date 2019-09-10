package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int1303FilterVo;
import th.go.excise.ims.ia.vo.WsPmPy2HVo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2H;

public interface WsPmPy2HRepositoryCustom {
	
	public void batchMerge(List<WsPmPy2H> pmPy2HList);

	public List<WsPmPy2HVo> filterWsPaPy2H(Int1303FilterVo request);
}
