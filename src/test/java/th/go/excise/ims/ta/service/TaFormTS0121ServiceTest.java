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
import th.go.excise.ims.ta.vo.TaFormTS0121Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0121ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0121Service taFormTS0121Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0121Service taFormTS0121Service = new TaFormTS0121Service();
		
		TaFormTS0121Vo formTS0121Vo = new TaFormTS0121Vo();
		formTS0121Vo.setFormTsNumber("000000-2562-000001");
		formTS0121Vo.setFactoryName("FactoryName");
		formTS0121Vo.setOfficerSendFullName1("OfficerSendFullName1");
		formTS0121Vo.setOfficerSendPosition1("OfficerSendPosition1");
		formTS0121Vo.setOfficerReceiveFullName1("OfficerReceiveFullName1");
		formTS0121Vo.setOfficerReceivePosition1("OfficerReceivePosition1");
		formTS0121Vo.setOfficeName("OfficeName");
		formTS0121Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0121Vo.setComdDesc("ComdDesc");
		formTS0121Vo.setComdDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0121Vo.setOfficerSendFullName2("OfficerSendFullName2");
		formTS0121Vo.setFactoryName2("FactoryName2");
		formTS0121Vo.setOfficerReceiveFullName2("OfficerReceiveFullName2");
		formTS0121Vo.setOfficerSendFullName3("OfficerSendFullName3");
		formTS0121Vo.setOfficerReceiveFullName3("OfficerReceiveFullName3");
		formTS0121Vo.setFactoryName3("FactoryName3");
		formTS0121Vo.setDoc1Num("20");
		formTS0121Vo.setDocAcct1Num("10");
		formTS0121Vo.setDocAcct1No("2");
		formTS0121Vo.setDocAcct2Num("30");
		formTS0121Vo.setDocAcct2No("4");
		formTS0121Vo.setDocOther("ลองดูนะ ดูดีๆ เลยนะ ดู ไม่ดู ดูไม่ดู ดู ไม่เสียตังค์ x x x ลองดูนะ ดูดีๆ เลยนะ ดู ไม่ดู ดูไม่ดู ดู ไม่เสียตังค์ x x x ลองดูนะ ดูดีๆ เลยนะ ดู ไม่ดู ดูไม่ดู ดู ไม่เสียตังค์");
		formTS0121Vo.setSignOfficerFullName1("SignOfficerFullName1");
		formTS0121Vo.setSignOfficerFullName2("SignOfficerFullName2");
		formTS0121Vo.setSignWitnessFullName1("SignWitnessFullName1");
		formTS0121Vo.setSignWitnessFullName2("SignWitnessFullName2");
		
		byte[] reportFile = taFormTS0121Service.generateReport(formTS0121Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_21))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0121Service taFormTS0121Service = new TaFormTS0121Service();
		
		TaFormTS0121Vo formTS0121Vo = new TaFormTS0121Vo();
		
		byte[] reportFile = taFormTS0121Service.generateReport(formTS0121Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_21 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0121Vo formTS0121Vo = new TaFormTS0121Vo();
		formTS0121Vo.setFormTsNumber("");
		formTS0121Vo.setFactoryName("FactoryName");
		formTS0121Vo.setOfficerSendFullName1("OfficerSendFullName1");
		formTS0121Vo.setOfficerSendPosition1("OfficerSendPosition1");
		formTS0121Vo.setOfficerReceiveFullName1("OfficerReceiveFullName1");
		formTS0121Vo.setOfficerReceivePosition1("OfficerReceivePosition1");
		formTS0121Vo.setOfficeName("OfficeName");
		formTS0121Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0121Vo.setComdDesc("ComdDesc");
		formTS0121Vo.setComdDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0121Vo.setOfficerSendFullName2("OfficerSendFullName2");
		formTS0121Vo.setFactoryName2("FactoryName2");
		formTS0121Vo.setOfficerReceiveFullName2("OfficerReceiveFullName2");
		formTS0121Vo.setOfficerSendFullName3("OfficerSendFullName3");
		formTS0121Vo.setOfficerReceiveFullName3("OfficerReceiveFullName3");
		formTS0121Vo.setFactoryName3("FactoryName3");
		formTS0121Vo.setDoc1Num("20");
		formTS0121Vo.setDocAcct1Num("10");
		formTS0121Vo.setDocAcct1No("2");
		formTS0121Vo.setDocAcct2Num("30");
		formTS0121Vo.setDocAcct2No("4");
		formTS0121Vo.setDocOther("ลองดูนะ ดูดีๆ เลยนะ ดู ไม่ดู ดูไม่ดู ดู ไม่เสียตังค์ x x x ลองดูนะ ดูดีๆ เลยนะ ดู ไม่ดู ดูไม่ดู ดู ไม่เสียตังค์ x x x ลองดูนะ ดูดีๆ เลยนะ ดู ไม่ดู ดูไม่ดู ดู ไม่เสียตังค์");
		formTS0121Vo.setSignOfficerFullName1("SignOfficerFullName1");
		formTS0121Vo.setSignOfficerFullName2("SignOfficerFullName2");
		formTS0121Vo.setSignWitnessFullName1("SignWitnessFullName1");
		formTS0121Vo.setSignWitnessFullName2("SignWitnessFullName2");
		
		taFormTS0121Service.saveFormTS(formTS0121Vo);
	}

//	@Test
	public void test_getFormTS() {
		TaFormTS0121Vo formTs0121Vo = taFormTS0121Service.getFormTS("000000-2562-000306");
		System.out.println(ToStringBuilder.reflectionToString(formTs0121Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0121Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
