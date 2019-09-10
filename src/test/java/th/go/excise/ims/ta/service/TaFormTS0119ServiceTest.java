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
import th.go.excise.ims.ta.vo.TaFormTS0119Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0119ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0119Service taFormTS0119Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0119Service taFormTS0119Service = new TaFormTS0119Service();
		
		TaFormTS0119Vo formTS0119Vo = new TaFormTS0119Vo();
		formTS0119Vo.setFormTsNumber("000000-2562-000001");
		formTS0119Vo.setBookNumber1("25632");
		formTS0119Vo.setBookNumber2("632522");
		formTS0119Vo.setDocText1("อํานาจในการอนุมัติให้ออกตรวจ");
		formTS0119Vo.setDocText2("อํานาจในการอนุมัติให้ออกตรวจ");
		formTS0119Vo.setDocText3("อํานาจในการอนุมัติให้ออกตรวจ");
		formTS0119Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0119Vo.setDocDear("ผบตร ธนพล ชัยภูมิ");
		formTS0119Vo.setCompanyName("โรงเหล้ายโสธร");
		formTS0119Vo.setFactoryType("3");
		formTS0119Vo.setFacThnName("โรงผลิตเหล้ายโสธร");
		formTS0119Vo.setNewRegId("25564541585");
		formTS0119Vo.setFacAddrNo("หนองนาคำ");
		formTS0119Vo.setFacMooNo("20");
		formTS0119Vo.setFacSoiName("หนองหินห่าว");
		formTS0119Vo.setFacThnName("โคกนาแก");
		formTS0119Vo.setFacTambolName("โคกอิโด่ย");
		formTS0119Vo.setFacAmphurName("นาบักเหลี่ยม");
		formTS0119Vo.setFacProvinceName("หนองบัวลำภู");
		formTS0119Vo.setFacZipCode("39000");
		formTS0119Vo.setFollowTypeFlag1("");
		formTS0119Vo.setFollowTypeFlag2("");
		formTS0119Vo.setRefBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0119Vo.setOfficeName1("");
		formTS0119Vo.setOfficeName2("");
		formTS0119Vo.setOfficePhone("");
		
		byte[] reportFile = taFormTS0119Service.generateReport(formTS0119Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_19))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0119Service taFormTS0119Service = new TaFormTS0119Service();
		
		TaFormTS0119Vo formTS0119Vo = new TaFormTS0119Vo();
		
		byte[] reportFile = taFormTS0119Service.generateReport(formTS0119Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_19 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0119Vo formTS0119Vo = new TaFormTS0119Vo();
		formTS0119Vo.setFormTsNumber("000000-2562-000359");
		formTS0119Vo.setBookNumber1("25632 e");
		formTS0119Vo.setBookNumber2("632522");
		formTS0119Vo.setDocText1("อํานาจในการอนุมัติให้ออกตรวจ");
		formTS0119Vo.setDocText2("อํานาจในการอนุมัติให้ออกตรวจ");
		formTS0119Vo.setDocText3("อํานาจในการอนุมัติให้ออกตรวจ");
		formTS0119Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0119Vo.setDocDear("ผบตร ธนพล ชัยภูมิ");
		formTS0119Vo.setCompanyName("โรงเหล้ายโสธร");
		formTS0119Vo.setFactoryType("3");
		formTS0119Vo.setFacThnName("โรงผลิตเหล้ายโสธร");
		formTS0119Vo.setNewRegId("25564541585");
		formTS0119Vo.setFacAddrNo("หนองนาคำ");
		formTS0119Vo.setFacMooNo("20");
		formTS0119Vo.setFacSoiName("หนองหินห่าว");
		formTS0119Vo.setFacThnName("โคกนาแก");
		formTS0119Vo.setFacTambolName("โคกอิโด่ย");
		formTS0119Vo.setFacAmphurName("นาบักเหลี่ยม");
		formTS0119Vo.setFacProvinceName("หนองบัวลำภู");
		formTS0119Vo.setFacZipCode("39000");
		formTS0119Vo.setFollowTypeFlag1("");
		formTS0119Vo.setFollowTypeFlag2("");
		formTS0119Vo.setRefBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2561, 6, 15))));
		formTS0119Vo.setOfficeName1("");
		formTS0119Vo.setOfficeName2("");
		formTS0119Vo.setOfficePhone("");
		
		taFormTS0119Service.saveFormTS(formTS0119Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0119Vo formTs0119Vo = taFormTS0119Service.getFormTS("000000-2562-000359");
		System.out.println(ToStringBuilder.reflectionToString(formTs0119Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0119Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
