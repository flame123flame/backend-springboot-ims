package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD1Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD2Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncD3Repository;
import th.go.excise.ims.ia.persistence.repository.IaAuditIncHRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0601JdbcRepository;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD1WasteReceiptVo;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.IaAuditIncD3DatatableDtlVo;
import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;
import th.go.excise.ims.ia.vo.IaAuditIncHVo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

@Service
public class Int0601Service {

	private static final Logger logger = LoggerFactory.getLogger(Int0601Service.class);

	@Autowired
	private IaCommonService iaCommonService;
	@Autowired
	private Int0601JdbcRepository int0601JdbcRepository;
	@Autowired
	private IaAuditIncHRepository iaAuditIncHRepository;
	@Autowired
	private IaAuditIncD1Repository iaAuditIncD1Repository;
	@Autowired
	private IaAuditIncD2Repository iaAuditIncD2Repository;
	@Autowired
	private IaAuditIncD3Repository iaAuditIncD3Repository;

	public List<IaAuditIncD1Vo> findTab1ByCriteria(Int0601RequestVo int0601Vo) {
		logger.info("findTab1ByCriteria");
		
		List<WsIncfri8020Inc> wsIncfri8020List = int0601JdbcRepository.findByCriteria(int0601Vo, "OFFLINE_STATUS, RECEIPT_DATE, RECEIPT_NO");
		
		List<IaAuditIncD1Vo> voList = new ArrayList<>();
		IaAuditIncD1Vo vo = null;
		if (wsIncfri8020List != null && wsIncfri8020List.size() > 0) {
			for (WsIncfri8020Inc data : wsIncfri8020List) {
				vo = new IaAuditIncD1Vo();
				vo.setIaAuditIncDId(data.getWsIncfri8020IncId());
				vo.setOfficeCode(data.getOfficeReceive());
				vo.setDocCtlNo(data.getIncCtlNo());
				vo.setReceiptNo(data.getReceiptNo());
				vo.setReceiptDate(data.getReceiptDate() != null ? ConvertDateUtils.formatDateToString(data.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				vo.setTaxName(data.getIncomeName());
				vo.setTaxCode(data.getIncomeCode());
				vo.setAmount(data.getNetTaxAmt());
				voList.add(vo);
			}
			checkWasteReceiptNo(voList);
		}
		
		return voList;
	}
	
	private void checkWasteReceiptNo(List<IaAuditIncD1Vo> voList) {
		String[] tmp = null;
		String group1 = null;
		long running1 = 0L;
		String group2 = null;
		long running2 = 0L;
		int size = voList.size();
		for (int i = 0; i < size; i++) {
			tmp = voList.get(i).getReceiptNo().split("/");
			group1 = tmp[0];
			running1 = Long.valueOf(StringUtils.trim(tmp[1]));
			if (i + 1 < size) {
				tmp = voList.get(i + 1).getReceiptNo().split("/");
				group2 = tmp[0];
				if (group1.equals(group2)) {
					running2 = Long.valueOf(StringUtils.trim(tmp[1]));
					if ((running1 + 1) != running2) {
						voList.get(i).setWasteReceiptFlag(FLAG.Y_FLAG);
						voList.get(i + 1).setWasteReceiptFlag(FLAG.Y_FLAG);
					}
				}
			}
		}
	}

	public List<IaAuditIncD2Vo> findIaAuditIncD2ByCriteria(Int0601RequestVo criteria) {
		logger.info("findIaAuditIncD2ByCriteria");
		return int0601JdbcRepository.findDataTab2(criteria);
	}

	public List<IaAuditIncD3Vo> findIaAuditIncD3ByCriteria(Int0601RequestVo criteria) {
		logger.info("findIaAuditIncD3ByCriteria");
		return int0601JdbcRepository.findDataTab3(criteria);
	}

	public List<IaAuditIncHVo> findAllIaAuditIncH() {
		logger.info("findAllIaAuditIncH");
		
		List<IaAuditIncH> iaAuditIncHList = iaAuditIncHRepository.findIaAuditIncHAllDataActive();
		IaAuditIncHVo incHVo = null;
		List<IaAuditIncHVo> IaAuditIncHVoList = new ArrayList<>();
		
		for (IaAuditIncH data : iaAuditIncHList) {
			incHVo = new IaAuditIncHVo();
			try {
				incHVo.setAuditIncSeq(data.getAuditIncSeq());
				incHVo.setOfficeCode(data.getOfficeCode());
				incHVo.setReceiptDateFrom(data.getReceiptDateFrom() != null ? ConvertDateUtils.formatDateToString(data.getReceiptDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				incHVo.setReceiptDateTo(data.getReceiptDateTo() != null ? ConvertDateUtils.formatDateToString(data.getReceiptDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
				incHVo.setAuditIncNo(data.getAuditIncNo());
				incHVo.setD1AuditFlag(data.getD1AuditFlag());
				incHVo.setD1ConditionText(data.getD1ConditionText());
				incHVo.setD1CriteriaText(data.getD1CriteriaText());
				incHVo.setD2ConditionText(data.getD2ConditionText());
				incHVo.setD2CriteriaText(data.getD2CriteriaText());
				incHVo.setD3ConditionText(data.getD3ConditionText());
				incHVo.setD3CriteriaText(data.getD3CriteriaText());
				incHVo.setD4ConditionText(data.getD4ConditionText());
				incHVo.setD4CriteriaText(data.getD4CriteriaText());
				IaAuditIncHVoList.add(incHVo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		
		return IaAuditIncHVoList;
	}

	@Transactional(rollbackOn = { Exception.class })
	public IaAuditIncHVo saveIaAuditInc(Int0601SaveVo vo) throws Exception {
		logger.info("saveIaAuditInc : {} ", vo.getIaAuditIncH().getAuditIncNo());
		
		// Header
		IaAuditIncH auditIncH = null;
		if (StringUtils.isNotBlank(vo.getIaAuditIncH().getAuditIncNo())) {
			auditIncH = iaAuditIncHRepository.findByAuditNo(vo.getIaAuditIncH().getAuditIncNo());
		} else {
			auditIncH = new IaAuditIncH();
			auditIncH.setOfficeCode(vo.getIaAuditIncH().getOfficeCode());
			auditIncH.setReceiptDateFrom(ConvertDateUtils.parseStringToDate(vo.getIaAuditIncH().getReceiptDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditIncH.setReceiptDateTo(ConvertDateUtils.parseStringToDate(vo.getIaAuditIncH().getReceiptDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			auditIncH.setAuditIncNo(iaCommonService.autoGetRunAuditNoBySeqName("INC", vo.getIaAuditIncH().getOfficeCode(), "AUDIT_INC_NO_SEQ", 8));
		}
		auditIncH.setD1AuditFlag(vo.getIaAuditIncH().getD1AuditFlag());
		auditIncH.setD1ConditionText(vo.getIaAuditIncH().getD1ConditionText());
		auditIncH.setD1CriteriaText(vo.getIaAuditIncH().getD1CriteriaText());
		auditIncH.setD2ConditionText(vo.getIaAuditIncH().getD2ConditionText());
		auditIncH.setD2CriteriaText(vo.getIaAuditIncH().getD2CriteriaText());
		auditIncH.setD3ConditionText(vo.getIaAuditIncH().getD3ConditionText());
		auditIncH.setD3CriteriaText(vo.getIaAuditIncH().getD3CriteriaText());
		auditIncH.setD4ConditionText(vo.getIaAuditIncH().getD4ConditionText());
		auditIncH.setD4CriteriaText(vo.getIaAuditIncH().getD4CriteriaText());
		auditIncH = iaAuditIncHRepository.save(auditIncH);
		vo.getIaAuditIncH().setAuditIncSeq(auditIncH.getAuditIncSeq());
		vo.getIaAuditIncH().setAuditIncNo(auditIncH.getAuditIncNo());
		
		// D1
		if (vo.getIaAuditIncD1List() != null && vo.getIaAuditIncD1List().size() > 0) {
			IaAuditIncD1 val1 = null;
			List<IaAuditIncD1> iaAuditIncD1List = new ArrayList<>();
			for (IaAuditIncD1Vo data1 : vo.getIaAuditIncD1List()) {
				val1 = new IaAuditIncD1();
				if (data1.getIaAuditIncDId() != null) {
					val1 = iaAuditIncD1Repository.findById(data1.getIaAuditIncDId()).get();
					val1.setSeqNo(data1.getSeqNo());
					val1.setRunCheck(data1.getRunCheck());
					val1.setRemark(data1.getRemark());
					val1.setCheckTax0307(data1.getCheckTax0307());
					val1.setCheckStamp(data1.getCheckStamp());
					val1.setCheckTax0704(data1.getCheckTax0704());
					val1.setRemarkTax(data1.getRemarkTax());
					val1.setWasteReceiptNoFlag(data1.getWasteReceiptFlag());
					val1.setActionFlag(data1.getActionFlag());
					val1 = iaAuditIncD1Repository.save(val1);
				} else {
					val1.setSeqNo(data1.getSeqNo());
					val1.setAuditIncNo(auditIncH.getAuditIncNo());
					val1.setOfficeCode(data1.getOfficeCode());
					val1.setDocCtlNo(data1.getDocCtlNo());
					val1.setReceiptNo(data1.getReceiptNo());
					val1.setRunCheck(data1.getRunCheck());
					val1.setReceiptDate(ConvertDateUtils.parseStringToDate(data1.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val1.setTaxName(data1.getTaxName());
					val1.setTaxCode(data1.getTaxCode());
					val1.setAmount(data1.getAmount());
					val1.setRemark(data1.getRemark());
					val1.setCheckTax0307(data1.getCheckTax0307());
					val1.setCheckStamp(data1.getCheckStamp());
					val1.setCheckTax0704(data1.getCheckTax0704());
					val1.setRemarkTax(data1.getRemarkTax());
					val1.setWasteReceiptNoFlag(data1.getWasteReceiptFlag());
					val1.setActionFlag(data1.getActionFlag());
					iaAuditIncD1List.add(val1);
				}
			}
			iaAuditIncD1Repository.saveAll(iaAuditIncD1List);
		}
		// D2
		if (vo.getIaAuditIncD2List() != null && vo.getIaAuditIncD2List().size() > 0) {
			IaAuditIncD2 val2 = null;
			List<IaAuditIncD2> iaAuditIncD2List = new ArrayList<>();
			for (IaAuditIncD2Vo data2 : vo.getIaAuditIncD2List()) {
				val2 = new IaAuditIncD2();
				if (data2.getIaAuditIncD2Id() != null) {
					val2 = iaAuditIncD2Repository.findById(data2.getIaAuditIncD2Id()).get();
					val2.setAuditCheck(data2.getAuditCheck());
					val2.setRemark(data2.getRemark());
					val2 = iaAuditIncD2Repository.save(val2);
				} else {
					val2.setAuditIncNo(auditIncH.getAuditIncNo());
					val2.setReceiptDate(ConvertDateUtils.parseStringToDate(data2.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
					val2.setAmount(data2.getAmount());
					val2.setPrintPerDay(data2.getPrintPerDay());
					val2.setAuditCheck(data2.getAuditCheck());
					val2.setRemark(data2.getRemark());
					iaAuditIncD2List.add(val2);
				}
			}
			iaAuditIncD2Repository.saveAll(iaAuditIncD2List);
		}
		// D3
		if (vo.getIaAuditIncD3List() != null && vo.getIaAuditIncD3List().size() > 0) {
			IaAuditIncD3 val3 = null;
			List<IaAuditIncD3> iaAuditIncD3List = new ArrayList<>();
			for (IaAuditIncD3Vo data3 : vo.getIaAuditIncD3List()) {
				val3 = new IaAuditIncD3();
				if (data3.getIaAuditIncD3Id() != null) {
					val3 = iaAuditIncD3Repository.findById(data3.getIaAuditIncD3Id()).get();
					val3.setAuditCheck(data3.getAuditCheck());
					val3.setRemark(data3.getRemark());
					val3 = iaAuditIncD3Repository.save(val3);
				} else {
					val3.setAuditIncNo(auditIncH.getAuditIncNo());
					val3.setTaxCode(data3.getTaxCode());
					val3.setTaxName(data3.getTaxName());
					val3.setAmount(data3.getAmount());
					val3.setCountReceipt(data3.getCountReceipt());
					val3.setAuditCheck(data3.getAuditCheck());
					val3.setRemark(data3.getRemark());
					iaAuditIncD3List.add(val3);
				}
			}
			iaAuditIncD3Repository.saveAll(iaAuditIncD3List);
		}
		
		return vo.getIaAuditIncH();
	}

	public IaAuditIncHVo findIaAuditIncHByAuditIncNo(String auditIncNo) {
		logger.info("findIaAuditIncHByAuditIncNo auditIncNo={}", auditIncNo);
		
		IaAuditIncHVo incHVo = null;
		IaAuditIncH data = null;
		ExciseDepartmentVo excise = null;
		data = iaAuditIncHRepository.findByAuditNo(auditIncNo);
		try {
			incHVo = new IaAuditIncHVo();
			incHVo.setAuditIncSeq(data.getAuditIncSeq());
			incHVo.setOfficeCode(data.getOfficeCode());
			incHVo.setReceiptDateFrom(data.getReceiptDateFrom() != null ? ConvertDateUtils.formatDateToString(data.getReceiptDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
			incHVo.setReceiptDateTo(data.getReceiptDateTo() != null ? ConvertDateUtils.formatDateToString(data.getReceiptDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH) : "");
			incHVo.setAuditIncNo(data.getAuditIncNo());
			incHVo.setD1AuditFlag(data.getD1AuditFlag());
			incHVo.setD1ConditionText(data.getD1ConditionText());
			incHVo.setD1CriteriaText(data.getD1CriteriaText());
			incHVo.setD2ConditionText(data.getD2ConditionText());
			incHVo.setD2CriteriaText(data.getD2CriteriaText());
			incHVo.setD3ConditionText(data.getD3ConditionText());
			incHVo.setD3CriteriaText(data.getD3CriteriaText());
			incHVo.setD4ConditionText(data.getD4ConditionText());
			incHVo.setD4CriteriaText(data.getD4CriteriaText());

			excise = ExciseDepartmentUtil.getExciseDepartmentFull(data.getOfficeCode());
			incHVo.setArea(excise.getArea());
			incHVo.setSector(excise.getSector());
			incHVo.setBranch(excise.getBranch());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return incHVo;
	}

	public List<IaAuditIncD1Vo> findIaAuditIncD1ByAuditIncNo(String auditIncNo) {
		logger.info("findIaAuditIncD1ByAuditIncNo auditIncNo={}", auditIncNo);
		
		List<IaAuditIncD1Vo> iaAuditIncD1VoList = new ArrayList<>();
		IaAuditIncD1Vo iaAuditIncD1Vo = null;
		List<IaAuditIncD1> auditIncD1List = iaAuditIncD1Repository.findByAuditIncNoOrderBySeqNo(auditIncNo);
		for (IaAuditIncD1 iaAuditIncD1 : auditIncD1List) {
			iaAuditIncD1Vo = new IaAuditIncD1Vo();
			iaAuditIncD1Vo.setIaAuditIncDId(iaAuditIncD1.getIaAuditIncDId());
			iaAuditIncD1Vo.setSeqNo(iaAuditIncD1.getSeqNo());
			iaAuditIncD1Vo.setAuditIncNo(iaAuditIncD1.getAuditIncNo());
			iaAuditIncD1Vo.setOfficeCode(iaAuditIncD1.getOfficeCode());
			iaAuditIncD1Vo.setDocCtlNo(iaAuditIncD1.getDocCtlNo());
			iaAuditIncD1Vo.setReceiptNo(iaAuditIncD1.getReceiptNo());
			iaAuditIncD1Vo.setRunCheck(iaAuditIncD1.getRunCheck());
			iaAuditIncD1Vo.setReceiptDate(ConvertDateUtils.formatDateToString(iaAuditIncD1.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			iaAuditIncD1Vo.setTaxName(iaAuditIncD1.getTaxName());
			iaAuditIncD1Vo.setTaxCode(iaAuditIncD1.getTaxCode());
			iaAuditIncD1Vo.setAmount(iaAuditIncD1.getAmount());
			iaAuditIncD1Vo.setRemark(iaAuditIncD1.getRemark());
			iaAuditIncD1Vo.setCheckTax0307(iaAuditIncD1.getCheckTax0307());
			iaAuditIncD1Vo.setCheckStamp(iaAuditIncD1.getCheckStamp());
			iaAuditIncD1Vo.setCheckTax0704(iaAuditIncD1.getCheckTax0704());
			iaAuditIncD1Vo.setRemarkTax(iaAuditIncD1.getRemarkTax());
			iaAuditIncD1Vo.setWasteReceiptFlag(iaAuditIncD1.getWasteReceiptNoFlag());
			iaAuditIncD1Vo.setActionFlag(iaAuditIncD1.getActionFlag());
			iaAuditIncD1VoList.add(iaAuditIncD1Vo);
		}
		
		return iaAuditIncD1VoList;
	}

	public List<IaAuditIncD2Vo> findIaAuditIncD2ByAuditIncNo(String auditIncNo) {
		logger.info("findIaAuditIncD2ByAuditIncNo auditIncNo={}", auditIncNo);
		
		List<IaAuditIncD2Vo> iaAuditIncD2VoList = new ArrayList<>();
		IaAuditIncD2Vo iaAuditIncD2Vo = null;
		List<IaAuditIncD2> auditIncD2List = iaAuditIncD2Repository.findByAuditIncNoOrderByReceiptDate(auditIncNo);
		for (IaAuditIncD2 iaAuditIncD2 : auditIncD2List) {
			iaAuditIncD2Vo = new IaAuditIncD2Vo();
			iaAuditIncD2Vo.setIaAuditIncD2Id(iaAuditIncD2.getIaAuditIncD2Id());
			iaAuditIncD2Vo.setAuditIncNo(iaAuditIncD2.getAuditIncNo());
			iaAuditIncD2Vo.setReceiptDate(ConvertDateUtils.formatDateToString(iaAuditIncD2.getReceiptDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			iaAuditIncD2Vo.setAmount(iaAuditIncD2.getAmount());
			iaAuditIncD2Vo.setPrintPerDay(iaAuditIncD2.getPrintPerDay());
			iaAuditIncD2Vo.setAuditCheck(iaAuditIncD2.getAuditCheck());
			iaAuditIncD2Vo.setRemark(iaAuditIncD2.getRemark());
			iaAuditIncD2VoList.add(iaAuditIncD2Vo);
		}
		
		return iaAuditIncD2VoList;
	}

	public List<IaAuditIncD3Vo> findIaAuditIncD3ByAuditIncNo(String auditIncNo) {
		logger.info("findIaAuditIncD3ByAuditIncNo auditIncNo={}", auditIncNo);
		
		List<IaAuditIncD3Vo> iaAuditIncD3VoList = new ArrayList<>();
		IaAuditIncD3Vo iaAuditIncD3Vo = null;
		List<IaAuditIncD3> auditIncD3List = iaAuditIncD3Repository.findByAuditIncNoOrderByTaxCode(auditIncNo);
		for (IaAuditIncD3 iaAuditIncD3 : auditIncD3List) {
			iaAuditIncD3Vo = new IaAuditIncD3Vo();
			iaAuditIncD3Vo.setIaAuditIncD3Id(iaAuditIncD3.getIaAuditIncD3Id());
			iaAuditIncD3Vo.setAuditIncNo(iaAuditIncD3.getAuditIncNo());
			iaAuditIncD3Vo.setTaxCode(iaAuditIncD3.getTaxCode());
			iaAuditIncD3Vo.setTaxName(iaAuditIncD3.getTaxName());
			iaAuditIncD3Vo.setAmount(iaAuditIncD3.getAmount());
			iaAuditIncD3Vo.setCountReceipt(iaAuditIncD3.getCountReceipt());
			iaAuditIncD3Vo.setAuditCheck(iaAuditIncD3.getAuditCheck());
			iaAuditIncD3Vo.setRemark(iaAuditIncD3.getRemark());
			iaAuditIncD3VoList.add(iaAuditIncD3Vo);
		}
		return iaAuditIncD3VoList;
	}

	public IaAuditIncD3DatatableDtlVo findTab3Dtl(Int0601RequestVo criteria) {
		logger.info("findTab3Dtl");
		
		IaAuditIncD3DatatableDtlVo iaAuditIncD3DatatableDtlVo = new IaAuditIncD3DatatableDtlVo();
		List<WsIncfri8020Inc> wsIncfri8020IncList = int0601JdbcRepository.findByCriteria(criteria, "INCOME_CODE,RECEIPT_DATE");
		BigDecimal sumAmt = BigDecimal.ZERO;
		for (WsIncfri8020Inc wsIncfri8020Inc : wsIncfri8020IncList) {
			sumAmt = sumAmt.add(wsIncfri8020Inc.getNetTaxAmt());
		}
		iaAuditIncD3DatatableDtlVo.setWsIncfri8020Inc(wsIncfri8020IncList);
		iaAuditIncD3DatatableDtlVo.setSumAmt(sumAmt);
		
		return iaAuditIncD3DatatableDtlVo;
	}
	
	public DataTableAjax<IaAuditIncD1WasteReceiptVo> findWasteReceipt(Int0601RequestVo criteria) {
		logger.info("findWasteReceipt");
		
		List<IaAuditIncD1WasteReceiptVo> voList = int0601JdbcRepository.findWasteReceipt(criteria);
		Long count = int0601JdbcRepository.countWasteReceipt(criteria);
		
		DataTableAjax<IaAuditIncD1WasteReceiptVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setDraw(criteria.getDraw() + 1);
		dataTableAjax.setData(voList);
		dataTableAjax.setRecordsTotal(count.intValue());
		dataTableAjax.setRecordsFiltered(count.intValue());
		
		return dataTableAjax;
	}

	public byte[] export(String auditIncNo) throws Exception {
		logger.info("export auditIncNo={}", auditIncNo);
		
		IaAuditIncHVo iaAuditIncH = findIaAuditIncHByAuditIncNo(auditIncNo);
		iaAuditIncH.setTitleName("กระดาษทำการ การใช้ใบเสร็จรับเงิน การงบหลังใบเส็จรับเงินรายวัน กับทะเบียนควบคุมรายรับ-จ่ายใบเสร็จรายรับ");
		iaAuditIncH.setOfficeName("สำนักงาน " + ApplicationCache.getExciseDepartment(iaAuditIncH.getOfficeCode()).getDeptName());
		iaAuditIncH.setReceiptDateRangeText("ช่วงในเวลา " + iaAuditIncH.getReceiptDateFrom() + " - " + iaAuditIncH.getReceiptDateTo());

		// Create SpreadSheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		WorkbookCellStyle workbookCellStyle = new WorkbookCellStyle(workbook);
		
		// Sheet 1
		generateSheetD1(auditIncNo, iaAuditIncH, workbook, workbookCellStyle);
		
		// Sheet 2
		generateSheetD2(auditIncNo, iaAuditIncH, workbook, workbookCellStyle);
		
		// Sheet 3
		generateSheetD3(auditIncNo, iaAuditIncH, workbook, workbookCellStyle);
		
		// Sheet 4
		generateSheetD4(auditIncNo, iaAuditIncH, workbook, workbookCellStyle);

		// set output
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			throw e;
		}

		return content;
	}
	
	private class WorkbookCellStyle {

		private CellStyle cellTitle;
		private CellStyle thStyle;
		private CellStyle cellCenter;
		private CellStyle cellLeft;
		private CellStyle cellRight;
		private CellStyle wrapText;
		private CellStyle cellLeftBold;

		public WorkbookCellStyle(Workbook workbook) {
			// Font
			Font headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			headerFont.setBold(true);

			Font detailFont = workbook.createFont();
			detailFont.setFontHeightInPoints((short) 14);
			detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);

			// Cell Style
			cellTitle = (XSSFCellStyle) workbook.createCellStyle();
			cellTitle.setAlignment(HorizontalAlignment.CENTER);
			cellTitle.setFont(headerFont);

			this.thStyle = ExcelUtils.createThCellStyle((XSSFWorkbook) workbook);
			this.thStyle.setFont(headerFont);
			this.cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			this.cellCenter.setFont(detailFont);
			this.cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			this.cellLeft.setFont(detailFont);
			this.cellRight = ExcelUtils.createRightCellStyle(workbook);
			this.cellRight.setFont(detailFont);
			this.wrapText = ExcelUtils.createWrapTextStyle((XSSFWorkbook) workbook);
			this.wrapText.setFont(detailFont);
			this.cellLeftBold = workbook.createCellStyle();
			this.cellLeftBold.setAlignment(HorizontalAlignment.LEFT);
			this.cellLeftBold.setFont(headerFont);
		}

		public CellStyle getCellTitle() {
			return cellTitle;
		}

		public CellStyle getThStyle() {
			return thStyle;
		}

		public CellStyle getCellCenter() {
			return cellCenter;
		}

		public CellStyle getCellLeft() {
			return cellLeft;
		}

		public CellStyle getCellRight() {
			return cellRight;
		}

		public CellStyle getWrapText() {
			return wrapText;
		}

		public CellStyle getCellLeftBold() {
			return cellLeftBold;
		}

	}
	
	private void generateSheetD1(String auditIncNo, IaAuditIncHVo iaAuditIncH, Workbook workbook, WorkbookCellStyle workbookCellStyle) throws Exception {
		List<IaAuditIncD1Vo> dataList = findIaAuditIncD1ByAuditIncNo(auditIncNo);
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
		
		// Prepare Data for Export
		Sheet sheet = workbook.createSheet("การใช้ใบเสร็จรับเงิน");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;
		
		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getTitleName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 8));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getOfficeName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 8));
		rowNum++;
		// Receipt Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getReceiptDateRangeText());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 8));
		rowNum++;
		
		// Column Header
		String[] tbTH = { "ลำดับ", "เลขที่ควบคุมเอกสาร", "เลขที่ใบเสร็จ", "ตรวจสอบเลขที่แบบพิมพ์", "วันเดือนปี", "รายการภาษี ", "รหัสภาษี", "จำนวนเงิน", "หมายเหตุผลการตรวจ" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(workbookCellStyle.getThStyle());
		}
		rowNum++;
		
		// Column Details
		int no = 1;
		for (IaAuditIncD1Vo data : dataList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDocCtlNo());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getReceiptNo());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (data.getRunCheck() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getRunCheck().toString());
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (StringUtils.isEmpty(data.getReceiptDate())) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getReceiptDate());
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxName());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxCode());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (data.getAmount() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getAmount()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;
			
			no++;
			rowNum++;
		}
		
		// Summary
		rowNum++;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		if ("1".equals(StringUtils.defaultString(iaAuditIncH.getD1AuditFlag()))) {
			cell.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  ถูกต้อง");
		} else if ("2".equals(StringUtils.defaultString(iaAuditIncH.getD1AuditFlag()))) {
			cell.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  ไม่ถูกต้อง");
		} else {
			cell.setCellValue("ตรวจสอบกับทะเบียนควบคุมใบเสร็จรับเงิน :  -");
		}
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 2;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD1ConditionText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());

		rowNum += 5;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD1CriteriaText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());
		
		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 5); // ลำดับ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // เลขที่ควบคุมเอกสาร
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // เลขที่ใบเสร็จ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // ตรวจสอบเลขที่แบบพิมพ์
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันเดือนปี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // รายการภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // รหัสภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเงิน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 25); // หมายเหตุผลการตรวจ
	}
	
	private void generateSheetD2(String auditIncNo, IaAuditIncHVo iaAuditIncH, Workbook workbook, WorkbookCellStyle workbookCellStyle) throws Exception {
		List<IaAuditIncD2Vo> dataList = findIaAuditIncD2ByAuditIncNo(auditIncNo);
		DecimalFormat decimalFormatZeroDigit = new DecimalFormat("#,##0");
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
		
		// Prepare Data for Export
		Sheet sheet = workbook.createSheet("สรุปรายวัน");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;
		
		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getTitleName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getOfficeName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		rowNum++;
		// Receipt Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getReceiptDateRangeText());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		rowNum++;
		
		// Column Header
		String[] tbTH = { "ลำดับ", "วันที่", "จำนวนเงิน", "จำนวนแบบพิมพ์/วัน", "ผลการตรวจกับงบหลังสำเนาใบเสร็จ", "หมายเหตุ" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(workbookCellStyle.getThStyle());
		}
		rowNum++;
		
		// Column Details
		int no = 1;
		for (IaAuditIncD2Vo data : dataList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getReceiptDate() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getReceiptDate());
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getAmount() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getAmount()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if (data.getPrintPerDay() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatZeroDigit.format(data.getPrintPerDay()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;

			cell = row.createCell(cellNum);
			if ("1".equals(data.getAuditCheck())) {
				cell.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getAuditCheck())) {
				cell.setCellValue("ไม่ถูกต้อง");
			} else if ("3".equals(data.getAuditCheck())) {
				cell.setCellValue("ไม่ได้งบหลังในเสร็จ");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;

			no++;
			rowNum++;
		}

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD2ConditionText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());

		rowNum += 5;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());

		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD2CriteriaText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());
		
		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 5); // ลำดับ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันที่
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเงิน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนแบบพิมพ์/วัน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // ผลการตรวจกับงบหลังสำเนาใบเสร็จ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // หมายเหตุ
	}
	
	private void generateSheetD3(String auditIncNo, IaAuditIncHVo iaAuditIncH, Workbook workbook, WorkbookCellStyle workbookCellStyle) throws Exception {
		List<IaAuditIncD3Vo> dataList = findIaAuditIncD3ByAuditIncNo(auditIncNo);
		DecimalFormat decimalFormatZeroDigit = new DecimalFormat("#,##0");
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
		
		// Prepare Data for Export
		Sheet sheet = workbook.createSheet("ตรวจสอบยอดเงินค่าภาษี");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;
		
		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getTitleName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getOfficeName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;
		// Receipt Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getReceiptDateRangeText());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 6));
		rowNum++;
		
		// Column Header
		String[] tbTH = { "ลำดับ", "รหัสภาษี", "รายการภาษี", "จำนวนเงิน", "จำนวนราย", "ผลการตรวจกับสรุปเงินค่าภาษี", "หมายเหตุ" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(workbookCellStyle.getThStyle());
		}
		rowNum++;
		
		// Column Details
		int no = 1;
		for (IaAuditIncD3Vo data : dataList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxCode());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxName());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (data.getAmount() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getAmount()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (data.getCountReceipt() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatZeroDigit.format(data.getCountReceipt()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if ("1".equals(data.getAuditCheck())) {
				cell.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getAuditCheck())) {
				cell.setCellValue("ไม่ถูกต้อง");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemark());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;
			
			no++;
			rowNum++;
		}
		
		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());
		
		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD3ConditionText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());
		
		rowNum += 5;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());
		
		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD3CriteriaText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());
		
		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 5); // ลำดับ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // รหัสภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // รายการภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเงิน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 12); // จำนวนราย
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // ผลการตรวจกับสรุปเงินค่าภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // หมายเหตุ
	}
	
	private void generateSheetD4(String auditIncNo, IaAuditIncHVo iaAuditIncH, Workbook workbook, WorkbookCellStyle workbookCellStyle) throws Exception {
		List<IaAuditIncD1Vo> dataList = findIaAuditIncD1ByAuditIncNo(auditIncNo);
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
		
		// Prepare Data for Export
		Sheet sheet = workbook.createSheet("ตรวจสอบกับแบบรายการภาษี");
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;
		
		// Title
		// Report Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getTitleName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 10));
		rowNum++;
		// Office Name
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getOfficeName());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 10));
		rowNum++;
		// Receipt Date
		row = sheet.createRow(rowNum);
		row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
		cellNum = 0;
		cell = row.createCell(cellNum);
		cell.setCellValue(iaAuditIncH.getReceiptDateRangeText());
		cell.setCellStyle(workbookCellStyle.getCellTitle());
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 10));
		rowNum++;
		
		// Column Header
		String[] tbTH = { "ลำดับ", "เลขที่ควบคุมเอกสาร", "เลขที่ใบเสร็จ", "วันเดือนปี", "รายการภาษี", "รหัสภาษี", "จำนวนเงิน", "กรอกเลขที่ใบเสร็จในแบบ (ภส. 03-07)", "มีแบบคำขอร้องแสตมป์ สุรา ยา เครื่องดื่ม", "งบเดือน", "หมายเหตุ / ผลการตรวจ" };
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(workbookCellStyle.getThStyle());
		}
		rowNum++;
		
		// Column Details
		int no = 1;
		for (IaAuditIncD1Vo data : dataList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDocCtlNo());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDocCtlNo());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (data.getReceiptDate() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(data.getReceiptDate());
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxName());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxCode());
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (data.getAmount() == null) {
				cell.setCellValue("");
			} else {
				cell.setCellValue(decimalFormatTwoDigits.format(data.getAmount()));
			}
			cell.setCellStyle(workbookCellStyle.getCellRight());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if ("1".equals(data.getCheckTax0307())) {
				cell.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getCheckTax0307())) {
				cell.setCellValue("พบประเด็น");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if ("1".equals(data.getCheckStamp())) {
				cell.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getCheckStamp())) {
				cell.setCellValue("พบประเด็น");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			if ("1".equals(data.getCheckTax0704())) {
				cell.setCellValue("ถูกต้อง");
			} else if ("2".equals(data.getCheckTax0704())) {
				cell.setCellValue("พบประเด็น");
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(workbookCellStyle.getCellCenter());
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRemarkTax());
			cell.setCellStyle(workbookCellStyle.getCellLeft());
			
			no++;
			rowNum++;
		}
		
		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("ข้อตรวจพบ/ข้อสังเกต(ข้อเท็จจริง/Condition) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());
		
		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD4ConditionText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());
		
		rowNum += 5;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));
		cell.setCellValue("สิ่งที่ควรจะเป็น(หลักเกณฑ์/Criteria) :");
		cell.setCellStyle(workbookCellStyle.getCellLeftBold());
		
		rowNum += 1;
		row = sheet.createRow(rowNum);
		cell = row.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 3, 0, 5));
		cell.setCellValue(StringUtils.defaultString(iaAuditIncH.getD4CriteriaText()));
		cell.setCellStyle(workbookCellStyle.getWrapText());
		
		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 5); // ลำดับ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // เลขที่ควบคุมเอกสาร
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // เลขที่ใบเสร็จ
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันเดือนปี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // รายการภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // รหัสภาษี
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเงิน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // กรอกเลขที่ใบเสร็จในแบบ (ภส. 03-07)
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // มีแบบคำขอร้องแสตมป์ สุรา ยา เครื่องดื่ม
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // งบเดือน
		sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 30); // หมายเหตุ / ผลการตรวจ
	}
	
}
