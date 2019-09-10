package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.persistence.repository.IaAuditTxinsurD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditTxinsurHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int061402JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.AuditTxinsurHVo;
import th.go.excise.ims.ia.vo.Int061402FilterVo;
import th.go.excise.ims.ia.vo.Int061402FormVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;

@Service
public class Int061402Service {
	private Logger logger = LoggerFactory.getLogger(Int061402Service.class);
	
	@Autowired
	private Int061402JdbcRepository int061402JdbcRepository;

	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;

	@Autowired
	private IaAuditTxinsurD1Repository iaAuditTxinsurD1Repository;

	@Autowired
	private IaAuditTxinsurHRepository iaAuditTxinsurHRepository;
	
	@Autowired
	private IaCommonService iaCommonService;

	public DataTableAjax<Ws_Reg4000Vo> filter(Int061402FilterVo formVo) {
		List<Ws_Reg4000Vo> data = new ArrayList<Ws_Reg4000Vo>();
		Integer count = null;
		
		if ("Y".equals(formVo.getFlagSearch())) {
			if (StringUtils.isBlank(formVo.getAuditTxinsurNo())) {
				data = int061402JdbcRepository.getDataFilter(formVo);
				count = int061402JdbcRepository.countDatafilter(formVo);
			} else {
				data = int061402JdbcRepository.findDataNotEqualAuditTxinsurNo(formVo);
				count = int061402JdbcRepository.countDataNotEqualAuditTxinsurNo(formVo);
			}
		}

		DataTableAjax<Ws_Reg4000Vo> dataTableAjax = new DataTableAjax<Ws_Reg4000Vo>();
		// dataTableAjax.setDraw(formVo.getDraw() + 1);
		dataTableAjax.setData(data);
		dataTableAjax.setRecordsTotal(count);
		dataTableAjax.setRecordsFiltered(count);

		return dataTableAjax;
	}

	public void save(Int061402FormVo request) {
		IaAuditTxinsurH header = new IaAuditTxinsurH();
		IaAuditTxinsurD1 detail = null;

		/* get getSeqTxinsurNo */
		String seqTxinsurNo = "";
		if(StringUtils.isBlank(request.getHeader().getAuditTxinsurNo())) {
			seqTxinsurNo = iaCommonService.autoGetRunAuditNoBySeqName( "T", request.getHeader().getOfficeCode(), "AUDIT_TXINSUR_NO_SEQ", 8);
		} else {
			seqTxinsurNo = request.getHeader().getAuditTxinsurNo();
		}

		/* save header */
		if(StringUtils.isBlank(request.getHeader().getAuditTxinsurNo())) {
			header.setAuditTxinsurNo(seqTxinsurNo);
			header.setOfficeCode(request.getHeader().getOfficeCode());
			header.setRegistDateStart(ConvertDateUtils.parseStringToDate(request.getHeader().getRegistDateStart(),
					ConvertDateUtils.DD_MM_YYYY));
			header.setRegistDateEnd(ConvertDateUtils.parseStringToDate(request.getHeader().getRegistDateEnd(),
					ConvertDateUtils.DD_MM_YYYY)); 
				iaAuditTxinsurHRepository.save(header);
		}

		/* find ResultSeq */
		BigDecimal resultSeq = iaAuditTxinsurD1Repository.countByAuditTxinsurNo(request.getHeader().getAuditTxinsurNo());
		/* save detail */
		for (int i = 0; i < request.getDetail().size(); i++) {
			detail = new IaAuditTxinsurD1();	
			if(StringUtils.isNotBlank(request.getHeader().getAuditTxinsurNo())) {
				detail.setAuditTxinsurNo(request.getHeader().getAuditTxinsurNo());
			} else {
				detail.setAuditTxinsurNo(seqTxinsurNo);
			}
			detail.setOfficeCode(request.getDetail().get(i).getOfficeCode());
			detail.setNewRegId(request.getDetail().get(i).getNewRegId());
			detail.setResultSeq(new java.math.BigDecimal(i+1).add(resultSeq));

			iaAuditTxinsurD1Repository.save(detail);
		}
	}
	
	public AuditTxinsurHVo findHeader(String auditTxinsurNo) {
		IaAuditTxinsurH header = iaAuditTxinsurHRepository.findByAuditTxinsurNo(auditTxinsurNo);
		
		AuditTxinsurHVo response = new AuditTxinsurHVo();
		response.setOfficeCode(header.getOfficeCode());
		response.setAuditTxinsurNo(header.getAuditTxinsurNo());
		response.setIaAuditTxinsurHId(header.getIaAuditTxinsurHId());
		response.setRegistDateStartStr(ConvertDateUtils.formatDateToString(header.getRegistDateStart(), ConvertDateUtils.DD_MM_YYYY));
		response.setRegistDateEndStr(ConvertDateUtils.formatDateToString(header.getRegistDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		response.setTxinsurConditionText(header.getTxinsurConditionText());
		response.setTxinsurCriteriaText(header.getTxinsurCriteriaText());
		response.setTxinsurAuditFlag(header.getTxinsurAuditFlag());
		
		/* set ExciseDepartmentVo */
		logger.info(header.getOfficeCode());
		if(header.getOfficeCode() != null) {
			response.setExciseDepartmentVo(ExciseDepartmentUtil.getExciseDepartmentFull(header.getOfficeCode()));
		}
		return response;
	}

}
