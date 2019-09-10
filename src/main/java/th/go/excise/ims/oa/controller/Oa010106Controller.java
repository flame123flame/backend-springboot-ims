package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicen;
import th.go.excise.ims.oa.service.Oa010106Service;
import th.go.excise.ims.oa.vo.Oa010106ButtonVo;
import th.go.excise.ims.oa.vo.Oa010106FormVo;

@Controller
@RequestMapping("api/oa/01/01/06")
public class Oa010106Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa010106Controller.class);

	@Autowired
	private Oa010106Service oa010106Service;

	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<Oa010106ButtonVo> findButtonById(@PathVariable("id") String idStr) {
		ResponseData<Oa010106ButtonVo> responseData = new ResponseData<Oa010106ButtonVo>();
		Oa010106ButtonVo data = new Oa010106ButtonVo();
		try {
			data = oa010106Service.findButtonById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa010106Controller::findButtonById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/customers/{id}")
	@ResponseBody
	public ResponseData<OaHydCustomerLicen> findById(@PathVariable("id") String idStr) {
		ResponseData<OaHydCustomerLicen> responseData = new ResponseData<>();
		OaHydCustomerLicen data = new OaHydCustomerLicen();
		try {
			data = oa010106Service.findById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa010106Controller::findById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/find/customerLicense/{licenseId}")
	@ResponseBody 
	public ResponseData<Oa010106FormVo> findAll(@PathVariable("licenseId") String licenseIdStr) {
		ResponseData<Oa010106FormVo> responseData = new ResponseData<>();
		Oa010106FormVo data = new Oa010106FormVo();
		try {
			data = oa010106Service.findCustomerLicenAll(licenseIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa010106Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/complete/license/{licenseId}")
	@ResponseBody
	public ResponseData<?> completeLicense(@PathVariable("licenseId") String licenseId) {
		ResponseData<?> responseData = new ResponseData<>();
		try {
			oa010106Service.completeLicense(licenseId);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa010106Controller::completeLicense ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
