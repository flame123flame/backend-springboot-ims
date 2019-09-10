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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int030407Service;
import th.go.excise.ims.ia.vo.Int030407Vo;

@Controller
@RequestMapping("/api/ia/int03/04/07")
public class Int030407Controller {

	private Logger logger = LoggerFactory.getLogger(Int030407Controller.class);

	@Autowired
	private Int030407Service int030407Service;

	@GetMapping("/year/{budgetYear}/{idConfig}")
	@ResponseBody
	public ResponseData<List<Int030407Vo>> findByYear(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("idConfig") String idConfig) {
		ResponseData<List<Int030407Vo>> response = new ResponseData<List<Int030407Vo>>();
		try {
			List<Int030407Vo> res = int030407Service.findByBudgetYear(budgetYear, idConfig);
			response.setData(res);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030407Controller::findByYear => ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/year/export/{budgetYear}/{idConfig}/{riskHrdPaperName}/{createUserName}/{createLastName}/{createPosition}/{checkUserName}/{checkLastName}/{checkPosition}")
	public void exportByYear(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("idConfig") String idConfigStr, @PathVariable("riskHrdPaperName") String riskHrdPaperName,
			@PathVariable("createUserName") String createUserName,
			@PathVariable("createLastName") String createLastName,
			@PathVariable("createPosition") String createPosition, 
			@PathVariable("checkUserName") String checkUserName,
			@PathVariable("checkLastName") String checkLastName, 
			@PathVariable("checkPosition") String checkPosition,
			HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("สรุปผลปัจจัยเสี่ยงประสิทธิภาพของการจัดเก็บรายได้", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int030407Service.exportInt030407(budgetYear, idConfigStr,riskHrdPaperName,createUserName,createLastName,createPosition,checkUserName,checkLastName,checkPosition);
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
