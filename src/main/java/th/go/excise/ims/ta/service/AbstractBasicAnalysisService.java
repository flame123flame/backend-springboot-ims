package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;

public abstract class AbstractBasicAnalysisService<VO> {
	
	protected static final String NO_VALUE = "-";
	protected static final String DATEFORMAT_DD_MMMM_YYYY = "dd MMMM yyyy";
	protected static final String DATEFORMAT_MMMM_YYYY = "MMMM yyyy";

	public List<VO> inquiry(BasicAnalysisFormVo formVo) {
		if (StringUtils.isEmpty(formVo.getPaperBaNumber())) {
			return inquiryByWs(formVo);
		} else {
			return inquiryByPaperBaNumber(formVo);
		}
	};

	protected abstract List<VO> inquiryByWs(BasicAnalysisFormVo formVo);

	protected abstract List<VO> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo);

	protected abstract void save(BasicAnalysisFormVo formVo);
	
	protected abstract JasperPrint getJasperPrint(BasicAnalysisFormVo formVo, TaxAuditDetailVo taxAuditDetailVo) throws Exception;
	
	protected LocalDate toLocalDate(String inputDate) {
		return LocalDateUtils.thaiMonthYear2LocalDate(inputDate);
	}
	
}
