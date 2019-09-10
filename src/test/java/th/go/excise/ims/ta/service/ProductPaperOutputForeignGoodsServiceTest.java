package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import th.go.excise.ims.ta.vo.ProductPaperFormVo;

public class ProductPaperOutputForeignGoodsServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS = "product_paper_output_foreign_goods";

	ProductPaperOutputForeignGoodsService productPaperOutputForeignGoodsService = new ProductPaperOutputForeignGoodsService();

	@Test
	public void test_exportPayForeignFinishedGoods() {
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS + TYPE)) {
			byte[] outArray = productPaperOutputForeignGoodsService.export(formVo);
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}
