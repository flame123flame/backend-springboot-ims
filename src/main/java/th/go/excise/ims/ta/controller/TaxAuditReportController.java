package th.go.excise.ims.ta.controller;

import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.ims.ta.service.WorksheetExportService;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Controller
@RequestMapping("/api/ta/report")
public class TaxAuditReportController {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditReportController.class);

	@Autowired
	private WorksheetExportService worksheetExportService;

	// TODO preview Worksheet
	@GetMapping("/ta-rpt0001")
	@ResponseBody
	public void exportPreviewWorksheet(@ModelAttribute TaxOperatorFormVo formVo, HttpServletResponse response) throws Exception {
		logger.info("exportPreviewWorksheet");
		
		String fileName = URLEncoder.encode("ta-rpt0001", "UTF-8");
		
		byte[] bytes = worksheetExportService.exportPreviewWorksheet(formVo);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		
		OutputStream os = response.getOutputStream();
		os.write(bytes);
	}

	// TODO Draft Worksheet
	@GetMapping("/ta-rpt0002")
	@ResponseBody
	public void exportDraftWorksheet(@ModelAttribute TaxOperatorFormVo formVo, HttpServletResponse response) throws Exception {
		logger.info("exportDraftWorksheet");
		
		String fileName = URLEncoder.encode("ta-rpt0002", "UTF-8");
		
		byte[] bytes = worksheetExportService.exportDraftWorksheet(formVo);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		
		OutputStream os = response.getOutputStream();
		os.write(bytes);
	}

	// TODO Worksheet
	@GetMapping("/ta-rpt0003")
	@ResponseBody
	public void exportWorksheet(@ModelAttribute TaxOperatorFormVo formVo, HttpServletResponse response) throws Exception {
		logger.info("exportWorksheet");
		
		String fileName = URLEncoder.encode("ta-rpt0003", "UTF-8");
		
		byte[] bytes = worksheetExportService.exportWorksheet(formVo);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		
		OutputStream os = response.getOutputStream();
		os.write(bytes);
	}

	// TODO Worksheet Sub Condition
	@GetMapping("/ta-rpt0003-1")
	@ResponseBody
	public void exportWorksheetCondSub(@ModelAttribute TaxOperatorFormVo formVo, HttpServletResponse response) throws Exception {
		logger.info("exportWorksheetCondSub");
		
		/*String fileName = URLEncoder.encode("Worksheet Cond sub", "UTF-8");
		
		byte[] bytes = worksheetExportService.exportCondSubWorksheet(formVo);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		
		OutputStream os = response.getOutputStream();
		os.write(bytes);*/
	}

	// TODO PlanWorksheet
	@GetMapping("/export-worksheet-selected")
	@ResponseBody
	public void exportPlanWorksheet(@ModelAttribute PlanWorksheetVo formVo, HttpServletResponse response) throws Exception {
		logger.info("exportPlanWorksheet");
		
		String fileName = URLEncoder.encode("ta-rpt0004", "UTF-8");
		
		byte[] bytes = worksheetExportService.exportPlanWorksheetSelected(formVo);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		
		OutputStream os = response.getOutputStream();
		os.write(bytes);
	}

}
