package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputMaterialVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)

public class ProductPaperOutputMaterialServiceTest {

	private static final String PRODUCT_PAPER_OUTPUT_MATERIAL = "product_paper_output_material";

	@Autowired
	private ProductPaperOutputMaterialService productPaperOutputMaterialService;

	// @Test
	public void test_inquiry() {
		System.out.println("- - - - - test_inquiry");

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		List<ProductPaperOutputMaterialVo> voList = productPaperOutputMaterialService.inquiryByWs(formVo);
		voList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});

	}

	// @Test
	public void test_exportData() {
		System.out.println("- - - - - test_export");

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH.TEST_PATH + PRODUCT_PAPER_OUTPUT_MATERIAL + "." + FILE_EXTENSION.XLSX)) {
			byte[] outArray = productPaperOutputMaterialService.export(formVo);
			Output.write(outArray);
			System.out.println("Creating excel");
		} catch (IOException e) {
			System.out.println("false excel");
		}
	}

//	@Test
	public void test_upload() {
		System.out.println("- - - - - test_upload");
	}

	@Test
	public void test_save() {
		System.out.println("- - - - - test_save");

		List<ProductPaperOutputMaterialVo> inputVoList = new ArrayList<>();
		ProductPaperOutputMaterialVo vo1 = new ProductPaperOutputMaterialVo();
		vo1.setMaterialDesc("materialDesc1");
		vo1.setOutputMaterialQty("100");
		vo1.setDailyAccountQty("105");
		vo1.setMonthStatementQty("110");
		vo1.setExternalDataQty("100");
		vo1.setMaxDiffQty("10");
		;
		inputVoList.add(vo1);
		ProductPaperOutputMaterialVo vo2 = new ProductPaperOutputMaterialVo();
		vo2.setMaterialDesc("materialDesc2");
		vo2.setOutputMaterialQty("200");
		vo2.setDailyAccountQty("205");
		vo2.setMonthStatementQty("220");
		vo2.setExternalDataQty("200");
		vo2.setMaxDiffQty("20");
		;
		inputVoList.add(vo2);

		Gson gson = new Gson();
		String json = gson.toJson(inputVoList);

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");
		formVo.setAuditPlanCode("0014012562000012");
		formVo.setJson(json);

		productPaperOutputMaterialService.save(formVo);
	}
	
//	@Test
	public void test_getPaperPrNumberList() {
		System.out.println("- - - - - test_getPaperPrNumberList");
		
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setAuditPlanCode("");
		
		List<String> paperPrNumberList = productPaperOutputMaterialService.getPaperPrNumberList(formVo);
		paperPrNumberList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

}
