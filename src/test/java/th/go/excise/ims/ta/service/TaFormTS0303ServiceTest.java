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
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0303DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0303Vo;
import th.go.excise.ims.ta.vo.TaFormTsFormVo;

/*@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)*/
public class TaFormTS0303ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Autowired
	private TaFormTS0303Service taFormTS0303Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0303Service service0303 = new TaFormTS0303Service();

		TaFormTS0303Vo formTS0303Vo = new TaFormTS0303Vo();
		formTS0303Vo.setFormTsNumber("000000-2562-000126");
		List<TaFormTS0303DtlVo> formTS0303DtlVoList = new ArrayList<>();
		TaFormTS0303DtlVo formTS0303DtlVo = null;
		for (int i = 0; i < 2; i++) {
			formTS0303DtlVo = new TaFormTS0303DtlVo();
			formTS0303DtlVo.setRecNo(String.valueOf(i + 1));
			formTS0303DtlVo.setOwnerFullName("นาย วิทยารัตน์ สุรบดีพงษ์");
			formTS0303DtlVo.setNewRegId("2557026887");
			formTS0303DtlVo.setFactoryTypeText("น้ำหอม");
			formTS0303DtlVo.setReqDocNo("กข0001");
			formTS0303DtlVo.setReqDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
			formTS0303DtlVo.setInformDocNo("กข002");
			formTS0303DtlVo.setInformDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 8))));
			formTS0303DtlVo.setCallDocNo("กข003");
			formTS0303DtlVo.setCallDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 10))));
			formTS0303DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 11))));
			formTS0303DtlVo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 13))));
			formTS0303DtlVo.setResultDocNo("กข004");
			formTS0303DtlVo.setResultDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 16))));
			formTS0303DtlVo.setResultTaxAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultFineAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultExtraAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultMoiAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultNetTaxAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setAssessmentAmt(new BigDecimal("5000"));
			formTS0303DtlVo.setOfficerFullName("นายอมร  คนดี");
			formTS0303DtlVo.setOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 20))));
			formTS0303DtlVo.setOfficerComment("ผ่านการตรวจสอบแล้ว");
			formTS0303DtlVoList.add(formTS0303DtlVo);
		}
		formTS0303Vo.setTaFormTS0303DtlVoList(formTS0303DtlVoList);

		byte[] reportFile = service0303.generateReport(formTS0303Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS03_03))));
	}

	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0303Service service0303 = new TaFormTS0303Service();

		TaFormTS0303Vo formTS0303Vo = new TaFormTS0303Vo();

		TaFormTS0303DtlVo formTS0303DtlVo = null;
		List<TaFormTS0303DtlVo> formTS0303DtlVoList = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			formTS0303DtlVo = new TaFormTS0303DtlVo();
			formTS0303DtlVoList.add(formTS0303DtlVo);
		}
		formTS0303Vo.setTaFormTS0303DtlVoList(formTS0303DtlVoList);

		byte[] reportFile = service0303.generateReport(formTS0303Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS03_03 + "_blank"))));
	}

//	@Test
	public void test_saveFormTS() throws Exception {
		String formTsNumber = "000000-2562-000126";

		// set data
		TaFormTS0303Vo formTS0303Vo = new TaFormTS0303Vo();
		formTS0303Vo.setFormTsNumber(formTsNumber);

		List<TaFormTS0303DtlVo> formTS0303DtlVoList = new ArrayList<>();
		TaFormTS0303DtlVo formTS0303DtlVo = null;
		for (int i = 0; i < 2; i++) {
			formTS0303DtlVo = new TaFormTS0303DtlVo();
			formTS0303DtlVo.setRecNo(String.valueOf(i + 1));
			formTS0303DtlVo.setOwnerFullName("นาย วิทยารัตน์ สุรบดีพงษ์");
			formTS0303DtlVo.setNewRegId("2557026887");
			formTS0303DtlVo.setFactoryTypeText("น้ำหอม");
			formTS0303DtlVo.setReqDocNo("กข0001");
			formTS0303DtlVo.setReqDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
			formTS0303DtlVo.setInformDocNo("กข002");
			formTS0303DtlVo.setInformDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 8))));
			formTS0303DtlVo.setCallDocNo("กข003");
			formTS0303DtlVo.setCallDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 10))));
			formTS0303DtlVo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 11))));
			formTS0303DtlVo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 13))));
			formTS0303DtlVo.setResultDocNo("กข004");
			formTS0303DtlVo.setResultDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 16))));
			formTS0303DtlVo.setResultTaxAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultFineAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultExtraAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultMoiAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setResultNetTaxAmt(new BigDecimal("1000"));
			formTS0303DtlVo.setAssessmentAmt(new BigDecimal("5000"));
			formTS0303DtlVo.setOfficerFullName("นายอมร  คนดี");
			formTS0303DtlVo.setOfficerDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 20))));
			formTS0303DtlVo.setOfficerComment("ผ่านการตรวจสอบแล้ว");
			formTS0303DtlVoList.add(formTS0303DtlVo);
		}

		formTS0303Vo.setTaFormTS0303DtlVoList(formTS0303DtlVoList);

		taFormTS0303Service.saveFormTS(formTS0303Vo);
	}

	// @Test
	public void test_getFormTS() throws Exception {
		String formTsNumber = "000000-2562-000126";

		TaFormTS0303Vo formTS0303Vo = taFormTS0303Service.getFormTS(formTsNumber);
		System.out.println(ToStringBuilder.reflectionToString(formTS0303Vo, ToStringStyle.MULTI_LINE_STYLE));
	}

//	@Test
	public void test_getFormTsNumberList() {
		TaFormTsFormVo formVo = new TaFormTsFormVo();
		//formVo.setAuditPlanCode("");
		
		taFormTS0303Service.getFormTsNumberList(formVo).forEach(e -> System.out.println(e));
	}

}
