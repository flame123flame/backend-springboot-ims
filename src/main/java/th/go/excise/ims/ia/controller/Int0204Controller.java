package th.go.excise.ims.ia.controller;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import th.go.excise.ims.ia.service.Int0204Service;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Controller
@RequestMapping("/api/ia/int02/04")
public class Int0204Controller {
private static final Logger logger = LoggerFactory.getLogger(Int02Controller.class);
	
	@Autowired
	private Int0204Service int0204Service;
	
	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Int02Vo> filterQtnHdr(@RequestBody Int02FormVo request) {
		logger.info("filter Datatable int02");

		DataTableAjax<Int02Vo> response = new DataTableAjax<>();
		try {
			response  = int0204Service.filterQtnHdr(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@PutMapping("/cancel-send-qtn/{id}")
	@ResponseBody
	public ResponseData<T> cancelSendQtn(@PathVariable("id") String idStr) {
		ResponseData<T> responseData = new ResponseData<T>();
		try {
			int0204Service.updateStatus(idStr);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
}
