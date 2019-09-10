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
import th.go.excise.ims.ia.service.Int1101Service;
import th.go.excise.ims.ia.vo.Int1101FormVo;
import th.go.excise.ims.ia.vo.Int1101Vo;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Controller
@RequestMapping("/api/ia/int11/01")
public class Int1101Controller {
	
	@Autowired
	private Int1101Service int1101Service;
	
	private Logger logger = LoggerFactory.getLogger(Int11Controller.class);
	
	@GetMapping("/findConcludeFollowHdr/{id}")
	@ResponseBody
	public ResponseData<Int11Vo> findConcludeFollowHdr(@PathVariable("id") String id) {
		ResponseData<Int11Vo> responseData = new ResponseData<Int11Vo>();
		Int11Vo data = new Int11Vo();
		try {
			data = int1101Service.list(id);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller findConcludeFollowHdr : ", e);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
		}
		return responseData;
	}
	
	@GetMapping("/findConcludeFollowHdrDetailList/{id}")
	@ResponseBody
	public ResponseData<List<Int1101Vo>> findConcludeFollowHdrDetailList(@PathVariable("id") String id) {
		ResponseData<List<Int1101Vo>> responseData = new ResponseData<List<Int1101Vo>>();
		List<Int1101Vo> data = new ArrayList<Int1101Vo>();
		try {
			data = int1101Service.findConcludeFollowHdrDetailList(id);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller findConcludeFollowHdrDetailList : ", e);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
		}
		return responseData;
	}
	
	@PostMapping("/updateSentStatus")
	@ResponseBody
	public ResponseData<String> updateSentStatus(@RequestBody Int1101FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int1101Service.updateSentStatus(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
}
