package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessD;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtD;
import th.go.excise.ims.ia.persistence.entity.IaAuditPmqtH;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmqtDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmqtHRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.IaAuditPmQtDVo;
import th.go.excise.ims.ia.vo.IaAuditPmQtHVo;
import th.go.excise.ims.ia.vo.IaAuditPmassessDVo;
import th.go.excise.ims.ia.vo.IaAuditPmassessHVo;
import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.Int1301SaveVo;
import th.go.excise.ims.ia.vo.Int1301UpdateVo;
import th.go.excise.ims.ia.vo.Int1301Vo;
import th.go.excise.ims.ia.vo.Int1304FormVo;
import th.go.excise.ims.ia.vo.Int1304SaveVo;
import th.go.excise.ims.ia.vo.Int1304UpdateVo;
import th.go.excise.ims.ia.vo.Int1304Vo;
import th.go.excise.ims.ia.vo.WsPmAssessDVo;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ia.vo.WsPmQtDVo;
import th.go.excise.ims.ia.vo.WsPmQtHVo;
import th.go.excise.ims.ws.persistence.repository.WsPmQtDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmQtHRepository;

@Service
public class Int1304Service {
	
	private Logger logger = LoggerFactory.getLogger(Int1304Service.class);
	
	@Autowired
	private WsPmQtHRepository wsPmQtHRepository;
	
	@Autowired
	private WsPmQtDRepository wsPmQtDRepository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	@Autowired
	private IaAuditPmqtHRepository iaAuditPmqtHRepository ;
	
	@Autowired
	private IaAuditPmqtDRepository iaAuditPmqtDRepository ;
	
	
	
	public Int1304SaveVo getWsQt(Int1304FormVo request) {
		Int1304SaveVo response = new Int1304SaveVo();
		/* find header */
		List<WsPmQtHVo> resHeader = wsPmQtHRepository.filterWsPmQt(request);
		for (WsPmQtHVo header : resHeader) {
			/* find and set data detail */
			header.setDetail(wsPmQtDRepository.filterWsPmQtD(header.getOffCode(),header.getFormCode()));
			header.setProcessDateStr(ConvertDateUtils.formatLocalDateToString(header.getProcessDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		}
		response.setPmQtData(resHeader);
		return response;
	}	
	
	public String saveWsQt(Int1304SaveVo request) throws Exception {
		IaAuditPmqtH header = null;
		IaAuditPmqtD detail = null;
		String auditQtNo = iaCommonService.autoGetRunAuditNoBySeqName("P", request.getPmQtData().get(0).getOffCode(), "AUDIT_PMQT_NO_SEQ", 8);
		for (WsPmQtHVo requestHdr :  request.getPmQtData()) {
			header = new IaAuditPmqtH();
			requestHdr.setProcessDate(null);
			BeanUtils.copyProperties(header, requestHdr);
			header.setProcessDate(ConvertDateUtils.parseStringToDate(requestHdr.getProcessDateStr(), ConvertDateUtils.DD_MM_YYYY));
			header.setAuditPmqtNo(auditQtNo);
			/* set form header (id same id = same data) */
			header.setQtAuditResult(request.getFormHeader().getQtAuditResult());
			header.setQtAuditSuggestion(request.getFormHeader().getQtAuditSuggestion());
			header.setQtAuditEvident(request.getFormHeader().getQtAuditEvident());
			iaAuditPmqtHRepository.save(header);
			
			for (WsPmQtDVo requestDtl : requestHdr.getDetail()) {
				detail = new IaAuditPmqtD();
				BeanUtils.copyProperties(detail, requestDtl);
				detail.setPmQtDSeq(new BigDecimal(requestDtl.getPmQtDSeq()));
				detail.setAuditPmqtNo(auditQtNo);
				iaAuditPmqtDRepository.save(detail);
			}
		}
		return auditQtNo;
	}
	
	
	public List<IaAuditPmqtH> getAuditPmQtNo() {
		return iaAuditPmqtHRepository.getAuditPmQtNoList();
	}
	
	public Int1304Vo getIaPmQt(String auditPmQtNo) {
		Int1304Vo response = new Int1304Vo();
		/* find header by auditPmassessNo */
		List<IaAuditPmQtHVo> resHeader = iaAuditPmqtHRepository.filterIaPmQtByAuditPmQtNo(auditPmQtNo);
		for (IaAuditPmQtHVo header : resHeader) {
			/* find and set data detail */
			header.setDetail(iaAuditPmqtDRepository.filterIaPmQtDByAuditPmQtNo(auditPmQtNo, header.getFormCode()));
			header.setProcessDateStr(ConvertDateUtils.formatDateToString(header.getProcessDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		}
		response.setHeader(resHeader);
		response.setBudgetYear(resHeader.get(0).getFormYear());
		
		/* set ExciseDepartmentVo */
		logger.info(resHeader.get(0).getOffCode());
		if(resHeader.get(0).getOffCode() != null) {
			response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartment(resHeader.get(0).getOffCode()));
		}

		return response;
	}
	
	public void updateIaPmQt(Int1304UpdateVo request) {
		/* loop for update headers */
		List<IaAuditPmqtH> dataHeaderList = iaAuditPmqtHRepository.findByAuditPmqtNoAndIsDeleted(request.getHeader().getAuditPmqtNo(), "N");
		for (IaAuditPmqtH entity : dataHeaderList) {
			entity.setQtAuditSuggestion(request.getHeader().getQtAuditSuggestion());
			entity.setQtAuditResult(request.getHeader().getQtAuditResult());
			entity.setQtAuditEvident(request.getHeader().getQtAuditEvident());
			iaAuditPmqtHRepository.save(entity);
		}	
		/* loop for update details */
		for (IaAuditPmQtDVo iaAuditPmQtDVo : request.getDetail()){
			IaAuditPmqtD dataDetail = iaAuditPmqtDRepository.findById(iaAuditPmQtDVo.getAuditPmqtDId()).get();
			dataDetail.setAuditResult(iaAuditPmQtDVo.getAuditResult());
			iaAuditPmqtDRepository.save(dataDetail);
		}
	}
	
	
	
	
	
	
	
	
}
