package th.go.excise.ims.ia.controller;

import org.apache.poi.ss.formula.functions.T;
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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.service.Int02Service;
import th.go.excise.ims.ia.vo.Int020101UpdateVo;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Controller
@RequestMapping("/api/ia/int02")
public class Int02Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int02Controller.class);
	
	@Autowired
	private Int02Service int02Service;
	
	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Int02Vo> filterQtnHdr(@RequestBody Int02FormVo request) {
		logger.info("filter Datatable int02");

		DataTableAjax<Int02Vo> response = new DataTableAjax<>();
		try {
			response  = int02Service.filterQtnHdr(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> findOne(@PathVariable("id") String idStr) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int02Service.findOne(idStr));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> save(@RequestBody IaQuestionnaireHdr request) {
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int02Service.save(request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseData<IaQuestionnaireHdr> update(@PathVariable("id") String idStr, @RequestBody Int020101UpdateVo  request) {
//		IaQuestionnaireHdr
		ResponseData<IaQuestionnaireHdr> responseData = new ResponseData<IaQuestionnaireHdr>();
		try {
			responseData.setData(int02Service.update(idStr, request));
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
	
	
	@PutMapping("/cancel-send-qtn/{id}")
	@ResponseBody
	public ResponseData<T> cancelSendQtn(@PathVariable("id") String idStr) {
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int02Service.updateStatus(idStr);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
}
