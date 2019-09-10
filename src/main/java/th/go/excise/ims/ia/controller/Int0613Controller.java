package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int0613Service;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;
import th.go.excise.ims.ia.vo.Int0613FormVo;
import th.go.excise.ims.ia.vo.Int0613Vo;

@Controller
@RequestMapping("/api/ia/int06/13")
public class Int0613Controller {
	@Autowired
	private Int0613Service int0613Service;

	private Logger logger = LoggerFactory.getLogger(Int0613Controller.class);
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int0613Vo> list(@RequestBody Int0613FormVo formVo){
		
		DataTableAjax<Int0613Vo> licenseList6010List =  new DataTableAjax<>();
		try {
			licenseList6010List = int0613Service.filterQtnHdr(formVo);
			
		} catch (Exception e) {
			logger.error("Error ! ==> Int0161Controller method licFri6010",e);
		}
		
		return licenseList6010List;
	}
	
//	@PostMapping("/filter")
//	@ResponseBody
//	public DataTableAjax<Int02Vo> filterQtnHdr(@RequestBody Int02FormVo request) {
//		logger.info("filter Datatable int02");
//
//		DataTableAjax<Int02Vo> response = new DataTableAjax<>();
//		try {
//			response  = int02Service.filterQtnHdr(request);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return response;
//	}
}
