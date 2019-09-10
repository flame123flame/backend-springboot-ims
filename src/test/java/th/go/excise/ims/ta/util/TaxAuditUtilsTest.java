package th.go.excise.ims.ta.util;

import org.junit.Test;

import th.go.excise.ims.common.constant.ProjectConstants.TAX_COMPARE_TYPE;

public class TaxAuditUtilsTest {
	
	@Test
	public void test_getWorksheetDateRangeVo_22M() {
		System.out.println("Case 22 Month");
		String dateStart = "05/2559";
		String dateEnd = "03/2560";
		int dateRange = 22;
		String compType = TAX_COMPARE_TYPE.MONTH;
		
		TaxAuditUtils.getWorksheetDateRangeVo(dateStart, dateEnd, dateRange, compType);
	}
	
	@Test
	public void test_getWorksheetDateRangeVo_24M() {
		System.out.println("Case 24 Month");
		String dateStart = "05/2559";
		String dateEnd = "04/2560";
		int dateRange = 24;
		String compType = TAX_COMPARE_TYPE.HALF;
		
		TaxAuditUtils.getWorksheetDateRangeVo(dateStart, dateEnd, dateRange, compType);
	}
	
	@Test
	public void test_getWorksheetDateRangeVo_26M() {
		System.out.println("Case 26 Month");
		String dateStart = "05/2559";
		String dateEnd = "05/2560";
		int dateRange = 26;
		String compType = TAX_COMPARE_TYPE.HALF;
		
		TaxAuditUtils.getWorksheetDateRangeVo(dateStart, dateEnd, dateRange, compType);
	}
	
}
