package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.service.Int0803Service;
import th.go.excise.ims.ia.vo.Int0803Search;
import th.go.excise.ims.ia.vo.Int0803Vo;

@Controller
@RequestMapping("/api/ia/int08/03")
public class Int0803Controller {
	@Autowired
	private Int0803Service int0803Service;

	@GetMapping("/get-coa-dropdown")
	@ResponseBody
	public ResponseData<List<IaChartOfAcc>> getDropdown() {

		ResponseData<List<IaChartOfAcc>> response = new ResponseData<List<IaChartOfAcc>>();
		try {
			response.setData(int0803Service.getDropdown());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/search")
	@ResponseBody
	public ResponseData<Int0803Vo> search(@RequestBody Int0803Search request) {

		ResponseData<Int0803Vo> response = new ResponseData<Int0803Vo>();
		try {
			response.setData(int0803Service.getExperimentalBudget(request));
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
