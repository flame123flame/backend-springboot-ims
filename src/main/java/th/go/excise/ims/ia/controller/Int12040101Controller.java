package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.service.Int12040101Service;
import th.go.excise.ims.ia.vo.Int12040101FindVo;
import th.go.excise.ims.ia.vo.Int12040101SaveFormVo;
import th.go.excise.ims.ia.vo.Int12040101ValidateSearchFormVo;

@Controller
@RequestMapping("/api/ia/int02/04/01/01")
public class Int12040101Controller {
	@Autowired
	private Int12040101Service int12040101Service;

	private static final Logger logger = LoggerFactory.getLogger(Int12040101Service.class);

	@GetMapping("/getChartOfAcc")
	@ResponseBody
	public ResponseData<List<IaChartOfAcc>> findAllQtnSide() {
		ResponseData<List<IaChartOfAcc>> responseData = new ResponseData<List<IaChartOfAcc>>();
		List<IaChartOfAcc> data = new ArrayList<>();
		try {
			data = int12040101Service.findAll();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int12040101Controller::getChartOfAcc ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/saveExpenses")
	@ResponseBody
	public ResponseData<List<IaExpenses>> saveExpenses(@RequestBody Int12040101SaveFormVo expenses) {
		ResponseData<List<IaExpenses>> responseData = new ResponseData<List<IaExpenses>>();
//		List<IaExpenses> data = new ArrayList<>();
		try {
			int12040101Service.saveExpenses(expenses);
//			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int12040101Controller::saveExpenses ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/findExpensesById/{id}")
	@ResponseBody
	public ResponseData<Int12040101FindVo> findByYearByCoa(@PathVariable("id") BigDecimal id) {
		ResponseData<Int12040101FindVo> responseData = new ResponseData<Int12040101FindVo>();
		Int12040101FindVo data = new Int12040101FindVo();

		try {
			data = int12040101Service.findExpensesById(id);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int12040101Controller::findExpensesById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/findValidate")
	@ResponseBody
	public ResponseData<Int12040101FindVo> findValidate(@RequestBody Int12040101ValidateSearchFormVo formReq) {
		ResponseData<Int12040101FindVo> responseData = new ResponseData<>();

		try {
			responseData.setData(int12040101Service.findValidate(formReq));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int12040101Controller::findExpensesById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
