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
import th.go.excise.ims.ia.service.Int0202Service;
import th.go.excise.ims.ia.vo.Int0202FormVo;
import th.go.excise.ims.ia.vo.Int0202Vo;

@Controller
@RequestMapping("/api/ia/int0202")
public class Int0202Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0202Controller.class);
	
	@Autowired
	private Int0202Service int0202Service;
	
	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Int0202Vo> filterQtnMadeHdr(@RequestBody Int0202FormVo request) {
		logger.info("filter Datatable int02");

		DataTableAjax<Int0202Vo> response = new DataTableAjax<Int0202Vo>();
		try {
			response  = int0202Service.findByRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
