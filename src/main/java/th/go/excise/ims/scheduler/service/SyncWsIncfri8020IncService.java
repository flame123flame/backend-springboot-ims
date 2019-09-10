package th.go.excise.ims.scheduler.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.Income;
import th.go.excise.ims.ws.client.pcc.incfri8020.model.RequestData;
import th.go.excise.ims.ws.client.pcc.incfri8020.service.IncFri8020Service;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8020IncRepository;

@Service
public class SyncWsIncfri8020IncService {

	private static final Logger logger = LoggerFactory.getLogger(SyncWsIncfri8020IncService.class);
	
	private final int WS_DATA_SIZE = 500;
	
	@Autowired
	private IncFri8020Service incFri8020Service;
	
	@Autowired
	private WsIncfri8020IncRepository wsIncfri8020IncRepository;
	
	public void syncData(RequestData requestData) {
		int pageNo = 1;
		List<Income> incomeList;
		List<WsIncfri8020Inc> wsIncfri8020IncList;
		WsIncfri8020Inc wsInc = null;
		try {
			do {
				incomeList = new ArrayList<>();
				wsIncfri8020IncList = new ArrayList<>();
				requestData.setPageNo(String.valueOf(pageNo));
				requestData.setDataPerPage(String.valueOf(WS_DATA_SIZE));
				incomeList = incFri8020Service.execute(requestData).getIncomeList();
				for (Income income : incomeList) {
					wsInc = new WsIncfri8020Inc();
					wsInc.setDepositDate(ConvertDateUtils.parseStringToDate(income.getDepositDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setSendDate(ConvertDateUtils.parseStringToDate(income.getSendDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setReceiptDate(ConvertDateUtils.parseStringToDate(income.getReceiptDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setIncomeName(income.getIncomeName());
					wsInc.setReceiptNo(income.getReceiptNo());
					wsInc.setNetTaxAmt(NumberUtils.toBigDecimal(income.getNettaxAmount()));
					wsInc.setNetLocAmt(NumberUtils.toBigDecimal(income.getNetLocAmount()));
					wsInc.setLocOthAmt(NumberUtils.toBigDecimal(income.getLocOthAmount()));
					wsInc.setLocExpAmt(NumberUtils.toBigDecimal(income.getLocExpAmount()));
					wsInc.setOlderFundAmt(NumberUtils.toBigDecimal(income.getOlderFundAmount()));
					wsInc.setTpbsFundAmt(NumberUtils.toBigDecimal(income.getTpbsFundAmount()));
					wsInc.setSendAmt(NumberUtils.toBigDecimal(income.getSendAmount()));
					wsInc.setStampAmt(NumberUtils.toBigDecimal(income.getStampAmount()));
					wsInc.setCustomAmt(NumberUtils.toBigDecimal(income.getCustomAmount()));
					wsInc.setTrnDate(ConvertDateUtils.parseStringToDate(income.getTrnDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setOfficeReceive(income.getOfficeReceive());
					wsInc.setIncomeCode(income.getIncomeCode());
					wsInc.setReceiptNoOlderFund(income.getReceiptNoOlderFund());
					wsInc.setReceiptNoTpbsFund(income.getReceiptNoTpbsFund());
					wsInc.setReceiptNoSssFund(income.getReceiptNoSssFund());
					wsInc.setReceiptNoSportFund(income.getReceiptNoSportFund());
					wsInc.setSportFundAmt(NumberUtils.toBigDecimal(income.getSportFundAmount()));
					wsInc.setPinNidId(income.getPinNidId());
					wsInc.setNewRegId(income.getNewRegId());
					wsInc.setCusName(income.getCusName());
					wsInc.setFacName(income.getFacName());
					wsInc.setIncCtlNo(income.getIncCtlNo());
					wsInc.setOfflineStatus(income.getOfflineStatus());
					wsIncfri8020IncList.add(wsInc);
				}
				pageNo++;
				wsIncfri8020IncRepository.batchInsert(wsIncfri8020IncList);
			} while (WS_DATA_SIZE == incomeList.size());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
