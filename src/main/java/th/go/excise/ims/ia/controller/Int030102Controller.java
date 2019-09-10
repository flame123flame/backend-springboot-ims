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
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.service.Int030102Service;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Controller
@RequestMapping("/api/ia/int03/01/02")
public class Int030102Controller {

	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);

	@Autowired
	private Int030102Service int030102Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int030102Vo> list(@RequestBody Int030102FormVo form) {
		DataTableAjax<Int030102Vo> response = new DataTableAjax<Int030102Vo>();
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();

		try {
			iaRiskFactorsMasterList = int030102Service.list(form);
			response.setData(iaRiskFactorsMasterList);

		} catch (Exception e) {
			logger.error("Int030102Controller List : ", e);
		}
		return response;
	}
	
	@PostMapping("/budgetYearDropdown")
	@ResponseBody
	public List<Int030102FormVo> budgetYearDropdown() {
		List<Int030102FormVo> response = new ArrayList<Int030102FormVo>();
		try {	
			response = int030102Service.budgetYearDropdown();
		} catch (Exception e) {
			logger.error("Int030102Controller BudgetYearDropdown : ", e);
		}
		return response;
	}

	@PostMapping("/delete")
	@ResponseBody
	public ResponseData<BigDecimal> delete(@RequestBody Int030102FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int030102Service.delete(form);
			response.setData(id);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller Delete : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/editStatus")
	@ResponseBody
	public ResponseData<BigDecimal> editStatus(@RequestBody Int030102FormVo form) {
		ResponseData<BigDecimal> response = new ResponseData<BigDecimal>();
		BigDecimal id = form.getId();

		try {
			int030102Service.editStatus(form);
			response.setData(id);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller EditStatus : ", e);
			response.setMessage("ERROR");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseData<String> save(@RequestBody Int030102FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		String save = "บันทึกเรียบร้อบ";

		try {
			int030102Service.save(form);
			response.setData(save);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller save : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/saveRiskFactorsLevel")
	@ResponseBody
	public ResponseData<String> saveRiskFactorsLevel(@RequestBody Int030102FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int030102Service.saveRiskFactorsLevel(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller saveRiskFactorsLevelAndUpdateStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/updateStatus")
	@ResponseBody
	public ResponseData<String> updateStatus(@RequestBody Int030102FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {	
			int030102Service.updateStatus(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller saveRiskFactorsLevelAndUpdateStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/factorList")
	@ResponseBody
	public ResponseData<IaRiskFactors> factorList(@RequestBody IaRiskFactorsMaster form) {
		ResponseData<IaRiskFactors> response = new ResponseData<IaRiskFactors>();
		try {	
			
			response.setData(int030102Service.factorList(form));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030102Controller saveRiskFactorsLevelAndUpdateStatus : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
