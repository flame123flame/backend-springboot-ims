package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmPy1H;

public interface WsPmPy1HRepositoryCustom {
	public void batchMerge(List<WsPmPy1H> pmPy1HList);
}
