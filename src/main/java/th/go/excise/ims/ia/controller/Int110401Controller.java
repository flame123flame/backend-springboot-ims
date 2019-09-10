package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int110401Service;
import th.go.excise.ims.ia.vo.Int110401DtlVo;
import th.go.excise.ims.ia.vo.Int1104Vo;

@Controller
@RequestMapping("/api/ia/int11/04/01")
public class Int110401Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int110401Controller.class);
	
	@Autowired
	Int110401Service int110401Service;
	
	@GetMapping("find-by/id-hdr/{idHdr}")
	@ResponseBody
	public ResponseData<Int1104Vo> findByidHdr(@PathVariable("idHdr") BigDecimal idHdr) {
		logger.info("idHdr: {}", idHdr);
		
		ResponseData<Int1104Vo> response = new ResponseData<Int1104Vo>();
		try {
			response.setData(int110401Service.findByIdHdr(idHdr));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("save/follow-recommend-dtl")
	@ResponseBody
	public ResponseData<T> saveDetails(@RequestBody List<Int110401DtlVo> request) {
		logger.info("savefollowRecommendDtl");
		ResponseData<T> response = new ResponseData<T>();
		try {
			int110401Service.savefollowRecommendDtl(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
