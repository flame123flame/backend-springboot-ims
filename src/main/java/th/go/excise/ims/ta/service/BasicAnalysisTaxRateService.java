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
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD4;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD4Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRateVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxRateService extends AbstractBasicAnalysisService<BasicAnalysisTaxRateVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxRateService.class);

	@Autowired
	private TaPaperBaD4Repository taPaperBaD4Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxRateVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxRateVo> voList = new ArrayList<>();
		BasicAnalysisTaxRateVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxRateVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxRateByPrice(anafri0001Vo.getValueRate());
			vo.setTaxRateByQty(anafri0001Vo.getQtyRate());
			vo.setAnaTaxRateByPrice(BigDecimal.ZERO); // FIXME Find Value
			vo.setAnaTaxRateByQty(BigDecimal.ZERO); // FIXME Find Value
			vo.setDiffTaxRateByPrice(vo.getAnaTaxRateByPrice().subtract(vo.getTaxRateByPrice()));
			vo.setDiffTaxRateByQty(vo.getAnaTaxRateByQty().subtract(vo.getTaxRateByQty()));	
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxRateVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<TaPaperBaD4> entityList = taPaperBaD4Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxRateVo> voList = new ArrayList<>();
		BasicAnalysisTaxRateVo vo = null;
		for (TaPaperBaD4 entity : entityList) {
			vo = new BasicAnalysisTaxRateVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxRateByPrice(entity.getTaxRateByPrice());
			vo.setTaxRateByQty(entity.getTaxRateByQty());
			vo.setAnaTaxRateByPrice(entity.getAnaTaxRateByPrice());
			vo.setAnaTaxRateByQty(entity.getAnaTaxRateByQty());
			vo.setDiffTaxRateByPrice(entity.getDiffTaxRateByPrice());
			vo.setDiffTaxRateByQty(entity.getDiffTaxRateByQty());	
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxRateVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD4> entityList = new ArrayList<>();
		TaPaperBaD4 entity = null;
		int i = 1;
		for (BasicAnalysisTaxRateVo vo : voList) {
			entity = new TaPaperBaD4();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(vo.getGoodsDesc());
			entity.setTaxRateByPrice(vo.getTaxRateByPrice());
			entity.setTaxRateByQty(vo.getTaxRateByQty());
			entity.setAnaTaxRateByPrice(vo.getAnaTaxRateByPrice());
			entity.setAnaTaxRateByQty(vo.getAnaTaxRateByQty());
			entity.setDiffTaxRateByPrice(vo.getDiffTaxRateByPrice());
			entity.setDiffTaxRateByQty(vo.getDiffTaxRateByQty());
			i++;
		}
		
		taPaperBaD4Repository.saveAll(entityList);
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
		params.put("commentText4", formVo.getCommentText4());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisTaxRateVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D4 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}

}
