package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquirytitle.model.Title;

public interface ExciseTitleRepositoryCustom {

	public void batchMerge(List<Title> titleList);
	
}
