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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int1305Service;
import th.go.excise.ims.ia.vo.IaAuditPmcommitHVo;

@Controller
@RequestMapping("/api/ia/int13/05")
public class Int1305Controller {

	@Autowired
	private Int1305Service int1305Service;

	@PostMapping("/find-all-audit-pmcommit-no")
	@ResponseBody
	public ResponseData<List<IaAuditPmcommitHVo>> findAllIaAuditPmcommitH() {
		ResponseData<List<IaAuditPmcommitHVo>> response = new ResponseData<List<IaAuditPmcommitHVo>>();
		try {
			response.setData(int1305Service.findAuditPmcommitHList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaAuditPmcommitHVo> save(@RequestBody IaAuditPmcommitHVo request) {
		ResponseData<IaAuditPmcommitHVo> response = new ResponseData<IaAuditPmcommitHVo>();
		try {
			response.setData(int1305Service.save(request));
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/find-by-audit-pmcommit-no")
	@ResponseBody
	public ResponseData<IaAuditPmcommitHVo> findIaAuditPmcommitHByAuditPmcommitNo(@RequestBody String auditPmcommitNo) {
		ResponseData<IaAuditPmcommitHVo> response = new ResponseData<IaAuditPmcommitHVo>();
		try {
			response.setData(int1305Service.findByAuditPmcommitNo(auditPmcommitNo));
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
