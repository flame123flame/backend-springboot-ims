package th.go.excise.ims.ia.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int1306Service;
import th.go.excise.ims.ia.vo.IaAuditPmResultVo;
import th.go.excise.ims.ia.vo.Int1306FormVo;
import th.go.excise.ims.ia.vo.Int1306Vo;

@Controller
@RequestMapping("/api/ia/int13/06")
public class Int1306Controller {

	@Autowired
	private Int1306Service int1306Service;

	@PostMapping("/find-by-criteria")
	@ResponseBody
	public ResponseData<Int1306Vo> findByCriteria(@RequestBody Int1306FormVo request) {
		ResponseData<Int1306Vo> response = new ResponseData<Int1306Vo>();
		try {
			response.setData(int1306Service.findCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-all-audit-pmresult-no")
	@ResponseBody
	public ResponseData<List<IaAuditPmResultVo>> findAllIaAuditPmResult() {
		ResponseData<List<IaAuditPmResultVo>> response = new ResponseData<List<IaAuditPmResultVo>>();
		try {
			response.setData(int1306Service.findAuditPmResultList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaAuditPmResultVo> save(@RequestBody IaAuditPmResultVo request) {
		ResponseData<IaAuditPmResultVo> response = new ResponseData<IaAuditPmResultVo>();
		try {
			response.setData(int1306Service.save(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-by-audit-pmresult-no")
	@ResponseBody
	public ResponseData<IaAuditPmResultVo> findByAuditPmassessNo(@RequestBody String auditPmresultNo) {
		ResponseData<IaAuditPmResultVo> response = new ResponseData<IaAuditPmResultVo>();
		try {
			response.setData(int1306Service.findByAuditPmResultNo(auditPmresultNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/export/{auditPmresultNo}")
	public void export(@PathVariable("auditPmresultNo") String auditPmresultNo, HttpServletResponse response) throws Exception {
		String fileName = URLEncoder.encode("สรุปผลการตรวจสอบทานการประเมินการจัดวางระบบและการควบคุมภายใน ", "UTF-8");
		String replaceString = auditPmresultNo.replace('_', '/');

		// write it as an excel attachment
		byte[] outByteStream = int1306Service.export(replaceString);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outByteStream);
		outStream.flush();
		outStream.close();
	}
	
	@GetMapping("/pdf/{auditPmresultNo}")
	public void exportPdf(@PathVariable("auditPmresultNo") String auditPmresultNo, HttpServletResponse response) throws Exception {
		String name = URLEncoder.encode("PMR", "UTF-8");
		String replaceString = auditPmresultNo.replace('_', '/');

		// write it as an excel attachment		
		byte[] reportFile = int1306Service.generateReport(replaceString);

		String filename = String.format(name + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(reportFile, response.getOutputStream());
	}
}
