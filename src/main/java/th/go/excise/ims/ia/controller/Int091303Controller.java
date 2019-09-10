package th.go.excise.ims.ia.controller;

import java.util.List;

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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaUtilityBudget;
import th.go.excise.ims.ia.service.Int091303Service;
import th.go.excise.ims.ia.vo.Int091303ResultVo;
import th.go.excise.ims.ia.vo.Int091303SaveVo;
import th.go.excise.ims.ia.vo.Int091303SearchVo;
import th.go.excise.ims.preferences.vo.ExciseDepartment;

@Controller
@RequestMapping("/api/ia/int091303")
public class Int091303Controller {

	@Autowired
	private Int091303Service int091303Service;
	
	@PostMapping("/find-091303-search")
	@ResponseBody
	public ResponseData<List<Int091303ResultVo>> findIaUtilityBill(@RequestBody Int091303SearchVo request) {
		ResponseData<List<Int091303ResultVo>> response = new ResponseData<List<Int091303ResultVo>>();
		try {
			response.setData(int091303Service.int091303SearchVo(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	

	@PostMapping("/find-091303-save")
	@ResponseBody
	public ResponseData<IaUtilityBudget> save(@RequestBody Int091303SaveVo request) {
		ResponseData<IaUtilityBudget> response = new ResponseData<IaUtilityBudget>();
		try {
			response.setData(int091303Service.save(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/get-department/{officeCode}")
	@ResponseBody
	public ResponseData<ExciseDepartment> save(@PathVariable("officeCode") String officeCode) {
		ResponseData<ExciseDepartment> response = new ResponseData<ExciseDepartment>();
		try {
			response.setData(ApplicationCache.getExciseDepartment(officeCode));
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
