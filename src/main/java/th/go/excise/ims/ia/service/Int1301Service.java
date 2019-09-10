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
import th.go.excise.ims.ia.persistence.repository.IaAuditPmassessDRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPmassessHRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.IaAuditPmassessDVo;
import th.go.excise.ims.ia.vo.IaAuditPmassessHVo;
import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.Int1301SaveVo;
import th.go.excise.ims.ia.vo.Int1301UpdateVo;
import th.go.excise.ims.ia.vo.Int1301Vo;
import th.go.excise.ims.ia.vo.WsPmAssessDVo;
import th.go.excise.ims.ia.vo.WsPmAssessHVo;
import th.go.excise.ims.ws.persistence.repository.WsPmAssessDRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmAssessHRepository;

@Service
public class Int1301Service {
	private Logger logger = LoggerFactory.getLogger(Int1301Service.class);

	@Autowired
	private WsPmAssessDRepository wsPmAssessDRepository;

	@Autowired
	private WsPmAssessHRepository wsPmAssessHRepository;
	
	@Autowired
	private IaAuditPmassessDRepository iaAuditPmassessDRepository;
	
	@Autowired
	private IaAuditPmassessHRepository iaAuditPmassessHRepository;
	
	@Autowired
	private IaCommonService iaCommonService;

	public Int1301SaveVo getWsPaAssess(Int1301Filter request) {
		Int1301SaveVo response = new Int1301SaveVo();
		/* find header */
		List<WsPmAssessHVo> resHeader = wsPmAssessHRepository.filterWsPaAssess(request);
		for (WsPmAssessHVo header : resHeader) {
			/* find and set data detail */
			header.setDetail(wsPmAssessDRepository.filterWsPaAssessD(header.getOffCode(), header.getFormCode()));
			header.setProcessDateStr(ConvertDateUtils.formatLocalDateToString(header.getProcessDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		}
		response.setPmAssessData(resHeader);

		return response;
	}

	public String saveWsPmAssess(Int1301SaveVo request) throws Exception {
		IaAuditPmassessH header = null;
		IaAuditPmassessD detail = null;
		String auditPmassessNo = iaCommonService.autoGetRunAuditNoBySeqName("PMA", request.getPmAssessData().get(0).getOffCode(), "AUDIT_PMASSESS_NO_SEQ", 8);
		for (WsPmAssessHVo requestHdr :  request.getPmAssessData()) {
			header = new IaAuditPmassessH();
			requestHdr.setProcessDate(null);
			BeanUtils.copyProperties(header, requestHdr);
			header.setProcessDate(ConvertDateUtils.parseStringToDate(requestHdr.getProcessDateStr(), ConvertDateUtils.DD_MM_YYYY));
			header.setAuditPmassessNo(auditPmassessNo);
			/* set form header (id same id = same data) */
			header.setPmaAuditResult(request.getFormHeader().getPmaAuditResult());
			header.setPmaAuditSuggestion(request.getFormHeader().getPmaAuditSuggestion());
			header.setPmaAuditEvident(request.getFormHeader().getPmaAuditEvident());
			iaAuditPmassessHRepository.save(header);
			
			for (WsPmAssessDVo requestDtl : requestHdr.getDetail()) {
				detail = new IaAuditPmassessD();
				BeanUtils.copyProperties(detail, requestDtl);
				detail.setPmAssessDSeq(new BigDecimal(requestDtl.getPmAssessDSeq()));
				detail.setAuditPmAssessNo(auditPmassessNo);
				iaAuditPmassessDRepository.save(detail);
			}
		}
		
		return auditPmassessNo;
	}

	public List<IaAuditPmassessH> getAuditPmassessNo() {
		return iaAuditPmassessHRepository.getAuditPmassessNoList();
	}

	public Int1301Vo getIaPmAssess(String auditPmassessNo) {
		Int1301Vo response = new Int1301Vo();
		/* find header by auditPmassessNo */
		List<IaAuditPmassessHVo> resHeader = iaAuditPmassessHRepository.filterIaPmAssessByAuditPmassessNo(auditPmassessNo);
		for (IaAuditPmassessHVo header : resHeader) {
			/* find and set data detail */
			header.setDetail(iaAuditPmassessDRepository.filterIaPaAssessDByAuditPmassessNo(auditPmassessNo, header.getFormCode()));
			header.setProcessDateStr(ConvertDateUtils.formatDateToString(header.getProcessDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_EN));
		}
		response.setHeader(resHeader);
		response.setBudgetYear(resHeader.get(0).getFormYear());
		
		/* set ExciseDepartmentVo */
		logger.info(resHeader.get(0).getOffCode());
		if(resHeader.get(0).getOffCode() != null) {
			response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartmentFull(resHeader.get(0).getOffCode()));
		}

		return response;
	}
	
	public void updateIaPmAssess(Int1301UpdateVo request) {
		/* loop for update headers */
		List<IaAuditPmassessH> dataHeaderList = iaAuditPmassessHRepository.findByAuditPmassessNoAndIsDeleted(request.getHeader().getAuditPmassessNo(), "N");
		for (IaAuditPmassessH entity : dataHeaderList) {
			entity.setPmaAuditSuggestion(request.getHeader().getPmaAuditSuggestion());
			entity.setPmaAuditResult(request.getHeader().getPmaAuditResult());
			entity.setPmaAuditEvident(request.getHeader().getPmaAuditEvident());
			iaAuditPmassessHRepository.save(entity);
		}
		
		/* loop for update details */
		for (IaAuditPmassessDVo iaAuditPmassessDVo : request.getDetail()){
			IaAuditPmassessD dataDetail = iaAuditPmassessDRepository.findById(iaAuditPmassessDVo.getAuditPmDId()).get();
			dataDetail.setAuditResult(iaAuditPmassessDVo.getAuditResult());
			iaAuditPmassessDRepository.save(dataDetail);
		}
	}

}
