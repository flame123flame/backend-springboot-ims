package th.go.excise.ims.ia.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.service.Int090102Service;
import th.go.excise.ims.ia.vo.IaEmpWorkingDtlSaveVo;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrFormVo;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;

@Controller
@RequestMapping("/api/ia/int090102")
public class Int090102Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int090102Controller.class);
	
	private Gson gson = new Gson();
	
	@Autowired
	private Int090102Service int090102Service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<T> save(@RequestBody IaEmpWorkingDtlSaveVo request) {
		logger.info("save -> Int090102");
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int090102Service.save(request);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/get-by-month")
	@ResponseBody
	public ResponseData<List<IaEmpWorkingDtl>> getByMonth(@RequestBody IaEmpWorkingDtlSaveVo request) {
		logger.info("get-by-month -> Int090102");
		ResponseData<List<IaEmpWorkingDtl>> responseData = new ResponseData<List<IaEmpWorkingDtl>>();
		try {
			responseData.setData(int090102Service.getByMonth(request));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<T> delete(@RequestBody IaEmpWorkingDtlSaveVo request) {
		logger.info("delete -> Int090102");
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int090102Service.delete(request);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/saveHdr")
	@ResponseBody
	public ResponseData<T> saveHdr(@RequestBody IaEmpWorkingHdrVo request) {
		logger.info("saveHdr -> Int090102");
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int090102Service.saveHdr(request);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/get-by-month-hdr")
	@ResponseBody
	public ResponseData<IaEmpWorkingHdrVo> getByMonth(@RequestBody IaEmpWorkingHdrFormVo request) {
		logger.info("get-by-month-hdr -> Int090102");
		ResponseData<IaEmpWorkingHdrVo> responseData = new ResponseData<IaEmpWorkingHdrVo>();
		try {
			responseData.setData(int090102Service.getByMonthHdr(request));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/pdf/emp-working")
	public void generatePdfReportEmpWorking(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws Exception {
		logger.info("generatePdfReportEmpWorking");
		
		IaEmpWorkingHdrVo formVo = gson.fromJson(reportJsonBean.getJson(), IaEmpWorkingHdrVo.class);
		byte[] reportFile = int090102Service.generateReport(formVo);

		String filename = String.format(REPORT_NAME.IA_EMP_WORKING + "_%s." + FILE_EXTENSION.PDF, DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(reportFile, response.getOutputStream());
	}
	
	@PostMapping("/get-holiday")
	@ResponseBody
	public ResponseData<List<ExciseHoliday>> getHoliday(@RequestBody IaEmpWorkingDtlSaveVo request) {
		logger.info("get-holiday -> Int090102");
		ResponseData<List<ExciseHoliday>> responseData = new ResponseData<List<ExciseHoliday>>();
		try {
			responseData.setData(int090102Service.getHoliday(request.getWorkingDate()));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
