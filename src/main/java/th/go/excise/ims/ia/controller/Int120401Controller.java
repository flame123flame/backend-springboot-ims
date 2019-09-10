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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.service.Int120401Service;
import th.go.excise.ims.ia.vo.Int120401FormVo;

@Controller
@RequestMapping("api/ia/int12/04/01")
public class Int120401Controller {
	@Autowired
	private Int120401Service int120401Service;

	private static final Logger logger = LoggerFactory.getLogger(Int120401Service.class);
	
	@PostMapping("/findByYearByCoa")
	@ResponseBody
	public DataTableAjax<IaExpenses> findByYearByCoa(@RequestBody Int120401FormVo form) {
		DataTableAjax<IaExpenses> response = new DataTableAjax<IaExpenses>();
		List<IaExpenses> systemUnworkingList = new ArrayList<IaExpenses>();

		try {
			systemUnworkingList = int120401Service.findByYearByCoa(form);
			response.setData(systemUnworkingList);

		} catch (Exception e) {
			logger.error("Int120401Service findByYearByCoa : ", e);
		}
		return response;
	}
	
	@DeleteMapping("/deleteById/{id}")
	@ResponseBody
	public ResponseData<String> deleteById(@PathVariable("id") String id) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			String idRes = int120401Service.deleteById(id);
			response.setData(idRes);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int120401Service deleteById : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
