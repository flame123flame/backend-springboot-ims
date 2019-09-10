package th.go.excise.ims.ia.controller;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.go.excise.ims.ia.persistence.entity.IaRentHouse;
import th.go.excise.ims.ia.service.Int12070101Service;
import th.go.excise.ims.ia.vo.Int12070101SaveFormVo;

@Controller
@RequestMapping("/api/ia/int12/07/01/01")
public class Int12070101Controller {
	private Logger logger = LoggerFactory.getLogger(Int12070101Controller.class);

	@Autowired
	private Int12070101Service int12070101Service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaRentHouse> save(@RequestBody Int12070101SaveFormVo en) {

		ResponseData<IaRentHouse> response = new ResponseData<IaRentHouse>();
		IaRentHouse data = new IaRentHouse();
		try {
			data = int12070101Service.save(en);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save => ", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@GetMapping("/find-by-renhouse/{id}")
	@ResponseBody
	public ResponseData<Int12070101SaveFormVo> findById(@PathVariable("id") long id) {
		ResponseData<Int12070101SaveFormVo> response = new ResponseData<Int12070101SaveFormVo>();
		try {
			response.setData(int12070101Service.findById(id));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("save => ", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@GetMapping("/export/{id}")
	public void exportReport(@PathVariable("id") long id, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("บันทึกคำขอเบิกเงินค่าเช่าบ้าน(แบบ6006)", "UTF-8");
		byte[] outArray = null;
		try {
			outArray = int12070101Service.exportReport(id);
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
