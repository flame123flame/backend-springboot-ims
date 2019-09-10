package th.go.excise.ims.ia.controller;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import th.go.excise.ims.ia.service.Int0502Service;

@Controller
@RequestMapping("/api/ia/int05/02")
public class Int0502Controller {
	
	@Autowired
	private Int0502Service int0502Service;
	
	@GetMapping("/estexpno2/export/{estExpNo}/{allSumTranCost}/{allSumOtherExpenses}/{allSumAmt}/{allSumAllowances}/{allSumAccom}")
	public void exportByestExpNo(@PathVariable("estExpNo") String estExpNo,
								@PathVariable("allSumTranCost") BigDecimal allSumTranCost,
								@PathVariable("allSumOtherExpenses") BigDecimal allSumOtherExpenses,
								@PathVariable("allSumAmt") BigDecimal allSumAmt,
								@PathVariable("allSumAllowances") BigDecimal allSumAllowances,
								@PathVariable("allSumAccom") BigDecimal allSumAccom,
								HttpServletResponse response) throws Exception {
		// set fileName
		String fileName = URLEncoder.encode("เอกสารหลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ", "UTF-8");
		// write it as an excel attachment
		ByteArrayOutputStream outByteStream = int0502Service.exportInt0502(estExpNo,allSumTranCost,allSumOtherExpenses,allSumAmt,allSumAllowances,allSumAccom);
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
