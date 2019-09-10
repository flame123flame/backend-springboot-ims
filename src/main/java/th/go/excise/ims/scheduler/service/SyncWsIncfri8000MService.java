package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000M;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8000MRepository;
import th.go.excise.ims.ws.persistence.repository.WsIncfri8000Repository;
import th.go.excise.ims.ws.vo.WsIncfri8000MVo;

@Service
public class SyncWsIncfri8000MService {

	private static final Logger logger = LoggerFactory.getLogger(SyncWsIncfri8000MService.class);
	
	@Autowired
	private WsIncfri8000Repository wsIncfri8000Repository;
	
	@Autowired
	private WsIncfri8000MRepository wsIncfri8000MRepository;
	
	@Transactional(rollbackOn = {Exception.class})
	public void syncData(String dateType, LocalDate localDate) throws PccRestfulException {
		logger.info("syncData Incfri8000M Start");
		long start = System.currentTimeMillis();
		
		String dateTypeCode = ExciseUtils.convertIncfri8000DateType(dateType);
		String dateStart = localDate.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDate.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String taxYear = String.valueOf(localDate.getYear());
		String taxMonth = String.valueOf(localDate.getMonthValue());
		
		List<WsIncfri8000MVo> incfri8000MVoList = wsIncfri8000Repository.findFor8000M(dateTypeCode, dateStart, dateEnd);
		
		WsIncfri8000M incfri8000M = null;
		List<WsIncfri8000M> incfri8000MList = new ArrayList<>();
		for (WsIncfri8000MVo incfri8000MVo : incfri8000MVoList) {
			incfri8000M = new WsIncfri8000M();
			incfri8000M.setDateType(dateTypeCode);
			incfri8000M.setRegId(incfri8000MVo.getRegId());
			incfri8000M.setNewRegId(incfri8000MVo.getNewRegId());
			incfri8000M.setDutyGroupId(incfri8000MVo.getDutyGroupId());
			incfri8000M.setTaxAmount(incfri8000MVo.getSumTaxAmount());
			incfri8000M.setTaxYear(taxYear);
			incfri8000M.setTaxMonth(taxMonth);
			incfri8000MList.add(incfri8000M);
		}
		
		wsIncfri8000MRepository.forceDeleteByDateType(dateTypeCode, taxYear, taxMonth);
		wsIncfri8000MRepository.batchInsert(incfri8000MList);
		logger.info("Batch Merge WS_INCFRI8000_M Success");
		
		long end = System.currentTimeMillis();
		logger.info("syncData Incfri8000M Success, using {} seconds", (float) (end - start) / 1000F);
	}

}
