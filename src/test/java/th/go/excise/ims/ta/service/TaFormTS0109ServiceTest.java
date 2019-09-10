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
import th.go.excise.ims.ta.vo.TaFormTS0109Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0109ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Autowired
	private TaFormTS0109Service taFormTS0109Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0109Service taFormTS0109Service = new TaFormTS0109Service();

		TaFormTS0109Vo formTS0109Vo = new TaFormTS0109Vo();
		formTS0109Vo.setFormTsNumber("000000-2562-000126");
		formTS0109Vo.setBookNumber1("12345");
		formTS0109Vo.setBookNumber2("54321");
		formTS0109Vo.setComdPlace("กรมสรรพสามิต");
		formTS0109Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 15))));
		formTS0109Vo.setEvidenceReason("บริษัท เชลล์แห่งประเทศไทย จำกัด ");
		formTS0109Vo.setNewRegId("01005150424621001");
		formTS0109Vo.setDocText1("setDocText1");
		formTS0109Vo.setDocText2("setDocText2");
		formTS0109Vo.setDocText3("setDocText3");
		formTS0109Vo.setHeadOfficerFullName("ผู้ดูแลระบบ000000");
		formTS0109Vo.setHeadOfficerPosition("นามสกุล");
		formTS0109Vo.setOfficerText("ผู้ติดตาม 1 2 3");
		formTS0109Vo.setSearchPlace("ทำการตรวจค้นในสถานที่ที่น่าเป็นที่อยู่ของผู้กระทำความผิด");
		formTS0109Vo.setSearchDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 30))));
		formTS0109Vo.setSignOfficerFullName("ผู้ดูแลระบบ000000 นามสกุล");
		formTS0109Vo.setSignOfficerPosition("สำนักงานสรรพสามิตส่วนกลาง");

		byte[] reportFile = taFormTS0109Service.generateReport(formTS0109Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_09))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0109Service taFormTS0109Service = new TaFormTS0109Service();
		
		TaFormTS0109Vo formTS0109Vo = new TaFormTS0109Vo();
		
		byte[] reportFile = taFormTS0109Service.generateReport(formTS0109Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_09 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0109Vo formTS0109Vo = new TaFormTS0109Vo();
		formTS0109Vo.setFormTsNumber("");
		formTS0109Vo.setBookNumber1("12345");
		formTS0109Vo.setBookNumber2("54321");
		formTS0109Vo.setComdPlace("กรมสรรพสามิต");
		formTS0109Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 15))));
		formTS0109Vo.setEvidenceReason("บริษัท เชลล์แห่งประเทศไทย จำกัด ");
		formTS0109Vo.setNewRegId("01005150424621001");
		formTS0109Vo.setDocText1("setDocText1");
		formTS0109Vo.setDocText2("setDocText2");
		formTS0109Vo.setDocText3("setDocText3");
		formTS0109Vo.setHeadOfficerFullName("ผู้ดูแลระบบ000000");
		formTS0109Vo.setHeadOfficerPosition("นามสกุล");
		formTS0109Vo.setOfficerText("ผู้ติดตาม 1 2 3");
		formTS0109Vo.setSearchPlace("ทำการตรวจค้นในสถานที่ที่น่าเป็นที่อยู่ของผู้กระทำความผิด");
		formTS0109Vo.setSearchDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 30))));
		formTS0109Vo.setSignOfficerFullName("ผู้ดูแลระบบ000000 นามสกุล");
		formTS0109Vo.setSignOfficerPosition("สำนักงานสรรพสามิตส่วนกลาง");
		
		taFormTS0109Service.saveFormTS(formTS0109Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0109Vo formTs0109Vo = taFormTS0109Service.getFormTS("000000-2562-000243");
		System.out.println(ToStringBuilder.reflectionToString(formTs0109Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0109Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
