package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaUtilityBill;
import th.go.excise.ims.ia.persistence.repository.IaUtilityBillRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0913JdbcRepository;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SaveVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;

@Service
public class Int0913Service {
	
	@Autowired
	private Int0913JdbcRepository int0913JdbcRepository;
	
	@Autowired
	private IaUtilityBillRepository iaUtilityBillRepository;
	
	public List<Int091301ResultSearchVo> findIaUtilityBill(Int091301SearchVo int091301SearchVo){
		List<Int091301ResultSearchVo> dataFilter = new ArrayList<Int091301ResultSearchVo>();
		if ("Y".equals(int091301SearchVo.getFlagSearch())) {
			dataFilter = int0913JdbcRepository.findIaUtilityBillByCriteria(int091301SearchVo);
			if(dataFilter.size() > 0) {
				for (Int091301ResultSearchVo vo : dataFilter) {
					if(StringUtils.isNotBlank(vo.getLatePayCause())) {
						vo.setLatePayCauseStr(ApplicationCache.getParamInfoByCode(IaConstants.UTILITY_BILL_REASON.PARAM_GROUP_CODE, vo.getLatePayCause()).getValue1());
					}
					/* change format YYYYMM to MM/YYYY */	
					vo.setMonthWdPay(formatYYYYMMToMM_YYYY(vo.getMonthWdPay()));
					vo.setInvoiceMonth(formatYYYYMMToMM_YYYY(vo.getInvoiceMonth()));
				}
			}
		}
		return dataFilter;
	}
	
	public IaUtilityBill saveIaUtilityBill(Int091301SaveVo vo) {
		IaUtilityBill entity = null;
		if (vo.getUtilityBillSeq() != null ) {
			entity = iaUtilityBillRepository.findById(vo.getUtilityBillSeq()).get();
		} else {
			entity = new IaUtilityBill();
			entity.setExciseCode(vo.getExciseCode());
		}

		entity.setUbillType(vo.getUbillType());
		entity.setMonthWdPay(formatMM_YYYYToYYYYMM(vo.getMonthWdPay()));
		entity.setInvoiceSeq(vo.getInvoiceSeq());
		entity.setInvoiceMonth(formatMM_YYYYToYYYYMM(vo.getInvoiceMonth()));
		entity.setInvoiceNo(vo.getInvoiceNo());
		entity.setTelInvNumber(vo.getTelInvNumber());
		entity.setInvoiceDate(ConvertDateUtils.parseStringToDate(vo.getInvoiceDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH) );
		entity.setReceiveInvDate(ConvertDateUtils.parseStringToDate(vo.getReceiveInvDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
		entity.setInvoiceAmt(vo.getInvoiceAmt());
		entity.setReqWdDate(ConvertDateUtils.parseStringToDate(vo.getReqWdDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
		entity.setReqWdNo(vo.getReqWdNo());
		entity.setReqWdAmt(vo.getReqWdAmt());
		entity.setReqTaxAmt(vo.getReqTaxAmt());
		entity.setReqNetAmt(vo.getReqNetAmt());
		entity.setReqPayNo(vo.getReqPayNo());
		entity.setReqReceiptDate(ConvertDateUtils.parseStringToDate(vo.getReqReceiptDate(), ConvertDateUtils.DD_MM_YYYY , ConvertDateUtils.LOCAL_TH));
		entity.setLatePayCause(vo.getLatePayCause());
		entity.setUbillRemark(vo.getUbillRemark());
		
		return iaUtilityBillRepository.save(entity);
	}
	
	public void deleteIaUtilityBillById(Long id) {
		 iaUtilityBillRepository.deleteById(id);
	}
	
	private String formatMM_YYYYToYYYYMM(String dateStr) {
		return ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(dateStr, ConvertDateUtils.MM_YYYY), ConvertDateUtils.YYYYMM);
	}
	
	private String formatYYYYMMToMM_YYYY(String dateStr) {
		return ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(dateStr, ConvertDateUtils.YYYYMM), ConvertDateUtils.MM_YYYY);
	}

}
