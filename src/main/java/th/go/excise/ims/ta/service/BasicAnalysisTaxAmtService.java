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
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD5;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD5Repository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxAmtVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class BasicAnalysisTaxAmtService extends AbstractBasicAnalysisService<BasicAnalysisTaxAmtVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisTaxAmtService.class);

	@Autowired
	private TaPaperBaD5Repository taPaperBaD5Repository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<BasicAnalysisTaxAmtVo> voList = new ArrayList<BasicAnalysisTaxAmtVo>();
		BasicAnalysisTaxAmtVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new BasicAnalysisTaxAmtVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setTaxSubmissionDate(""); // FIXME anafri0001Vo.getRegInDate()
			vo.setNetTaxByValue(BigDecimal.ZERO); // FIXME Calculate
			vo.setNetTaxByQty(BigDecimal.ZERO); // FIXME Calculate
			vo.setNetTaxAmt(BigDecimal.ZERO); // FIXME Calculate
			vo.setAnaNetTaxByValue(anafri0001Vo.getTaxValueAmt());
			vo.setAnaNetTaxByQty(anafri0001Vo.getTaxQuantityAmt());
			vo.setAnaNetTaxAmt(anafri0001Vo.getTaxAmt());
			vo.setDiffNetTaxByValue(vo.getAnaNetTaxByValue().subtract(vo.getNetTaxByValue()));
			vo.setDiffNetTaxByQty(vo.getAnaNetTaxByQty().subtract(vo.getNetTaxByQty()));
			vo.setDiffNetTaxAmt(vo.getAnaNetTaxAmt().subtract(vo.getNetTaxAmt()));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisTaxAmtVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());

		List<TaPaperBaD5> entityList = taPaperBaD5Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisTaxAmtVo> voList = new ArrayList<>();
		BasicAnalysisTaxAmtVo vo = null;
		for (TaPaperBaD5 entity : entityList) {
			vo = new BasicAnalysisTaxAmtVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setTaxSubmissionDate(""); // FIXME Convert Date
			vo.setNetTaxByValue(entity.getNetTaxByValue());
			vo.setNetTaxByQty(entity.getNetTaxByQty());
			vo.setNetTaxAmt(entity.getNetTaxAmt());
			vo.setAnaNetTaxByValue(entity.getAnaNetTaxByValue());
			vo.setAnaNetTaxByQty(entity.getAnaNetTaxByQty());
			vo.setAnaNetTaxAmt(entity.getAnaNetTaxAmt());
			vo.setDiffNetTaxByValue(entity.getDiffNetTaxByValue());
			vo.setDiffNetTaxByQty(entity.getDiffNetTaxByQty());
			vo.setDiffNetTaxAmt(entity.getDiffNetTaxAmt());
			voList.add(vo);
		}
		
		return voList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		logger.info("save paperBaNumber={}", formVo.getPaperBaNumber());
		
		List<BasicAnalysisTaxAmtVo> voList = inquiryByWs(formVo);
		List<TaPaperBaD5> entityList = new ArrayList<>();
		TaPaperBaD5 entity = null;
		int i = 1;
		for (BasicAnalysisTaxAmtVo vo : voList) {
			entity = new TaPaperBaD5();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setGoodsDesc(vo.getGoodsDesc());
			entity.setTaxSubmissionDate(null); // FIXME Convert Date
			entity.setNetTaxByValue(vo.getNetTaxByValue());
			entity.setNetTaxByQty(vo.getNetTaxByQty());
			entity.setNetTaxAmt(vo.getNetTaxAmt());
			entity.setAnaNetTaxByValue(vo.getAnaNetTaxByValue());
			entity.setAnaNetTaxByQty(vo.getAnaNetTaxByQty());
			entity.setAnaNetTaxAmt(vo.getAnaNetTaxAmt());
			entity.setDiffNetTaxByValue(vo.getAnaNetTaxByValue());
			entity.setDiffNetTaxByQty(vo.getAnaNetTaxByQty());
			entity.setDiffNetTaxAmt(vo.getAnaNetTaxAmt());
			i++;
		}
		
		taPaperBaD5Repository.saveAll(entityList);
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
		params.put("commentText5", formVo.getCommentText5());
		// get data from inquiryByPaperBaNumber()
		List<BasicAnalysisTaxAmtVo> dataList = inquiryByPaperBaNumber(formVo);
		// add data to dataSource
		JRDataSource dataSource = new JRBeanCollectionDataSource(dataList);
		// check jasper by yearNum
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_PAPER_BA_D5 + "." + FILE_EXTENSION.JASPER, params, dataSource);
		
		return jasperPrint;
	}

}
