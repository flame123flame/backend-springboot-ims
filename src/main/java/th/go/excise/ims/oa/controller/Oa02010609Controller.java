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
import th.go.excise.ims.oa.service.Oa02010609Service;
import th.go.excise.ims.oa.vo.Oa020106DtlVo;

@Controller
@RequestMapping("api/oa/02/01/06/09")
public class Oa02010609Controller {
	private static final Logger logger = LoggerFactory.getLogger(Oa02010609Controller.class);

	@Autowired
	private Oa02010609Service oa02010609Service;

	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<Oa020106DtlVo> findDetailById(@PathVariable("id") String idStr) {
		ResponseData<Oa020106DtlVo> responseData = new ResponseData<Oa020106DtlVo>();
		Oa020106DtlVo data = new Oa020106DtlVo();
		try {
			data = oa02010609Service.findDetailById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa02010609Controller::findDetailById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/save/{id}")
	@ResponseBody
	public ResponseData<OaLubricantsDtl> updateById(@RequestBody Oa020106DtlVo request,
			@PathVariable("id") String idStr) {
		ResponseData<OaLubricantsDtl> responseData = new ResponseData<OaLubricantsDtl>();
		OaLubricantsDtl data = new OaLubricantsDtl();
		try {
			data = oa02010609Service.updateById(request, idStr);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa02010609Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
