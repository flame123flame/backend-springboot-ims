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
import th.go.excise.ims.ta.vo.ProductPaperBalanceMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ProductPaperBalanceMaterialServiceTest {

	private static final String PRODUCT_PAPER_BALANCE_MATERIAL = "product_paper_balance_material";

	@Autowired
	private ProductPaperBalanceMaterialService productPaperBalanceMaterialService;

	// @Test
	public void test_inquiry() {
		System.out.println("- - - - - test_inquiry");
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		List<ProductPaperBalanceMaterialVo> voList = productPaperBalanceMaterialService.inquiryByWs(formVo);
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
		try (FileOutputStream Output = new FileOutputStream(PATH.TEST_PATH + PRODUCT_PAPER_BALANCE_MATERIAL + "." + FILE_EXTENSION.XLSX)) {
			byte[] outArray = productPaperBalanceMaterialService.export(formVo);
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

		List<ProductPaperBalanceMaterialVo> inputVoList = new ArrayList<>();
		ProductPaperBalanceMaterialVo vo1 = new ProductPaperBalanceMaterialVo();
		vo1.setMaterialDesc("materialDesc1");
		vo1.setBalanceByAccountQty("100");
		vo1.setBalanceByStockQty("105");
		vo1.setBalanceByCountQty("110");
		vo1.setMaxDiffQty2("100");
		vo1.setMaxDiffQty1("10");
		inputVoList.add(vo1);
		ProductPaperBalanceMaterialVo vo2 = new ProductPaperBalanceMaterialVo();
		vo2.setMaterialDesc("materialDesc2");
		vo2.setBalanceByAccountQty("200");
		vo2.setBalanceByStockQty("205");
		vo2.setBalanceByCountQty("220");
		vo2.setMaxDiffQty2("200");
		vo2.setMaxDiffQty1("20");
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

		productPaperBalanceMaterialService.save(formVo);
	}

//	@Test
	public void test_getPaperPrNumberList() {
		System.out.println("- - - - - test_getPaperPrNumberList");

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setAuditPlanCode("");

		List<String> paperPrNumberList = productPaperBalanceMaterialService.getPaperPrNumberList(formVo);
		paperPrNumberList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

}
