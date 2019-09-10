package th.go.excise.ims.ia.controller;

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
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.service.Int0802Service;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;

@Controller
@RequestMapping("/api/ia/int08/02")
public class Int0802Controller {
	private Logger logger = LoggerFactory.getLogger(Int0802Controller.class);

	@Autowired
	private Int0802Service int0802Service;

	@GetMapping("/get-range-period/{gfDisburseUnit}")
	@ResponseBody
	public ResponseData<List<IaGftrialBalance>> getRangePeriod(@PathVariable("gfDisburseUnit") String gfDisburseUnit) {
		logger.debug("gfDisburseUnit: {}", gfDisburseUnit);
		ResponseData<List<IaGftrialBalance>> response = new ResponseData<List<IaGftrialBalance>>();
		try {
			response.setData(int0802Service.getRangePeriod(gfDisburseUnit));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/search-tab1")
	@ResponseBody
	public ResponseData<List<Int0802Vo>> searchTab1(@RequestBody Int0802SearchVo reqeust) {
		ResponseData<List<Int0802Vo>> response = new ResponseData<List<Int0802Vo>>();
		try {
			response.setData(int0802Service.getResultByConditionTab1(reqeust));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/search-tab2")
	@ResponseBody
	public ResponseData<List<Int0802Vo>> searchTab2(@RequestBody Int0802SearchVo reqeust) {
		ResponseData<List<Int0802Vo>> response = new ResponseData<List<Int0802Vo>>();
		try {
			response.setData(int0802Service.getResultByConditionTab2(reqeust));
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
