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
import th.go.excise.ims.oa.service.Oa0107Service;
import th.go.excise.ims.oa.vo.Oa010106FormVo;
import th.go.excise.ims.oa.vo.Oa0107CustomerVo;
import th.go.excise.ims.oa.vo.Oa0107Vo;

@Controller
@RequestMapping("/api/oa/01/07")
public class Oa0107Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0107Controller.class);

	@Autowired
	private Oa0107Service oa0107Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Oa010106FormVo> filterByCriteria(@RequestBody Oa0107Vo request) {
		DataTableAjax<Oa010106FormVo> response = new DataTableAjax<>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0107Service.filterByCriteria(request, offCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0107Controller::filterByCriteria => ", e);
		}
		return response;
	}

	@GetMapping("/find/customerLicenses/{customerId}/{licenseType}")
	@ResponseBody
	public ResponseData<List<Oa010106FormVo>> findAllName(@PathVariable("customerId") String customerIdStr,
			@PathVariable("licenseType") String licenseType) {
		ResponseData<List<Oa010106FormVo>> responseData = new ResponseData<List<Oa010106FormVo>>();
		List<Oa010106FormVo> data = new ArrayList<Oa010106FormVo>();
		try {
			data = oa0107Service.findCustomerLicenList(customerIdStr, licenseType);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0107Controller::findAllName ", e);
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
			data = oa0107Service.findCustomerLicenAll(licenseIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0107Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find/customers")
	@ResponseBody
	public ResponseData<List<Oa0107CustomerVo>> findCustomers() {
		ResponseData<List<Oa0107CustomerVo>> responseData = new ResponseData<>();
		List<Oa0107CustomerVo> data = new ArrayList<Oa0107CustomerVo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0107Service.findCustomers(offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0107Controller::findCustomers ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find/customer/{customerNo}")
	@ResponseBody
	public ResponseData<Oa010106FormVo> findCustomer(@PathVariable("customerNo") String customerNo) {
		ResponseData<Oa010106FormVo> responseData = new ResponseData<>();
		Oa010106FormVo data = new Oa010106FormVo();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0107Service.findCustomer(customerNo, offCode);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0107Controller::findCustomer ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/customerLicense")
	@ResponseBody
	public ResponseData<Oa010106FormVo> saveAll(@RequestBody Oa010106FormVo form) {
		ResponseData<Oa010106FormVo> responseData = new ResponseData<>();
		Oa010106FormVo data = new Oa010106FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa0107Service.saveCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0107Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/customerLicense")
	@ResponseBody
	public ResponseData<Oa010106FormVo> updateAll(@RequestBody Oa010106FormVo form) {
		ResponseData<Oa010106FormVo> responseData = new ResponseData<>();
		Oa010106FormVo data = new Oa010106FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa0107Service.updateCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0107Controller::updateAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
