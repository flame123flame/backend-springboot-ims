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
import th.go.excise.ims.ta.vo.ProductPaperInputGoodsVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)

public class ProductPaperInputGoodsServiceTest {

	private static final String PRODUCT_PAPER_INPUT_GOODS = "product_paper_input_goods";

	@Autowired
	private ProductPaperInputGoodsService productPaperInputGoodsService;

	// @Test
	public void test_inquiry() {
		System.out.println("- - - - - test_inquiry");

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		List<ProductPaperInputGoodsVo> voList = productPaperInputGoodsService.inquiryByWs(formVo);
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
		try (FileOutputStream Output = new FileOutputStream(PATH.TEST_PATH + PRODUCT_PAPER_INPUT_GOODS + "." + FILE_EXTENSION.XLSX)) {
			byte[] outArray = productPaperInputGoodsService.export(formVo);
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

		List<ProductPaperInputGoodsVo> inputVoList = new ArrayList<>();
		ProductPaperInputGoodsVo vo1 = new ProductPaperInputGoodsVo();
		vo1.setGoodsDesc("materialDesc1");
		vo1.setInputGoodsQty("1000");
		vo1.setInputMonthStatementQty("1000");
		vo1.setInputDailyAccountQty("1000");
		vo1.setMaxDiffQty1("100");
		vo1.setMaxDiffQty2("50");
		inputVoList.add(vo1);
		ProductPaperInputGoodsVo vo2 = new ProductPaperInputGoodsVo();
		vo1.setGoodsDesc("materialDesc2");
		vo1.setInputGoodsQty("1000");
		vo1.setInputMonthStatementQty("1000");
		vo1.setInputDailyAccountQty("1000");
		vo1.setMaxDiffQty1("100");
		vo1.setMaxDiffQty2("50");
		inputVoList.add(vo2);

		Gson gson = new Gson();
		String json = gson.toJson(inputVoList);

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");
		formVo.setAuditPlanCode("0014012562000016");
		formVo.setJson(json);

		productPaperInputGoodsService.save(formVo);
	}

//	@Test
	public void test_getPaperPrNumberList() {
		System.out.println("- - - - - test_getPaperPrNumberList");

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setAuditPlanCode("");

		List<String> paperPrNumberList = productPaperInputGoodsService.getPaperPrNumberList(formVo);
		paperPrNumberList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

}
