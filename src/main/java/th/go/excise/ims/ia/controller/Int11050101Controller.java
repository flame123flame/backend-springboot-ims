package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int11050101Service;
import th.go.excise.ims.ia.vo.Int11050101FormVo;

@Controller
@RequestMapping("/api/ia/int11/05/01/01")
public class Int11050101Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int11050101Controller.class);
	
	@Autowired
	Int11050101Service int11050101Service;

	@PostMapping("/editDetailPerformance")
	@ResponseBody
	public ResponseData<String> editDetailPerformance(@RequestBody Int11050101FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int11050101Service.editDetailPerformance(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int11050101Controller editDetailPerformance : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
}
