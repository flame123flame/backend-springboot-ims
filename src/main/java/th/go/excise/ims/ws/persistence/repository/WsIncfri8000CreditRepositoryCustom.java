package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsIncfri8000Credit;

public interface WsIncfri8000CreditRepositoryCustom {
	
	public void forceDeleteByDateType(String dateType, String dateStart, String dateEnd);
	
	public void batchInsert(List<WsIncfri8000Credit> incfri8000CreditList);
	
}
