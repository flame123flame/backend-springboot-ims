package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaLubricantsDtl;
import th.go.excise.ims.oa.service.Oa02010602Service;
import th.go.excise.ims.oa.service.Oa02010604Service;
import th.go.excise.ims.oa.vo.Oa02010604FormVo;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Controller
@RequestMapping("api/oa/02/01/06/04")
public class Oa02010604Controller {
	
private static final Logger logger = LoggerFactory.getLogger(Oa02010602Controller.class);
	
	@Autowired
	private Oa02010604Service oa02010604Service;
	
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<Oa02010604FormVo> findDetailById(@PathVariable("id") String idStr) {
		ResponseData<Oa02010604FormVo> responseData = new ResponseData<Oa02010604FormVo>();
		Oa02010604FormVo data = new Oa02010604FormVo();
		try {
			data = oa02010604Service.findDetailById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::findDetailById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PutMapping("/save/{id}")
	@ResponseBody
	public ResponseData<OaLubricantsDtl> updateById(@RequestBody Oa02010604FormVo request, @PathVariable("id") String idStr) {
		ResponseData<OaLubricantsDtl> responseData = new ResponseData<OaLubricantsDtl>();
		OaLubricantsDtl data = new OaLubricantsDtl();
		try {
			data = oa02010604Service.updateLubircantsDtlById(request, idStr);
			data = oa02010604Service.updateById(request, idStr);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
//	@PutMapping("/saveLubircantsDtl/{id}")
//	@ResponseBody
//	public ResponseData<OaLubricantsDtl> updateLubircantsDtlById(@RequestBody Oa02010604FormVo request, @PathVariable("id") String idStr) {
//		ResponseData<OaLubricantsDtl> responseData = new ResponseData<OaLubricantsDtl>();
//		OaLubricantsDtl data = new OaLubricantsDtl();
//		try {
//			data = oa02010604Service.updateLubircantsDtlById(request, idStr);
//			responseData.setData(data);
//			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
//			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
//		} catch (Exception e) { 
//			logger.error("Oa02010608Controller::updateById ", e);
//			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
//			responseData.setStatus(RESPONSE_STATUS.FAILED);
//		}
//		return responseData;
//	}
//	


}
