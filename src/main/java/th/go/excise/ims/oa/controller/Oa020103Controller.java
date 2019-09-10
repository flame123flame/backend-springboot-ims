package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.oa.service.Oa020103Service;
import th.go.excise.ims.oa.vo.Oa020103Vo;
import th.go.excise.ims.oa.vo.Oa0206FormVo;

@Controller
@RequestMapping("api/oa/02/01/03")
public class Oa020103Controller {
	private static final Logger logger = LoggerFactory.getLogger(Oa020103Controller.class);
	
	@Autowired
	private Oa020103Service oa020103Service;
	
	@PostMapping("/findAuditer")
	@ResponseBody
	public DataTableAjax<Oa020103Vo> filterByCriteria(@RequestBody Oa0206FormVo request) {
		DataTableAjax<Oa020103Vo> response = new DataTableAjax<Oa020103Vo>();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa020103Service.getUserAuditer(officeCode,request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}
	

}
