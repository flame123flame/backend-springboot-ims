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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int1505Service;
import th.go.excise.ims.ia.vo.Int1505FormVo;

@Controller
@RequestMapping("/api/ia/int15/05")
public class Int1505Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int1505Controller.class);
	
	@Autowired
	private Int1505Service int1505Service;
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> save(@RequestBody Int1505FormVo request) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			int1505Service.saveChartOfAcc(request);
			response.setData("SUCCESS");
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<String> delete(@RequestBody Int1505FormVo request) {
		ResponseData<String> response = new ResponseData<>();
		try {	
			int1505Service.delete(request);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1505Controller : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
