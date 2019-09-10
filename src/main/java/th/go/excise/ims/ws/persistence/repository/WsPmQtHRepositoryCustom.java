package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int1304FormVo;
import th.go.excise.ims.ia.vo.WsPmQtHVo;
import th.go.excise.ims.ws.persistence.entity.WsPmQtH;

public interface WsPmQtHRepositoryCustom  {
	
	public void batchMerge(List<WsPmQtH> pmQtHList);
	
	public List<WsPmQtHVo> filterWsPmQt(Int1304FormVo request);

}
