package th.go.excise.ims.preferences.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int110101FormVo;
import th.go.excise.ims.preferences.service.Ed0201Service;
import th.go.excise.ims.preferences.vo.Ed01Vo;
import th.go.excise.ims.preferences.vo.Ed0201FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Controller
@RequestMapping("/api/ed/ed02/01")
public class Ed0201Controller {

	private Logger logger = LoggerFactory.getLogger(Ed0201Controller.class);
	
	@Autowired
	private Ed0201Service ed0201Service;
	
	@PostMapping("/saveConfigPosition")
	@ResponseBody
	public ResponseData<String> saveConfigPosition(@RequestBody Ed0201FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			ed0201Service.saveConfigPosition(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed0201Controller saveConfigPosition : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/getConfigEdit/{edPersonSeq}")
	@ResponseBody
	public ResponseData<List<Ed02Vo>> getConfigEdit(@PathVariable("edPersonSeq") BigDecimal edPersonSeq) {
		ResponseData<List<Ed02Vo>> responseData = new ResponseData<List<Ed02Vo>>();
		List<Ed02Vo> data = new ArrayList<Ed02Vo>();
		try {
			data = ed0201Service.getConfigEdit(edPersonSeq);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed0201Controller getConfigEdit : ", e);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
		}
		return responseData;
	}
	
	@PostMapping("/updateConfigposition")
	@ResponseBody
	public ResponseData<String> updateConfigposition(@RequestBody Ed0201FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			ed0201Service.updateConfigposition(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Ed0201Controller updateConfigposition : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	

	
	
}
