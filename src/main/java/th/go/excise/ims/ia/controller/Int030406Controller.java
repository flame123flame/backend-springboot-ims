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
import th.go.excise.ims.ia.service.Int030406Service;
import th.go.excise.ims.ia.vo.Int030406FormVo;
import th.go.excise.ims.ia.vo.Int030406Vo;

@Controller
@RequestMapping("/api/ia/int03/04/06")
public class Int030406Controller {
	@Autowired
	private Int030406Service int030406Service;
	
	private Logger logger = LoggerFactory.getLogger(Int030406Controller.class);
	
	@PostMapping("/checkPeriodList")
	@ResponseBody
	public DataTableAjax<Int030406Vo> systemUnworkingList(@RequestBody Int030406FormVo form) {
		DataTableAjax<Int030406Vo> response = new DataTableAjax<Int030406Vo>();
		List<Int030406Vo> checkPeriodList = new ArrayList<Int030406Vo>();

		try {
			checkPeriodList = int030406Service.checkPeriodList(form);
			response.setData(checkPeriodList);

		} catch (Exception e) {
			logger.error("Int030404Controller checkPeriodList : ", e);
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
		String fileName = URLEncoder.encode("สรุปผลปัจจัยเสี่ยงความถี่การเข้าตรวจสอบของผู้ตวรจสอบภายใน", "UTF-8");

		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int030406Service.exportInt030405(budgetYear,inspectionWork,idConfig,riskHrdPaperName,createUserName,createLastName,createPosition,checkUserName,checkLastName,checkPosition);
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
