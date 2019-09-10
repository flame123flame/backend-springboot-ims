package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD1;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD1Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxQtyVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxQtyService extends AbstractBasicAnalysisService<BasicAnalysisTaxQtyVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxQtyService.class);

	@Autowired
	private TaPaperBaD1Repository taPaperBaD1Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxQtyVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxQtyVo> voList = new ArrayList<BasicAnalysisTaxQtyVo>();
		BasicAnalysisTaxQtyVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxQtyVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxQty(anafri0001Vo.getProductQty());
			vo.setMonthStatementTaxQty(BigDecimal.ZERO); // FIXME Find Month Statement Tax Qty from WS OASFRI0100
			vo.setDiffTaxQty(vo.getMonthStatementTaxQty().subtract(vo.getTaxQty()));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxQtyVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD1> entityList = taPaperBaD1Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxQtyVo> voList = new ArrayList<>();
		BasicAnalysisTaxQtyVo vo = null;
		for (TaPaperBaD1 entity : entityList) {
			vo = new BasicAnalysisTaxQtyVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxQty(entity.getTaxQty());
			vo.setMonthStatementTaxQty(entity.getMonthStmtTaxQty());
			vo.setDiffTaxQty(entity.getDiffTaxQty());
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxQtyVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD1> entityList = new ArrayList<>();
		TaPaperBaD1 entity = null;
		int i = 1;
		for (BasicAnalysisTaxQtyVo vo : voList) {
			entity = new TaPaperBaD1();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(vo.getGoodsDesc());
			entity.setTaxQty(vo.getTaxQty());
			entity.setMonthStmtTaxQty(vo.getMonthStatementTaxQty());
			entity.setDiffTaxQty(vo.getDiffTaxQty());
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD1Repository.saveAll(entityList);
	}

	@Override
	protected JasperPrint getJasperPrint(BasicAnalysisFormVo formVo, TaxAuditDetailVo taxAuditDetailVo) throws Exception {
		logger.info("generateReport paperBaNumber={}", formVo.getPaperBaNumber());
		
		// set data to report
		Map<String, Object> params = new HashMap<>();
		params.put("newRegId", formVo.getNewRegId());
		params.put("dutyGroupId", taxAuditDetailVo.getWsRegfri4000Vo().getRegDutyList().get(0).getGroupName());
		params.put("facFullname", taxAuditDetailVo.getWsRegfri4000Vo().getFacFullname());
		params.put("auJobResp", taxAuditDetailVo.getAuJobResp());
		params.put("paperBaNumber", formVo.getPaperBaNumber());
		params.put("startDate", formVo.getStartDate());
		params.put("endDate", formVo.getEndDate());
		params.put("commentText1", formVo.getCommentText1());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisTaxQtyVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D1 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}

}
