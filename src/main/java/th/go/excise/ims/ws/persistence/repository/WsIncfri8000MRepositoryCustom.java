package th.go.excise.ims.ws.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import th.go.excise.ims.ws.persistence.entity.WsIncfri8000M;

public interface WsIncfri8000MRepositoryCustom {
	
	public void forceDeleteByDateType(String dateType, String taxYear, String taxMonth);
	
	public void batchInsert(List<WsIncfri8000M> incfri8000MList);
	
	public Map<String, Map<String, BigDecimal>> findByMonthRange(List<String> newRegIdList, String startMonth, String endMonth);
	
}
