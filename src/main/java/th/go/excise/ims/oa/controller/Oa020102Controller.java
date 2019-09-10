package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.service.Oa020102Service;
import th.go.excise.ims.oa.vo.Oa0201001Vo;
import th.go.excise.ims.oa.vo.Oa0201Vo;
import th.go.excise.ims.oa.vo.Oa0206FormVo;

@Controller
@RequestMapping("api/oa/02/01/02")
public class Oa020102Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa020102Controller.class);
	
	@Autowired
	private Oa020102Service oa020102Service;
	
//	@GetMapping("/detail/{id}")
//	@ResponseBody
//	public ResponseData<OaLicensePlan> findDetailById(@PathVariable("id") String idStr) {
//		ResponseData<OaLicensePlan> responseData = new ResponseData<OaLicensePlan>();
//		OaLicensePlan data = new OaLicensePlan();
//		try {
//			data = oa020102Service.findDetailById(idStr);
//			responseData.setData(data);
//			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) { 
//			logger.error("Oa02010608Controller::findDetailById ", e);
//			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
//			responseData.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return responseData;
//	}
	
	@GetMapping("/getLicensePlan/{id}")
	@ResponseBody
	public ResponseData<Oa0201Vo> findLicensePlanById(@PathVariable("id") String idStr) {
		ResponseData<Oa0201Vo> responseData = new ResponseData<Oa0201Vo>();
		Oa0201Vo oa0201 = new Oa0201Vo();
		try {
			oa0201 = oa020102Service.findDetailByPlanID(idStr);
			responseData.setData(oa0201);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::findDetailById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseData<OaLicensePlan> updateById(@RequestBody OaLicensePlan request) {
		ResponseData<OaLicensePlan> responseData = new ResponseData<OaLicensePlan>();
		OaLicensePlan data = new OaLicensePlan();
		try {
			data = oa020102Service.updateById(request);
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
	
	
	@PostMapping("/getLicenseCustomer")
	@ResponseBody
	public DataTableAjax<Oa0201001Vo> filterByCriteria(@RequestBody Oa0206FormVo request) {
		DataTableAjax<Oa0201001Vo> response = new DataTableAjax<>();
		try {
			response = oa020102Service.getLicenseCustomer(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}
	
	
	

}
