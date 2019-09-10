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
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxFilingVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailFormVo;
import th.go.excise.ims.ta.vo.TaxAuditDetailVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisTaxFilingServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private BasicAnalysisService basicAnalysisService;
	@Autowired
	private TaxAuditService taxAuditService;
	@Autowired
	private BasicAnalysisTaxFilingService basicAnalysisTaxFilingService;
	
//	@Test
	public void test_inquiry() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		formVo.setPaperBaNumber("001401-2562-000001");
		
		List<BasicAnalysisTaxFilingVo> voList = basicAnalysisTaxFilingService.inquiry(formVo);
		for (BasicAnalysisTaxFilingVo vo : voList) {
			System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE));
		}
	}
	
//	@Test
	public void test_save() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		formVo.setPaperBaNumber("001401-2562-000001");
		basicAnalysisTaxFilingService.save(formVo);
		System.out.println("************************Is successfully saved************************");
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
		
		JasperPrint jasperPrint = basicAnalysisTaxFilingService.getJasperPrint(formVo, taxAuditDetailVo);
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
		exporter.exportReport();
		
		byte[] reportFile = outputStream.toByteArray();
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, "BA_REPORT_" + paperBaNumber))));
	}
	
}
