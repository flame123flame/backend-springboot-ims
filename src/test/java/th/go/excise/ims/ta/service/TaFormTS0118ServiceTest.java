package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
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
import th.go.excise.ims.ta.vo.TaFormTS0118DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0118Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0118ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private TaFormTS0118Service taFormTS0118Service;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0118Service taFormTS0118Service = new TaFormTS0118Service();
		
		TaFormTS0118Vo formTS0118Vo = new TaFormTS0118Vo();
		formTS0118Vo.setFormTsNumber("000000-2562-000001");
		formTS0118Vo.setBookNumber1("25632");
		formTS0118Vo.setBookNumber2("632522");
		formTS0118Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0118Vo.setOwnerFullName("นาย วิทยารัตน์ สุรบดีพงษ์");
		formTS0118Vo.setFactoryType("1");
		formTS0118Vo.setFactoryName("ร้าน หอมจันทร์ฟราแกรนซ์");
		formTS0118Vo.setNewRegId("2557026887");
		formTS0118Vo.setFactoryAddress("559/26 หมู่ 6 ซ.- ต.สันทราย อ.เมืองเชียงราย จ.เชียงราย 57000");
		formTS0118Vo.setCompanyAddress("559/26 หมู่ 6 ซ.- ต.สันทราย อ.เมืองเชียงราย จ.เชียงราย 57000");
		formTS0118Vo.setLawSection("1234");
		formTS0118Vo.setLawGroup("5");
		formTS0118Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0118Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 18))));
		formTS0118Vo.setSumAllTaxAmt(new BigDecimal("100000000"));
		formTS0118Vo.setSumAllTaxText("หนึ่งร้อยล้านบาท");
		formTS0118Vo.setOfficeName("สำนักงานสรรพสามิตภาคที่ 5");
		formTS0118Vo.setTableHeaderDutyType("เครื่องหอม");
		formTS0118Vo.setTableHeaderUnit("ขวด");
		formTS0118Vo.setReasonText("ไม่มารายงานตัวต่อเจ้าหน้าที่ มีความเสี่ยงในการหลีกเลี่ยงภาษี");
		formTS0118Vo.setSignOfficerFullName1("นาย ประจักษ์ ศรีวิชัย");
		formTS0118Vo.setSignOfficerDate1(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0118Vo.setSignOfficerFullName2("");
		formTS0118Vo.setSignOfficerDate2(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 15))));
		formTS0118Vo.setExtraMoneyDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 10, 8))));
		
		TaFormTS0118DtlVo formTS0118DtlVo = null;
		List<TaFormTS0118DtlVo> formTS0118DtlVoList = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			formTS0118DtlVo = new TaFormTS0118DtlVo();
			formTS0118DtlVo.setDutyTypeText("น้ำหอม " + (i + 1));
			formTS0118DtlVo.setGoodsQty(new BigDecimal("10"));
			formTS0118DtlVo.setGoodsValue(new BigDecimal("11"));
			formTS0118DtlVo.setTaxRate(new BigDecimal("12"));
			formTS0118DtlVo.setTaxAmt(new BigDecimal("22"));
			formTS0118DtlVo.setFineAmt(new BigDecimal("33"));
			formTS0118DtlVo.setExtraAmt(new BigDecimal("44"));
			formTS0118DtlVo.setSumTaxAmt(new BigDecimal("55"));
			formTS0118DtlVo.setFundHealthAmt(new BigDecimal("66"));
			formTS0118DtlVo.setFundTVAmt(new BigDecimal("77"));
			formTS0118DtlVo.setFundSportAmt(new BigDecimal("88"));
			formTS0118DtlVo.setOtherAmt(new BigDecimal("99"));
			formTS0118DtlVo.setSumAllTaxAmt(new BigDecimal("100"));
			formTS0118DtlVo.setMoiAmt(new BigDecimal("1000"));
			formTS0118DtlVo.setNetTaxAmt(new BigDecimal("100000"));
			formTS0118DtlVoList.add(formTS0118DtlVo);
		}
		formTS0118Vo.setTaFormTS0118DtlVoList(formTS0118DtlVoList);
		
		byte[] reportFile = taFormTS0118Service.generateReport(formTS0118Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_18))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0118Service taFormTS0118Service = new TaFormTS0118Service();
		
		TaFormTS0118Vo formTS0118Vo = new TaFormTS0118Vo();
		
		List<TaFormTS0118DtlVo> formTS0118DtlVoList = new ArrayList<>();
		formTS0118DtlVoList.add(new TaFormTS0118DtlVo());
		
		formTS0118Vo.setTaFormTS0118DtlVoList(formTS0118DtlVoList);
		
		byte[] reportFile = taFormTS0118Service.generateReport(formTS0118Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_18 + "_blank"))));
	}
	
