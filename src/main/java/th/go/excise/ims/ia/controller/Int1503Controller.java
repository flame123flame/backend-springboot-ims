package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int1503Service;
import th.go.excise.ims.ia.vo.Int1502FormVo;
import th.go.excise.ims.ia.vo.Int1503FormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.vo.Ed02FormVo;

@Controller
@RequestMapping("/api/ia/int15/03")
public class Int1503Controller {

	private Logger logger = LoggerFactory.getLogger(Int1503Controller.class);
	
	@Autowired
	private Int1503Service int1503Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<ExciseDepaccMas> listData() {
		DataTableAjax<ExciseDepaccMas> response = new DataTableAjax<ExciseDepaccMas>();
		List<ExciseDepaccMas> dataList = new ArrayList<ExciseDepaccMas>();
		try {
			dataList = int1503Service.listData();
			response.setData(dataList);
		} catch (Exception e) {
			logger.error("Int1503Controller : ", e);
		}
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> save(@RequestBody Int1503FormVo request) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			int1503Service.saveDepaccMas(request);
			response.setData("SUCCESS");
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public ResponseData<String> edit(@RequestBody Int1503FormVo request) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			int1503Service.editDepaccMas(request);
			response.setData("SUCCESS");
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<String> delete(@RequestBody Int1503FormVo request) {
		ResponseData<String> response = new ResponseData<>();
		try {	
			int1503Service.delete(request);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int1503Controller : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
}
