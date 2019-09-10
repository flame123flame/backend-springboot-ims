package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmPy2D;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2DVo;

public interface WsPmPy2DRepositoryCustom {

	public void batchMerge(List<WsPmPy2D> pmPy2DList);

	public List<WsPmPy2DVo> filterWsPaPy2D(String formCode, String offCode);
}
