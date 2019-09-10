package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.WsPmQtDVo;
import th.go.excise.ims.ws.persistence.entity.WsPmQtD;

public interface WsPmQtDRepositoryCustom {
	public void batchMerge(List<WsPmQtD> pmQtDList);
	
	List<WsPmQtDVo> filterWsPmQtD(String formCode , String offCode);
	
}
