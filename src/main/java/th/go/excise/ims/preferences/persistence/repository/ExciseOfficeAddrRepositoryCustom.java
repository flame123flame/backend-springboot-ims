package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.model.OffCodeAddress;

public interface ExciseOfficeAddrRepositoryCustom {

	public void batchMerge(List<OffCodeAddress> offCodeAddressList);
	
}
