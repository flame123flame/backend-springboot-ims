package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;

public interface WsRegfri4000DutyRepositoryCustom {
	
	public void batchMerge(List<WsRegfri4000Duty> regfri4000DutyList);
	
}
