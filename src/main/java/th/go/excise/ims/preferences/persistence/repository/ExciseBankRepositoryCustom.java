package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquirybank.model.Bank;

public interface ExciseBankRepositoryCustom {

	public void batchMerge(List<Bank> bankList);

}
