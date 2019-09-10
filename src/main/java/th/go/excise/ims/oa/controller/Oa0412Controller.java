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
import th.go.excise.ims.oa.service.Oa0412Service;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa040106FormVo;
import th.go.excise.ims.oa.vo.Oa0412Vo;

@Controller
@RequestMapping("/api/oa/04/12")
public class Oa0412Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0412Controller.class);

	@Autowired
	private Oa0412Service oa0412Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Oa020106FormVo> filterByCriteria(@RequestBody Oa0412Vo request) {
		DataTableAjax<Oa020106FormVo> response = new DataTableAjax<>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0412Service.filterByCriteria(request,offCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0412Controller::filterByCriteria => ", e);
		}
		return response;
	}

	@GetMapping("/find/customerLicenses/{customerId}")
	@ResponseBody
	public ResponseData<List<Oa040106FormVo>> findAllName(@PathVariable("customerId") String customerIdStr) {
		ResponseData<List<Oa040106FormVo>> responseData = new ResponseData<List<Oa040106FormVo>>();
		List<Oa040106FormVo> data = new ArrayList<Oa040106FormVo>();
		try {
			data = oa0412Service.findCustomerLicenList(customerIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0412Controller::findAllName ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find/customerLicense/{licenseId}")
	@ResponseBody
	public ResponseData<Oa040106FormVo> findAll(@PathVariable("licenseId") String licenseIdStr) {
		ResponseData<Oa040106FormVo> responseData = new ResponseData<>();
		Oa040106FormVo data = new Oa040106FormVo();
		try {
			
			data = oa0412Service.findCustomerLicenAll(licenseIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0412Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/customerLicense")
	@ResponseBody
	public ResponseData<Oa040106FormVo> saveAll(@RequestBody Oa040106FormVo form) {
		ResponseData<Oa040106FormVo> responseData = new ResponseData<>();
		Oa040106FormVo data = new Oa040106FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa0412Service.saveCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0412Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/customerLicense")
	@ResponseBody
	public ResponseData<Oa040106FormVo> updateAll(@RequestBody Oa040106FormVo form) {
		ResponseData<Oa040106FormVo> responseData = new ResponseData<>();
		Oa040106FormVo data = new Oa040106FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa0412Service.updateCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0412Controller::updateAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
