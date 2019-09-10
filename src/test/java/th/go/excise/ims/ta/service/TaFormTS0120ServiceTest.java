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
import th.go.excise.ims.ta.vo.TaFormTS0120Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0120ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0120Service taFormTS0120Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0120Service taFormTS0120Service = new TaFormTS0120Service();
		
		TaFormTS0120Vo formTS0120Vo = new TaFormTS0120Vo();
		formTS0120Vo.setFormTsNumber("000000-2562-000001");
		formTS0120Vo.setFactoryName("โรงงาน อุตุนิย วิทยา");
		formTS0120Vo.setDocDear("ผู้อำนวยการ กรมสรรพสามิต");
		formTS0120Vo.setBookNumber1("กด 23456");
		formTS0120Vo.setBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setFactoryName2("โรงงาน อุตุนิย วิทยา");
		formTS0120Vo.setNewRegId("7657868");
		formTS0120Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setFacAddrNo("12");
		formTS0120Vo.setFacMooNo("3");
		formTS0120Vo.setFacSoiName("อุดมเกียตริ");
		formTS0120Vo.setFacThnName("อุดมเกียตริ");
		formTS0120Vo.setFacTambolName("ห้วยขวาง");
		formTS0120Vo.setFacAmphurName("ห้วยขวาง");
		formTS0120Vo.setFacProvinceName("กรุงเทพฯ");
		formTS0120Vo.setFacZipCode("10123");
		formTS0120Vo.setExpandReason("เป็นไงบ้าง กับ เหตุผลเดิมๆ เลยเนอะ abc abc abc abc  abc abc abc abc abc abc abc abc abc abc abc abc ทดสอบขึ้นบรรดทัดใหม่อยุ่ด้วย จุ๊ก จู๊กๆเลยนะ");
		formTS0120Vo.setExpandFlag("1");
		formTS0120Vo.setExpandNo("12");
		formTS0120Vo.setExpandDateOld(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setExpandDateNew(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setSignOfficerFullName("SignOfficerFullName");
		formTS0120Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setHeadOfficerComment("HeadOfficerComment");
		formTS0120Vo.setSignHeadOfficerFullName("SignHeadOfficerFullName");
		formTS0120Vo.setSignHeadOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setApproverComment("ApproverComment");
		formTS0120Vo.setApproveFlag("2");
		formTS0120Vo.setSignApproverFullName("SignApproverFullName");
		formTS0120Vo.setSignApproverDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		
		byte[] reportFile = taFormTS0120Service.generateReport(formTS0120Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_20))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0120Service taFormTS0120Service = new TaFormTS0120Service();
		
		TaFormTS0120Vo formTS0120Vo = new TaFormTS0120Vo();
		
		byte[] reportFile = taFormTS0120Service.generateReport(formTS0120Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_20 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0120Vo formTS0120Vo = new TaFormTS0120Vo();
		formTS0120Vo.setFormTsNumber("");
		formTS0120Vo.setFactoryName("โรงงาน อุตุนิย วิทยา");
		formTS0120Vo.setDocDear("ผู้อำนวยการ กรมสรรพสามิต");
		formTS0120Vo.setBookNumber1("กด 23456");
		formTS0120Vo.setBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setFactoryName2("โรงงาน อุตุนิย วิทยา");
		formTS0120Vo.setNewRegId("7657868");
		formTS0120Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setFacAddrNo("12");
		formTS0120Vo.setFacMooNo("3");
		formTS0120Vo.setFacSoiName("อุดมเกียตริ");
		formTS0120Vo.setFacThnName("อุดมเกียตริ");
		formTS0120Vo.setFacTambolName("ห้วยขวาง");
		formTS0120Vo.setFacAmphurName("ห้วยขวาง");
		formTS0120Vo.setFacProvinceName("กรุงเทพฯ");
		formTS0120Vo.setFacZipCode("10123");
		formTS0120Vo.setExpandReason("เป็นไงบ้าง กับ เหตุผลเดิมๆ เลยเนอะ abc abc abc abc  abc abc abc abc abc abc abc abc abc abc abc abc ทดสอบขึ้นบรรดทัดใหม่อยุ่ด้วย จุ๊ก จู๊กๆเลยนะ");
		formTS0120Vo.setExpandFlag("1");
		formTS0120Vo.setExpandNo("12");
		formTS0120Vo.setExpandDateOld(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setExpandDateNew(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setSignOfficerFullName("SignOfficerFullName");
		formTS0120Vo.setSignOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setHeadOfficerComment("HeadOfficerComment");
		formTS0120Vo.setSignHeadOfficerFullName("SignHeadOfficerFullName");
		formTS0120Vo.setSignHeadOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0120Vo.setApproverComment("ApproverComment");
		formTS0120Vo.setApproveFlag("2");
		formTS0120Vo.setSignApproverFullName("SignApproverFullName");
		formTS0120Vo.setSignApproverDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		
		taFormTS0120Service.saveFormTS(formTS0120Vo);
	}

//	@Test
	public void test_getFormTS() {
		TaFormTS0120Vo formTs0120Vo = taFormTS0120Service.getFormTS("000000-2562-000202");
		System.out.println(ToStringBuilder.reflectionToString(formTs0120Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0120Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
