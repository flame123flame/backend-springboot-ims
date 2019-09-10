package th.go.excise.ims.ia.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy2H;
import th.go.excise.ims.ia.service.Int1303Service;
import th.go.excise.ims.ia.vo.Int1303FilterVo;
import th.go.excise.ims.ia.vo.Int1303UpdateVo;
import th.go.excise.ims.ia.vo.Int1303Vo;

@Controller
@RequestMapping("/api/ia/int13/03")
public class Int1303Controller {
	private Logger logger = LoggerFactory.getLogger(Int1303Controller.class);

	@Autowired
	private Int1303Service int1303Service;

	@PostMapping("/get-ws-pm-py2")
	@ResponseBody
	public ResponseData<Int1303Vo> getWsPmPy2(@RequestBody Int1303FilterVo request) {

		ResponseData<Int1303Vo> response = new ResponseData<Int1303Vo>();
		try {
			response.setData(int1303Service.getWsPmPy2(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/save-pm-py2")
	@ResponseBody
	public ResponseData<String> savePmPy2(@RequestBody Int1303Vo request) {

		ResponseData<String> response = new ResponseData<String>();
		try {
			response.setData(int1303Service.savePmPy2(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@GetMapping("/get-dropdown-auditpy2no")
	@ResponseBody
	public ResponseData<List<IaAuditPy2H>> getDropdownAuditPy2No() {

		ResponseData<List<IaAuditPy2H>> response = new ResponseData<List<IaAuditPy2H>>();
		try {
			response.setData(int1303Service.findAuditPy2NoList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/get-ia-pm-py2")
	@ResponseBody
	public ResponseData<Int1303UpdateVo> getIaPmPy2(@RequestBody String auditPy2No) {
		logger.debug("auditPy2No: {}", auditPy2No);
		ResponseData<Int1303UpdateVo> response = new ResponseData<Int1303UpdateVo>();

		try {
			response.setData(int1303Service.getIaPmPy2(auditPy2No));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/update-ia-pm-py2")
	@ResponseBody
	public ResponseData<T> updateIaPmAssess(@RequestBody Int1303UpdateVo request) {

		ResponseData<T> response = new ResponseData<T>();
		try {
			int1303Service.updateIaPmPy2(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
}
