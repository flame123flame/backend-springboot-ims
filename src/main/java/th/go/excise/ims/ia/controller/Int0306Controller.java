package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int0306Service;
import th.go.excise.ims.ia.vo.Int030102FormVo;

@Controller
@RequestMapping("/api/ia/int03/06")
public class Int0306Controller {

	private Logger logger = LoggerFactory.getLogger(Int0306Controller.class);

	@Autowired
	private Int0306Service int0306Service;
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<BigDecimal> delete(@RequestBody Int030102FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int0306Service.delete(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int0306Controller delete : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	

}
