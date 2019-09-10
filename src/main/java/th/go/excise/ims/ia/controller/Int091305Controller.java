package th.go.excise.ims.ia.controller;

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
import th.go.excise.ims.ia.service.Int091305Service;
import th.go.excise.ims.ia.vo.Int091305Vo;

@Controller
@RequestMapping("/api/ia/int091305")
public class Int091305Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int091305Controller.class);

	@Autowired
	private Int091305Service int091305Service;

	@GetMapping("/get/by-ubillType/{ubillType}/{budgetYear}/{quarterFlag}")
	@ResponseBody
	public ResponseData<Int091305Vo> findQuarter(@PathVariable("ubillType") String ubillType,
			@PathVariable("budgetYear") String budgetYear, @PathVariable("quarterFlag") String quarterFlag) {
		logger.info("ubillType: {}", ubillType);
		logger.info("budgetYear: {}", budgetYear);
		logger.info("quarterFlag: {}", quarterFlag);

		ResponseData<Int091305Vo> response = new ResponseData<Int091305Vo>();
		try {
			response.setData(int091305Service.getDataByUbillType(ubillType, budgetYear, quarterFlag));
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
