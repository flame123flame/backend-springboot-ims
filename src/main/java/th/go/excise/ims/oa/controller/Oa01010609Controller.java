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
import th.go.excise.ims.oa.service.Oa01010609Service;
import th.go.excise.ims.oa.vo.Oa010106DtlVo;

@Controller
@RequestMapping("api/oa/01/01/06/09")
public class Oa01010609Controller {
	private static final Logger logger = LoggerFactory.getLogger(Oa01010609Controller.class);

	@Autowired
	private Oa01010609Service oa01010609Service;

	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<Oa010106DtlVo> findDetailById(@PathVariable("id") String idStr) {
		ResponseData<Oa010106DtlVo> responseData = new ResponseData<Oa010106DtlVo>();
		Oa010106DtlVo data = new Oa010106DtlVo();
		try {
			data = oa01010609Service.findDetailById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa01010609Controller::findDetailById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/save/{id}")
	@ResponseBody
	public ResponseData<OaHydrocarbDtl> updateById(@RequestBody Oa010106DtlVo request,
			@PathVariable("id") String idStr) {
		ResponseData<OaHydrocarbDtl> responseData = new ResponseData<OaHydrocarbDtl>();
		OaHydrocarbDtl data = new OaHydrocarbDtl();
		try {
			data = oa01010609Service.updateById(request, idStr);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa01010609Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
