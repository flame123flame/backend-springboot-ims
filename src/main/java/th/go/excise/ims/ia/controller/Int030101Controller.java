package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.service.Int030101Service;
import th.go.excise.ims.ia.service.UpdateStatusRiskFactorsService;
import th.go.excise.ims.ia.vo.Int030101FormVo;
import th.go.excise.ims.ia.vo.Int030101Vo;

@Controller
@RequestMapping("/api/ia/int03/01/01")
public class Int030101Controller {
	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);
	@Autowired
	private Int030101Service int030101Service;
	
	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	@PostMapping("/saveFactors")
	@ResponseBody
	public ResponseData<Int030101Vo> saveFactors(@RequestBody Int030101FormVo form) {
		ResponseData<Int030101Vo> response = new ResponseData<Int030101Vo>();

		// UserLoginUtils.getCurrentUserBean().getUserId()
		try {
			Int030101Vo res = int030101Service.saveFactors(form);
			updateStatusRiskFactorsService.updateStatusAddIaRiskFactorsMaster(form.getBudgetYear(), form.getInspectionWork());
			response.setData(res);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
//			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/dowloadTemplate")
	public void export(@RequestParam String dataJson, HttpServletResponse response) throws Exception {
		try {
			// set fileName
			String fileName = URLEncoder.encode("โครงการ", "UTF-8");

			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = int030101Service.exportInt030101();
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/octet-stream");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			logger.error("Int030102Controller dowloadTemplate : ", e);
		}
	}

	@PostMapping("/dowloadTemplate2")
	public void export2(@RequestParam String dataJson, HttpServletResponse response) throws Exception {
		try {
			// set fileName
			String fileName = URLEncoder.encode("ระบบสารสนเทศฯ", "UTF-8");

			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = int030101Service.exportInt0301012();
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/octet-stream");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			logger.error("Int030102Controller dowloadTemplate : ", e);
		}
	}

	@PostMapping("/dowloadTemplate3")
	public void export3(@RequestParam String dataJson, HttpServletResponse response) throws Exception {
		try {
			// set fileName
			String fileName = URLEncoder.encode("พื้นที่", "UTF-8");

			// write it as an excel attachment
			ByteArrayOutputStream outByteStream = int030101Service.exportInt0301013();
			byte[] outArray = outByteStream.toByteArray();
			response.setContentType("application/octet-stream");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		} catch (Exception e) {
			logger.error("Int030102Controller dowloadTemplate : ", e);
		}
	}

	@PostMapping("/upload")
	@ResponseBody
	public ResponseData<List<IaRiskFactorsData>> upload(@ModelAttribute Int030101FormVo form)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		ResponseData<List<IaRiskFactorsData>> responseData = new ResponseData<List<IaRiskFactorsData>>();
		try {
			List<IaRiskFactorsData> res = int030101Service.readFileExcel(form);
			responseData.setData(res);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
//			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller upload : ", e);
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/saveFactorsData")
	@ResponseBody
	public ResponseData<String> saveFactorsData(@RequestBody Int030101FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			int030101Service.saveFactorsData(form);
			response.setData("บันทึกสำเร็จ	  ");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
//			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int030101Controller saveFactorsData : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/configFactorsDataList")
	@ResponseBody
	public ResponseData<Int030101Vo> configFactorsDataList(@RequestBody Int030101FormVo form) {
		ResponseData<Int030101Vo> response = new ResponseData<Int030101Vo>();
		try {

			response.setData(int030101Service.configFactorsDataList(form));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
//			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int030102Controller configFactorsDataList : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}

