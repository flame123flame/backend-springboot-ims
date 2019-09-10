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
import th.go.excise.ims.ia.service.Int030404Service;
import th.go.excise.ims.ia.vo.Int030404FormVo;
import th.go.excise.ims.ia.vo.Int030404Vo;

@Controller
@RequestMapping("/api/ia/int03/04/04")
public class Int030404Controller {
	@Autowired
	private Int030404Service int030404Service;

	private Logger logger = LoggerFactory.getLogger(Int030404Controller.class);

	@PostMapping("/projectEfficiencyList")
	@ResponseBody
	public DataTableAjax<Int030404Vo> projectEfficiencyList(@RequestBody Int030404FormVo form) {
		DataTableAjax<Int030404Vo> response = new DataTableAjax<Int030404Vo>();
//		Int030404Vo projectEfficiency = new Int030404Vo();
		List<Int030404Vo> projectEfficiencyList = new ArrayList<Int030404Vo>();
		try {
			projectEfficiencyList = int030404Service.projectEfficiencyList(form);
			response.setData(projectEfficiencyList);

		} catch (Exception e) {
			logger.error("Int030404Controller projectEfficiencyList : ", e);
		}
		return response;
	}

	@GetMapping("/year/export/{budgetYear}/{inspectionWork}/{idConfig}/{riskHrdPaperName}/{createUserName}/{createLastName}/{createPosition}/{checkUserName}/{checkLastName}/{checkPosition}")
	public void exportByYear(@PathVariable("budgetYear") String budgetYear,
			@PathVariable("inspectionWork") BigDecimal inspectionWork, @PathVariable("idConfig") BigDecimal idConfig,
			@PathVariable("riskHrdPaperName") String riskHrdPaperName,
			@PathVariable("createUserName") String createUserName,
			@PathVariable("createLastName") String createLastName,
			@PathVariable("createPosition") String createPosition, @PathVariable("checkUserName") String checkUserName,
			@PathVariable("checkLastName") String checkLastName, @PathVariable("checkPosition") String checkPosition,
			HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("สรุปผลปัจจัยเสี่ยงประสิทธิภาพในการดำเนินงานโครงการ", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int030404Service.exportInt030404(budgetYear, inspectionWork, idConfig,riskHrdPaperName,createUserName,createLastName,createPosition,checkUserName,checkLastName,checkPosition);
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
