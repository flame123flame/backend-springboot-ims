package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa0701Service;
import th.go.excise.ims.oa.service.Oa0702Service;
import th.go.excise.ims.oa.service.Oa0703Service;
import th.go.excise.ims.oa.vo.Oa07FormVo;
import th.go.excise.ims.oa.vo.ResponseOa07Vo;

@Controller
@RequestMapping("/api/oa/07")
public class Oa07Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa07Controller.class);

	@Autowired
	private Oa0701Service oa0701Service;
	@Autowired
	private Oa0702Service oa0702Service;
	@Autowired
	private Oa0703Service oa0703Service;

	@PostMapping("/reg4000/{page}")
	@ResponseBody
	public ResponseData<ResponseOa07Vo> reg4000(@PathVariable String page, @RequestBody Oa07FormVo formVo){
		ResponseData<ResponseOa07Vo> response = new ResponseData<>();
		try {
			ResponseOa07Vo data = null;
			if ("01".equals(page)){
				data = oa0701Service.reg4000(formVo);
			}else if("02".equals(page)){
				data = oa0702Service.reg4000(formVo);
			}else if("03".equals(page)){
				data = oa0703Service.reg4000(formVo);
			}else{}

			response.setData(data);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0208Controller::findByBudgetYear ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
