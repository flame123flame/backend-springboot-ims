package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

public interface WsAnafri0001DRepositoryCustom {

	public void batchInsert(List<WsAnafri0001D> anafri0001DList);

	public List<WsAnafri0001Vo> findProductList(String newRegId, String dutyGroupId, String dateStart, String dateEnd);

}
