package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.service.Int020201Service;
import th.go.excise.ims.ia.vo.Int020201ConcludeVo;
import th.go.excise.ims.ia.vo.Int020201DtlVo;
import th.go.excise.ims.ia.vo.Int020201SidesFormVo;
import th.go.excise.ims.ia.vo.Int020201Vo;

@Controller
@RequestMapping("/api/ia/int020201")
public class Int020201Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int020201Controller.class);

	@Autowired
	private Int020201Service int020201Service;

	@PostMapping("/find-qtnside-by-id")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireSide>> findQtnSideById(@RequestBody Int020201SidesFormVo request) {
		logger.info("find-by-id IaQuestionnaireSide");

		ResponseData<List<IaQuestionnaireSide>> response = new ResponseData<List<IaQuestionnaireSide>>();
		List<IaQuestionnaireSide> data = null;
		try {
			data = int020201Service.findQtnSideById(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@GetMapping("find/qtn-made-hdr/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireMadeHdr> findOne(@PathVariable("id") BigDecimal id) {
		ResponseData<IaQuestionnaireMadeHdr> responseData = new ResponseData<IaQuestionnaireMadeHdr>();
		try {
			responseData.setData(int020201Service.findQtnMadeHdrById(id));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/find-qtnside-dtl-by-id")
	@ResponseBody
	public ResponseData<Int020201Vo> findQtnSideDtlById(@RequestBody Int020201Vo request) {
		logger.info("find-by-id IaQuestionnaireSideDtl");

		ResponseData<Int020201Vo> response = new ResponseData<Int020201Vo>();
		Int020201Vo data = null;
		try {
			data = int020201Service.findQtnSideDtlById(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/update-qtnmade-confirm")
	@ResponseBody
	public ResponseData<Int020201DtlVo> updateConfirmForm(@RequestBody Int020201DtlVo request) {
		logger.info("Update-Confirm-Form");
		ResponseData<Int020201DtlVo> response = new ResponseData<Int020201DtlVo>();

		try {
			int020201Service.updateQtnMadeByRequest(request);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PutMapping("/update-conclude")
	@ResponseBody
	public ResponseData<String> updateConclude(@RequestBody Int020201ConcludeVo request) {
		logger.info("Int020201 Update-Conclude");
		ResponseData<String> response = new ResponseData<String>();

		try {
			int020201Service.updateConclude(request);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("find/count-check-qtn/{id}")
	@ResponseBody
	public ResponseData<Boolean> countCheckQtn(@PathVariable("id") BigDecimal id) {
		ResponseData<Boolean> responseData = new ResponseData<Boolean>();
		try {
			responseData.setData(int020201Service.countCheckQtn(id));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update/status/{idMadeHdr}")
	@ResponseBody
	public ResponseData<IaQuestionnaireMadeHdr> updateStatusReplyQtn(@PathVariable("idMadeHdr") BigDecimal idMadeHdr, @RequestBody String status) {
		logger.info("Int020201 Update-Conclude");
		ResponseData<IaQuestionnaireMadeHdr> response = new ResponseData<IaQuestionnaireMadeHdr>();

		try {
			int020201Service.updateStatusReplyQtn(idMadeHdr, status);
//			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
