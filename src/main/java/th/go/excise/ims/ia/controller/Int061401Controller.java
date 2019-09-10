package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.service.Int061401Service;
import th.go.excise.ims.ia.vo.Int061401FilterVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;

@Controller
@RequestMapping("/api/ia/int06/14/01")
public class Int061401Controller {
	private Logger logger = LoggerFactory.getLogger(Int061401Controller.class);
	
	@Autowired
	private Int061401Service int061401Service;
	
	@GetMapping("/get-dropdown")
	@ResponseBody
	public ResponseData<List<IaAuditTxinsurH>> getDropdown(){
		logger.info("get dropdown Int061401!!");
		
		ResponseData<List<IaAuditTxinsurH>> response =  new ResponseData<List<IaAuditTxinsurH>>();
		try {
			response.setData(int061401Service.getgetAudittxInsurNoList());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Ws_Reg4000Vo> filter(@RequestBody Int061401FilterVo formVo){
		logger.info("officeCode: {}", formVo.getOfficeCode());
		
		DataTableAjax<Ws_Reg4000Vo> response =  new DataTableAjax<Ws_Reg4000Vo>();
		try {
			response = int061401Service.filter(formVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

}
