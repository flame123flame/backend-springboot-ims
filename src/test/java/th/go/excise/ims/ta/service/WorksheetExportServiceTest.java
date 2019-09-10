package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class WorksheetExportServiceTest {
	
	private static final String OUTPUT_PATH = "/tmp/excise/ims/report";
	
	@Autowired
	private WorksheetExportService worksheetExportService;
	
//	@Test
	public void test_exportPreviewWorksheet() {
		long start = System.currentTimeMillis();
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setBudgetYear("2562");
		formVo.setDateStart("01/2560");
		formVo.setDateEnd("12/2560");
		
		String fileName = "previewWorksheet" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] bytes = worksheetExportService.exportPreviewWorksheet(formVo);
			fos.write(bytes);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Process Success, using " + ((float) (end - start) / 1000F) + " seconds");
	}
	
//	@Test
	public void test_exportDraftWorksheet() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setDraftNumber("001401-2563-000001");
		formVo.setOfficeCode("001401");
		
		String fileName = "draftWorksheet" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] bytes = worksheetExportService.exportDraftWorksheet(formVo);
			fos.write(bytes);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void test_exportWorksheet() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setAnalysisNumber("001401-2563-000001");
		
		String fileName = "worksheet" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] bytes = worksheetExportService.exportWorksheet(formVo);
			fos.write(bytes);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	/*public void exportCondSubWorksheet() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setAnalysisNumber("000000-2562-000031");
		
		String fileName = "WorksheetCondSub" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream Output = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] bytes = worksheetExportService.exportCondSubWorksheet(formVo);
			Output.write(bytes);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void test_exportPlanWorksheetSelected() {
		PlanWorksheetVo formVo = new PlanWorksheetVo();
		formVo.setPlanNumber("001401-2563-000001");
		
		String fileName = "PlanWorksheetSelected" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream fos = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] outArray = worksheetExportService.exportPlanWorksheetSelected(formVo);
			fos.write(outArray);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
