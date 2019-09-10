package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int0101Service;
import th.go.excise.ims.ia.vo.Int0101FormVo;
import th.go.excise.ims.ia.vo.Int0101PlanDayVo;

@Controller
@RequestMapping("/api/ia/int0101")
public class Int0101Controller {
	
private static final Logger logger = LoggerFactory.getLogger(Int0101Controller.class);
	
	@Autowired
	private Int0101Service int0101Service;

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<T> save(@RequestBody Int0101FormVo request) {
		logger.info("save -> Int0101");
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int0101Service.save(request);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/find/by-id-dtl/{idDtl}")
	@ResponseBody
	public ResponseData<List<Int0101PlanDayVo>> findDataDtlAndAtc(@PathVariable("idDtl") BigDecimal idDtl) {
		logger.info("findDataDtlAndAtc {}", idDtl);
		
		ResponseData<List<Int0101PlanDayVo>> responseData = new ResponseData<List<Int0101PlanDayVo>>();
		try {
			responseData.setData(int0101Service.findDataDtlAndAct(idDtl));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
