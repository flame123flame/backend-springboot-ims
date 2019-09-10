package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.service.Int1502Service;
import th.go.excise.ims.ia.vo.IaChartAndIncVo;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501SaveVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.ia.vo.Int1502FormVo;
import th.go.excise.ims.ia.vo.Int1503FormVo;
import th.go.excise.ims.preferences.vo.ExciseIncMast;

@Controller
@RequestMapping("/api/ia/int15/02")
public class Int1502Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int1502Controller.class);
	
	@Autowired
	private Int1502Service int1502Service;
	
	@GetMapping("/get-dropdown-coa-code")
	@ResponseBody
	public ResponseData<List<IaChartOfAcc>> getDropdownCoaCode() {
		ResponseData<List<IaChartOfAcc>> response = new ResponseData<List<IaChartOfAcc>>();
		try {
			response.setData(int1502Service.getDropdownCoaCode());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/get-dropdown-inc-code")
	@ResponseBody
	public ResponseData<List<ExciseIncMast>> getDropdownIncCode() {
		ResponseData<List<ExciseIncMast>> response = new ResponseData<List<ExciseIncMast>>();
		try {
			response.setData(ApplicationCache.getExciseIncMastList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> save(@RequestBody Int1502FormVo request) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			int1502Service.saveChartAndInc(request);
			response.setData("SUCCESS");
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<IaChartAndIncVo> listData() {
		DataTableAjax<IaChartAndIncVo> response = new DataTableAjax<IaChartAndIncVo>();
		List<IaChartAndIncVo> dataList = new ArrayList<IaChartAndIncVo>();
		try {
			dataList = int1502Service.listData();
			response.setData(dataList);
		} catch (Exception e) {
			logger.error("Int1502Controller : ", e);
		}
		return response;
	}
	
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<String> delete(@RequestBody Int1502FormVo request) {
		ResponseData<String> response = new ResponseData<>();
		try {	
			int1502Service.delete(request);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1503Controller : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
	


}
