package th.go.excise.ims.oa.controller;

import java.util.ArrayList;
import java.util.List;

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
import th.go.excise.ims.oa.persistence.entity.OaAlcoholPacking;
import th.go.excise.ims.oa.service.Oa04010607Service;
import th.go.excise.ims.oa.vo.Oa04010607FormVo;

@Controller
@RequestMapping("api/oa/04/01/06/07")
public class Oa04010607Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa04010607Controller.class);
	
	@Autowired
	private Oa04010607Service oa04010607Service;
	
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<List<OaAlcoholPacking>> findAll(@PathVariable("id") String oaAlcoholIdStr) {
		ResponseData<List<OaAlcoholPacking>> responseData = new ResponseData<List<OaAlcoholPacking>>();
		List<OaAlcoholPacking> data = new ArrayList<OaAlcoholPacking>();
		try {
			data = oa04010607Service.findAll(oaAlcoholIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa04010607Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseData<Oa04010607FormVo> updatePacking(@RequestBody Oa04010607FormVo request) {
		ResponseData<Oa04010607FormVo> responseData = new ResponseData<Oa04010607FormVo>();
		Oa04010607FormVo data = new Oa04010607FormVo();
		try {
			data = oa04010607Service.updatePacking(request);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa04010607Controller::updatePacking ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
