package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import th.go.excise.ims.ta.vo.ProductPaperFormVo;

public class ProductPaperOutputGoodsServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_OUTPUT_GOODS = "product_paper_output_goods";

	ProductPaperOutputGoodsService productPaperOutputGoodsService = new ProductPaperOutputGoodsService();

	@Test
	public void test_exportProductPaperOutputGoods() {
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_OUTPUT_GOODS + TYPE)) {
			byte[] outArray = productPaperOutputGoodsService.export(formVo);
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_OUTPUT_GOODS + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PRODUCT_PAPER_OUTPUT_GOODS + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}
