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
import th.go.excise.ims.ia.service.Int0804Service;
import th.go.excise.ims.ia.vo.Int0804SearchVo;
import th.go.excise.ims.ia.vo.Int0804Vo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;

@Controller
@RequestMapping("/api/ia/int08/04")
public class Int0804Controller {

	@Autowired
	private Int0804Service int0804Service;

	@GetMapping("/get-depacc-mas-dropdown")
	@ResponseBody
	public ResponseData<List<ExciseDepaccMas>> getDepaccMasDropdown() {

		ResponseData<List<ExciseDepaccMas>> response = new ResponseData<List<ExciseDepaccMas>>();
		
		try {
			response.setData(int0804Service.getDepaccMasDropdown());
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
	public ResponseData<Int0804Vo> findByCondition(@RequestBody Int0804SearchVo request) {
		
		ResponseData<Int0804Vo> response = new ResponseData<Int0804Vo>();
		try {
			response.setData(int0804Service.findByCondition(request));
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
