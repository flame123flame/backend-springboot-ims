package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
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
import th.go.excise.ims.ta.vo.TaFormTS0101Vo;
import th.go.excise.ims.ta.vo.TaFormTS0111DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0111ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0111Service taFormTS0111Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0111Service taFormTS0111Service = new TaFormTS0111Service();
		
		TaFormTS0111Vo formTS0111Vo = new TaFormTS0111Vo();
		formTS0111Vo.setFormTsNumber("000000-2562-000001");
		formTS0111Vo.setDocPlace("สำนักงาน");
		formTS0111Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 22))));
		formTS0111Vo.setDocTime("10:00");
		formTS0111Vo.setOfficerFullName("เจ้าหน้าที่ 1");
		formTS0111Vo.setOfficerPosition("เจ้าหน้าที่ตรวจสอบภาษี");
		formTS0111Vo.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		formTS0111Vo.setNewRegId("123456789");
		formTS0111Vo.setFactoryType("1");
		formTS0111Vo.setFacAddrNo("345");
		formTS0111Vo.setFacMooNo("5");
		formTS0111Vo.setFacSoiName("1");
		formTS0111Vo.setFacThnName("มิตรภาพ");
		formTS0111Vo.setFacTambolName("จอหอ");
		formTS0111Vo.setFacAmphurName("เมือง");
		formTS0111Vo.setFacProvinceName("นครราชสีมา");
		formTS0111Vo.setFacZipCode("30310");
		formTS0111Vo.setDeliverFullName("นาย ปวิช เตชไพบูล");
		formTS0111Vo.setDeliverPosition("1");
		formTS0111Vo.setDeliverOther("หัวหน้าแผนก");
		formTS0111Vo.setRefBookNumber1("กด3432");
		formTS0111Vo.setRefBookNumber2("256212");
		formTS0111Vo.setRefDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 31))));
		formTS0111Vo.setSignAuthFullName1("นาย ผู้มีอำนาจอนุมัติ ณ บัวใหญ่");
		formTS0111Vo.setSignWitnessFullName1("นาย พยาน จำเลย");
		formTS0111Vo.setSignWitnessFullName2("น.ส. กล้วยหอม ใจดี");
		formTS0111Vo.setAuthFullName1("นาย ภาวนา หมื่นไว");
		formTS0111Vo.setSignAuthFullName2("นายทดสอบ เลยนะจ๊ะ");
		formTS0111Vo.setSignWitnessFullName3("นางพยาน ปากสำคัญ");
		formTS0111Vo.setSignWitnessFullName4("นายพยาน ใจใหญ่");
		formTS0111Vo.setAuthFullName2("นายอะไรดี เยอะจัง");
		formTS0111Vo.setAuthPosition("2");
		formTS0111Vo.setAuthPositionOther("ลองๆ ใส่ดูนะ");
		formTS0111Vo.setAuthFrom("นาย ขจรศักดิ์ ไหคำ");
		formTS0111Vo.setAuthDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 13))));
		formTS0111Vo.setSignAuthFullName3("นายอมร สร้อยมะลิลา");
		formTS0111Vo.setSignAuthFullName4("นางอเนกประสงค์ ทำดี");
		formTS0111Vo.setSignWitnessFullName5("นายใจดี จริงๆ");
		formTS0111Vo.setSignWitnessFullName6("นายภานุวัฒน์ แดงดำ");
		
		TaFormTS0111DtlVo formTS0111DtlVo = null;
		List<TaFormTS0111DtlVo> formTS0111DtlVoList = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			formTS0111DtlVo = new TaFormTS0111DtlVo();
			formTS0111DtlVo.setRecNo(String.valueOf(i + 1));
			formTS0111DtlVo.setDocName("เอกสาร " + (i + 1));
			formTS0111DtlVo.setDocQty(String.valueOf(i * 20));
			formTS0111DtlVo.setDocComment("ทดสอบหมายเหตุ " + (i + 1));
			formTS0111DtlVoList.add(formTS0111DtlVo);
		}
		formTS0111Vo.setTaFormTS0111DtlVoList(formTS0111DtlVoList);
		
		byte[] reportFile = taFormTS0111Service.generateReport(formTS0111Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_11))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0111Service taFormTS0111Service = new TaFormTS0111Service();
		
		TaFormTS0111Vo formTS0111Vo = new TaFormTS0111Vo();
		
		byte[] reportFile = taFormTS0111Service.generateReport(formTS0111Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_11 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		// set data
		TaFormTS0111Vo formTS0111Vo = new TaFormTS0111Vo();
		formTS0111Vo.setFormTsNumber("000000-2562-000352");
		formTS0111Vo.setDocPlace("สำนักงาน edit");
		formTS0111Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 22))));
		formTS0111Vo.setDocTime("10:00");
		formTS0111Vo.setOfficerFullName("เจ้าหน้าที่ 1");
		formTS0111Vo.setOfficerPosition("เจ้าหน้าที่ตรวจสอบภาษี");
		formTS0111Vo.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		formTS0111Vo.setNewRegId("123456789");
		formTS0111Vo.setFactoryType("1");
		formTS0111Vo.setFacAddrNo("345");
		formTS0111Vo.setFacMooNo("5");
		formTS0111Vo.setFacSoiName("1");
		formTS0111Vo.setFacThnName("มิตรภาพ");
		formTS0111Vo.setFacTambolName("จอหอ");
		formTS0111Vo.setFacAmphurName("เมือง");
		formTS0111Vo.setFacProvinceName("นครราชสีมา");
		formTS0111Vo.setFacZipCode("30310");
		formTS0111Vo.setDeliverFullName("นาย ปวิช เตชไพบูล");
		formTS0111Vo.setDeliverPosition("1");
		formTS0111Vo.setDeliverOther("หัวหน้าแผนก");
		formTS0111Vo.setRefBookNumber1("กด3432");
		formTS0111Vo.setRefBookNumber2("256212");
		formTS0111Vo.setRefDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 31))));
		formTS0111Vo.setSignAuthFullName1("นาย ผู้มีอำนาจอนุมัติ ณ บัวใหญ่");
		formTS0111Vo.setSignWitnessFullName1("นาย พยาน จำเลย");
		formTS0111Vo.setSignWitnessFullName2("น.ส. กล้วยหอม ใจดี");
		formTS0111Vo.setAuthFullName1("นาย ภาวนา หมื่นไว");
		formTS0111Vo.setSignAuthFullName2("นายทดสอบ เลยนะจ๊ะ");
		formTS0111Vo.setSignWitnessFullName3("นางพยาน ปากสำคัญ");
		formTS0111Vo.setSignWitnessFullName4("นายพยาน ใจใหญ่");
		formTS0111Vo.setAuthFullName2("นายอะไรดี เยอะจัง");
		formTS0111Vo.setAuthPosition("2");
		formTS0111Vo.setAuthPositionOther("ลองๆ ใส่ดูนะ");
		formTS0111Vo.setAuthFrom("นาย ขจรศักดิ์ ไหคำ");
		formTS0111Vo.setAuthDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 13))));
		formTS0111Vo.setSignAuthFullName3("นายอมร สร้อยมะลิลา");
		formTS0111Vo.setSignAuthFullName4("นางอเนกประสงค์ ทำดี");
		formTS0111Vo.setSignWitnessFullName5("นายใจดี จริงๆ");
		formTS0111Vo.setSignWitnessFullName6("นายภานุวัฒน์ แดงดำ");
		
		TaFormTS0111DtlVo formTS0111DtlVo = null;
		List<TaFormTS0111DtlVo> formTS0111DtlVoList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			formTS0111DtlVo = new TaFormTS0111DtlVo();
			formTS0111DtlVo.setFormTs0111DtlId(String.valueOf(43 + i + 1));
			formTS0111DtlVo.setRecNo(String.valueOf(i + 1));
			formTS0111DtlVo.setDocName("เอกสาร " + (i + 1));
			formTS0111DtlVo.setDocQty(String.valueOf(i * 20));
			formTS0111DtlVo.setDocComment("ทดสอบหมายเหตุ " + (i + 1));
			formTS0111DtlVoList.add(formTS0111DtlVo);
		}
		formTS0111Vo.setTaFormTS0111DtlVoList(formTS0111DtlVoList);
		
		taFormTS0111Service.saveFormTS(formTS0111Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0111Vo formTs0111Vo = taFormTS0111Service.getFormTS("000000-2562-000352");
		System.out.println(ToStringBuilder.reflectionToString(formTs0111Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0111Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
