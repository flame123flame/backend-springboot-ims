package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int1302FormVo;
import th.go.excise.ims.ia.vo.Int1302Vo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy1D;

public interface WsPmPy1DRepositoryCustom {
	public void batchMerge(List<WsPmPy1D> pmPy1D);
	
}
