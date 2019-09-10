package th.go.excise.ims.ia.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaCheckControlRegis;
import th.go.excise.ims.ia.service.Int090303Service;
import th.go.excise.ims.ia.vo.Int0903FormVo;

@Controller
@RequestMapping("/api/ia/int09/03/03")
public class Int090303Controller {

	private Logger logger = LoggerFactory.getLogger(Int090303Controller.class);

	@Autowired
	private Int090303Service int090303Service;

	@PostMapping("/list")
	@ResponseBody
	public ResponseData<List<IaCheckControlRegis>> list(@RequestBody Int0903FormVo form) {
		ResponseData<List<IaCheckControlRegis>> response = new ResponseData<List<IaCheckControlRegis>>();
		try {
			response.setData(int090303Service.list(form));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/budgetTypeDropdown")
	@ResponseBody
	public ResponseData<List<Int0903FormVo>> budgetYearDropdown() {
		ResponseData<List<Int0903FormVo>> response = new ResponseData<List<Int0903FormVo>>();
		try {
			response.setData(int090303Service.budgetTypeDropdown());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/export")
	@ResponseBody
	public void exportData(@ModelAttribute Int0903FormVo formVo, HttpServletResponse response) throws IOException {
		byte[] bytes = int090303Service.exportData(formVo);
		String fileName = URLEncoder.encode("รายการคุมเช็ค", "UTF-8");

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(bytes);
		outStream.flush();
		outStream.close();
	}

}
