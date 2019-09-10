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
import th.go.excise.ims.ia.service.Int030403Service;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;

@Controller
@RequestMapping("/api/ia/int03/04/03")
public class Int030403Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int030403Controller.class);

	
	@Autowired
	private Int030403Service int030403Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int030403Vo> list(@RequestBody Int030403FormVo form) {
		DataTableAjax<Int030403Vo> response = new DataTableAjax<Int030403Vo>();
		List<Int030403Vo> iaRiskBudgetProject = new ArrayList<Int030403Vo>();
		try {	
			iaRiskBudgetProject = int030403Service.list(form);
			response.setData(iaRiskBudgetProject);
		} catch (Exception e) {	
			logger.error("Int0301Controller : " , e);
		}
		return response;
	}
	
	
	@GetMapping("/year/export/{projectYear}/{projecttypecode}/{budgetYear}/{inspectionWork}/{idConfig}/{riskHrdPaperName}/{createUserName}/{createLastName}/{createPosition}/{checkUserName}/{checkLastName}/{checkPosition}")
	public void exportByYear(@PathVariable("projectYear") String projectYear, 
							 @PathVariable("projecttypecode") String projecttypecode,
							 @PathVariable("budgetYear") String budgetYear,
							 @PathVariable("inspectionWork") BigDecimal inspectionWork,
							 @PathVariable("idConfig") BigDecimal idConfig,
							 @PathVariable("riskHrdPaperName") String riskHrdPaperName,
							 @PathVariable("createUserName") String createUserName,
							 @PathVariable("createLastName") String createLastName,
							 @PathVariable("createPosition") String createPosition,
							 @PathVariable("checkUserName") String checkUserName,
							 @PathVariable("checkLastName") String checkLastName,
							 @PathVariable("checkPosition") String checkPosition, HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("สรุปผลปัจจัยเสี่ยงงบประมาณที่ใช้ดำเนินงานโครงการ", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int030403Service.exportInt030403(projectYear, projecttypecode,budgetYear,inspectionWork,idConfig,riskHrdPaperName,createUserName,createLastName,createPosition,checkUserName,checkLastName,checkPosition);
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

