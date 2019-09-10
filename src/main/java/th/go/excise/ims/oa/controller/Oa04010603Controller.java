package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaAlcoholDtl;
import th.go.excise.ims.oa.service.Oa04010603Service;

@Controller
@RequestMapping("api/oa/04/01/06/03")
public class Oa04010603Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa04010603Controller.class);
	
	@Autowired
	private Oa04010603Service oa04010603Service;
	
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<OaAlcoholDtl> findDetailById(@PathVariable("id") String idStr) {
		ResponseData<OaAlcoholDtl> responseData = new ResponseData<OaAlcoholDtl>();
		OaAlcoholDtl data = new OaAlcoholDtl();
		try {
			data = oa04010603Service.findDetailById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa04010603Controller::findDetailById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/save/{id}")
	@ResponseBody
	public ResponseData<OaAlcoholDtl> updateById(@RequestBody OaAlcoholDtl request, @PathVariable("id") String idStr) {
		ResponseData<OaAlcoholDtl> responseData = new ResponseData<OaAlcoholDtl>();
		OaAlcoholDtl data = new OaAlcoholDtl();
		try {
			data = oa04010603Service.updateById(request, idStr);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa04010603Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
