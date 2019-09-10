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
import th.go.excise.ims.ta.vo.ProductPaperRelationProducedGoodsVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)

public class ProductPaperRelationProducedGoodsServiceTest {

	private static final String PRODUCT_PAPER_RELATION_PRODUCED_GOODS = "product_paper_relation_produced_goods";

	@Autowired
	private ProductPaperRelationProducedGoodsService productPaperRelationProducedGoodsService;

	// @Test
	public void test_inquiry() {
		System.out.println("- - - - - test_inquiry");
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setNewRegId("09920020600391004");
		formVo.setDutyGroupId("7001");
		formVo.setStartDate("09/2561");
		formVo.setEndDate("10/2561");

		List<ProductPaperRelationProducedGoodsVo> voList = productPaperRelationProducedGoodsService.inquiryByWs(formVo);
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
		try (FileOutputStream Output = new FileOutputStream(PATH.TEST_PATH + PRODUCT_PAPER_RELATION_PRODUCED_GOODS + "." + FILE_EXTENSION.XLSX)) {
			byte[] outArray = productPaperRelationProducedGoodsService.export(formVo);
			Output.write(outArray);
			System.out.println("Creating excel");
		} catch (IOException e) {
			System.out.println("false excel");
		}
	}

	// @Test
	public void test_upload() {
		System.out.println("- - - - - test_upload");
	}

	@Test
	public void test_save() {
		System.out.println("- - - - - test_save");

		List<ProductPaperRelationProducedGoodsVo> inputVoList = new ArrayList<>();
		ProductPaperRelationProducedGoodsVo vo1 = new ProductPaperRelationProducedGoodsVo();
		vo1.setDocNo("T001");
		vo1.setMaterialDesc("materialDesc1");
		vo1.setInputMaterialQty("1000");
		vo1.setFormulaMaterialQty("200");
		vo1.setUsedMaterialQty("150");
		vo1.setRealUsedMaterialQty("100");
		vo1.setDiffMaterialQty("100");
		vo1.setMaterialQty("100");
		vo1.setGoodsQty("100");
		vo1.setDiffGoodsQty("100");
		vo1.setWasteGoodsPnt("100");
		vo1.setWasteGoodsQty("100");
		vo1.setBalanceGoodsQty("100");

		inputVoList.add(vo1);
		ProductPaperRelationProducedGoodsVo vo2 = new ProductPaperRelationProducedGoodsVo();
		vo2.setDocNo("T002");
		vo2.setMaterialDesc("materialDesc1");
		vo2.setInputMaterialQty("2000");
		vo2.setFormulaMaterialQty("200");
		vo2.setUsedMaterialQty("150");
		vo2.setRealUsedMaterialQty("100");
		vo2.setDiffMaterialQty("100");
		vo2.setMaterialQty("200");
		vo2.setGoodsQty("200");
		vo2.setDiffGoodsQty("200");
		vo2.setWasteGoodsPnt("200");
		vo2.setWasteGoodsQty("200");
		vo2.setBalanceGoodsQty("200");
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

		productPaperRelationProducedGoodsService.save(formVo);
	}

//	@Test
	public void test_getPaperPrNumberList() {
		System.out.println("- - - - - test_getPaperPrNumberList");

		ProductPaperFormVo formVo = new ProductPaperFormVo();
		formVo.setAuditPlanCode("");

		List<String> paperPrNumberList = productPaperRelationProducedGoodsService.getPaperPrNumberList(formVo);
		paperPrNumberList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}

}
