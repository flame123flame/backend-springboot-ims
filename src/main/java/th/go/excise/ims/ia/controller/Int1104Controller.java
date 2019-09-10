package th.go.excise.ims.ia.controller;

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
import th.go.excise.ims.ia.service.Int1104Service;
import th.go.excise.ims.ia.vo.Int1104Vo;

@Controller
@RequestMapping("/api/ia/int11/04")
public class Int1104Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int10Controller.class);
	
	@Autowired
	private Int1104Service int1104Service;
	
	@GetMapping("/find/ins-plan/{budgetYear}/{inspectionWork}")
	@ResponseBody
	public ResponseData<List<Int1104Vo>> saveInspectionPlan(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") String inspectionWorkStr) {
		logger.info("FILTER INSPECTION_PLAN");
		
		ResponseData<List<Int1104Vo>> response = new ResponseData<List<Int1104Vo>>();
		try {
			response.setData(int1104Service.findByBudgetYearAndInspectionWork(budgetYear, inspectionWorkStr));
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
