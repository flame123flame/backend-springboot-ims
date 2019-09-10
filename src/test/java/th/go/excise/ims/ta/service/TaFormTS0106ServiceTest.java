package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;

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
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS0106Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0106ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0106Service taFormTS0106Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0106Service taFormTS0106Service = new TaFormTS0106Service();
		
		TaFormTS0106Vo formTS0106Vo = new TaFormTS0106Vo();
		formTS0106Vo.setFormTsNumber("000000-2562-000126");
		formTS0106Vo.setDocPlace("");
		formTS0106Vo.setDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0106Vo.setWriterFullName("ธนพล ชัยภูมิ");
		formTS0106Vo.setWriterPositionFlag("1");
		formTS0106Vo.setFactoryName("ตรวจสอบภาษี");
		formTS0106Vo.setNewRegId("2561212511520");
		formTS0106Vo.setFacAddrNo("25");
		formTS0106Vo.setFacMooNo("36");
		formTS0106Vo.setFacSoiName("อุดมเกียตร");
		formTS0106Vo.setFacThnName("อุดมเกียตร");
		formTS0106Vo.setFacTambolName("อุดมเกียตร");
		formTS0106Vo.setFacAmphurName("อุดมเกียตร");
		formTS0106Vo.setFacProvinceName("หนองบัวลำภู");
		formTS0106Vo.setFacZipCode("39000");
		formTS0106Vo.setFacTelNo("");
		formTS0106Vo.setRefBookNumber1("");
		formTS0106Vo.setRefBookNumber2("");
		formTS0106Vo.setRefDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0106Vo.setAuthFullName("");
		formTS0106Vo.setAuthAge("24");
		formTS0106Vo.setAuthAddrNo("");
		formTS0106Vo.setAuthSoiName("");
		formTS0106Vo.setAuthThnName("");
		formTS0106Vo.setAuthTambolName("");
		formTS0106Vo.setAuthAmphurName("");
		formTS0106Vo.setAuthProvinceName("");
		formTS0106Vo.setAuthZipCode("");
		formTS0106Vo.setAuthTelNo("");
		formTS0106Vo.setAuthCardId("");
		formTS0106Vo.setAuthCardPlace("");
		formTS0106Vo.setDocText("");
		formTS0106Vo.setSignAuthFullName1("");
		formTS0106Vo.setSignAuthFullName2("");
		formTS0106Vo.setSignAuthFullName3("");
		formTS0106Vo.setSignWitnessFullName1("");
		formTS0106Vo.setSignWitnessFullName2("");
		
		byte[] reportFile = taFormTS0106Service.generateReport(formTS0106Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_06))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0106Service taFormTS0106Service = new TaFormTS0106Service();
		
		TaFormTS0106Vo formTS0106Vo = new TaFormTS0106Vo();
		
		byte[] reportFile = taFormTS0106Service.generateReport(formTS0106Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_06 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0106Vo formTS0106Vo = new TaFormTS0106Vo();
		formTS0106Vo.setFormTsNumber("");
		formTS0106Vo.setDocPlace("");
		formTS0106Vo.setDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0106Vo.setWriterFullName("ธนพล ชัยภูมิ");
		formTS0106Vo.setWriterPositionFlag("1");
		formTS0106Vo.setFactoryName("ตรวจสอบภาษี");
		formTS0106Vo.setNewRegId("2561212511520");
		formTS0106Vo.setFacAddrNo("25");
		formTS0106Vo.setFacMooNo("36");
		formTS0106Vo.setFacSoiName("อุดมเกียตร");
		formTS0106Vo.setFacThnName("อุดมเกียตร");
		formTS0106Vo.setFacTambolName("อุดมเกียตร");
		formTS0106Vo.setFacAmphurName("อุดมเกียตร");
		formTS0106Vo.setFacProvinceName("หนองบัวลำภู");
		formTS0106Vo.setFacZipCode("39000");
		formTS0106Vo.setFacTelNo("");
		formTS0106Vo.setRefBookNumber1("");
		formTS0106Vo.setRefBookNumber2("");
		formTS0106Vo.setRefDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0106Vo.setAuthFullName("");
		formTS0106Vo.setAuthAge("24");
		formTS0106Vo.setAuthAddrNo("");
		formTS0106Vo.setAuthSoiName("");
		formTS0106Vo.setAuthThnName("");
		formTS0106Vo.setAuthTambolName("");
		formTS0106Vo.setAuthAmphurName("");
		formTS0106Vo.setAuthProvinceName("");
		formTS0106Vo.setAuthZipCode("");
		formTS0106Vo.setAuthTelNo("");
		formTS0106Vo.setAuthCardId("");
		formTS0106Vo.setAuthCardPlace("");
		formTS0106Vo.setDocText("");
		formTS0106Vo.setSignAuthFullName1("");
		formTS0106Vo.setSignAuthFullName2("");
		formTS0106Vo.setSignAuthFullName3("");
		formTS0106Vo.setSignWitnessFullName1("");
		formTS0106Vo.setSignWitnessFullName2("");
		
		taFormTS0106Service.saveFormTS(formTS0106Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0106Vo formTs0106Vo = taFormTS0106Service.getFormTS("000000-2562-000293");
		System.out.println(ToStringBuilder.reflectionToString(formTs0106Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0106Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
