package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2D;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2H;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy2DRepository;
import th.go.excise.ims.ia.persistence.repository.IaAuditPy2HRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.IaAuditPy2DVo;
import th.go.excise.ims.ia.vo.IaAuditPy2HVo;
import th.go.excise.ims.ia.vo.Int1303FilterVo;
import th.go.excise.ims.ia.vo.Int1303UpdateVo;
import th.go.excise.ims.ia.vo.Int1303Vo;
import th.go.excise.ims.ia.vo.WsPmPy2HVo;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2DVo;
import th.go.excise.ims.ws.persistence.repository.WsPmPy2DRepository;
import th.go.excise.ims.ws.persistence.repository.WsPmPy2HRepository;

@Service
public class Int1303Service {
	private Logger logger = LoggerFactory.getLogger(Int1303Service.class);
	
	@Autowired
	private WsPmPy2DRepository wsPmPy2DRepository;
	
	@Autowired
	private WsPmPy2HRepository wsPmPy2HRepository;
	
	@Autowired
	private IaCommonService iaCommonService;
	
	@Autowired
	private IaAuditPy2HRepository iaAuditPy2HRepository;
	
	@Autowired
	private IaAuditPy2DRepository iaAuditPy2DRepository;

	public Int1303Vo getWsPmPy2(Int1303FilterVo request) {
		Int1303Vo response = new Int1303Vo();
		/* headers */
		List<WsPmPy2HVo> headers = wsPmPy2HRepository.filterWsPaPy2H(request);
		if (headers.size() > 0) {
			response.setHeaders(headers.get(0));
			/* details */
			List<WsPmPy2DVo> details = wsPmPy2DRepository.filterWsPaPy2D(headers.get(0).getFormCode(), headers.get(0).getOffCode());
			for (WsPmPy2DVo wsPmPy2DVo : details) {
				wsPmPy2DVo.setProcessDateStr(ConvertDateUtils.formatLocalDateToString(wsPmPy2DVo.getProcessDate(), ConvertDateUtils.DD_MM_YYYY));
			}
			response.setDetails(details);
		}
		return response;
	}

	public String savePmPy2(Int1303Vo request) throws Exception {
		IaAuditPy2H header = new IaAuditPy2H();
		IaAuditPy2D detail = null;
		String auditPy2No = iaCommonService.autoGetRunAuditNoBySeqName("PY2", request.getHeaders().getOffCode(), "AUDIT_PY2_NO_SEQ", 8);
		BeanUtils.copyProperties(header, request.getHeaders());
		header.setBudgetYear(request.getHeaders().getFormYear());
		header.setOfficeCode(request.getHeaders().getOffCode());
		header.setAuditPy2No(auditPy2No);
		iaAuditPy2HRepository.save(header);
		
		for (WsPmPy2DVo d : request.getDetails()) {
			detail = new IaAuditPy2D();
			BeanUtils.copyProperties(detail, d);
			detail.setAuditPy2No(auditPy2No);
			iaAuditPy2DRepository.save(detail);
		}
		return auditPy2No;
	}

	public List<IaAuditPy2H> findAuditPy2NoList() {
		return iaAuditPy2HRepository.getAuditPy2NoList();
	}
	
	public Int1303UpdateVo getIaPmPy2(String auditPy2No) throws Exception {
		Int1303UpdateVo response = new Int1303UpdateVo();
		List<IaAuditPy2DVo> details = new ArrayList<IaAuditPy2DVo>();
		IaAuditPy2HVo header = iaAuditPy2HRepository.filterIaPmPy2ByAuditPy2No(auditPy2No).get(0);
		response.setHeaders(header);
		response.setDetails(iaAuditPy2DRepository.findByAuditPy2No(auditPy2No));
		
		/* set ExciseDepartmentVo */
		logger.debug(header.getOfficeCode());
		if(header.getOfficeCode() != null) {
			response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartmentFull(header.getOfficeCode()));
		}
		return response;
	}

	public void updateIaPmPy2(Int1303UpdateVo request) {
		/* update header */
		IaAuditPy2H header = iaAuditPy2HRepository.findByAuditPy2NoAndIsDeleted(request.getHeaders().getAuditPy2No(), "N");
		header.setPy2AuditSuggestion(request.getHeaders().getPy2AuditSuggestion());
		header.setPy2AuditResult(request.getHeaders().getPy2AuditResult());
		header.setPy2ActivityResult(request.getHeaders().getPy2ActivityResult());
		header.setPy2AuditEvident(request.getHeaders().getPy2AuditEvident());
		iaAuditPy2HRepository.save(header);
		
		/* update details */
		IaAuditPy2D detail = null;
		for (IaAuditPy2DVo d : request.getDetails()) {
			detail = new IaAuditPy2D();
			detail = iaAuditPy2DRepository.findById(d.getAuditPy2DId()).get();
			detail.setPy2AuditResult(d.getPy2AuditResult());
			iaAuditPy2DRepository.save(detail);
		}
	}

}
