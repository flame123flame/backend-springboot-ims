package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquiryhospital.model.Hospital;

public interface ExciseHospitalRepositoryCustom {
	
	public void batchMerge(List<Hospital> hospitalList);
	
}
