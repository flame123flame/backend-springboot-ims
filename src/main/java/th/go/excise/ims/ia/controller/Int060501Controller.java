package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int060501Service;
import th.go.excise.ims.ia.vo.Int060501FormVo;
import th.go.excise.ims.ia.vo.Int060501Vo;

@Controller
@RequestMapping("/api/ia/int06/05/01")
public class Int060501Controller {

	@Autowired
	private Int060501Service int060501Service;
	
	@PostMapping("/fillterDate")
	@ResponseBody
	public ResponseData<List<Int060501Vo>> fillterDate(@RequestBody Int060501FormVo request) {
		ResponseData<List<Int060501Vo>> response = new ResponseData<List<Int060501Vo>>();
		try {
			response.setData(int060501Service.fillterDate(request));
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
