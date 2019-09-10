package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int110101Service;
import th.go.excise.ims.ia.vo.Int110101FormVo;
import th.go.excise.ims.ia.vo.Int110101Vo;

@Controller
@RequestMapping("/api/ia/int11/01/01")
public class Int110101Controller {
	
	@Autowired
	private Int110101Service int110101Service;
	
	private Logger logger = LoggerFactory.getLogger(Int110101Controller.class);
		
	@GetMapping("/findConcludeFollowEdit/{id}")
	@ResponseBody
	public ResponseData<List<Int110101Vo>> findConcludeFollowEdit(@PathVariable("id") String id) {
		ResponseData<List<Int110101Vo>> responseData = new ResponseData<List<Int110101Vo>>();
		List<Int110101Vo> data = new ArrayList<Int110101Vo>();
		try {
			data = int110101Service.findConcludeFollowEdit(id);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			logger.error("Int110101Controller findConcludeFollowEdit : ", e);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
		}
		return responseData;
	}
	
	@PostMapping("/editDetails")
	@ResponseBody
	public ResponseData<String> editDetails(@RequestBody Int110101FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int110101Service.editDetails(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int110101Controller editDetails : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
