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
import th.go.excise.ims.ta.vo.TaFormTS0302DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0302Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0302ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Autowired
	private TaFormTS0302Service taFormTS0302Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0302Service taFormTS0302Service = new TaFormTS0302Service();

		TaFormTS0302Vo formTS0302Vo = new TaFormTS0302Vo();
		formTS0302Vo.setFormTsNumber("000000-2562-000356");
		formTS0302Vo.setFactoryName("ร้าน หอมจันทร์ฟราแกรนซ์");
		formTS0302Vo.setFactoryTypeText("น้ำหอม");
		formTS0302Vo.setOwnerName("นาย วิทยารัตน์ สุรบดีพงษ์");
		formTS0302Vo.setNewRegId("2557026887");
		formTS0302Vo.setFacAddrNo("559/26");
		formTS0302Vo.setFacSoiName("สันทราย 22");
		formTS0302Vo.setFacThnName("เจริญยิ่ง");
		formTS0302Vo.setFacTambolName("สันทราย");
		formTS0302Vo.setFacAmphurName("เมืองเชียงราย");
		formTS0302Vo.setFacProvinceName("เชียงราย");
		formTS0302Vo.setAssessmentText("-");

		TaFormTS0302DtlVo formTS0302DtlVo = null;
		List<TaFormTS0302DtlVo> formTS0302DtlVoList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			formTS0302DtlVo = new TaFormTS0302DtlVo();

			formTS0302DtlVo.setAuditNo(String.valueOf(i + 1));
			formTS0302DtlVo.setOperatorOfficeName("เจ้าหน้าที่กรมสรรพสามิต");
			formTS0302DtlVo.setOperatorFullName("นาย ไกรสร  ใจดี");
			formTS0302DtlVo.setRefDocNo("กข0001");
			formTS0302DtlVo.setRefDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
			formTS0302DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 1))));
			formTS0302DtlVo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 5))));
			formTS0302DtlVo.setAuditStatus("ส่งตรวจสอบ");
			formTS0302DtlVo.setAuditStatusDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 1))));
			formTS0302DtlVo.setResultDocNo("กข002");
			formTS0302DtlVo.setResultDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 8))));
			formTS0302DtlVo.setResultTaxAmt(new BigDecimal("1000"));
			formTS0302DtlVo.setResultFineAmt(new BigDecimal("200"));
			formTS0302DtlVo.setResultExtraAmt(new BigDecimal("100"));
			formTS0302DtlVo.setResultMoiAmt(new BigDecimal("200"));
			formTS0302DtlVo.setResultNetTaxAmt(new BigDecimal("100"));
			formTS0302DtlVo.setAssessmentAmt(new BigDecimal("1600"));
			formTS0302DtlVo.setOfficerFullName("นายอมร  คนดี");
			formTS0302DtlVo.setOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 9))));
			formTS0302DtlVo.setOfficerComment("ผ่านการตรวจสอบแล้ว");
			formTS0302DtlVoList.add(formTS0302DtlVo);
		}
		formTS0302Vo.setTaFormTS0302DtlVoList(formTS0302DtlVoList);

		byte[] reportFile = taFormTS0302Service.generateReport(formTS0302Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS03_02))));
	}

	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0302Service taFormTS0302Service = new TaFormTS0302Service();

		TaFormTS0302Vo formTS0302Vo = new TaFormTS0302Vo();

		TaFormTS0302DtlVo formTS0302DtlVo = null;
		List<TaFormTS0302DtlVo> formTS0302DtlVoList = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			formTS0302DtlVo = new TaFormTS0302DtlVo();
			formTS0302DtlVoList.add(formTS0302DtlVo);
		}
		formTS0302Vo.setTaFormTS0302DtlVoList(formTS0302DtlVoList);

		byte[] reportFile = taFormTS0302Service.generateReport(formTS0302Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS03_02 + "_blank"))));
	}

//	@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS0302Vo formTS0302Vo = new TaFormTS0302Vo();
		formTS0302Vo.setFormTsNumber("000000-2562-000356");
		formTS0302Vo.setFactoryName("ร้าน หอมจันทร์ฟราแกรนซ์");
		formTS0302Vo.setFactoryTypeText("น้ำหอม");
		formTS0302Vo.setOwnerName("นาย วิทยารัตน์ สุรบดีพงษ์");
		formTS0302Vo.setNewRegId("2557026887");
		formTS0302Vo.setFacAddrNo("559/26");
		formTS0302Vo.setFacSoiName("สันทราย 22");
		formTS0302Vo.setFacThnName("เจริญยิ่ง");
		formTS0302Vo.setFacTambolName("สันทราย");
		formTS0302Vo.setFacAmphurName("เมืองเชียงราย");
		formTS0302Vo.setFacProvinceName("เชียงราย");
		formTS0302Vo.setAssessmentText("-");

		TaFormTS0302DtlVo formTS0302DtlVo = null;
		List<TaFormTS0302DtlVo> formTS0302DtlVoList = new ArrayList<>();
		for (int i = 0; i < 1; i++) {
			formTS0302DtlVo = new TaFormTS0302DtlVo();
			formTS0302DtlVo.setAuditNo(String.valueOf(i + 1));
			formTS0302DtlVo.setOperatorOfficeName("เจ้าหน้าที่กรมสรรพสามิต");
			formTS0302DtlVo.setOperatorFullName("นาย ไกรสร  ใจดี");
			formTS0302DtlVo.setRefDocNo("กข0001");
			formTS0302DtlVo.setRefDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
			formTS0302DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 1))));
			formTS0302DtlVo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 5))));
			formTS0302DtlVo.setAuditStatus("ส่งตรวจสอบ");
			formTS0302DtlVo.setAuditStatusDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 1))));
			formTS0302DtlVo.setResultDocNo("กข002");
			formTS0302DtlVo.setResultDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 8))));
			formTS0302DtlVo.setResultTaxAmt(new BigDecimal("1000"));
			formTS0302DtlVo.setResultFineAmt(new BigDecimal("200"));
			formTS0302DtlVo.setResultExtraAmt(new BigDecimal("100"));
			formTS0302DtlVo.setResultMoiAmt(new BigDecimal("200"));
			formTS0302DtlVo.setResultNetTaxAmt(new BigDecimal("100"));
			formTS0302DtlVo.setAssessmentAmt(new BigDecimal("1600"));
			formTS0302DtlVo.setOfficerFullName("นายอมร  คนดี");
			formTS0302DtlVo.setOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 9))));
			formTS0302DtlVo.setOfficerComment("ผ่านการตรวจสอบแล้ว");
			formTS0302DtlVoList.add(formTS0302DtlVo);
		}
		formTS0302Vo.setTaFormTS0302DtlVoList(formTS0302DtlVoList);

		taFormTS0302Service.saveFormTS(formTS0302Vo);
	}

//	@Test
	public void test_getFormTS() {
		TaFormTS0302Vo formTs0302Vo = taFormTS0302Service.getFormTS("000000-2562-000356");
		System.out.println(ToStringBuilder.reflectionToString(formTs0302Vo, ToStringStyle.JSON_STYLE));
	}

//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0302Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}

}
