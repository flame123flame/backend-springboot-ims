package th.go.excise.ims.ia.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int0304Service;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;

@Controller
@RequestMapping("/api/ia/int03/04")
public class Int0304Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int0304Controller.class);
	
	private final String INT0304_TOINT030401 = "INT0304_TOINT030401";
	
	@Autowired
	private Int0304Service Int0304Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int0301Vo> list(@RequestBody Int0301FormVo form) {
		DataTableAjax<Int0301Vo> response = new DataTableAjax<Int0301Vo>();
		try {	
			response.setData(Int0304Service.list(form));
		} catch (Exception e) {
			logger.error("Int0304Controller : " , e);
		}
		return response;
	}
	
	@PostMapping("/setSessionList0304")
	@ResponseBody
	public void setSessionList0304(@RequestBody Int0301Vo vo,HttpServletRequest httpServletRequest) {
		try {	
			HttpSession session = httpServletRequest.getSession();
			Int0301Vo readVo = new Int0301Vo();
			readVo = vo;	
			session.setAttribute(INT0304_TOINT030401, readVo);
		} catch (Exception e) {
			logger.error("setSessionList0304 : " , e);
		}
	}
	
	@PostMapping("/getSessionList0304")
	@ResponseBody
	public Int0301Vo getSessionList0304(HttpServletRequest httpServletRequest) {
		HttpSession session = httpServletRequest.getSession();
		Int0301Vo readVo = new Int0301Vo();
		try {	
			readVo = (Int0301Vo) session.getAttribute(INT0304_TOINT030401);
		} catch (Exception e) {
			logger.error("getSessionList0304 : " , e);
		}
		return readVo;
	}
}
