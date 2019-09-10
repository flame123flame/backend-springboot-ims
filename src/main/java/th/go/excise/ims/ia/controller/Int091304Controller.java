package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int091304Service;
import th.go.excise.ims.ia.vo.Int091304SearchVo;
import th.go.excise.ims.ia.vo.Int091304Vo;

@Controller
@RequestMapping("/api/ia/int091304")
public class Int091304Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int091304Controller.class);
	
	@Autowired
	private Int091304Service int091304Service;
	@PostMapping("/filter/budget-year")
	@ResponseBody
	public ResponseData<Int091304Vo> findQuarter(@RequestBody Int091304SearchVo formVo) {
		logger.info("budgetYear: {}",formVo.getBudgetYear());
		
		ResponseData<Int091304Vo> response = new ResponseData<Int091304Vo>();
		try {
			response.setData(int091304Service.filterByBudgetYear(formVo));
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
