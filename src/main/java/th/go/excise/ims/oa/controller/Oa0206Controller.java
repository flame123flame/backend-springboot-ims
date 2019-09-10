package th.go.excise.ims.oa.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.oa.service.Oa0206Service;
import th.go.excise.ims.oa.vo.Oa0206FormVo;
import th.go.excise.ims.oa.vo.Oa0206Vo;

@Controller
@RequestMapping("/api/oa/02/06")
public class Oa0206Controller {
	

	private static final Logger logger = LoggerFactory.getLogger(Oa0206Controller.class);
	
	@Autowired
	private Oa0206Service oa0206Service;
	
	@PostMapping("/getData")
	@ResponseBody
	public DataTableAjax<Oa0206Vo> filterByCriteria(@RequestBody Oa0206FormVo request) {
		DataTableAjax<Oa0206Vo> response = new DataTableAjax<>();
		try {
			String offcode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0206Service.filterByCriteria(request,offcode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}
	
	@PostMapping("/getDataHyd")
	@ResponseBody
	public DataTableAjax<Oa0206Vo> filteHydByCriteria(@RequestBody Oa0206FormVo request) {
		DataTableAjax<Oa0206Vo> response = new DataTableAjax<>();
		try {
			String offcode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0206Service.filterHydByCriteria(request,offcode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0207Controller::filterByCriteria => ", e);
		}
		return response;
	}
	
	@GetMapping("/pdf/{id}/{dtlId}")
	public void pdfTs(@PathVariable("id") String idStr,@PathVariable("dtlId") String dtlId, HttpServletResponse response)throws IOException, JRException{
		byte[] reportFile = oa0206Service.objectToPDF(idStr,dtlId);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=lubricantService.pdf");

		IOUtils.write(reportFile, response.getOutputStream());
	}
	
	@GetMapping("/Hydpdf/{id}/{dtlId}")
	public void pdfTsGet(@PathVariable("id") String idStr,@PathVariable("dtlId") String dtlId ,HttpServletResponse response)throws IOException, JRException{
		
		byte[] reportFile = oa0206Service.objectToPDF(idStr,dtlId);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=lubricantService.pdf");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");

		IOUtils.write(reportFile, response.getOutputStream());
	}
	
	@GetMapping("/pdf/lubricant/{id}/{dtlId}/{planId}")
	public void pdfSolvent(@PathVariable("id") String idStr,@PathVariable("dtlId") String dtlId, @PathVariable("planId") String planId, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = oa0206Service.objectToLubricant(idStr, dtlId, planId);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=lubricantService.pdf");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");

		IOUtils.write(reportFile, response.getOutputStream());
	}

}
