package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;
import th.go.excise.ims.ia.service.Int10Service;
import th.go.excise.ims.ia.vo.Int10Vo;

@Controller
@RequestMapping("/api/ia/int10")
public class Int10Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int10Controller.class);
	
	@Autowired
	private Int10Service int10Service;
	
	@GetMapping("/find/ins-plan/{budgetYear}/{inspectionWork}/{status}")
	@ResponseBody
	public ResponseData<List<Int10Vo>> saveInspectionPlan(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") String inspectionWorkStr, @PathVariable("status") String status) {
		logger.info("FILTER INSPECTION_PLAN");
		
		ResponseData<List<Int10Vo>> response = new ResponseData<List<Int10Vo>>();
		try {
			response.setData(int10Service.findByBudgetYearAndInspectionWork(budgetYear, inspectionWorkStr, status));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/find/ins-plan-params/{id}")
	@ResponseBody
	public ResponseData<List<IaInspectionPlan>> saveInspectionPlanParams(@PathVariable("id") BigDecimal id) {
		
		ResponseData<List<IaInspectionPlan>> response = new ResponseData<List<IaInspectionPlan>>();
		try {
			response.setData(int10Service.findByIdParams(id));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
