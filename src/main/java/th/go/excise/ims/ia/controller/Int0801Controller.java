package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaAuditGftbH;
import th.go.excise.ims.ia.service.Int0801Service;
import th.go.excise.ims.ia.vo.Int0801SaveVo;
import th.go.excise.ims.ia.vo.Int0801Tabs;
import th.go.excise.ims.ia.vo.SearchVo;

@Controller
@RequestMapping("/api/ia/int08/01")
public class Int0801Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0801Controller.class);

	@Autowired
	private Int0801Service int0801Service;

	@PostMapping("/search")
	@ResponseBody
	public ResponseData<List<Int0801Tabs>> search(@RequestBody SearchVo request) {
		ResponseData<List<Int0801Tabs>> response = new ResponseData<List<Int0801Tabs>>();
		try {
			response.setData(int0801Service.search(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.debug("Int0801Controller:search ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> save(@RequestBody Int0801SaveVo request) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			response.setData(int0801Service.save(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Int0801Controller:save ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/find-all")
	@ResponseBody
	public ResponseData<List<IaAuditGftbH>> getauditGftbNoList() {
		ResponseData<List<IaAuditGftbH>> response = new ResponseData<List<IaAuditGftbH>>();
		try {
			response.setData(int0801Service.getauditGftbNoList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Int0801Controller:getauditGftbNoList ", e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/find/dropdown/audit-gftb-no")
	@ResponseBody
	public ResponseData<Int0801SaveVo> findByAuditGftbNo(@RequestBody String auditGftbNo) {
		ResponseData<Int0801SaveVo> response = new ResponseData<Int0801SaveVo>();
		try {
			response.setData(int0801Service.findByAuditGftbNo(auditGftbNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("Int0801Controller:getauditGftbNoList ", e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/export/excel/{auditGftbNo}")
	public void exportExcel(@PathVariable("auditGftbNo") String auditGftbNo, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode(auditGftbNo.replace(",", "-").concat("-").concat("กระดาษทำการตรวจสอบข้อมูลทางบัญชีที่ถูกต้องตามดุลบัญชีปกติ"), "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int0801Service.exportExcel(auditGftbNo.replace(",", "/"));
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}
}
