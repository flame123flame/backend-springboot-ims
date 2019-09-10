package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;
import th.go.excise.ims.ws.vo.WsRegfri4000Vo;

public interface WsRegfri4000RepositoryCustom {

	public void batchMerge(List<WsRegfri4000> regfri4000List);

	public List<WsRegfri4000Vo> findByCriteria(WsRegfri4000Vo regfri4000Vo, boolean isPaging);

	public Long countByCriteria(WsRegfri4000Vo regfri4000Vo);

}
