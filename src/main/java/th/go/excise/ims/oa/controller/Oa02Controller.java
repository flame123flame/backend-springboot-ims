package th.go.excise.ims.oa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa02Service;
import th.go.excise.ims.oa.vo.Oa02Vo;

@Controller
@RequestMapping("api/oa/02")
public class Oa02Controller {
	
	@Autowired
	private Oa02Service oa02Service;
	
	@GetMapping("/findPlan/{year}")
	@ResponseBody
	public ResponseData<Oa02Vo> findOaplanLubbyYear(@PathVariable("year") String year) {
		ResponseData<Oa02Vo> responseData = new ResponseData<Oa02Vo>();
		Oa02Vo data = new Oa02Vo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa02Service.findOaPlanByYear(year,offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
//			logger.error("Oa02010608Controller::updateById ", e)
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findPlanHydro/{year}")
	@ResponseBody
	public ResponseData<Oa02Vo> findOaplanHydrobyYear(@PathVariable("year") String year) {
		ResponseData<Oa02Vo> responseData = new ResponseData<Oa02Vo>();
		Oa02Vo data = new Oa02Vo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa02Service.findOaPlanHydroByYear(year,offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
//			logger.error("Oa02010608Controller::updateById ", e)
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/findPlanACH/{year}")
	@ResponseBody
	public ResponseData<Oa02Vo> findOaplanACHYear(@PathVariable("year") String year) {
		ResponseData<Oa02Vo> responseData = new ResponseData<Oa02Vo>();
		Oa02Vo data = new Oa02Vo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa02Service.findOaPlanACHByYear(year,offCode);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
//			logger.error("Oa02010608Controller::updateById ", e)
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
