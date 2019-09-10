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
import th.go.excise.ims.oa.service.Oa0413Service;
import th.go.excise.ims.oa.vo.Oa041301FormVo;
import th.go.excise.ims.oa.vo.Oa041301Vo;
import th.go.excise.ims.oa.vo.Oa0413ApproveVo;
import th.go.excise.ims.oa.vo.Oa0413Vo;

@Controller
@RequestMapping("/api/oa/04/13")
public class Oa0413Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0413Controller.class);

	@Autowired
	private Oa0413Service oa0413Service;

	@GetMapping("/find/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Oa0413Vo>> findByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Oa0413Vo>> responseData = new ResponseData<List<Oa0413Vo>>();
		List<Oa0413Vo> data = new ArrayList<Oa0413Vo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0413Service.findByBudgetYear(budgetYear, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0413Controller::findByBudgetYear ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findApprove/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Oa0413ApproveVo>> findApproveByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Oa0413ApproveVo>> responseData = new ResponseData<List<Oa0413ApproveVo>>();
		List<Oa0413ApproveVo> data = new ArrayList<Oa0413ApproveVo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0413Service.findApproveByBudgetYear(budgetYear, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0413Controller::findApproveByBudgetYear ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findPlan/{id}")
	@ResponseBody
	public ResponseData<Oa041301Vo> findPlan(@PathVariable("id") String idStr) {
		ResponseData<Oa041301Vo> responseData = new ResponseData<Oa041301Vo>();
		Oa041301Vo data = new Oa041301Vo();
		try {
			data = oa0413Service.findPlan(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0413Controller::findPlan ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update/{id}/{status}")
	@ResponseBody
	public ResponseData<String> update(@PathVariable("id") String idStr, @PathVariable("status") String status, @RequestBody Oa041301FormVo request) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			oa0413Service.updatePlan(idStr, status, request);
			responseData.setData(request.getRemark());
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0413Controller::update ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
