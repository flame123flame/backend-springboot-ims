package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.vo.Int0610SearchVo;
import th.go.excise.ims.ia.vo.Int0610SumVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

public interface WsIncfri8020IncRepositoryCustom {

	public void batchInsert(List<WsIncfri8020Inc> wsIncfri8020IncList);

	public List<Int0610SumVo> summaryByDisburseUnit(Int0610SearchVo request);

	public List<WsIncfri8020Inc> findTabs(String officeCode);

}
