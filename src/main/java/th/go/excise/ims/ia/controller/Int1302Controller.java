package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaAuditPy1H;
import th.go.excise.ims.ia.service.Int1302Service;
import th.go.excise.ims.ia.vo.Int1302FormVo;
import th.go.excise.ims.ia.vo.Int1302Py1NoVo;
import th.go.excise.ims.ia.vo.Int1302SaveDtlFormVo;
import th.go.excise.ims.ia.vo.Int1302Vo;

@Controller
@RequestMapping("/api/ia/int13/02")
public class Int1302Controller {
	private Logger logger = LoggerFactory.getLogger(Int1302Controller.class);

	@Autowired
	private Int1302Service int1302Service;

	@PostMapping("/findData")
	@ResponseBody
	public ResponseData<Int1302Vo> findData(@RequestBody Int1302FormVo form) {
		ResponseData<Int1302Vo> response = new ResponseData<Int1302Vo>();
		try {
			response = int1302Service.list(form);
		} catch (Exception e) {
			logger.error("Int1101Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/saveData")
	@ResponseBody
	public ResponseData<String> saveData(@RequestBody Int1302SaveDtlFormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {
//			int1302Service.saveData(form);
			response.setData(int1302Service.saveData(form));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-by-py1No")
	@ResponseBody
	public ResponseData<Int1302Py1NoVo> findByPy1No(@RequestBody String py1No) {
//		public ResponseData<Int1302Py1NoVo> findByPy1No(@PathVariable("py1No") String py1No) {
		ResponseData<Int1302Py1NoVo> response = new ResponseData<Int1302Py1NoVo>();
		try {
			response.setData(int1302Service.findByPy1No(py1No));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@GetMapping("/getPy1NoList")
	@ResponseBody
	public ResponseData<List<IaAuditPy1H>> getPy1NoList() {
		ResponseData<List<IaAuditPy1H>> response = new ResponseData<List<IaAuditPy1H>>();
		try {
			response.setData(int1302Service.getPy1NoList());
//			int1302Service.saveData(form);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PutMapping("/editData")
	@ResponseBody
	public ResponseData<String> editData(@RequestBody Int1302SaveDtlFormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {
//			response.setData(int1302Service.list(form));
			int1302Service.editData(form);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1101Controller updateSentStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
