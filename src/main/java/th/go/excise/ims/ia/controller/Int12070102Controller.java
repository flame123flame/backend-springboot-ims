package th.go.excise.ims.ia.controller;

import java.net.URLEncoder;
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
import th.go.excise.ims.ia.service.Int12070102Service;
import th.go.excise.ims.ia.vo.HospitalVo;
import th.go.excise.ims.ia.vo.Int1200702HdrVo;

@Controller
@RequestMapping("/api/ia/int12/07/01/02")
public class Int12070102Controller {

	private Logger logger = LoggerFactory.getLogger(Int12070102Controller.class);

	@Autowired
	private Int12070102Service int12070102Service;
	
	@GetMapping("/hospital")
	@ResponseBody
	public ResponseData<List<HospitalVo>> getHospital() {

		ResponseData<List<HospitalVo>> response = new ResponseData<>();
		try {
			response.setData(int12070102Service.getHospital());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int12070102Controller : getHospital ->", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<T> save(@RequestBody Int1200702HdrVo form) {

		ResponseData<T> response = new ResponseData<>();
		try {
			int12070102Service.save(form);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int12070102Controller : save ->", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@GetMapping("/findById/{id}")
	@ResponseBody
	public ResponseData<Int1200702HdrVo> findById(@PathVariable("id") Long id) {

		ResponseData<Int1200702HdrVo> response = new ResponseData<>();
		try {
			response.setData(int12070102Service.findById(id));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int12070102Controller : findById ->", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@GetMapping("/exportReport/{id}")
	public void exportReport(@PathVariable("id") long id, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("บันทึกคำขอเบิกเงินค่าเช่าบ้าน(แบบ7131)", "UTF-8");
		byte[] outArray = null;
		try {
			outArray = int12070102Service.exportReport(id);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("exportReport => ", e.getMessage());
		}
//		response.setContentType("application/octet-stream");
//		response.setContentLength(outArray.length);
//		response.setHeader("Expires:", "0"); // eliminates browser caching
//		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
//		OutputStream outStream = response.getOutputStream();
//		outStream.write(outArray);
//		outStream.flush();
//		outStream.close();
//		
		String filename = String.format("test" + "_%s." + FILE_EXTENSION.PDF,
				DateTimeFormatter.BASIC_ISO_DATE.format(LocalDate.now()));
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", filename));
		response.setContentType("application/octet-stream");

		FileCopyUtils.copy(outArray, response.getOutputStream());
	}
}
