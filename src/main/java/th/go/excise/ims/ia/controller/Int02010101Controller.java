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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int02010101Service;
import th.go.excise.ims.ia.vo.Int02010101FormVo;
import th.go.excise.ims.ia.vo.Int02010101Vo;

@Controller
@RequestMapping("/api/ia/int02/01/01/01")
public class Int02010101Controller {

	private static final Logger logger = LoggerFactory.getLogger(Int02010101Controller.class);

	@Autowired
	private Int02010101Service int02010101Service;

	@GetMapping("/by/side/{idSide}")
	@ResponseBody
	public ResponseData<List<Int02010101Vo>> findByIdHead(@PathVariable("idSide") String idSideStr) {
		ResponseData<List<Int02010101Vo>> responseData = new ResponseData<List<Int02010101Vo>>();
		List<Int02010101Vo> data = new ArrayList<>();
		try {
			data = int02010101Service.findByIdSide(idSideStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int02010101Controller::findByIdHead ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/side/all")
	@ResponseBody
	public ResponseData<Int02010101FormVo> saveAll(@RequestBody Int02010101FormVo form) {
		ResponseData<Int02010101FormVo> responseData = new ResponseData<>();
		Int02010101FormVo data = new Int02010101FormVo();
		try {
			data = int02010101Service.saveAll(form);
			int02010101Service.updateStatusIaQuestionnaireAutomatic(data.getIdHead());
			
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int02010101Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
