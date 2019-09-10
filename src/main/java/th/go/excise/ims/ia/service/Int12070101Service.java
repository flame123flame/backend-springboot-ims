package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse1;
import th.go.excise.ims.ia.persistence.repository.IaRentHouse1Repository;
import th.go.excise.ims.ia.persistence.repository.IaRentHouseRepository;
import th.go.excise.ims.ia.vo.Int12070101D1Vo;
import th.go.excise.ims.ia.vo.Int12070101SaveFormVo;

@Service
public class Int12070101Service {

	@Autowired
	private IaRentHouseRepository iaRentHouseRepository;

	@Autowired
	private IaRentHouse1Repository iaRentHouse1Repository;

	@Transactional
	public IaRentHouse save(Int12070101SaveFormVo en) {
		IaRentHouse data = new IaRentHouse();
		data.setAffiliation(en.getAffiliation());
		data.setBillAmount(en.getBillAmount());
		data.setName(en.getName());
		data.setNotOver(en.getNotOver());
		data.setPaymentCost(en.getPaymentCost());
		data.setPaymentFor(en.getPaymentFor());
		data.setPeriod(en.getPeriod());
		data.setPeriodWithdraw(en.getPeriodWithdraw());
		data.setPosition(en.getPosition());
		data.setReceipts(en.getReceipts());
		data.setRefReceipts(en.getRefReceipts());
		data.setRequestNo(en.getRequestNo());
		data.setSalary(en.getSalary());
		data.setTotalMonth(en.getTotalMonth());
		data.setTotalWithdraw(en.getTotalWithdraw());
		data.setStatus(IaConstants.STATUS.PROCESS);
		data.setPeriodWithdrawTo(en.getPeriodWithdrawTo());
		data.setForm6005No(en.getForm6005No());
		IaRentHouse dataSave = iaRentHouseRepository.save(data);

		IaRentHouse1 detialSave = null;
		int seq = 0;
		for (Int12070101D1Vo detail : en.getReceiptsRH()) {
			detialSave = new IaRentHouse1();
			detialSave.setRentHouseId(dataSave.getRentHouseId());
			detialSave.setDocumentSeq(new BigDecimal(seq));
			detialSave.setDocReceiptNo(detail.getReceiptNo());
			detialSave.setDocReceiptDate(ConvertDateUtils.parseStringToDate(detail.getReceiptDate(),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			detialSave.setDocReceiptAmount(new BigDecimal(detail.getReceiptAmount()));
			iaRentHouse1Repository.save(detialSave);
			seq++;
		}
		return dataSave;
	}

	public Int12070101SaveFormVo findById(long id) {
		IaRentHouse headerData = new IaRentHouse();
		headerData = iaRentHouseRepository.findById(id).get();
		List<IaRentHouse1> detailData = iaRentHouse1Repository.findByRentHouseId(id);
		
		Int12070101SaveFormVo dataRes = new Int12070101SaveFormVo();
		dataRes.setAffiliation(headerData.getAffiliation());
		dataRes.setBillAmount(headerData.getBillAmount());
		dataRes.setName(headerData.getName());
		dataRes.setNotOver(headerData.getNotOver());
		dataRes.setPaymentCost(headerData.getPaymentCost());
		dataRes.setPaymentFor(headerData.getPaymentFor());
		dataRes.setPeriod(headerData.getPeriod());
		dataRes.setPeriodWithdraw(headerData.getPeriodWithdraw());
		dataRes.setPosition(headerData.getPosition());
		dataRes.setReceipts(headerData.getReceipts());
		dataRes.setRefReceipts(headerData.getRefReceipts());
		dataRes.setRequestNo(headerData.getRequestNo());
		dataRes.setSalary(headerData.getSalary());
		dataRes.setTotalMonth(headerData.getTotalMonth());
		dataRes.setTotalWithdraw(headerData.getTotalWithdraw());
		dataRes.setStatus(headerData.getStatus());
		dataRes.setPeriodWithdrawTo(headerData.getPeriodWithdrawTo());
		dataRes.setForm6005No(headerData.getForm6005No());
		List<Int12070101D1Vo> detailsSet = new ArrayList<Int12070101D1Vo>();
		Int12070101D1Vo detailSet = null;
		for (IaRentHouse1 detail : detailData) {
			detailSet = new Int12070101D1Vo();
			detailSet.setRentHouse1Id(detail.getRentHouseId());
			detailSet.setDocumentSeq(detail.getDocumentSeq().toString());
			detailSet.setReceiptNo(detail.getDocReceiptNo());
			detailSet.setReceiptDate(ConvertDateUtils.formatDateToString(detail.getDocReceiptDate(), ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH));
			detailSet.setReceiptAmount(detail.getDocReceiptAmount().toString());
			detailsSet.add(detailSet);
		}
		dataRes.setReceiptsRH(detailsSet);
		return dataRes;
	}
	
	public byte[] exportReport(long id) throws Exception {
		Int12070101SaveFormVo dataFind = findById(id);
		
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		Map<String, Object> params = new HashMap<>();
		params.put("name", dataFind.getName());
		params.put("position", dataFind.getPosition());
		params.put("affiliation", dataFind.getAffiliation());
		params.put("paymentCost", dataFind.getPaymentCost());
		params.put("paymentFor", dataFind.getPaymentFor());
		params.put("period", dataFind.getPeriod());
		params.put("requestNo", dataFind.getRequestNo());
		params.put("periodWithdraw", dataFind.getPeriodWithdraw());
		params.put("receipts", dataFind.getReceipts());
		params.put("status", dataFind.getStatus());
		params.put("periodWithdrawTo", dataFind.getPeriodWithdrawTo());
		params.put("form6005No", dataFind.getForm6005No());
		params.put("refReceipts", dataFind.getRefReceipts());
		
		params.put("billAmount", decimalFormat.format(dataFind.getBillAmount()));
		params.put("salary",  decimalFormat.format(dataFind.getSalary()));
		params.put("notOver",  decimalFormat.format(dataFind.getNotOver()));
		params.put("totalMonth", dataFind.getTotalMonth());
		params.put("totalWithdraw", dataFind.getTotalWithdraw());
		
//		ObjectMapper oMapper = new ObjectMapper();
//		@SuppressWarnings("unchecked")
//		Map<String, Object> params =  oMapper.convertValue(dataFind, Map.class);
		params.put("billAmountText", ThaiNumberUtils.toThaiBaht(dataFind.getBillAmount().toString()));
		params.put("salaryText",ThaiNumberUtils.toThaiBaht(dataFind.getSalary().toString()));
		params.put("notOverText", ThaiNumberUtils.toThaiBaht(dataFind.getNotOver().toString()));
		params.put("totalWithdrawText", ThaiNumberUtils.toThaiBaht(dataFind.getTotalWithdraw().toString()));
		params.put("totalMonthThaiNumber", ThaiNumberUtils.toThaiNumber(dataFind.getTotalMonth().toString()));
		params.put("receiptsNumber", ThaiNumberUtils.toThaiNumber(dataFind.getReceipts().toString()));
		
		
		System.out.println("dataFind--------->"+ params);
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.IA_FORM_6006_NO + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);
		return content;
	}
	
}
