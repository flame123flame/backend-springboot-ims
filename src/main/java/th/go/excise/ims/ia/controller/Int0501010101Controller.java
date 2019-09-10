package th.go.excise.ims.ia.controller;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.TravelEstimatorFormDtl;
import th.go.excise.ims.ia.service.Int0501010101Serevice;

@Controller
@RequestMapping("/api/ia/int05/01/01/01/01")
public class Int0501010101Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0501010101Controller.class);
	
	@Autowired
	private Int0501010101Serevice int0501010101Serevice;
	
	@PostMapping("save/travel-estimator-form-dtl")
	@ResponseBody
	public ResponseData<T> saveDetails(@RequestBody TravelEstimatorFormDtl request) {
		logger.info("savefollowRecommendDtl");
		ResponseData<T> response = new ResponseData<T>();
		try {
			int0501010101Serevice.saveDtl(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
