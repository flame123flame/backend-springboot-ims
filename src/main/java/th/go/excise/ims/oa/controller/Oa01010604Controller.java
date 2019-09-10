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
import th.go.excise.ims.oa.persistence.entity.OaHydrocarbDtl;
import th.go.excise.ims.oa.service.Oa01010604Service;
import th.go.excise.ims.oa.vo.Oa01010604FromVo;

@Controller
@RequestMapping("api/oa/01/01/06/04")
public class Oa01010604Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa01010604Controller.class);
	
	@Autowired
	private Oa01010604Service oa01010604Service;
	
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<Oa01010604FromVo> findDetailById(@PathVariable("id") String idStr) {
		ResponseData<Oa01010604FromVo> responseData = new ResponseData<Oa01010604FromVo>();
		Oa01010604FromVo data = new Oa01010604FromVo();
		try {
			data = oa01010604Service.findDetailById(idStr);
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
	public ResponseData<OaHydrocarbDtl> updateById(@RequestBody Oa01010604FromVo request, @PathVariable("id") String idStr) {
		ResponseData<OaHydrocarbDtl> responseData = new ResponseData<OaHydrocarbDtl>();
		OaHydrocarbDtl data = new OaHydrocarbDtl();
		try {
			data = oa01010604Service.updateLubircantsDtlById(request, idStr);
			data = oa01010604Service.updateById(request, idStr);
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

}
