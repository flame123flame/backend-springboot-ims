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
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ProductPaperInputMaterialServiceTest {

	private static final String EXCEL_FILE_NAME = "productPaper01.xlsx";

	@Autowired
	private ProductPaperInputMaterialService productPaperInputMaterialService;

	@Test
	public void test_inquiry() {
		System.out.println("- - - - - test_inquiry");
		
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("01055210150261001");
		formVo.setDutyGroupId("0201");
		//formVo.setStartDate("09/2561");
		//formVo.setEndDate("10/2561");
		formVo.setPaperPrNumber("001402-2561-000001");

		ProductPaperVo vo = productPaperInputMaterialService.inquiry(formVo);
		System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE));
		vo.getDataTableAjax().getData().forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

//	@Test
	public void test_export() {
		System.out.println("- - - - - test_export");
		
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("01055210150261001");
		formVo.setDutyGroupId("0201");
		formVo.setStartDate("07/2561");
		formVo.setEndDate("12/2561");

		try (FileOutputStream Output = new FileOutputStream(PATH.TEST_PATH + EXCEL_FILE_NAME)) {
			byte[] outArray = productPaperInputMaterialService.export(formVo);
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
	
//	@Test
	public void test_save() {
		System.out.println("- - - - - test_save");
		
		List<ProductPaperInputMaterialVo> inputVoList = new ArrayList<>();
		ProductPaperInputMaterialVo vo1 = new ProductPaperInputMaterialVo();
		vo1.setMaterialDesc("materialDesc1");
		vo1.setInputMaterialQty("100");
		vo1.setDailyAccountQty("105");
		vo1.setMonthStatementQty("110");
		vo1.setExternalDataQty("100");
		vo1.setMaxDiffQty("10");;
		inputVoList.add(vo1);
		ProductPaperInputMaterialVo vo2 = new ProductPaperInputMaterialVo();
		vo2.setMaterialDesc("materialDesc2");
		vo2.setInputMaterialQty("200");
		vo2.setDailyAccountQty("205");
		vo2.setMonthStatementQty("220");
		vo2.setExternalDataQty("200");
		vo2.setMaxDiffQty("20");
		inputVoList.add(vo2);
		
		Gson gson = new Gson();
		String json = gson.toJson(inputVoList);
		
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");
		formVo.setAuditPlanCode("0014012561000005");
		formVo.setJson(json);
		
		productPaperInputMaterialService.save(formVo);
	}
	
//	@Test
	public void test_getPaperPrNumberList() {
		System.out.println("- - - - - test_getPaperPrNumberList");
		
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setAuditPlanCode("");
		
		List<String> paperPrNumberList = productPaperInputMaterialService.getPaperPrNumberList(formVo);
		paperPrNumberList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

}
