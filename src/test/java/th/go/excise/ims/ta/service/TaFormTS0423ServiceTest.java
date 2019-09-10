package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0423DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0423Vo;

public class TaFormTS0423ServiceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0423Service taFormTS0423Service = new TaFormTS0423Service();

		TaFormTS0423Vo formTS04232Vo = new TaFormTS0423Vo();
		formTS04232Vo.setFormTsNumber("000000-2562-000001");
		formTS04232Vo.setAlphabet("ต.ส 0423");
		formTS04232Vo.setFactoryTypeText("ธนพล ชัยภูมิ");
		formTS04232Vo.setNewRegId("01035520119771001");
		formTS04232Vo.setFacAddrNo("32/2");
		formTS04232Vo.setFacSoiName("อุดมเกียรติ");
		formTS04232Vo.setFacThnName("สุทธิสารวินิจฉัย");
		formTS04232Vo.setFacTambolName("ห้วยขวาง");
		formTS04232Vo.setFacAmphurName("ห้วยขวาง");
		formTS04232Vo.setFacProvinceName("กรุงเทพมหานคร");
		List<TaFormTS0423DtlVo> formTS01142DtlVoList = new ArrayList<>();
		
		for(int i = 0; i<=5;i++) {
			TaFormTS0423DtlVo formTS0423DtlVo = new TaFormTS0423DtlVo();
			formTS0423DtlVo.setRecNo(String.valueOf((i + 1)));
			formTS0423DtlVo.setOperatorOfficeName("ตรวจสอบภายใน");
			formTS0423DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 4))));
			formTS0423DtlVo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 6, 4))));
			formTS0423DtlVo.setReqDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
			formTS0423DtlVo.setInformDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
			formTS0423DtlVo.setAuditReason("การจ่ายภาษีผิดปกติ");
			formTS0423DtlVo.setAuditResult("จ่ายภาษีไม่ครบ 2 เดือน");
			formTS0423DtlVo.setOfficerFullName("กิตติศักดิ์ สาสอง");
			formTS01142DtlVoList.add(formTS0423DtlVo);
			
			
			
		}
		formTS04232Vo.setTaFormTS0423DtlVoList(formTS01142DtlVoList);
		


		byte[] reportFile = taFormTS0423Service.generateReport(formTS04232Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS04_23))));
	}

//	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0423Service taFormTS0423Service = new TaFormTS0423Service();

		TaFormTS0423Vo vo = new TaFormTS0423Vo();

		byte[] reportFile = taFormTS0423Service.generateReport(vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS04_23 + "_blank"))));
	}



}
