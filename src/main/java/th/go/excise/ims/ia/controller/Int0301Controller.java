package th.go.excise.ims.ia.controller;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int0301Service;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Controller
@RequestMapping("/api/ia/int03/01")
public class Int0301Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int0301Controller.class);
	
	@Autowired
	private Int0301Service int0301Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int0301Vo> list(@RequestBody Int0301FormVo form) {
		DataTableAjax<Int0301Vo> response = new DataTableAjax<Int0301Vo>();
		List<Int0301Vo> iaRiskFactorsList = new ArrayList<Int0301Vo>();
		try {	
			iaRiskFactorsList = int0301Service.list(form);
			response.setData(iaRiskFactorsList);
		} catch (Exception e) {
			logger.error("Int0301Controller : " , e);
		}
		return response;
	}
	
	@PostMapping("/listdynamic")
	@ResponseBody
	public List<Int0301Vo> listdynamic(@RequestBody Int0301FormVo form) {
		List<Int0301Vo> response = new ArrayList<Int0301Vo>();
		try {	
			response = int0301Service.listdynamic(form);
		} catch (Exception e) {
			logger.error("Int0301Controller : " , e);
		}
		return response;
	}
	
	
	@PostMapping("/saveRiskFactorsLevel")
	@ResponseBody
	public ResponseData<String> saveRiskFactorsLevel(@RequestBody Int0301FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int0301Service.saveRiskFactorsLevel(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0301Controller saveRiskFactorsLevel : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/saveRiskFactorsConfig")
	@ResponseBody
	public ResponseData<String> saveRiskFactorsConfig(@RequestBody Int0301FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int0301Service.saveRiskFactorsConfig(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0301Controller saveRiskFactorsConfig : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
