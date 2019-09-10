package th.go.excise.ims.ia.controller;

import java.util.List;

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
import th.go.excise.ims.ia.service.Int120701Service;
import th.go.excise.ims.ia.vo.Int120701FilterVo;
import th.go.excise.ims.ia.vo.Int120701Type6006Vo;
import th.go.excise.ims.ia.vo.int120701Type7131Vo;

@Controller
@RequestMapping("/api/ia/int12/07/01/01")
public class Int120701Controller {
	private Logger logger = LoggerFactory.getLogger(Int120701Controller.class);

	@Autowired
	private Int120701Service int120701Service;

	@PostMapping("/filterByDate")
	@ResponseBody
	public ResponseData<List<Int120701Type6006Vo>> filterByDate(@RequestBody Int120701FilterVo dataReq) {
		ResponseData<List<Int120701Type6006Vo>> response = new ResponseData<>();
		try {
			response.setData(int120701Service.filterByDate(dataReq));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("filterByDate => ", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/filterByDate7131")
	@ResponseBody
	public ResponseData<List<int120701Type7131Vo>> filterByDate7131(@RequestBody Int120701FilterVo dataReq) {
		ResponseData<List<int120701Type7131Vo>> response = new ResponseData<>();
		try {
			response.setData(int120701Service.filterByDate7131(dataReq));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("filterByDate7131 => ", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
