package th.go.excise.ims.ta.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.BasicAnalysisService;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;

@Controller
@RequestMapping("/api/ta/basic-analysis")
public class BasicAnalysisController {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisController.class);

	private BasicAnalysisService basicAnalysisService;

	@Autowired
	public BasicAnalysisController(BasicAnalysisService basicAnalysisService) {
		this.basicAnalysisService = basicAnalysisService;
	}

	@PostMapping("/inquiry-{analysisType}-data")
	@ResponseBody
	public DataTableAjax<?> inquiryData(@PathVariable("analysisType") String analysisType, @RequestBody BasicAnalysisFormVo formVo) {
		logger.info("inquiryData analysisType={}", analysisType);

		DataTableAjax<?> response = new DataTableAjax<>();
		try {
			response = basicAnalysisService.inquiry(analysisType, formVo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> saveData(@RequestBody BasicAnalysisFormVo formVo) {
		logger.info("saveData");

		ResponseData<String> response = new ResponseData<>();
		try {
			basicAnalysisService.save(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/get-paper-ba-number-list")
	@ResponseBody
	public ResponseData<List<String>> getPaperBaNumberList(@RequestBody BasicAnalysisFormVo formVo) {
		logger.info("getPaperBaNumberList auditPlanCode={}", formVo.getAuditPlanCode());
		ResponseData<List<String>> response = new ResponseData<>();
		try {
			List<String> baNumberList = basicAnalysisService.getPaperBaNumberList(formVo);
			response.setData(baNumberList);
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@GetMapping("/pdf/{paperBaNumber}")
	public void generateBasicAnalysisPdfReport(@PathVariable("paperBaNumber") String paperBaNumber, HttpServletResponse response) throws Exception {
		logger.info("generateBasicAnalysisPdfReport paperBaNumber={}", paperBaNumber);

		byte[] reportFile = basicAnalysisService.generateReport(paperBaNumber);

		String filename = String.format("ba_report_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(reportFile, response.getOutputStream());
	}
	
	@PostMapping("/get-paper-ba-header")
	@ResponseBody
	public ResponseData<BasicAnalysisFormVo> getPaperBaHeader(@RequestBody BasicAnalysisFormVo form) {
		ResponseData<BasicAnalysisFormVo> response = new ResponseData<>();
		
		try {
			response.setData(basicAnalysisService.getPaperBaHeader(form));
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

}
