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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.service.Oa020102Service;
import th.go.excise.ims.oa.service.Oa0201Service;
import th.go.excise.ims.oa.vo.Oa020103Vo;
import th.go.excise.ims.oa.vo.Oa0201FromVo;

@Controller
@RequestMapping("api/oa/02/01")
public class Oa0201Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa0201Controller.class);
	
	@Autowired
	private Oa0201Service oa0201Service;
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseData<Oa0201FromVo> save(@RequestBody Oa0201FromVo request) {
		ResponseData<Oa0201FromVo> responseData = new ResponseData<Oa0201FromVo>();
		Oa0201FromVo data = new Oa0201FromVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0201Service.saveOAPlan(request,offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update")
	@ResponseBody
	public ResponseData<Oa0201FromVo> updateById(@RequestBody Oa0201FromVo request) {
		ResponseData<Oa0201FromVo> responseData = new ResponseData<Oa0201FromVo>();
		Oa0201FromVo data = new Oa0201FromVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0201Service.updateOAPlan(request,offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findOaPlan/{planID}")
	@ResponseBody
	public ResponseData<Oa0201FromVo> findOaplan(@PathVariable("planID") String planID) {
		ResponseData<Oa0201FromVo> responseData = new ResponseData<Oa0201FromVo>();
		Oa0201FromVo data = new Oa0201FromVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0201Service.findOaplan(planID, offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findOaPlanHyd/{planID}")
	@ResponseBody
	public ResponseData<Oa0201FromVo> findOaplanHyd(@PathVariable("planID") String planID) {
		ResponseData<Oa0201FromVo> responseData = new ResponseData<Oa0201FromVo>();
		Oa0201FromVo data = new Oa0201FromVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0201Service.findOaplanHydro(planID, offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	
	@GetMapping("/findApprover")
	@ResponseBody
	public ResponseData<List<Oa020103Vo>> findApprover() {
		ResponseData<List<Oa020103Vo>> responseData = new ResponseData<List<Oa020103Vo>>();
		List<Oa020103Vo> data = new ArrayList<Oa020103Vo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0201Service.findApprover(offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@GetMapping("/sendApprove/{planID}")
	@ResponseBody
	public ResponseData<Oa0201FromVo> sendApprove(@PathVariable("planID") String planID) {
		ResponseData<Oa0201FromVo> responseData = new ResponseData<Oa0201FromVo>();
		Oa0201FromVo data = new Oa0201FromVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0201Service.sendApprove(planID);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	

}
