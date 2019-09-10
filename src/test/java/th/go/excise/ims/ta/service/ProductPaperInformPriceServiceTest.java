package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import th.go.excise.ims.ta.vo.ProductPaperFormVo;

public class ProductPaperInformPriceServiceTest {
	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_IN_FORM_PRICE = "product_paper_in_form_price";

	ProductPaperInformPriceService productPaperInformPriceService = new ProductPaperInformPriceService();

	@Test
	public void test_exportProductPaperInformPrice() {
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_IN_FORM_PRICE + TYPE)) {
			byte[] outArray = productPaperInformPriceService.export(formVo);
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_IN_FORM_PRICE + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PRODUCT_PAPER_IN_FORM_PRICE + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}
