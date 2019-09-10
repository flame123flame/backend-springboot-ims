package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

public interface WsOasfri0100DRepositoryCustom {
	
	public void batchInsert(List<WsOasfri0100D> oasfri0100DList);
	
	public List<WsOasfri0100Vo> findByCriteria(WsOasfri0100FromVo formVo);
	
}
