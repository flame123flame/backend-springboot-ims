package th.go.excise.ims.ia.controller;

import java.math.BigDecimal;
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
import th.go.excise.ims.ia.service.Int0305Service;
import th.go.excise.ims.ia.vo.Int030102Vo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0305FormVo;
import th.go.excise.ims.ia.vo.Int0305Vo;

@Controller
@RequestMapping("/api/ia/int03/05")
public class Int0305Controller {

	private Logger logger = LoggerFactory.getLogger(Int0305Controller.class);

	@Autowired
	private Int0305Service int0305Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int030102Vo> list(@RequestBody Int0305FormVo form) {
		DataTableAjax<Int030102Vo> response = new DataTableAjax<Int030102Vo>();
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();

		try {
			iaRiskFactorsMasterList = int0305Service.list(form);
			response.setData(iaRiskFactorsMasterList);

		} catch (Exception e) {
			logger.error("Int0305Controller List : ", e);
		}
		return response;
	}
	

	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<BigDecimal> delete(@RequestBody Int0305FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int0305Service.delete(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int0305Controller Delete : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public ResponseData<BigDecimal> edit(@RequestBody Int0305FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int0305Service.edit(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int0305Controller Edit : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseData<BigDecimal> add(@RequestBody Int0305FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int0305Service.add(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int0305Controller Edit : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
	@PostMapping("/addRiskFactors")
	@ResponseBody
	public ResponseData<BigDecimal> addRiskFactors(@RequestBody Int0305FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int0305Service.addRiskFactors(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int0305Controller add RiskFactors : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/saveRiskFactorsConfig")
	@ResponseBody
	public ResponseData<String> saveRiskFactorsConfig(@RequestBody Int0305FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int0305Service.saveRiskFactorsConfig(form);
//			int0305Service.updateStatusFactors(form.getIaRiskFactorsConfig().getIdFactors());
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
	
	@PostMapping("/saveAll")
	@ResponseBody
	public ResponseData<String> saveAll(@RequestBody Int0305FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			BigDecimal idFactors = int0305Service.saveAll(form);
//			int0305Service.updateStatusFactors(idFactors);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0301Controller saveAll : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
