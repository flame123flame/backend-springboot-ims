package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
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
import th.go.excise.ims.ta.vo.TaFormTS0108DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0108ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0108Service taFormTS0108Service;
	
//	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0108Service service08 = new TaFormTS0108Service();
		
		TaFormTS0108Vo formTS0108Vo = new TaFormTS0108Vo();
		formTS0108Vo.setFormTsNumber("000000-2562-000126");
		List<TaFormTS0108DtlVo> formTS0108DtlVoList = new ArrayList<>();
		TaFormTS0108DtlVo formTS0108DtlVo = null;
		for (int i = 0; i < 19; i++) {
			formTS0108DtlVo = new TaFormTS0108DtlVo();
			formTS0108DtlVo.setRecNo(String.valueOf((i + 1)));
			formTS0108DtlVo.setAuditDate(new Date());
			formTS0108DtlVo.setOfficerFullName("ธนพล ชัยภูมิ");
			formTS0108DtlVo.setOfficerPosition("ตรวจสอบภาษี");
			formTS0108DtlVo.setAuditTime("13:30");
			formTS0108DtlVo.setAuditDest("โรงผลิตเหล้า");
			formTS0108DtlVo.setAuditTopic("การหมักเหล้า");
			formTS0108DtlVo.setApprovedAck("สุรศักดิ์ ");
			formTS0108DtlVo.setAuditComment("");
			formTS0108DtlVoList.add(formTS0108DtlVo);
		}
		formTS0108Vo.setTaFormTS0108DtlVoList(formTS0108DtlVoList);

		byte[] reportFile = service08.generateReport(formTS0108Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_08))));
	}
	
//	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0108Service service08 = new TaFormTS0108Service();
		
		TaFormTS0108Vo formTS0108Vo = new TaFormTS0108Vo();
		
		byte[] reportFile = service08.generateReport(formTS0108Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_08 + "_blank"))));
	}
	
	@Test
	public void test_saveFormTS() throws Exception {
		String formTsNumber = "000000-2562-000075";
		
		// set data
		TaFormTS0108Vo formTS0108Vo = new TaFormTS0108Vo();
//		formTS0108Vo.setFormTsNumber(formTsNumber);
		
		List<TaFormTS0108DtlVo> formTS0108DtlVoList = new ArrayList<>();
		TaFormTS0108DtlVo formTS0108DtlVo = null;
		for (int i = 0; i < 2; i++) {
			formTS0108DtlVo = new TaFormTS0108DtlVo();
			formTS0108DtlVo.setFormTs0108DtlId(String.valueOf((i + 1)));
			formTS0108DtlVo.setRecNo(String.valueOf((i + 1)));
			formTS0108DtlVo.setAuditDate(new Date());
			formTS0108DtlVo.setOfficerFullName("ธนพล ชัยภูมิ");
			formTS0108DtlVo.setOfficerPosition("ตรวจสอบภาษี");
			formTS0108DtlVo.setAuditTime("13:30");
			formTS0108DtlVo.setAuditDest("โรงผลิตเหล้า");
			formTS0108DtlVo.setAuditTopic("การหมักเหล้า");
			formTS0108DtlVo.setApprovedAck("สุรศักดิ์ ");
			formTS0108DtlVo.setAuditComment("");
			formTS0108DtlVoList.add(formTS0108DtlVo);
		}
		
		// Case Update with New Page
//		formTS0108DtlVo = new TaFormTS0108DtlVo();
//		formTS0108DtlVo.setRecNo("3");
//		formTS0108DtlVo.setAuditDate(new Date());
//		formTS0108DtlVo.setOfficerFullName("เตชภณ หิมารัตน์");
//		formTS0108DtlVo.setOfficerPosition("ตรวจสอบภาษี");
//		formTS0108DtlVoList.add(formTS0108DtlVo);
		
		formTS0108Vo.setTaFormTS0108DtlVoList(formTS0108DtlVoList);
		
		formTS0108Vo.setAuditPlanCode("0014022561000005");
		formTS0108Vo.setAuditStepStatus("1040");
		
		taFormTS0108Service.saveFormTS(formTS0108Vo);
	}
	
	//@Test
	public void test_getFormTS() throws Exception {
		String formTsNumber = "000000-2562-000075";
		
		TaFormTS0108Vo formTS0108Vo = taFormTS0108Service.getFormTS(formTsNumber);
		System.out.println(ToStringBuilder.reflectionToString(formTS0108Vo, ToStringStyle.MULTI_LINE_STYLE));
	}
	
}
