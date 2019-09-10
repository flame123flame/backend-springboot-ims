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
import th.go.excise.ims.ta.vo.TaFormTS0105Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0105ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0105Service taFormTS0105Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0105Service taFormTS0105Service = new TaFormTS0105Service();
		
		TaFormTS0105Vo formTS0105Vo = new TaFormTS0105Vo();
		formTS0105Vo.setFormTsNumber("000000-2562-000126");
		formTS0105Vo.setBookNumber1("253");
		formTS0105Vo.setBookNumber2("325");
		formTS0105Vo.setOfficeName("ธนพล ชัยภูมิ");
		formTS0105Vo.setDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setDocDear("ธนพล ชัยภูมิ");
		formTS0105Vo.setRefBookNumber1("523");
		formTS0105Vo.setRefBookNumber2("632");
		formTS0105Vo.setRefDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setRefDocSend("ตรวจสอบภาษี");
		formTS0105Vo.setCaseDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setCaseTime("13.00");
		formTS0105Vo.setDestText("ตรวจสอบภาษี");
		formTS0105Vo.setDestDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setDestTime("11.30");
		formTS0105Vo.setSignOfficerFullName("ธนพล ชัยภูมิ");
		formTS0105Vo.setSignOfficerPosition("ตรวจสอบภาษี");
		formTS0105Vo.setOtherText("");
		formTS0105Vo.setOtherPhone("0933413252");
		
		byte[] reportFile = taFormTS0105Service.generateReport(formTS0105Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_05))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0105Service taFormTS0105Service = new TaFormTS0105Service();
		
		TaFormTS0105Vo formTS0105Vo = new TaFormTS0105Vo();
		
		byte[] reportFile = taFormTS0105Service.generateReport(formTS0105Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_05 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0105Vo formTS0105Vo = new TaFormTS0105Vo();
		formTS0105Vo.setFormTsNumber("");
		formTS0105Vo.setBookNumber1("253");
		formTS0105Vo.setBookNumber2("325");
		formTS0105Vo.setOfficeName("ธนพล ชัยภูมิ");
		formTS0105Vo.setDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setDocDear("ธนพล ชัยภูมิ");
		formTS0105Vo.setRefBookNumber1("523");
		formTS0105Vo.setRefBookNumber2("632");
		formTS0105Vo.setRefDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setRefDocSend("ตรวจสอบภาษี");
		formTS0105Vo.setCaseDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setCaseTime("13.00");
		formTS0105Vo.setDestText("ตรวจสอบภาษี");
		formTS0105Vo.setDestDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setDestTime("11.30");
		formTS0105Vo.setSignOfficerFullName("ธนพล ชัยภูมิ");
		formTS0105Vo.setSignOfficerPosition("ตรวจสอบภาษี");
		formTS0105Vo.setOtherText("");
		formTS0105Vo.setOtherPhone("0933413252");
		
		taFormTS0105Service.saveFormTS(formTS0105Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0105Vo formTs0105Vo = taFormTS0105Service.getFormTS("000000-2562-000272");
		System.out.println(ToStringBuilder.reflectionToString(formTs0105Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0105Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
