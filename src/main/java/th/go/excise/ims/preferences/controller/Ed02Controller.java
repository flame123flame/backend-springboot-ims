package th.go.excise.ims.preferences.controller;

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
import th.go.excise.ims.ia.vo.Int120101FormVo;
import th.go.excise.ims.preferences.service.Ed02Service;
import th.go.excise.ims.preferences.vo.Ed01Vo;
import th.go.excise.ims.preferences.vo.Ed02FormVo;
import th.go.excise.ims.preferences.vo.Ed02Vo;

@Controller
@RequestMapping("/api/ed/ed02")
public class Ed02Controller {
	
	private Logger logger = LoggerFactory.getLogger(Ed02Controller.class);
	
	@Autowired
	private Ed02Service ed02Service;
	
	@PostMapping("/searchPosition")
	@ResponseBody
	public DataTableAjax<Ed02Vo> searchPosition(@RequestBody Ed02FormVo form) {
		DataTableAjax<Ed02Vo> response = new DataTableAjax<Ed02Vo>();
		List<Ed02Vo> positionList = new ArrayList<Ed02Vo>();
		try {	
			positionList = ed02Service.list(form);
			response.setData(positionList);
		} catch (Exception e) {
			logger.error("Ed02Controller : " , e);
		}
		return response;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<String> delete(@RequestBody Ed02FormVo formVo) {
		ResponseData<String> response = new ResponseData<>();
		try {	
			ed02Service.delete(formVo);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
