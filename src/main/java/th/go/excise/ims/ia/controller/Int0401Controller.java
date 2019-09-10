package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
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

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;
import th.go.excise.ims.ia.service.Int0401Service;
import th.go.excise.ims.ia.vo.Int0401HeaderVo;
import th.go.excise.ims.ia.vo.Int0401Vo;

@Controller
@RequestMapping("/api/ia/int04/01")
public class Int0401Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int0401Controller.class);

	@Autowired
	private Int0401Service int0401Service;

	@GetMapping("/by/{budgetYear}/{inspectionWork}/{status}")
	@ResponseBody
	public ResponseData<List<Int0401Vo>> findByBudgetYearAndInspectionWork(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") String inspectionWorkStr, @PathVariable("status") String status) {
		ResponseData<List<Int0401Vo>> response = new ResponseData<List<Int0401Vo>>();
		try {
			List<Int0401Vo> data = int0401Service.findByBudgetYearAndInspectionWork(budgetYear, inspectionWorkStr, status);
			response.setData(data);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0401Controller::findByBudgetYearAndInspectionWork => ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/status/by/{budgetYear}/{inspectionWork}/{status}")
	@ResponseBody
	public ResponseData<BigDecimal> findStatusByBudgetYearAndInspectionWork(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") String inspectionWorkStr, @PathVariable("status") String status) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		try {
			BigDecimal data = int0401Service.findStatusByBudgetYearAndInspectionWork(budgetYear, inspectionWorkStr, status);
			response.setData(data);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0401Controller::findStatusByBudgetYearAndInspectionWork => ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/head/by/{budgetYear}/{inspectionWork}")
	@ResponseBody
	public ResponseData<List<Int0401HeaderVo>> findHeadByBudgetYearAndInspectionWork(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") String inspectionWorkStr) {
		ResponseData<List<Int0401HeaderVo>> response = new ResponseData<List<Int0401HeaderVo>>();
		try {
			List<Int0401HeaderVo> data = int0401Service.findHeadByBudgetYearAndInspectionWork(budgetYear, inspectionWorkStr);
			response.setData(data);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0401Controller::findHeadByBudgetYearAndInspectionWork => ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PutMapping("/update/status/{status}")
	@ResponseBody
	public ResponseData<List<Int0401Vo>> updateRowByStatus(@RequestBody List<BigDecimal> ids, @PathVariable("status") String status) {
		ResponseData<List<Int0401Vo>> response = new ResponseData<List<Int0401Vo>>();
		try {
			List<Int0401Vo> data = int0401Service.updateRowByStatus(ids, status);
			response.setData(data);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0401Controller::updateRowByStatus => ", e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/save/inspection-plan")
	@ResponseBody
	public ResponseData<IaInspectionPlan> saveInspectionPlan(@RequestBody List<IaInspectionPlan> request) {
		logger.info("SAVE INSPECTION_PLAN");
		
		ResponseData<IaInspectionPlan> response = new ResponseData<IaInspectionPlan>();
		try {
			int0401Service.saveInspectionPlan(request);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
