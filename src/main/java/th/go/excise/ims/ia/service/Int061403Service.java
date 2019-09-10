package th.go.excise.ims.ia.service;

import javax.persistence.Convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.persistence.repository.IaAuditTxinsurD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditTxinsurHRepository;
import th.go.excise.ims.ia.vo.AuditTxinsurD1Vo;
import th.go.excise.ims.ia.vo.Int061403FormVo;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;

@Service
public class Int061403Service {
	@Autowired
	private IaAuditTxinsurD1Repository iaAuditTxinsurD1Repository;
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	
	@Autowired
	private IaAuditTxinsurHRepository iaAuditTxinsurHRepository;
	
	public TaWsReg4000 findReg4000ById(Long wsReg4000Id) {
		return taWsReg4000Repository.findById(wsReg4000Id).get();
	}

	public void update(Int061403FormVo request) {
		/* update header */
		IaAuditTxinsurH header = iaAuditTxinsurHRepository.findById(request.getHeader().getIaAuditTxinsurHId()).get();
		header.setTxinsurAuditFlag(request.getHeader().getTxinsurAuditFlag());
		header.setTxinsurConditionText(request.getHeader().getTxinsurConditionText());
		header.setTxinsurCriteriaText(request.getHeader().getTxinsurCriteriaText());
		iaAuditTxinsurHRepository.save(header);
		
		/* update detail */
		IaAuditTxinsurD1 detail = iaAuditTxinsurD1Repository.findByAuditTxinsurNoAndNewRegIdAndIsDeleted(header.getAuditTxinsurNo(), request.getDetail().getNewRegId(), "N");
		detail.setBankGuaranteeAmt(request.getDetail().getBankGuaranteeAmt());
		detail.setBankGuaranteeDate(ConvertDateUtils.parseStringToDate(request.getDetail().getBankGuaranteeDateStr(), ConvertDateUtils.DD_MM_YYYY));
		detail.setBankGuaranteeNo(request.getDetail().getBankGuaranteeNo());
		if(request.getDetail().getBankGuaranteeResultBL()) {
			request.getDetail().setBankGuaranteeResult("Y");
		} else {
			request.getDetail().setBankGuaranteeResult("N");
		}
		detail.setBankGuaranteeResult(request.getDetail().getBankGuaranteeResult());
		detail.setCashGuaranteeAmt(request.getDetail().getCashGuaranteeAmt());
		detail.setCashGuaranteeDate(ConvertDateUtils.parseStringToDate(request.getDetail().getCashGuaranteeDateStr(), ConvertDateUtils.DD_MM_YYYY));
		if(request.getDetail().getCashGuaranteeResultBL()) {
			request.getDetail().setCashGuaranteeResult("Y");
		} else {
			request.getDetail().setCashGuaranteeResult("N");
		}
		detail.setCashGuaranteeResult(request.getDetail().getCashGuaranteeResult());
		detail.setCashReceiptNo(request.getDetail().getCashReceiptNo());
		iaAuditTxinsurD1Repository.save(detail);
	}

	public IaAuditTxinsurH getHeader(Long iaAuditTxinsurHId) {
		return iaAuditTxinsurHRepository.findById(iaAuditTxinsurHId).get();
	}

	public AuditTxinsurD1Vo getDetail(String newRegId, Long iaAuditTxinsurHId) {
		IaAuditTxinsurH header = iaAuditTxinsurHRepository.findById(iaAuditTxinsurHId).get();
		IaAuditTxinsurD1 detail = iaAuditTxinsurD1Repository.findByAuditTxinsurNoAndNewRegIdAndIsDeleted(header.getAuditTxinsurNo(), newRegId, "N");
		AuditTxinsurD1Vo response = new AuditTxinsurD1Vo();
		response.setBankGuaranteeAmt(detail.getBankGuaranteeAmt());
		response.setBankGuaranteeDateStr(ConvertDateUtils.formatDateToString(detail.getBankGuaranteeDate(), ConvertDateUtils.DD_MM_YYYY));
		response.setBankGuaranteeNo(detail.getBankGuaranteeNo());
		if("Y".equals(detail.getBankGuaranteeResult())) {
			response.setBankGuaranteeResultBL(true);
		} else {
			response.setBankGuaranteeResultBL(false);
		}
		response.setCashGuaranteeAmt(detail.getCashGuaranteeAmt());
		response.setCashGuaranteeDateStr(ConvertDateUtils.formatDateToString(detail.getCashGuaranteeDate(), ConvertDateUtils.DD_MM_YYYY));
		response.setCashReceiptNo(detail.getCashReceiptNo());
		if("Y".equals(detail.getCashGuaranteeResult())) {
			response.setCashGuaranteeResultBL(true);
		} else {
			response.setCashGuaranteeResultBL(false);
		}
		
		return response;
	}
}
