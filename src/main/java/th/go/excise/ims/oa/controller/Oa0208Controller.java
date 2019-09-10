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
import th.go.excise.ims.oa.service.Oa0208Service;
import th.go.excise.ims.oa.vo.Oa020801FormVo;
import th.go.excise.ims.oa.vo.Oa020801Vo;
import th.go.excise.ims.oa.vo.Oa0208ApproveVo;
import th.go.excise.ims.oa.vo.Oa0208Vo;

@Controller
@RequestMapping("/api/oa/02/08")
public class Oa0208Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0208Controller.class);

	@Autowired
	private Oa0208Service oa0208Service;

	@GetMapping("/find/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Oa0208Vo>> findByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Oa0208Vo>> responseData = new ResponseData<List<Oa0208Vo>>();
		List<Oa0208Vo> data = new ArrayList<Oa0208Vo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0208Service.findByBudgetYear(budgetYear, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0208Controller::findByBudgetYear ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findApprove/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Oa0208ApproveVo>> findApproveByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Oa0208ApproveVo>> responseData = new ResponseData<List<Oa0208ApproveVo>>();
		List<Oa0208ApproveVo> data = new ArrayList<Oa0208ApproveVo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0208Service.findApproveByBudgetYear(budgetYear, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0208Controller::findApproveByBudgetYear ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findPlan/{id}")
	@ResponseBody
	public ResponseData<Oa020801Vo> findPlan(@PathVariable("id") String idStr) {
		ResponseData<Oa020801Vo> responseData = new ResponseData<Oa020801Vo>();
		Oa020801Vo data = new Oa020801Vo();
		try {
			data = oa0208Service.findPlan(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0208Controller::findPlan ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update/{id}/{status}")
	@ResponseBody
	public ResponseData<String> update(@PathVariable("id") String idStr, @PathVariable("status") String status, @RequestBody Oa020801FormVo request) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			oa0208Service.updatePlan(idStr, status, request);
			responseData.setData(request.getRemark());
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0208Controller::update ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
