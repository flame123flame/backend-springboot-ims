package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSide;
import th.go.excise.ims.ia.service.Int020101Service;
import th.go.excise.ims.ia.service.UpdateStatusQuestionnaireService;
import th.go.excise.ims.ia.vo.Int020101NameVo;
import th.go.excise.ims.ia.vo.Int020101Vo;
import th.go.excise.ims.ia.vo.Int020101YearVo;

@Controller
@RequestMapping("/api/ia/int02/01/01")
public class Int020101Controller {

	private static final Logger logger = LoggerFactory.getLogger(Int020101Controller.class);

	@Autowired
	private Int020101Service int020101Service;

	@Autowired
	private UpdateStatusQuestionnaireService questionnaireService;

	@GetMapping("/all")
	@ResponseBody
	public ResponseData<List<Int020101Vo>> findAllQtnSide() {
		ResponseData<List<Int020101Vo>> responseData = new ResponseData<List<Int020101Vo>>();
		List<Int020101Vo> data = new ArrayList<>();
		try {
			data = int020101Service.findAll();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::findAllQtnSide ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/by/id/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireSide> findById(@PathVariable("id") String idStr) {
		ResponseData<IaQuestionnaireSide> responseData = new ResponseData<IaQuestionnaireSide>();
		IaQuestionnaireSide data;
		try {
			data = int020101Service.findOne(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::findById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/by/head/{head}")
	@ResponseBody
	public ResponseData<List<Int020101Vo>> findByIdHead(@PathVariable("head") String idHead) {
		ResponseData<List<Int020101Vo>> responseData = new ResponseData<List<Int020101Vo>>();
		List<Int020101Vo> data = new ArrayList<>();
		try {
			data = int020101Service.findByIdHead(idHead);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::findByIdHead ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaQuestionnaireSide> save(@RequestBody IaQuestionnaireSide request) {
		ResponseData<IaQuestionnaireSide> responseData = new ResponseData<IaQuestionnaireSide>();
		try {
			responseData.setData(int020101Service.save(request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::save ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/all")
	@ResponseBody
	public ResponseData<List<IaQuestionnaireSide>> saveAll(@RequestBody List<IaQuestionnaireSide> request) {
		ResponseData<List<IaQuestionnaireSide>> responseData = new ResponseData<List<IaQuestionnaireSide>>();
		try {
			responseData.setData(int020101Service.saveAll(request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireSide> update(@PathVariable("id") String idStr,
			@RequestBody IaQuestionnaireSide request) {

		ResponseData<IaQuestionnaireSide> responseData = new ResponseData<IaQuestionnaireSide>();
		try {
			responseData.setData(int020101Service.update(idStr, request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::update ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireSide> delete(@PathVariable("id") String idStr) {
		ResponseData<IaQuestionnaireSide> responseData = new ResponseData<IaQuestionnaireSide>();
		try {
			responseData.setData(int020101Service.delete(idStr));
			int020101Service.updateStatusIaQuestionnaireAutomatic(responseData.getData().getIdHead());
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::delete ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.DELETE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/by/user")
	@ResponseBody
	public ResponseData<List<Int020101YearVo>> getByUsername() {
		ResponseData<List<Int020101YearVo>> responseData = new ResponseData<List<Int020101YearVo>>();
		List<Int020101YearVo> data = new ArrayList<>();
		try {
			String username = UserLoginUtils.getCurrentUsername();
			data = int020101Service.findByUsername(username);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::getByUsername ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/by/status")
	@ResponseBody
	public ResponseData<List<Int020101YearVo>> getByStatus() {
		ResponseData<List<Int020101YearVo>> responseData = new ResponseData<List<Int020101YearVo>>();
		List<Int020101YearVo> data = new ArrayList<>();
		try {
			data = int020101Service.findByStatus();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::getByUsername ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/by/year/{year}/user")
	@ResponseBody
	public ResponseData<List<Int020101NameVo>> getByYearAndUsername(@PathVariable("year") String year) {
		ResponseData<List<Int020101NameVo>> responseData = new ResponseData<List<Int020101NameVo>>();
		List<Int020101NameVo> data = new ArrayList<>();
		try {
			String username = UserLoginUtils.getCurrentUsername();
			data = int020101Service.findByYearAndUsername(year, username);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::getByYearAndUsername ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/by/year/{year}/{id}/status")
	@ResponseBody
	public ResponseData<List<Int020101NameVo>> getByYearAndStatus(@PathVariable("year") String year,
			@PathVariable("id") String id) {
		ResponseData<List<Int020101NameVo>> responseData = new ResponseData<List<Int020101NameVo>>();
		List<Int020101NameVo> data = new ArrayList<>();
		try {
			data = int020101Service.findByYearAndStatus(year, id);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::getByYearAndUsername ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@DeleteMapping("/delete/choices/{id}/{choiceStr}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> deleteQtn(@PathVariable("id") BigDecimal id,
			@PathVariable("choiceStr") String choiceStr) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int020101Service.deleteQtn(id, choiceStr));
			if ("CANCEL".equals(choiceStr)) {
				responseData.setMessage(ApplicationCache.getParamInfoByCode("IA_QTN_MESSAGE", "2").getValue1());
			} else {
				responseData.setMessage(ApplicationCache
						.getMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS_CODE).getMessageTh());
			}
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/update/status/{idHdr}/{status}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> updateQtnStatus(@PathVariable("idHdr") BigDecimal idHdr,
			@PathVariable("status") String status) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			/* update status */
			questionnaireService.updateStatusIaQuestionnaire(idHdr, status);
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/checkUseQtn/{idHdr}")
	@ResponseBody
	public ResponseData<String> checkUseQtn(@PathVariable("idHdr") String idHead) {
		ResponseData<String> responseData = new ResponseData<String>();
		try {
			String data = int020101Service.checkUseQtn(idHead);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::checkUse ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