//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0118Vo formTS0118Vo = new TaFormTS0118Vo();
		formTS0118Vo.setFormTsNumber("000000-2562-000358");
		formTS0118Vo.setBookNumber1("25632");
		formTS0118Vo.setBookNumber2("632522");
		formTS0118Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0118Vo.setOwnerFullName("นาย วิทยารัตน์ สุรบดีพงษ์");
		formTS0118Vo.setFactoryType("1");
		formTS0118Vo.setFactoryName("ร้าน หอมจันทร์ฟราแกรนซ์");
		formTS0118Vo.setNewRegId("2557026887");
		formTS0118Vo.setFactoryAddress("559/26 หมู่ 6 ซ.- ต.สันทราย อ.เมืองเชียงราย จ.เชียงราย 57000");
		formTS0118Vo.setCompanyAddress("559/26 หมู่ 6 ซ.- ต.สันทราย อ.เมืองเชียงราย จ.เชียงราย 57000");
		formTS0118Vo.setLawSection("1234");
		formTS0118Vo.setLawGroup("5");
		formTS0118Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0118Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 18))));
		formTS0118Vo.setSumAllTaxAmt(new BigDecimal("100000000"));
		formTS0118Vo.setSumAllTaxText("หนึ่งร้อยล้านบาท");
		formTS0118Vo.setOfficeName("สำนักงานสรรพสามิตภาคที่ 5");
		formTS0118Vo.setTableHeaderDutyType("เครื่องหอม");
		formTS0118Vo.setTableHeaderUnit("ขวด");
		formTS0118Vo.setReasonText("ไม่มารายงานตัวต่อเจ้าหน้าที่ มีความเสี่ยงในการหลีกเลี่ยงภาษี");
		formTS0118Vo.setSignOfficerFullName1("นาย ประจักษ์ ศรีวิชัย");
		formTS0118Vo.setSignOfficerDate1(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0118Vo.setSignOfficerFullName2("");
		formTS0118Vo.setSignOfficerDate2(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 15))));
		formTS0118Vo.setExtraMoneyDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 10, 8))));
		
		TaFormTS0118DtlVo formTS0118DtlVo = null;
		List<TaFormTS0118DtlVo> formTS0118DtlVoList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			formTS0118DtlVo = new TaFormTS0118DtlVo();
			formTS0118DtlVo.setFormTs0118DtlId(String.valueOf(4 + (i + 1)));
			formTS0118DtlVo.setDutyTypeText("น้ำหอม " + (i + 1));
			formTS0118DtlVo.setGoodsQty(new BigDecimal("10"));
			formTS0118DtlVo.setGoodsValue(new BigDecimal("11"));
			formTS0118DtlVo.setTaxRate(new BigDecimal("12"));
			formTS0118DtlVo.setTaxAmt(new BigDecimal("22"));
			formTS0118DtlVo.setFineAmt(new BigDecimal("33"));
			formTS0118DtlVo.setExtraAmt(new BigDecimal("44"));
			formTS0118DtlVo.setSumTaxAmt(new BigDecimal("55"));
			formTS0118DtlVo.setFundHealthAmt(new BigDecimal("66"));
			formTS0118DtlVo.setFundTVAmt(new BigDecimal("77"));
			formTS0118DtlVo.setFundSportAmt(new BigDecimal("88"));
			formTS0118DtlVo.setOtherAmt(new BigDecimal("99"));
			formTS0118DtlVo.setSumAllTaxAmt(new BigDecimal("100"));
			formTS0118DtlVo.setMoiAmt(new BigDecimal("1000"));
			formTS0118DtlVo.setNetTaxAmt(new BigDecimal("100000"));
			formTS0118DtlVoList.add(formTS0118DtlVo);
		}
		formTS0118Vo.setTaFormTS0118DtlVoList(formTS0118DtlVoList);
		
		taFormTS0118Service.saveFormTS(formTS0118Vo);
	}
	
//	@Test
	public void test_getFormTS() {
		TaFormTS0118Vo formTs0118Vo = taFormTS0118Service.getFormTS("000000-2562-000358");
		System.out.println(ToStringBuilder.reflectionToString(formTs0118Vo, ToStringStyle.JSON_STYLE));
	}
	
//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0118Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}
	
}
