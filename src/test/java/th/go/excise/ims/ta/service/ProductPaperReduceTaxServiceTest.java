package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import th.go.excise.ims.ta.vo.ProductPaperFormVo;

public class ProductPaperReduceTaxServiceTest {

	private static final String PATH = "/tmp/";
	private static final String TYPE = ".xlsx";

	private static final String PRODUCT_PAPER_REDUCE_TAX = "product_paper_reduce_tax";

	ProductPaperReduceTaxService productPaperReduceTaxService = new ProductPaperReduceTaxService();

	@Test
	public void test_exportProductPaperReduceTax() {
		ProductPaperFormVo formVo = new ProductPaperFormVo();
		
		// set output
		try (FileOutputStream Output = new FileOutputStream(PATH + PRODUCT_PAPER_REDUCE_TAX + TYPE)) {
			byte[] outArray = productPaperReduceTaxService.export(formVo);
			Output.write(outArray);
			System.out.println("Creating excel" + "\n" + PRODUCT_PAPER_REDUCE_TAX + "\n" + "Done" + "\n");
		} catch (IOException e) {
			System.out.println(PRODUCT_PAPER_REDUCE_TAX + "\n" + "false" + "\n" + e.getMessage() + "\n" + e + "\n");
		}

	}
}
