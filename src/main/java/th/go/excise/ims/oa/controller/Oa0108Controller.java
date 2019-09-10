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
import th.go.excise.ims.oa.service.Oa0108Service;
import th.go.excise.ims.oa.vo.Oa010801FormVo;
import th.go.excise.ims.oa.vo.Oa010801Vo;
import th.go.excise.ims.oa.vo.Oa0108ApproveVo;
import th.go.excise.ims.oa.vo.Oa0108Vo;

@Controller
@RequestMapping("/api/oa/01/08")
public class Oa0108Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0108Controller.class);

	@Autowired
	private Oa0108Service oa0108Service;

	@GetMapping("/find/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Oa0108Vo>> findByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Oa0108Vo>> responseData = new ResponseData<List<Oa0108Vo>>();
		List<Oa0108Vo> data = new ArrayList<Oa0108Vo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0108Service.findByBudgetYear(budgetYear, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0108Controller::findByBudgetYear ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findApprove/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Oa0108ApproveVo>> findApproveByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Oa0108ApproveVo>> responseData = new ResponseData<List<Oa0108ApproveVo>>();
		List<Oa0108ApproveVo> data = new ArrayList<Oa0108ApproveVo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0108Service.findApproveByBudgetYear(budgetYear, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0108Controller::findApproveByBudgetYear ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findPlan/{id}")
	@ResponseBody
	public ResponseData<Oa010801Vo> findPlan(@PathVariable("id") String idStr) {
		ResponseData<Oa010801Vo> responseData = new ResponseData<Oa010801Vo>();
		Oa010801Vo data = new Oa010801Vo();
		try {
			data = oa0108Service.findPlan(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0108Controller::findPlan ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update/{id}/{status}")
	@ResponseBody
	public ResponseData<String> update(@PathVariable("id") String idStr, @PathVariable("status") String status, @RequestBody Oa010801FormVo request) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			oa0108Service.updatePlan(idStr, status, request);
			responseData.setData(request.getRemark());
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0108Controller::update ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
