package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendD;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendH;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncSendDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncSendHRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.IaAuditIncSendDVo;
import th.go.excise.ims.ia.vo.Int0609SaveVo;
import th.go.excise.ims.ia.vo.Int0609TableVo;
import th.go.excise.ims.ia.vo.Int0609Vo;
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.ia.vo.WsIncr0003Vo;
import th.go.excise.ims.preferences.persistence.repository.ExciseHolidayRepository;
import th.go.excise.ims.ws.persistence.repository.WsGfr01051JdbcRepository;
import th.go.excise.ims.ws.persistence.repository.WsIncr0003JdbcRepository;

@Service
public class Int0609Service {

	@Autowired
	private WsGfr01051JdbcRepository wsGfr01051JdbcRepository;

	@Autowired
	private WsIncr0003JdbcRepository wsIncr0003JdbcRepository;

	@Autowired
	private IaCommonService iaCommonService;

	@Autowired
	private IaAuditIncSendHRepository iaAuditIncSendHRepository;

	@Autowired
	private IaAuditIncSendDRepository iaAuditIncSendDRepository;
	
	@Autowired
	private ExciseHolidayRepository exciseHolidayRepository;

	public Int0609Vo search(SearchVo request) {
		/* __________ set year TH to EN __________ */
		request.setPeriodMonth(
				ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getPeriodMonth(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_EN));

		/* __________ filter from condition display __________ */
		List<Int0609TableVo> resWsGfr01051 = wsGfr01051JdbcRepository.findByFilter(request);
		List<WsIncr0003Vo> resWsIncr0003 = wsIncr0003JdbcRepository.findByFilter(request);

		/* __________ loop for map object __________ */
		for (Int0609TableVo wsGfr01051 : resWsGfr01051) {
			/* __________ calculate difference day__________ */
//			long diffInMillies = Math.abs(wsGfr01051.getGfDate().getTime() - wsGfr01051.getTrnDate().getTime());
//			long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

			/*total date wihtout holiday and weekend*/
			LocalDate startDate = ConvertDateUtils.parseStringToLocalDate(ConvertDateUtils.formatDateToString(wsGfr01051.getTrnDate(),ConvertDateUtils.DD_MM_YYYY),
					ProjectConstant.SHORT_DATE_FORMAT);
			LocalDate endDate = ConvertDateUtils.parseStringToLocalDate(ConvertDateUtils.formatDateToString(wsGfr01051.getGfDate(),ConvertDateUtils.DD_MM_YYYY),
					ProjectConstant.SHORT_DATE_FORMAT);
			
			List<LocalDate> listDate = LocalDateUtils.getLocalDateListWithoutHolodays(startDate, endDate);
			Long dateSize = (long) listDate.size() - 1;
			wsGfr01051.setDateDiff(dateSize);

			String dateStrHeader = ConvertDateUtils.formatDateToString(wsGfr01051.getTrnDate(), ConvertDateUtils.DD_MM_YY);
			wsGfr01051.setTrnDateStr(dateStrHeader);
			wsGfr01051.setGfDateStr(ConvertDateUtils.formatDateToString(wsGfr01051.getGfDate(), ConvertDateUtils.DD_MM_YY));
			for (WsIncr0003Vo wsIncr0003Vo : resWsIncr0003) {
				String dateStr = ConvertDateUtils.formatDateToString(wsIncr0003Vo.getTrnDate(), ConvertDateUtils.DD_MM_YY);

				if (dateStrHeader.equals(dateStr)) {
					wsGfr01051.setSum1Sum2(wsIncr0003Vo.getSum1Sum2());
					wsGfr01051.setSum4Sum5(wsIncr0003Vo.getSum4Sum5());
					wsGfr01051.setSum7(wsIncr0003Vo.getSum7());
				}
			}
			
			List<LocalDate> holidayList = exciseHolidayRepository.findByDateRange(startDate, endDate).stream().map(holiday -> holiday.getHolidayDate()).collect(Collectors.toList());
			ArrayList<String> holidayStrList = new ArrayList<String>();
			for (LocalDate localDate : holidayList) {
				holidayStrList.add(ConvertDateUtils.formatLocalDateToString(localDate, ProjectConstant.SHORT_DATE_FORMAT, ConvertDateUtils.LOCAL_TH));
			}
//			Arrays.stream(holidayStr).collect(Collectors.joining(","));
			//			String.join(", ", holidayList);
			wsGfr01051.setTootip(holidayStrList.stream().map(Object::toString).collect(Collectors.joining(", ")));
		}

		/* __________ set footer __________ */
		Int0609TableVo sumWsGfr01051 = wsGfr01051JdbcRepository.summaryByRequest(request);
		WsIncr0003Vo sumWsIncr0003 = wsIncr0003JdbcRepository.summaryByRequest(request);
		Int0609TableVo footer = new Int0609TableVo();
		if (sumWsGfr01051 != null) {
			footer.setCnt(sumWsGfr01051.getCnt());
			footer.setTotalAmt(sumWsGfr01051.getTotalAmt());
			footer.setTotalSendAmt(sumWsGfr01051.getTotalSendAmt());
		}
		if (sumWsIncr0003 != null) {
			footer.setSum1Sum2(sumWsIncr0003.getSum1Sum2());
			footer.setSum4Sum5(sumWsIncr0003.getSum4Sum5());
			footer.setSum7(sumWsIncr0003.getSum7());
		}

		/* __________ incomeCode = '115010' __________ */
		BigDecimal sum4_I = BigDecimal.ZERO;
		request.setIncomeCode("115010");
		List<WsIncr0003Vo> sum4_IList = wsIncr0003JdbcRepository.findByFilter(request);
		for (Int0609TableVo r : resWsGfr01051) {
			if (sum4_IList.size() > 0) {
				String dateStrHeader = ConvertDateUtils.formatDateToString(r.getTrnDate(), ConvertDateUtils.DD_MM_YY);
				for (WsIncr0003Vo I : sum4_IList) {
					String dateStr = ConvertDateUtils.formatDateToString(I.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					if (dateStrHeader.equals(dateStr)) {
						r.setSum4I(I.getSum4());
						sum4_I = sum4_I.add(NumberUtils.nullToZero(I.getSum4()));
					}
				}
			} else {
				r.setSum4I(BigDecimal.ZERO);
			}
		}

		/* __________ incomeCode = '116010' __________ */
		BigDecimal sum4_II = BigDecimal.ZERO;
		request.setIncomeCode("116010");
		List<WsIncr0003Vo> sum4_IIList = wsIncr0003JdbcRepository.findByFilter(request);
		for (Int0609TableVo r : resWsGfr01051) {
			if (sum4_IIList.size() > 0) {
				for (WsIncr0003Vo II : sum4_IIList) {
					String dateStr = ConvertDateUtils.formatDateToString(II.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					String dateStrHeader = ConvertDateUtils.formatDateToString(r.getTrnDate(), ConvertDateUtils.DD_MM_YY);
					if (dateStrHeader.equals(dateStr)) {
						r.setSum4II(II.getSum4());
						sum4_II = sum4_II.add(NumberUtils.nullToZero(II.getSum4()));
					}
				}
			} else {
				r.setSum4II(BigDecimal.ZERO);
			}
		}
		footer.setSum4I(sum4_I);
		footer.setSum4II(sum4_II);

		/* __________ set response __________ */
		Int0609Vo response = new Int0609Vo();
		response.setFooter(footer);
		response.setTable(resWsGfr01051);
		return response;
	}

	public String save(Int0609SaveVo request) throws Exception {
		String incsendNo = iaCommonService.autoGetRunAuditNoBySeqName("AIS", request.getHeader().getIncsendOfficeCode(), "INCSEND_NO_SEQ", 8);

		/* __________ header __________ */
		request.getHeader().setIncsendNo(incsendNo);
		request.getHeader().setIncsendPeriodMonth(
				ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(request.getHeader().getIncsendPeriodMonth(), ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYYMM));
		iaAuditIncSendHRepository.save(request.getHeader());

		/* __________ details __________ */
		IaAuditIncSendD detail = null;
		for (IaAuditIncSendDVo d : request.getDetails()) {
			detail = new IaAuditIncSendD();
			BeanUtils.copyProperties(detail, d);
			detail.setIncsendNo(incsendNo);
			detail.setIncsendTrnDate(ConvertDateUtils.parseStringToDate(d.getIncsendTrnDateStr(), ConvertDateUtils.DD_MM_YY));
			detail.setIncsendGfDate(ConvertDateUtils.parseStringToDate(d.getIncsendGfDateStr(), ConvertDateUtils.DD_MM_YY));
			iaAuditIncSendDRepository.save(detail);
		}
		return incsendNo;
	}

	public List<IaAuditIncSendH> getIncSendNoDropdown() {
		return iaAuditIncSendHRepository.findIncSendNo();
	}

	public Int0609Vo findByAuditIncsendNo(String incsendNo) throws Exception {
		IaAuditIncSendH header = iaAuditIncSendHRepository.findByIncsendNoAndIsDeleted(incsendNo, "N");

		List<IaAuditIncSendD> details = iaAuditIncSendDRepository.findByIncsendNoAndIsDeletedOrderByIncsendNo(incsendNo, "N");
		List<Int0609TableVo> tableList = new ArrayList<>();
		Int0609TableVo table = null;
		for (IaAuditIncSendD d : details) {
			table = new Int0609TableVo();
			table.setActcostCent(d.getIncsendActcostCent());
			table.setCnt(d.getIncsendCnt());
			table.setDateDiff(d.getIncsendPeriod().longValue());
			table.setGfDate(d.getIncsendGfDate());
			table.setGfDateStr(ConvertDateUtils.formatDateToString(d.getIncsendGfDate(), ConvertDateUtils.DD_MM_YY));
			table.setGfRefNo(d.getIncsendRefNo());
			table.setIncsendIncStm(d.getIncsendIncStm());
			table.setOffcode(d.getIncsendGfOffcode());
			table.setSum1Sum2(d.getIncsendAmount());
			table.setSum4I(d.getIncsendInc115010());
			table.setSum4II(d.getIncsendInc116010());
			table.setSum4Sum5(d.getIncsendEdc());
			table.setSum7(d.getIncsendEdcLicense());
			table.setTotalAmt(d.getIncsendTotalAmt());
			table.setTotalSendAmt(d.getIncsendAmtDelivery());
			table.setTrnDate(d.getIncsendTrnDate());
			table.setTrnDateStr(ConvertDateUtils.formatDateToString(d.getIncsendTrnDate(), ConvertDateUtils.DD_MM_YYYY));
			/* set value on input and check box */
			table.setIncsendIncStm(d.getIncsendIncStm());
			table.setIncsendIncKtb(d.getIncsendIncKtb());
			table.setIncsendAccPayIn(d.getIncsendAccPayIn());
			table.setIncsendAccCash(d.getIncsendAccCash());
			table.setIncsendNote(d.getIncsendNote());
			table.setIncTransfer115010_116010(d.getIncTransfer115010_116010());
			tableList.add(table);
		}

		Int0609Vo response = new Int0609Vo();
		response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartmentFull(header.getIncsendOfficeCode()));
		response.setHeader(header);
		response.setTable(tableList);

		/* set request search footer */
		SearchVo requestSearch = new SearchVo();
		Int0609TableVo footer = null;
		requestSearch.setPaperNumber(incsendNo);
		IaAuditIncSendD resFooter = iaAuditIncSendDRepository.summaryByIncsendNo(requestSearch);
		if (resFooter != null) {
			footer = new Int0609TableVo();
			footer.setCnt(resFooter.getIncsendCnt());
			footer.setTotalAmt(resFooter.getIncsendTotalAmt());
			footer.setTotalSendAmt(resFooter.getIncsendAmtDelivery());
			footer.setSum1Sum2(resFooter.getIncsendAmount());
			footer.setSum4Sum5(resFooter.getIncsendEdc());
			footer.setSum7(resFooter.getIncsendEdcLicense());
			footer.setIncsendIncKtb(resFooter.getIncsendIncKtb());
			footer.setSum4I(resFooter.getIncsendInc115010());
			footer.setSum4II(resFooter.getIncsendInc116010());
		}
		response.setFooter(footer);
		return response;
	}

}
