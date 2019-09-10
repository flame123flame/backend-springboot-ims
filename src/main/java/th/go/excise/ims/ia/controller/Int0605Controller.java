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
import th.go.excise.ims.ia.persistence.entity.IaCheckLicense;
import th.go.excise.ims.ia.persistence.entity.IaCheckTaxReceipt;
import th.go.excise.ims.ia.service.Int0605Service;
import th.go.excise.ims.ia.service.Int0606Service;
import th.go.excise.ims.ia.vo.Int0605FormVo;
import th.go.excise.ims.ia.vo.Int0606FormVo;

@Controller
@RequestMapping("/api/ia/int06/05")
public class Int0605Controller {

	private Logger logger = LoggerFactory.getLogger(Int0605Controller.class);
	
	@Autowired
	Int0605Service int0605Service;
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<IaCheckTaxReceipt> list(@RequestBody Int0605FormVo form) {
		DataTableAjax<IaCheckTaxReceipt> response = new DataTableAjax<IaCheckTaxReceipt>();
		List<IaCheckTaxReceipt> iaCheckTaxReceipt = new ArrayList<IaCheckTaxReceipt>();
		try {	
			iaCheckTaxReceipt = int0605Service.list(form);
			response.setData(iaCheckTaxReceipt);
		} catch (Exception e) {
			logger.error("Int0605Controller : " , e);
		}
		return response;
	}
		
}
