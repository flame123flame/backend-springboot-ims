package th.go.excise.ims.oa.controller;

import java.util.ArrayList;
import java.util.List;

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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa0207Service;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa0207CustomerVo;
import th.go.excise.ims.oa.vo.Oa0207Vo;

@Controller
@RequestMapping("/api/oa/02/07")
public class Oa0207Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0207Controller.class);

	@Autowired
	private Oa0207Service oa0207Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Oa020106FormVo> filterByCriteria(@RequestBody Oa0207Vo request) {
		DataTableAjax<Oa020106FormVo> response = new DataTableAjax<>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0207Service.filterByCriteria(request, offCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}

	@GetMapping("/find/customerLicenses/{customerId}/{licenseType}")
	@ResponseBody
	public ResponseData<List<Oa020106FormVo>> findAllName(@PathVariable("customerId") String customerIdStr,
			@PathVariable("licenseType") String licenseType) {
		ResponseData<List<Oa020106FormVo>> responseData = new ResponseData<List<Oa020106FormVo>>();
		List<Oa020106FormVo> data = new ArrayList<Oa020106FormVo>();
		try {
			data = oa0207Service.findCustomerLicenList(customerIdStr, licenseType);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0207Controller::findAllName ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find/customerLicense/{licenseId}")
	@ResponseBody
	public ResponseData<Oa020106FormVo> findAll(@PathVariable("licenseId") String licenseIdStr) {
		ResponseData<Oa020106FormVo> responseData = new ResponseData<>();
		Oa020106FormVo data = new Oa020106FormVo();
		try {
			data = oa0207Service.findCustomerLicenAll(licenseIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0207Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/find/customers")
	@ResponseBody
	public ResponseData<List<Oa0207CustomerVo>> findCustomers() {
		ResponseData<List<Oa0207CustomerVo>> responseData = new ResponseData<>();
		List<Oa0207CustomerVo> data = new ArrayList<Oa0207CustomerVo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0207Service.findCustomers(offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0207Controller::findCustomers ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/find/customer/{customerNo}")
	@ResponseBody
	public ResponseData<Oa020106FormVo> findCustomer(@PathVariable("customerNo") String customerNo) {
		ResponseData<Oa020106FormVo> responseData = new ResponseData<>();
		Oa020106FormVo data = new Oa020106FormVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0207Service.findCustomer(customerNo, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0207Controller::findCustomer ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/customerLicense")
	@ResponseBody
	public ResponseData<Oa020106FormVo> saveAll(@RequestBody Oa020106FormVo form) {
		ResponseData<Oa020106FormVo> responseData = new ResponseData<>();
		Oa020106FormVo data = new Oa020106FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa0207Service.saveCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0207Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/customerLicense")
	@ResponseBody
	public ResponseData<Oa020106FormVo> updateAll(@RequestBody Oa020106FormVo form) {
		ResponseData<Oa020106FormVo> responseData = new ResponseData<>();
		Oa020106FormVo data = new Oa020106FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa0207Service.updateCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0207Controller::updateAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
