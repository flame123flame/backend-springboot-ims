package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.math.MathContext;
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
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD3;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD3Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxValueVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxValueService extends AbstractBasicAnalysisService<BasicAnalysisTaxValueVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxValueService.class);

	@Autowired
	private TaPaperBaD3Repository taPaperBaD3Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxValueVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxValueVo> voList = new ArrayList<BasicAnalysisTaxValueVo>();
		BasicAnalysisTaxValueVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxValueVo();
			vo.setGoodsDescText(anafri0001Vo.getProductName());
			vo.setTaxQty(anafri0001Vo.getProductQty());
			vo.setInformPrice(anafri0001Vo.getProductPrice());
			vo.setGoodsValueAmt(vo.getTaxQty().multiply(vo.getInformPrice(), MathContext.DECIMAL32));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxValueVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD3> entityList = taPaperBaD3Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxValueVo> voList = new ArrayList<>();
		BasicAnalysisTaxValueVo vo = null;
		for (TaPaperBaD3 entity : entityList) {
			vo = new BasicAnalysisTaxValueVo();
			vo.setGoodsDescText(entity.getGoodsDescText());
			vo.setTaxQty(entity.getTaxQty());
			vo.setInformPrice(entity.getInformPrice());
			vo.setGoodsValueAmt(entity.getGoodsValueAmt());
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxValueVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD3> entityList = new ArrayList<>();
		TaPaperBaD3 entity = null;
		int i = 1;
		for (BasicAnalysisTaxValueVo vo : voList) {
			entity = new TaPaperBaD3();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setTaxQty(vo.getTaxQty());
			entity.setInformPrice(vo.getInformPrice());
			entity.setGoodsValueAmt(vo.getGoodsValueAmt());
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD3Repository.saveAll(entityList);
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
		params.put("commentText3", formVo.getCommentText3());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisTaxValueVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D3 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}

}
