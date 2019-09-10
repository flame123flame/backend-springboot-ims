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
import th.go.excise.ims.oa.persistence.entity.OaAlcoholDistil;
import th.go.excise.ims.oa.service.Oa04010606Service;
import th.go.excise.ims.oa.vo.Oa04010606FormVo;

@Controller
@RequestMapping("api/oa/04/01/06/06")
public class Oa04010606Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa04010606Controller.class);
	
	@Autowired
	private Oa04010606Service oa04010606Service;
	
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<List<OaAlcoholDistil>> findAll(@PathVariable("id") String oaAlcoholIdStr) {
		ResponseData<List<OaAlcoholDistil>> responseData = new ResponseData<List<OaAlcoholDistil>>();
		List<OaAlcoholDistil> data = new ArrayList<OaAlcoholDistil>();
		try {
			data = oa04010606Service.findAll(oaAlcoholIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa04010606Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseData<Oa04010606FormVo> updateDistil(@RequestBody Oa04010606FormVo request) {
		ResponseData<Oa04010606FormVo> responseData = new ResponseData<Oa04010606FormVo>();
		Oa04010606FormVo data = new Oa04010606FormVo();
		try {
			data = oa04010606Service.updateDistil(request);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa04010606Controller::updateDistil ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
