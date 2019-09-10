package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int030401Service;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;

@Controller
@RequestMapping("/api/ia/int03/04/01")
public class Int030401Controller {
	private Logger logger = LoggerFactory.getLogger(Int030102Controller.class);

	@Autowired
	private Int030401Service int030401Service;

	@PostMapping("/factorsDataList")
	@ResponseBody
	public DataTableAjax<Int030401Vo> factorsDataList(@RequestBody Int030401FormVo form) {
		DataTableAjax<Int030401Vo> response = new DataTableAjax<Int030401Vo>();

		try {
			List<Int030401Vo> iaRiskFactorsMasterList = new ArrayList<Int030401Vo>();
			iaRiskFactorsMasterList = int030401Service.factorsDataList(form);
			response.setData(iaRiskFactorsMasterList);
			
		} catch (Exception e) {
			logger.error("Int030102Controller factorsDataList : ", e);
		}
		return response;
	}
	
	@GetMapping("/export/{idFactors}/{idConfig}/{budgetYear}/{inspectionWork}/{riskHrdPaperName}/{createUserName}/{createLastName}/{createPosition}/{checkUserName}/{checkLastName}/{checkPosition}")
	public void exportByYear(@PathVariable("idFactors") BigDecimal idFactors,
			@PathVariable("idConfig") BigDecimal idConfig,
			@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") BigDecimal inspectionWork, 
			@PathVariable("riskHrdPaperName") String riskHrdPaperName,
			@PathVariable("createUserName") String createUserName,
			@PathVariable("createLastName") String createLastName,
			@PathVariable("createPosition") String createPosition, @PathVariable("checkUserName") String checkUserName,
			@PathVariable("checkLastName") String checkLastName, @PathVariable("checkPosition") String checkPosition,
			HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("int030401", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int030401Service.chooseExport(idFactors, idConfig,budgetYear,inspectionWork,riskHrdPaperName,createUserName,createLastName,createPosition,checkUserName,checkLastName,checkPosition);
		byte[] outArray = outByteStream.toByteArray();
		response.setContentType("application/octet-stream");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
	}

}
