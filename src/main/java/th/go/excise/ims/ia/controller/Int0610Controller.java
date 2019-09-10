package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
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
import th.go.excise.ims.ia.persistence.entity.IaAuditIncGfh;
import th.go.excise.ims.ia.service.Int0610Service;
import th.go.excise.ims.ia.vo.Int0610HeaderVo;
import th.go.excise.ims.ia.vo.Int0610SaveVo;
import th.go.excise.ims.ia.vo.Int0610SearchVo;
import th.go.excise.ims.ia.vo.Int0610Vo;

@Controller
@RequestMapping("/api/ia/int06/10")
public class Int0610Controller {
	private Logger logger = LoggerFactory.getLogger(Int0610Controller.class);

	@Autowired
	private Int0610Service int0610Service;

	@PostMapping("/find-tabs")
	@ResponseBody
	public ResponseData<List<Int0610Vo>> findTabs(@RequestBody Int0610SearchVo request) {
		ResponseData<List<Int0610Vo>> response = new ResponseData<List<Int0610Vo>>();
		try {
			response.setData(int0610Service.getTabs(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<T> save(@RequestBody Int0610SaveVo request) {
		ResponseData<T> response = new ResponseData<T>();
		try {
			int0610Service.save(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/get-dropdown/audit-inc-gf-no")
	@ResponseBody
	public ResponseData<List<IaAuditIncGfh>> getAuditIncGfNoDropdown() {
		ResponseData<List<IaAuditIncGfh>> response = new ResponseData<List<IaAuditIncGfh>>();
		try {
			response.setData(int0610Service.getAuditIncGfNoDropdown());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-by/audit-inc-gf-no")
	@ResponseBody
	public ResponseData<Int0610HeaderVo> findByAuditIncGfNo(@RequestBody String auditIncGfNo) {
		ResponseData<Int0610HeaderVo> response = new ResponseData<Int0610HeaderVo>();
		try {
			response.setData(int0610Service.findByAuditIncGfNo(auditIncGfNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/export/{officeCode}/{periodFrom}/{periodTo}")
	public void exportByYear(@PathVariable("officeCode") String officeCode, @PathVariable("periodFrom") String periodFrom, @PathVariable("periodTo") String periodTo, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("EXPORT".concat("-").concat(officeCode), "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int0610Service.export(officeCode, periodFrom, periodTo);
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
