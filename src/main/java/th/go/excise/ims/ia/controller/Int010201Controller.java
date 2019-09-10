package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int010201Service;
import th.go.excise.ims.ia.vo.Int010201HeaderVo;

@Controller
@RequestMapping("/api/ia/int01/02/01")
public class Int010201Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int010201Controller.class);
	
	@Autowired
	private Int010201Service int010201Service;
	
	@GetMapping("find-by/id-idDtl/{idDtl}")
	@ResponseBody
	public ResponseData<Int010201HeaderVo> findByidDtl(@PathVariable("idDtl") BigDecimal idDtl) {
		logger.info("idDtl: {}", idDtl);
		
		ResponseData<Int010201HeaderVo> response = new ResponseData<Int010201HeaderVo>();
		try {
			response.setData(int010201Service.findHeader(idDtl));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
