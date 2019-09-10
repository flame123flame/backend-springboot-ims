package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;

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
import th.go.excise.ims.ia.persistence.entity.IaPlanHdr;
import th.go.excise.ims.ia.service.Int01Service;
import th.go.excise.ims.ia.vo.Int01Vo;

@Controller
@RequestMapping("/api/ia/int01")
public class Int01Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int01Controller.class);
	
	@Autowired
	private Int01Service int01Service;
	
	@GetMapping("/filter/budget-year/{budgetYear}")
	@ResponseBody
	public ResponseData<Int01Vo> findDataByBudgetYear(@PathVariable("budgetYear") String budgetYear) {
		logger.info("findDataByBudgetYear: {}", budgetYear);
		
		ResponseData<Int01Vo> responseData = new ResponseData<Int01Vo>();
		try {
			responseData.setData(int01Service.findDataByBudgetYear(budgetYear));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("error: ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PutMapping("/update/choice/{planHdrId}/{flag}")
	@ResponseBody
	public ResponseData<IaPlanHdr> findDataByBudgetYear(@PathVariable("planHdrId") BigDecimal planHdrId, @PathVariable("flag") String flag) {
		logger.info("planHdrId: {}", planHdrId, "<---->", "flag: {}", flag);
		
		ResponseData<IaPlanHdr> responseData = new ResponseData<IaPlanHdr>();
		try {
			responseData.setData(int01Service.updateChoice(planHdrId, flag));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("error: ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
}
