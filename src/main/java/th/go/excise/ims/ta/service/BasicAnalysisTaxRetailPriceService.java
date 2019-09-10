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
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD2;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD2Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxRetailPriceVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxRetailPriceService extends AbstractBasicAnalysisService<BasicAnalysisTaxRetailPriceVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxRetailPriceService.class);

	@Autowired
	private TaPaperBaD2Repository taPaperBaD2Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxRetailPriceVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxRetailPriceVo> voList = new ArrayList<>();
		BasicAnalysisTaxRetailPriceVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxRetailPriceVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxInformPrice(anafri0001Vo.getProductPrice());
			vo.setInformPrice(BigDecimal.ZERO); // FIXME Find InformPrice from PS0201
			vo.setDiffTaxInformPrice(vo.getInformPrice().subtract(vo.getTaxInformPrice()));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxRetailPriceVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());

		List<TaPaperBaD2> entityList = taPaperBaD2Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxRetailPriceVo> voList = new ArrayList<>();
		BasicAnalysisTaxRetailPriceVo vo = null;
		for (TaPaperBaD2 entity : entityList) {
			vo = new BasicAnalysisTaxRetailPriceVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxInformPrice(entity.getTaxInformPrice());
			vo.setInformPrice(entity.getInformPrice());
			vo.setDiffTaxInformPrice(entity.getDiffTaxInformPrice());
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxRetailPriceVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD2> entityList = new ArrayList<>();
		TaPaperBaD2 entity = null;
		int i = 1;
		for (BasicAnalysisTaxRetailPriceVo vo : voList) {
			entity = new TaPaperBaD2();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(vo.getGoodsDesc());
			entity.setTaxInformPrice(vo.getTaxInformPrice());
			entity.setInformPrice(vo.getInformPrice());
			entity.setDiffTaxInformPrice(vo.getDiffTaxInformPrice());
			entityList.add(entity);
			i++;
		}
		
		taPaperBaD2Repository.saveAll(entityList);
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
		params.put("commentText2", formVo.getCommentText2());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisTaxRetailPriceVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D2 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}

}
