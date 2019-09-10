package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS0116Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0116ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0116Service taFormTS0116Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0116Service taFormTS0116Service = new TaFormTS0116Service();

		TaFormTS0116Vo formTS0116Vo = new TaFormTS0116Vo();
		formTS0116Vo.setFormTsNumber("000000-2562-000001");
		formTS0116Vo.setDocText("");
		formTS0116Vo.setDocDear("");
		formTS0116Vo.setFactoryName1("โรงอุตสหกรรม 1");
		formTS0116Vo.setFactoryType("");
		formTS0116Vo.setFactoryName2("โรงอุตสหกรรม 2");
		formTS0116Vo.setNewRegId("");
		formTS0116Vo.setRequestDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 4))));
		formTS0116Vo.setRequestTypeExcept(FLAG.Y_FLAG);
		formTS0116Vo.setRequestTypeReduce(FLAG.Y_FLAG);
		formTS0116Vo.setRequestTypeFineAmt(FLAG.Y_FLAG);
		formTS0116Vo.setRequestTypeExtraAmt(FLAG.Y_FLAG);
		formTS0116Vo.setRequestReason("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผลผลิตภายในประเทศ พร้อมทั้ง ขอให้ผลักดันผ่านกฎหมายโดยเร็ว");
		formTS0116Vo.setRequestDesc("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผลผลิตภายในประเทศ พร้อมทั้ง ขอให้ผลักดันผ่านกฎหมายโดยเร็ว");
		formTS0116Vo.setFineNoFlag(FLAG.Y_FLAG);
		formTS0116Vo.setFineExceptAmtFlag(FLAG.Y_FLAG);
		formTS0116Vo.setFineReduceAmtFlag(FLAG.Y_FLAG);
		formTS0116Vo.setFinePercent(new BigDecimal("1.5"));
		formTS0116Vo.setExtraNoFlag(FLAG.Y_FLAG);
		formTS0116Vo.setExtraReduceAmtFlag(FLAG.Y_FLAG);
		formTS0116Vo.setExtraPercent(new BigDecimal("2"));
		formTS0116Vo.setBeforeTaxAmt(new BigDecimal("200"));
		formTS0116Vo.setBeforeFinePercent(new BigDecimal("300"));
		formTS0116Vo.setBeforeFineAmt(new BigDecimal("400"));
		formTS0116Vo.setBeforeExtraAmt(new BigDecimal("500"));
		formTS0116Vo.setBeforeMoiAmt(new BigDecimal("40"));
		formTS0116Vo.setBeforeSumAmt(new BigDecimal("5000"));
		formTS0116Vo.setAfterTaxAmt(new BigDecimal("6789"));
		formTS0116Vo.setAfterFinePercent(new BigDecimal("1234"));
		formTS0116Vo.setAfterFineAmt(new BigDecimal("5432"));
		formTS0116Vo.setAfterExtraAmt(new BigDecimal("2345"));
		formTS0116Vo.setAfterMoiAmt(new BigDecimal("6543"));
		formTS0116Vo.setAfterSumAmt(new BigDecimal("2123"));
		formTS0116Vo.setSignOfficerFullName("");
		formTS0116Vo.setSignOfficerPosition("");
		formTS0116Vo.setHeadOfficerComment("");
		formTS0116Vo.setSignHeadOfficerFullName("");
		formTS0116Vo.setSignHeadOfficerPosition("");
		formTS0116Vo.setSignHeadOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 4))));
		formTS0116Vo.setApproverComment("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผล ");
		formTS0116Vo.setSignApproverFullName("ธนพล ชัยภูมิ");
		formTS0116Vo.setSignApproverPosition("ตรวจสอบภาษี");
		formTS0116Vo.setSignApproverDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 5))));
		
		byte[] reportFile = taFormTS0116Service.generateReport(formTS0116Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_16))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0116Service taFormTS0116Service = new TaFormTS0116Service();
		
		TaFormTS0116Vo formTS0116Vo = new TaFormTS0116Vo();
		
		byte[] reportFile = taFormTS0116Service.generateReport(formTS0116Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_16 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0116Vo formTS0116Vo = new TaFormTS0116Vo();
		formTS0116Vo.setFormTsNumber("");
		formTS0116Vo.setDocText("");
		formTS0116Vo.setDocDear("");
		formTS0116Vo.setFactoryName1("โรงอุตสหกรรม 1");
		formTS0116Vo.setFactoryType("");
		formTS0116Vo.setFactoryName2("โรงอุตสหกรรม 2");
		formTS0116Vo.setNewRegId("");
		formTS0116Vo.setRequestDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 4))));
		formTS0116Vo.setRequestTypeExcept(FLAG.Y_FLAG);
		formTS0116Vo.setRequestTypeReduce(FLAG.Y_FLAG);
		formTS0116Vo.setRequestTypeFineAmt(FLAG.Y_FLAG);
		formTS0116Vo.setRequestTypeExtraAmt(FLAG.Y_FLAG);
		formTS0116Vo.setRequestReason("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผลผลิตภายในประเทศ พร้อมทั้ง ขอให้ผลักดันผ่านกฎหมายโดยเร็ว");
		formTS0116Vo.setRequestDesc("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผลผลิตภายในประเทศ พร้อมทั้ง ขอให้ผลักดันผ่านกฎหมายโดยเร็ว");
		formTS0116Vo.setFineNoFlag(FLAG.Y_FLAG);
		formTS0116Vo.setFineExceptAmtFlag(FLAG.Y_FLAG);
		formTS0116Vo.setFineReduceAmtFlag(FLAG.Y_FLAG);
		formTS0116Vo.setFinePercent(new BigDecimal("1.5"));
		formTS0116Vo.setExtraNoFlag(FLAG.Y_FLAG);
		formTS0116Vo.setExtraReduceAmtFlag(FLAG.Y_FLAG);
		formTS0116Vo.setExtraPercent(new BigDecimal("2"));
		formTS0116Vo.setBeforeTaxAmt(new BigDecimal("200"));
		formTS0116Vo.setBeforeFinePercent(new BigDecimal("300"));
		formTS0116Vo.setBeforeFineAmt(new BigDecimal("400"));
		formTS0116Vo.setBeforeExtraAmt(new BigDecimal("500"));
		formTS0116Vo.setBeforeMoiAmt(new BigDecimal("40"));
		formTS0116Vo.setBeforeSumAmt(new BigDecimal("5000"));
		formTS0116Vo.setAfterTaxAmt(new BigDecimal("6789"));
		formTS0116Vo.setAfterFinePercent(new BigDecimal("1234"));
		formTS0116Vo.setAfterFineAmt(new BigDecimal("5432"));
		formTS0116Vo.setAfterExtraAmt(new BigDecimal("2345"));
		formTS0116Vo.setAfterMoiAmt(new BigDecimal("6543"));
		formTS0116Vo.setAfterSumAmt(new BigDecimal("2123"));
		formTS0116Vo.setSignOfficerFullName("");
		formTS0116Vo.setSignOfficerPosition("");
		formTS0116Vo.setHeadOfficerComment("");
		formTS0116Vo.setSignHeadOfficerFullName("");
		formTS0116Vo.setSignHeadOfficerPosition("");
		formTS0116Vo.setSignHeadOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 4))));
		formTS0116Vo.setApproverComment("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผล ");
		formTS0116Vo.setSignApproverFullName("ธนพล ชัยภูมิ");
		formTS0116Vo.setSignApproverPosition("ตรวจสอบภาษี");
		formTS0116Vo.setSignApproverDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 5))));
		
		taFormTS0116Service.saveFormTS(formTS0116Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0116Vo formTs0116Vo = taFormTS0116Service.getFormTS("000000-2562-000297");
		System.out.println(ToStringBuilder.reflectionToString(formTs0116Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0116Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
