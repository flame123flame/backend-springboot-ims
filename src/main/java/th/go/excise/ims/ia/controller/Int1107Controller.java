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
import th.go.excise.ims.ia.service.Int1107Service;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Controller
@RequestMapping("/api/ia/int11/07")
public class Int1107Controller {

	@Autowired
	private Int1107Service int1107Service;
	
	private Logger logger = LoggerFactory.getLogger(Int1107Controller.class);
	
	@PostMapping("/iaConcludeFollowHdrList07")
	@ResponseBody
	public DataTableAjax<Int11Vo> iaConcludeFollowHdrList07(@RequestBody Int11FormVo form) {
		DataTableAjax<Int11Vo> response = new DataTableAjax<>();
		try {
			response = int1107Service.list(form);
		} catch (Exception e) {
			logger.error("Int1107Controller iaConcludeFollowHdrList : ", e);
		}
		return response;
	}
	
	
	
}
