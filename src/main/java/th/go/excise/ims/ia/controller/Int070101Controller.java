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
import th.go.excise.ims.ia.persistence.entity.IaCheckStamp;
import th.go.excise.ims.ia.service.Int070101Service;
import th.go.excise.ims.ia.vo.Int070101FormVo;

@Controller
@RequestMapping("/api/ia/int07/01/01")
public class Int070101Controller {
	
	
private Logger logger = LoggerFactory.getLogger(Int070101Controller.class);
	
	@Autowired
	private Int070101Service int070101Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<IaCheckStamp> list(@RequestBody Int070101FormVo form) {
		DataTableAjax<IaCheckStamp> response = new DataTableAjax<IaCheckStamp>();
		List<IaCheckStamp> iaCheckStampList = new ArrayList<IaCheckStamp>();
		try {	
			iaCheckStampList = int070101Service.list(form);
			response.setData(iaCheckStampList);
		} catch (Exception e) {
			logger.error("Int070101Controller : " , e);
		}
		return response;
	}

}
