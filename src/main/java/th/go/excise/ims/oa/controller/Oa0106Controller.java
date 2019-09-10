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
import th.go.excise.ims.oa.service.Oa0106Service;
import th.go.excise.ims.oa.vo.Oa0106FormVo;
import th.go.excise.ims.oa.vo.Oa0106Vo;

@Controller
@RequestMapping("/api/oa/01/06")
public class Oa0106Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0106Controller.class);

	@Autowired
	private Oa0106Service oa0106Service;

	@PostMapping("/getData")
	@ResponseBody
	public DataTableAjax<Oa0106Vo> filterByCriteria(@RequestBody Oa0106FormVo request) {
		DataTableAjax<Oa0106Vo> response = new DataTableAjax<>();
		try {
			String offcode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0106Service.filterByCriteria(request, offcode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0106Controller::filterByCriteria => ", e);
		}
		return response;
	}

	@PostMapping("/getDataHyd")
	@ResponseBody
	public DataTableAjax<Oa0106Vo> filteHydByCriteria(@RequestBody Oa0106FormVo request) {
		DataTableAjax<Oa0106Vo> response = new DataTableAjax<>();
		try {
			String offcode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			response = oa0106Service.filterHydByCriteria(request, offcode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa0106Controller::filteHydByCriteria => ", e);
		}
		return response;
	}

	@PostMapping("/pdf/{id}/{dtlId}")
	public void pdfTs(@PathVariable("id") String idStr,@PathVariable("dtlId") String dtlId, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = oa0106Service.objectToPDF(idStr,dtlId);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=hydDocabonService.pdf");

		IOUtils.write(reportFile, response.getOutputStream());
	}

	@GetMapping("/pdf/{id}/{dtlId}")
	public void pdfTsGet(@PathVariable("id") String idStr,@PathVariable("dtlId") String dtlId, HttpServletResponse response)
			throws IOException, JRException {

		byte[] reportFile = oa0106Service.objectToPDF(idStr,dtlId);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=hydDocabonService.pdf");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");

		IOUtils.write(reportFile, response.getOutputStream());
	}
	
	@GetMapping("/pdf/solvent/{id}/{dtlId}/{planId}")
	public void pdfSolvent(@PathVariable("id") String idStr, @PathVariable("dtlId") String dtlId, @PathVariable("planId") String planId, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = oa0106Service.objectToSolvent(idStr,dtlId, planId);

		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=hydDocabonService.pdf");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");

		IOUtils.write(reportFile, response.getOutputStream());
	}

}
