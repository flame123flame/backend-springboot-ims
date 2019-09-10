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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.service.Int0201Service;
import th.go.excise.ims.ia.vo.Int0201FormVo;
import th.go.excise.ims.ia.vo.Int0201FormVo2;
import th.go.excise.ims.ia.vo.Int0201Vo;

@Controller
@RequestMapping("/api/ia/int0201")
public class Int0201Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0201Controller.class);

	@Autowired
	private Int0201Service int0201Service;

	@PostMapping("/find-qtnside-by-id")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireSide>> findQtnSideById(@RequestBody Int0201FormVo request) {
		logger.info("find-by-id IaQuestionnaireSide");

		ResponseData<List<IaQuestionnaireSide>> response = new ResponseData<List<IaQuestionnaireSide>>();
		List<IaQuestionnaireSide> data = null;
		try {
			data = int0201Service.findQtnSideById(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@GetMapping("find/qtn-hdr/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> findOne(@PathVariable("id") BigDecimal id) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int0201Service.findQtnHdrById(id));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/find-qtnside-dtl-by-id")
	@ResponseBody
	public ResponseData<Int0201Vo> findQtnSideDtlById(@RequestBody Int0201FormVo2 request) {
		logger.info("find-by-id IaQuestionnaireSideDtl");

		ResponseData<Int0201Vo> response = new ResponseData<Int0201Vo>();
		Int0201Vo data = null;
		try {
			data = int0201Service.findQtnSideDtlById(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/send-qtn-form")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> sendQtnForm(@RequestBody Int0201FormVo request) {
		logger.info("send questionaire form");

		ResponseData<IaQuestionnaireHdr> response = new ResponseData<IaQuestionnaireHdr>();
		try {
			int0201Service.sendQtnform(request);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/update/status")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> updateStatusQtnHdr(@RequestBody Int0201FormVo request) {
		ResponseData<IaQuestionnaireHdr> response = new ResponseData<IaQuestionnaireHdr>();
		try {
			response.setData(int0201Service.updateStatus(request));
			if (IaConstants.IA_STATUS.STATUS_1_CODE.equals(request.getStatus())) {
				response.setMessage(
						ApplicationCache.getParamInfoByCode("IA_QTN_MESSAGE", request.getStatus()).getValue1());
			} else {
				response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			}
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	/*
	 * ==================== == CONFIGS `START`== ====================
	 */
	@GetMapping("/config/{idQtnHdr}")
	@ResponseBody
	public ResponseData<IaRiskQtnConfig> findConfigByIdQtnHdr(@PathVariable("idQtnHdr") String idQtnHdrStr) {
		ResponseData<IaRiskQtnConfig> response = new ResponseData<IaRiskQtnConfig>();
		try {
			IaRiskQtnConfig data = int0201Service.findConfigByIdQtnHdr(idQtnHdrStr);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::findConfigByIdQtnHdr => ", e);
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PutMapping("/config/{idHdr}")
	@ResponseBody
	public ResponseData<IaRiskQtnConfig> updateConfig(@PathVariable("idHdr") String idHdrStr,
			@RequestBody IaRiskQtnConfig request) {
		ResponseData<IaRiskQtnConfig> response = new ResponseData<IaRiskQtnConfig>();
		try {
			IaRiskQtnConfig data = int0201Service.updateConfig(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::updateConfig => ", e);
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/config/")
	@ResponseBody
	public ResponseData<IaRiskQtnConfig> saveConfig(@RequestBody IaRiskQtnConfig request) {
		ResponseData<IaRiskQtnConfig> response = new ResponseData<IaRiskQtnConfig>();
		try {
			IaRiskQtnConfig data = int0201Service.saveConfig(request);
			response.setData(data);
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::saveConfig => ", e);
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	/*
	 * ==================== == CONFIGS `END`== ====================
	 */
	@PutMapping("/canceled-qtn/{idHdr}")
	@ResponseBody
	public ResponseData<String> canceledQtn(@PathVariable("idHdr") BigDecimal idHdrStr) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			BigDecimal idDown = int0201Service.canceledQtn(idHdrStr);
//			idDown.toString() + 
			response.setData("ยกเลิกแบบสอบถามสำเร็จ");
			response.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0201Controller::canceledQtn => ", e);
			response.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
