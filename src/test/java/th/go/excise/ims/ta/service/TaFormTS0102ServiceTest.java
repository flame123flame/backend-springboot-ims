package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;

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
import th.go.excise.ims.ta.vo.TaFormTS0102Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0102ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0102Service taFormTS0102Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0102Service taFormTS0102Service = new TaFormTS0102Service();

		// set data
		TaFormTS0102Vo formTS0102Vo = new TaFormTS0102Vo();
		formTS0102Vo.setFormTsNumber("000000-2562-000126");
		formTS0102Vo.setBookNumber1("กข0002");
		formTS0102Vo.setBookNumber2("0001");
		formTS0102Vo.setDocDear("นายธนาวัตร สุทธิสาร");
		formTS0102Vo.setDocFrom("กรมสรรพสามิต");
		formTS0102Vo.setDocText1("บริษัท อูซูอิ (ไทยแลนด์) จำกัด");
		formTS0102Vo.setCompanyType("1");
		formTS0102Vo.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		formTS0102Vo.setNewRegId("123456789");
		formTS0102Vo.setFactoryAddress("700/454 หมู่ 7 นิคมอุตสาหกรรมอมตะนคร ตำบลดอนหัว อำเภอเมืองชลบุรี จังหวัดชลบุรี 20000");
		formTS0102Vo.setFactoryTypeText("ขายส่งและผู้ผลิตชิ้นส่วนและอะไหล่รถยนต์");
		formTS0102Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0102Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 20))));
		formTS0102Vo.setAuditCase("ข้อมูลการส่งออกสินค้ามีปริมาณมากกว่ากำหนด");
		formTS0102Vo.setSignOfficerFullName("นายประวิช  เจริญสุข");
		formTS0102Vo.setSignOfficerPosition("เจ้าหน้าควบคุมภาษี");
		formTS0102Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 01))));
		formTS0102Vo.setRegDear("ผู้จัดการฝ่ายการผลิต");
		formTS0102Vo.setRegText("มีการผลิตสินค้าออกมาเกินกำหนด และขาดการรายรายงานต่อหัวหน้าฝ่ายควบคุม");
		formTS0102Vo.setSignRegFullName("นายอดิสร เทพทณากร");
		formTS0102Vo.setSignRegPosition("หัวหน้าผู้จัดการ");
		formTS0102Vo.setSignRegDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 05))));
		formTS0102Vo.setComdTypeFlag("1");
		formTS0102Vo.setSignComdFullName("นายมงคล อาสว่าง");
		formTS0102Vo.setSignComdPosition("หัวฝ่ายควบคุม");
		formTS0102Vo.setSignComdDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 06))));
		formTS0102Vo.setFinanceBookNumber1("กข001");
		formTS0102Vo.setFinanceBookNumber2("510");
		formTS0102Vo.setFinanceDear("ผู้อำนวยการศูนย์");
		formTS0102Vo.setSignFinanceFullName("นายเจริญพร ใจดี");
		formTS0102Vo.setSignFinancePosition("ผู้อำนายการ");
		formTS0102Vo.setSignFinanceDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 10))));

		byte[] reportFile = taFormTS0102Service.generateReport(formTS0102Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_02))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0102Service taFormTS0102Service = new TaFormTS0102Service();
		
		// set data
		TaFormTS0102Vo formTS0102Vo = new TaFormTS0102Vo();
		
		byte[] reportFile = taFormTS0102Service.generateReport(formTS0102Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_02 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0102Vo formTS0102Vo = new TaFormTS0102Vo();
		formTS0102Vo.setFormTsNumber("");
		formTS0102Vo.setBookNumber1("กข0002");
		formTS0102Vo.setBookNumber2("0001");
		formTS0102Vo.setDocDear("นายธนาวัตร สุทธิสาร");
		formTS0102Vo.setDocFrom("กรมสรรพสามิต");
		formTS0102Vo.setDocText1("บริษัท อูซูอิ (ไทยแลนด์) จำกัด");
		formTS0102Vo.setCompanyType("1");
		formTS0102Vo.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		formTS0102Vo.setNewRegId("123456789");
		formTS0102Vo.setFactoryAddress("700/454 หมู่ 7 นิคมอุตสาหกรรมอมตะนคร ตำบลดอนหัว อำเภอเมืองชลบุรี จังหวัดชลบุรี 20000");
		formTS0102Vo.setFactoryTypeText("ขายส่งและผู้ผลิตชิ้นส่วนและอะไหล่รถยนต์");
		formTS0102Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0102Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 20))));
		formTS0102Vo.setAuditCase("ข้อมูลการส่งออกสินค้ามีปริมาณมากกว่ากำหนด");
		formTS0102Vo.setSignOfficerFullName("นายประวิช  เจริญสุข");
		formTS0102Vo.setSignOfficerPosition("เจ้าหน้าควบคุมภาษี");
		formTS0102Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 01))));
		formTS0102Vo.setRegDear("ผู้จัดการฝ่ายการผลิต");
		formTS0102Vo.setRegText("มีการผลิตสินค้าออกมาเกินกำหนด และขาดการรายรายงานต่อหัวหน้าฝ่ายควบคุม");
		formTS0102Vo.setSignRegFullName("นายอดิสร เทพทณากร");
		formTS0102Vo.setSignRegPosition("หัวหน้าผู้จัดการ");
		formTS0102Vo.setSignRegDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 05))));
		formTS0102Vo.setComdTypeFlag("1");
		formTS0102Vo.setSignComdFullName("นายมงคล อาสว่าง");
		formTS0102Vo.setSignComdPosition("หัวฝ่ายควบคุม");
		formTS0102Vo.setSignComdDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 06))));
		formTS0102Vo.setFinanceBookNumber1("กข001");
		formTS0102Vo.setFinanceBookNumber2("510");
		formTS0102Vo.setFinanceDear("ผู้อำนวยการศูนย์");
		formTS0102Vo.setSignFinanceFullName("นายเจริญพร ใจดี");
		formTS0102Vo.setSignFinancePosition("ผู้อำนายการ");
		formTS0102Vo.setSignFinanceDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 10))));
		
		taFormTS0102Service.saveFormTS(formTS0102Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0102Vo formTs0102Vo = taFormTS0102Service.getFormTS("000000-2562-000260");
		System.out.println(ToStringBuilder.reflectionToString(formTs0102Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0102Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
