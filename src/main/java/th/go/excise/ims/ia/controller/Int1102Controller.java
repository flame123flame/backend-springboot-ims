package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int1102Service;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int1102FormVo;
import th.go.excise.ims.ia.vo.Int1102Vo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Controller
@RequestMapping("/api/ia/int11/02")
public class Int1102Controller {
	
	@Autowired
	private Int1102Service int1102Service;
	
	
	private Logger logger = LoggerFactory.getLogger(Int1102Controller.class);
	
	@PostMapping("/iaConcludeFollowDetail")
	@ResponseBody
	public DataTableAjax<Int1102Vo> iaConcludeFollowDetail(@RequestBody Int1102FormVo form) {
		DataTableAjax<Int1102Vo> response = new DataTableAjax<>();
		try {
			response = int1102Service.list(form);
		} catch (Exception e) {
			logger.error("Int1102Controller iaConcludeFollowDetail : ", e);
		}
		return response;
	}
	
	@PostMapping("/iaConFolDetail")
	@ResponseBody
	public DataTableAjax<Int11Vo> iaConFolDetail(@RequestBody Int1102FormVo form) {
		DataTableAjax<Int11Vo> response = new DataTableAjax<>();
		try {
			response = int1102Service.listConFol(form);
		} catch (Exception e) {
			logger.error("Int1102Controller iaConFolDetail : ", e);
		}
		return response;
	}
	
	@PostMapping("/updateCheckStatus")
	@ResponseBody
	public ResponseData<String> updateCheckStatus(@RequestBody Int1102FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int1102Service.updateCheckStatus(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1102Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
}
