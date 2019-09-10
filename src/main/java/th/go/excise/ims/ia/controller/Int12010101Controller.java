package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.go.excise.ims.ia.persistence.entity.IaStampGenre;
import th.go.excise.ims.ia.persistence.entity.IaStampType;
import th.go.excise.ims.ia.service.Int12010101Service;
import th.go.excise.ims.ia.vo.Int12010101Vo;

@Controller
@RequestMapping("/api/ia/int12/01/01/01")
public class Int12010101Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int12010101Controller.class);
	
	@Autowired
	Int12010101Service int12010101Service;
	
	@PostMapping("/save")
	@ResponseBody
	public List<Int12010101Vo> save(@RequestBody List<Int12010101Vo> formVos) {
		try {
			int12010101Service.save(formVos);
		} catch (Exception e) {
			logger.error("Int11Controller iaConcludeFollowHdrList : ", e);
		}
		return formVos;
	}
	
	@GetMapping("/stamTypes")
    @ResponseBody
    public List<IaStampType> stamTypes(){
	    return int12010101Service.stamTypes();
    }

    @GetMapping("/stamGenre/{stamTypeId}")
    @ResponseBody
    public List<IaStampGenre> stamGenres(@PathVariable("stamTypeId")String stamTypeId){
	    return int12010101Service.stamGenres(stamTypeId);
    }
	
	

}

