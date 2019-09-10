package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.Date;

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
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0101ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0101Service taFormTS0101Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0101Service taFormTS0101Service = new TaFormTS0101Service();
		
		// set data
		TaFormTS0101Vo formTS0101Vo = new TaFormTS0101Vo();
		formTS0101Vo.setFormTsNumber("000000-2562-000126");
		formTS0101Vo.setNewRegId("01005150424621002");
		formTS0101Vo.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		formTS0101Vo.setFactoryTypeText("ขายส่งและผู้ผลิตชิ้นส่วนและอะไหล่รถยนต์");
		formTS0101Vo.setFactoryAddress("700/454 หมู่ 7 นิคมอุตสาหกรรมอมตะนคร ตำบลดอนหัว อำเภอเมืองชลบุรี จังหวัดชลบุรี 20000");
		formTS0101Vo.setAnalysisDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0101Vo.setAnalysisDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 20))));
		formTS0101Vo.setAnalysisData1("ข้อมูลการนำเข้าวัตถุดิบ");
		formTS0101Vo.setAnalysisData2("ข้อมูลการส่งออกสินค้า");
		formTS0101Vo.setAnalysisData3("");
		formTS0101Vo.setAnalysisData4("");
		formTS0101Vo.setAnalysisData5("");
		formTS0101Vo.setAnalysisResultDear("นายประวิช  เจริญสุข");
		formTS0101Vo.setAnalysisResultText("มีการชำระภาษีไม่ถูกต้อง");
		formTS0101Vo.setCallAuditFlag("1");
		formTS0101Vo.setOtherText("");
		formTS0101Vo.setSignOfficerFullName("นายอภิรักษ์  ชูใจ");
		formTS0101Vo.setSignSupOfficerFullName("นายสิทธิ์ศักดฺ์  ใจชื่น");
		formTS0101Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 10))));
		formTS0101Vo.setApprovedFlag("1");
		formTS0101Vo.setSignApprOfficerFullName("นายปฏิพงษ์  เสรีไทย");
		formTS0101Vo.setSignApprOfficerPosition("ผู้อำนวยการเขต");
		formTS0101Vo.setSignApprDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 12))));
		
		byte[] reportFile = taFormTS0101Service.generateReport(formTS0101Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_01))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0101Service taFormTS0101Service = new TaFormTS0101Service();
		
		// set data
		TaFormTS0101Vo formTS0101Vo = new TaFormTS0101Vo();
		
		byte[] reportFile = taFormTS0101Service.generateReport(formTS0101Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_01 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		// set data
		TaFormTS0101Vo formTS0101Vo = new TaFormTS0101Vo();
		formTS0101Vo.setFormTsNumber("000000-2562-000351");
		formTS0101Vo.setNewRegId("123456789_edit2");
		formTS0101Vo.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		formTS0101Vo.setFactoryTypeText("ขายส่งและผู้ผลิตชิ้นส่วนและอะไหล่รถยนต์");
		formTS0101Vo.setFactoryAddress("700/454 หมู่ 7 นิคมอุตสาหกรรมอมตะนคร ตำบลดอนหัว อำเภอเมืองชลบุรี จังหวัดชลบุรี 20000");
		formTS0101Vo.setAnalysisDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0101Vo.setAnalysisDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 20))));
		formTS0101Vo.setAnalysisData1("ข้อมูลการนำเข้าวัตถุดิบ");
		formTS0101Vo.setAnalysisData2("ข้อมูลการส่งออกสินค้า");
		formTS0101Vo.setAnalysisData3("");
		formTS0101Vo.setAnalysisData4("");
		formTS0101Vo.setAnalysisData5("");
		formTS0101Vo.setAnalysisResultDear("นายประวิช  เจริญสุข");
		formTS0101Vo.setAnalysisResultText("มีการชำระภาษีไม่ถูกต้อง");
		formTS0101Vo.setCallAuditFlag("1");
		formTS0101Vo.setOtherText("");
		formTS0101Vo.setSignOfficerFullName("นายอภิรักษ์  ชูใจ");
		formTS0101Vo.setSignSupOfficerFullName("นายสิทธิ์ศักดฺ์  ใจชื่น");
		formTS0101Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 10))));
		formTS0101Vo.setApprovedFlag("1");
		formTS0101Vo.setSignApprOfficerFullName("นายปฏิพงษ์  เสรีไทย");
		formTS0101Vo.setSignApprOfficerPosition("ผู้อำนวยการเขต");
		formTS0101Vo.setSignApprDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 12))));
		
		taFormTS0101Service.saveFormTS(formTS0101Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0101Vo formTs0101Vo = taFormTS0101Service.getFormTS("000000-2562-000351");
		System.out.println(ToStringBuilder.reflectionToString(formTs0101Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0101Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
