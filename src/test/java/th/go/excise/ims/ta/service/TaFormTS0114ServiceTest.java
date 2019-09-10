package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS0114DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0114Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0114ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Autowired
	private TaFormTS0114Service taFormTS0114Service;

	@Test
	public void test_generateReport() throws Exception {		
		TaFormTS0114Service formTS0114Service = new TaFormTS0114Service();
		
		TaFormTS0114Vo formTS0114Vo = new TaFormTS0114Vo();
		formTS0114Vo.setFormTsNumber("000000-2562-000001");
		formTS0114Vo.setFactoryName("ชื่อโรงอุตสาหกรรม");
		formTS0114Vo.setNewRegId("3498530945");
		formTS0114Vo.setFacAddrNo("34");
		formTS0114Vo.setFacSoiName("สุทธิสาร");
		formTS0114Vo.setFacThnName("ห้วยขวาง");
		formTS0114Vo.setFacTambolName("ห้วยขวาง");
		formTS0114Vo.setFacAmphurName("ห้วยขวาง");
		formTS0114Vo.setFacProvinceName("กรุงเทพมหานคร");
		formTS0114Vo.setFacZipCode("10310");
		formTS0114Vo.setFacTelNo("0979856904");
		formTS0114Vo.setFacFaxNumber("333448344");
		formTS0114Vo.setFactoryTypeText("ประเภทกิจการ");
		formTS0114Vo.setOfficerFullName("ชื่อผู้ตรวจสอบ");
		formTS0114Vo.setOfficerDept("หน่วยงานผู้ตรวจสอบ");
		formTS0114Vo.setAuditDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setBookNumber1("21223232");
		formTS0114Vo.setBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 2))));
		formTS0114Vo.setAuditSumMonth("1");
		formTS0114Vo.setAuditSumDay("2");
		formTS0114Vo.setAuditBookType("2");
		formTS0114Vo.setAuditBookTypeOther("อื่นๆ");
		formTS0114Vo.setAuditBookNumber("2");
		formTS0114Vo.setAuditBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setDocNum("3");
		formTS0114Vo.setDoc1Num("2");
		formTS0114Vo.setDoc1Date(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setDoc2Num("1");
		formTS0114Vo.setDoc2Date(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 2))));
		formTS0114Vo.setDoc3Num("3");
		formTS0114Vo.setDoc3Date(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 4))));
		formTS0114Vo.setDoc4Num("4");
		formTS0114Vo.setDoc5Num("5");
		formTS0114Vo.setDoc6Num("4");
		formTS0114Vo.setDoc7Num("3");
		formTS0114Vo.setDoc8Num("2");
		formTS0114Vo.setDoc9Num("1");
		formTS0114Vo.setAssReason("เหตุผลการประเมิน");		
		formTS0114Vo.setSignOfficerFullName("4");
		formTS0114Vo.setSignOfficerPosition("ตำแหน่ง");
		
		List<TaFormTS0114DtlVo> formTS0114DtlVoList = new ArrayList<>();
		TaFormTS0114DtlVo formTS0114DtlVo =new TaFormTS0114DtlVo();
		formTS0114DtlVo.setRecNo("1");
		formTS0114DtlVo.setTaxDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 17))));
		formTS0114DtlVo.setDutyTypeText("1");
		formTS0114DtlVo.setTaxAmt(new BigDecimal(253));
		formTS0114DtlVo.setFineAmt(new BigDecimal(253));
		formTS0114DtlVo.setExtraAmt(new BigDecimal(253));
		formTS0114DtlVo.setMoiAmt(new BigDecimal(253));
		formTS0114DtlVo.setSumAmt(new BigDecimal(253));
		formTS0114DtlVoList.add(formTS0114DtlVo);
		formTS0114Vo.setTaFormTS0114DtlVoList(formTS0114DtlVoList);
		
		byte[] reportFile = formTS0114Service.generateReport(formTS0114Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_14))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {		
		TaFormTS0114Service formTS0114Service = new TaFormTS0114Service();
		
		TaFormTS0114Vo formTS0114Vo = new TaFormTS0114Vo();
		
		List<TaFormTS0114DtlVo> formTS0114DtlVoList = new ArrayList<>();
		formTS0114Vo.setTaFormTS0114DtlVoList(formTS0114DtlVoList);
		
		byte[] reportFile = formTS0114Service.generateReport(formTS0114Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_14 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0114Vo formTS0114Vo = new TaFormTS0114Vo();
		formTS0114Vo.setFormTsNumber("000000-2562-000353");
		formTS0114Vo.setFactoryName("ชื่อโรงอุตสาหกรรม");
		formTS0114Vo.setNewRegId("3498530945");
		formTS0114Vo.setFacAddrNo("34");
		formTS0114Vo.setFacSoiName("สุทธิสาร");
		formTS0114Vo.setFacThnName("ห้วยขวาง");
		formTS0114Vo.setFacTambolName("ห้วยขวาง");
		formTS0114Vo.setFacAmphurName("ห้วยขวาง");
		formTS0114Vo.setFacProvinceName("กรุงเทพมหานคร");
		formTS0114Vo.setFacZipCode("10310");
		formTS0114Vo.setFacTelNo("0979856904");
		formTS0114Vo.setFacFaxNumber("333448344");
		formTS0114Vo.setFactoryTypeText("ประเภทกิจการ");
		formTS0114Vo.setOfficerFullName("ชื่อผู้ตรวจสอบ");
		formTS0114Vo.setOfficerDept("หน่วยงานผู้ตรวจสอบ");
		formTS0114Vo.setAuditDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setBookNumber1("21223232");
		formTS0114Vo.setBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 2))));
		formTS0114Vo.setAuditSumMonth("1");
		formTS0114Vo.setAuditSumDay("2");
		formTS0114Vo.setAuditBookType("2");
		formTS0114Vo.setAuditBookTypeOther("อื่นๆ");
		formTS0114Vo.setAuditBookNumber("2");
		formTS0114Vo.setAuditBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setDocNum("3");
		formTS0114Vo.setDoc1Num("2");
		formTS0114Vo.setDoc1Date(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0114Vo.setDoc2Num("1");
		formTS0114Vo.setDoc2Date(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 2))));
		formTS0114Vo.setDoc3Num("3");
		formTS0114Vo.setDoc3Date(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 4))));
		formTS0114Vo.setDoc4Num("4");
		formTS0114Vo.setDoc5Num("5");
		formTS0114Vo.setDoc6Num("4");
		formTS0114Vo.setDoc7Num("3");
		formTS0114Vo.setDoc8Num("2");
		formTS0114Vo.setDoc9Num("1");
		formTS0114Vo.setAssReason("เหตุผลการประเมิน");		
		formTS0114Vo.setSignOfficerFullName("4");
		formTS0114Vo.setSignOfficerPosition("ตำแหน่ง");
		
		List<TaFormTS0114DtlVo> formTS0114DtlVoList = new ArrayList<>();
		TaFormTS0114DtlVo formTS0114DtlVo = null;
		for (int i = 0; i < 1; i++) {
			formTS0114DtlVo = new TaFormTS0114DtlVo();
			formTS0114DtlVo.setFormTs0114DtlId(String.valueOf(38 + i + 1));
			formTS0114DtlVo.setRecNo(String.valueOf(i + 1));
			formTS0114DtlVo.setTaxDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 17))));
			formTS0114DtlVo.setDutyTypeText(String.valueOf(i + 1));
			formTS0114DtlVo.setTaxAmt(new BigDecimal(253));
			formTS0114DtlVo.setFineAmt(new BigDecimal(253));
			formTS0114DtlVo.setExtraAmt(new BigDecimal(253));
			formTS0114DtlVo.setMoiAmt(new BigDecimal(253));
			formTS0114DtlVo.setSumAmt(new BigDecimal(253));
			formTS0114DtlVoList.add(formTS0114DtlVo);
		}
		formTS0114Vo.setTaFormTS0114DtlVoList(formTS0114DtlVoList);
		
		taFormTS0114Service.saveFormTS(formTS0114Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0114Vo formTs0114Vo = taFormTS0114Service.getFormTS("000000-2562-000353");
		System.out.println(ToStringBuilder.reflectionToString(formTs0114Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0114Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
