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
import th.go.excise.ims.ta.vo.TaFormTS0104Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0104ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0104Service taFormTS0104Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0104Service taFormTS0104Service = new TaFormTS0104Service();

		// set data
		TaFormTS0104Vo formTS0104Vo = new TaFormTS0104Vo();
		formTS0104Vo.setFormTsNumber("000000-2562-000126");
		formTS0104Vo.setBookNumber1("กข0002");
		formTS0104Vo.setBookNumber2("0001");
		formTS0104Vo.setSubject1("");
		formTS0104Vo.setSubject2("");
		formTS0104Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0104Vo.setDocTopic("การตรวจสอบภาษีสรรพสามิต");
		formTS0104Vo.setDocDear("คุณ สมชาย ใจดี");
		formTS0104Vo.setDocReference("ผู้จัดการบริษัท");
		formTS0104Vo.setDocRequire("เรื่องการส่งออกสินค้า");
		formTS0104Vo.setDestText("กรมสรรพสามิต");
		formTS0104Vo.setDestDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 20))));
		formTS0104Vo.setDestTime("09.30");
		formTS0104Vo.setDocPaper("เอกสารประกิจการที่เกี่ยวข้องกับภาษี");
		formTS0104Vo.setSignOfficerFullName("นายธนากร สรชัย");
		formTS0104Vo.setSignOfficerPosition("เจ้าหน้าภาษี");
		formTS0104Vo.setOtherText("");
		formTS0104Vo.setOtherPhone("082-4562626");
		
		byte[] reportFile = taFormTS0104Service.generateReport(formTS0104Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_04))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0104Service taFormTS0104Service = new TaFormTS0104Service();
		
		// set data
		TaFormTS0104Vo formTS0104Vo = new TaFormTS0104Vo();
		
		byte[] reportFile = taFormTS0104Service.generateReport(formTS0104Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_04 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0104Vo formTS0104Vo = new TaFormTS0104Vo();
		formTS0104Vo.setFormTsNumber("");
		formTS0104Vo.setBookNumber1("กข0002");
		formTS0104Vo.setBookNumber2("0001");
		formTS0104Vo.setSubject1("");
		formTS0104Vo.setSubject2("");
		formTS0104Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0104Vo.setDocTopic("การตรวจสอบภาษีสรรพสามิต");
		formTS0104Vo.setDocDear("คุณ สมชาย ใจดี");
		formTS0104Vo.setDocReference("ผู้จัดการบริษัท");
		formTS0104Vo.setDocRequire("เรื่องการส่งออกสินค้า");
		formTS0104Vo.setDestText("กรมสรรพสามิต");
		formTS0104Vo.setDestDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 20))));
		formTS0104Vo.setDestTime("09.30");
		formTS0104Vo.setDocPaper("เอกสารประกิจการที่เกี่ยวข้องกับภาษี");
		formTS0104Vo.setSignOfficerFullName("นายธนากร สรชัย");
		formTS0104Vo.setSignOfficerPosition("เจ้าหน้าภาษี");
		formTS0104Vo.setOtherText("");
		formTS0104Vo.setOtherPhone("082-4562626");
		
		taFormTS0104Service.saveFormTS(formTS0104Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0104Vo formTs0104Vo = taFormTS0104Service.getFormTS("000000-2562-000249");
		System.out.println(ToStringBuilder.reflectionToString(formTs0104Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0104Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
