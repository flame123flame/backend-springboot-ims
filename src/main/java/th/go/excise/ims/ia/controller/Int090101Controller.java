package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import th.go.excise.ims.ia.service.Int090101Service;
import th.go.excise.ims.ia.service.Int12040101Service;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;

@Controller
@RequestMapping("/api/ia/int09/01/01")
public class Int090101Controller {

	@Autowired
	private Int090101Service int090101Service;

	private static final Logger logger = LoggerFactory.getLogger(Int12040101Service.class);

	@PostMapping("/find-compare")
	@ResponseBody
	public ResponseData<List<Int090101Vo>> findCompare(@RequestBody Int090101CompareFormVo form) {
		ResponseData<List<Int090101Vo>> responseData = new ResponseData<List<Int090101Vo>>();
		List<Int090101Vo> data = new ArrayList<Int090101Vo>();
		try {
			data = int090101Service.findCompare(form);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int090101Controller::findCompare ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

//	/{officeCode}/{monthStart}/{yearStart}/monthEnd}/{yearEnd}
	@GetMapping("/year/export-{officeCode}-{yearStart}-{monthStart}-{yearEnd}-{monthEnd}")
	public void exportByYear(@PathVariable("officeCode") String officeCode, @PathVariable("yearStart") String yearStart,
			@PathVariable("monthStart") String monthStart, @PathVariable("yearEnd") String yearEnd,
			@PathVariable("monthEnd") String monthEnd, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("ตรวจสอบข้อมูลค่าใช้จ่ายพื้นที่:" + officeCode + "งวดที่" + monthStart + "/"
				+ yearStart + "ถึง" + monthEnd + "/" + yearEnd, "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = this.int090101Service.export(officeCode, yearStart, monthStart, yearEnd,
				monthEnd);
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
