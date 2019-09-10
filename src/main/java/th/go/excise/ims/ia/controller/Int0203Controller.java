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
import th.go.excise.ims.ia.service.Int0203Service;
import th.go.excise.ims.ia.vo.Int0203FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Controller
@RequestMapping("/api/ia/int02/03")
public class Int0203Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0203Controller.class);

	@Autowired
	private Int0203Service int0203Service;

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<Int02Vo> filterQtnHdr(@RequestBody Int0203FormVo request) {
		logger.info("filter Datatable int0203");

		DataTableAjax<Int02Vo> response = new DataTableAjax<>();
		try {
			response = int0203Service.filterQtnHdr(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Int0203Controller::filterQtnHdr => ", e);
		}
		return response;
	}

}