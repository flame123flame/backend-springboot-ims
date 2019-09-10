package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0424DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0424Vo;

public class TaFormTS0424ServiceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;



	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0424Service taFormTS0424Service = new TaFormTS0424Service();

		TaFormTS0424Vo formTS0424Vo = new TaFormTS0424Vo();
		formTS0424Vo.setFormTsNumber("000000-2562-000001");
		formTS0424Vo.setFactoryName("บริษัท กลอนกิจ อินเตอร์เทรด จำกัด  "
				);
		formTS0424Vo.setAuditMonthStart("มกราคม");
		formTS0424Vo.setAuditMonthEnd("เมษายน");
		formTS0424Vo.setAuditYear("2652");
		
		
		List<TaFormTS0424DtlVo> formTS0424DtlVoList = new ArrayList<>();
		
		for(int i = 0; i<=5;i++) {
			TaFormTS0424DtlVo formTS0424DtlVo = new TaFormTS0424DtlVo();
			formTS0424DtlVo.setRecNo(String.valueOf((i + 1)));
			formTS0424DtlVo.setOperatorOfficeName("ตรวจสอบภายใน");
			formTS0424DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 6, 4))));
			formTS0424DtlVo.setOperatorFullName("กิตติศักดิ์ สาสอง");
			formTS0424DtlVo.setOwnerFullName("หนึ่งฤทัย วงจันทอง"	);
			formTS0424DtlVo.setNewRegId("01035520119771001");
			formTS0424DtlVo.setFactoryTypeText("น้ำมันหล่อลื่น");
			formTS0424DtlVo.setCallDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
			formTS0424DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 4))));
			formTS0424DtlVo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 6, 4))));
			formTS0424DtlVo.setTaxAmt(new BigDecimal(253000));
			formTS0424DtlVo.setFineAmt(new BigDecimal(253000));
			formTS0424DtlVo.setExtraAmt(new BigDecimal(253000));			
			formTS0424DtlVo.setMoiAmt(new BigDecimal(253000));
			formTS0424DtlVo.setNettaxAmt(new BigDecimal(253000));
			formTS0424DtlVo.setResidueNum("12");
			formTS0424DtlVo.setOfficerComment("");
			
			
			
			formTS0424DtlVoList.add(formTS0424DtlVo);
			
			
			
		}
		formTS0424Vo.setTaFormTS0424DtlVoList(formTS0424DtlVoList);
		
		


		byte[] reportFile = taFormTS0424Service.generateReport(formTS0424Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS04_24))));
	}

//	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0424Service taFormTS0423Service = new TaFormTS0424Service();

		TaFormTS0424Vo vo = new TaFormTS0424Vo();

		byte[] reportFile = taFormTS0423Service.generateReport(vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS04_24 + "_blank"))));
		
		
		DecimalFormat df = new DecimalFormat("#,##0.00");
		BigDecimal amt = new BigDecimal("456");
		System.out.println(df.format(amt));// 456.00
		System.out.println(df.format(amt).split("\\.")[0]); // 456
		System.out.println(df.format(amt).split("\\.")[1]); // 00
		
	}

}
