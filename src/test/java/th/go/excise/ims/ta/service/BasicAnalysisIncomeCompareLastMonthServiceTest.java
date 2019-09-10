package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastMonthVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisIncomeCompareLastMonthServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private BasicAnalysisService basicAnalysisService;
	@Autowired
	private TaxAuditService taxAuditService;
	@Autowired
	private BasicAnalysisIncomeCompareLastMonthService basicAnalysisIncomeCompareLastMonthService;
	
//	@Test
	public void test_inquiry() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("07755390005721001");
		formVo.setDutyGroupId("0201");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("06/2561");
		formVo.setMonthIncomeType(TA_CONFIG.INCOME_TYPE_NET);
		//formVo.setPaperBaNumber("0100002562000001");
		
		List<BasicAnalysisIncomeCompareLastMonthVo> voList = basicAnalysisIncomeCompareLastMonthService.inquiry(formVo);
		for (BasicAnalysisIncomeCompareLastMonthVo vo : voList) {
			System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE));
		}
	}
	
	//@Test
	public void test_save() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("07755390005721001");
		formVo.setDutyGroupId("0201");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("06/2561");
		formVo.setPaperBaNumber("0100002562000001");
		
		basicAnalysisIncomeCompareLastMonthService.save(formVo);
	}
	
//	@Test
	public void test_getJasperPrint() throws Exception {
		String paperBaNumber = "001402-2563-000001";
		
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setPaperBaNumber(paperBaNumber);
		formVo = basicAnalysisService.getPaperBaHeader(formVo);
		
		TaxAuditDetailFormVo taxAuditDetailFormVo = new TaxAuditDetailFormVo();
		taxAuditDetailFormVo.setAuditPlanCode(formVo.getAuditPlanCode());
		TaxAuditDetailVo taxAuditDetailVo = taxAuditService.getOperatorDetailsByAuditPlanCode(taxAuditDetailFormVo);
		
		JasperPrint jasperPrint = basicAnalysisIncomeCompareLastMonthService.getJasperPrint(formVo, taxAuditDetailVo);
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		
		byte[] reportFile = outputStream.toByteArray();
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, "BA_REPORT_" + paperBaNumber))));
	}
	
}
