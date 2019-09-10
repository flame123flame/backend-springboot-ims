package th.go.excise.ims.common.util;

import org.junit.Test;

import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;

public class ExciseUtilsTest {
	
	private String[] officeCodes = new String[] {
		"000000",
		"005000",
		"010000",
		"020000",
		"100000",
		"010100",
		"100300",
		"010201",
		"0000",
		"00",
		"",
		null
	};
	
	@Test
	public void test_isCentral() {
		for (String officeCode : officeCodes) {
			System.out.println("isCentral() : " + officeCode + " ==> " + ExciseUtils.isCentral(officeCode));
		}
	}

	@Test
	public void test_isSector() {
		for (String officeCode : officeCodes) {
			System.out.println("isSector() : " + officeCode + " ==> " + ExciseUtils.isSector(officeCode));
		}
	}

	@Test
	public void test_isArea() {
		for (String officeCode : officeCodes) {
			System.out.println("isArea() : " + officeCode + " ==> " + ExciseUtils.isArea(officeCode));
		}
	}

	@Test
	public void test_isBranch() {
		for (String officeCode : officeCodes) {
			System.out.println("isBranch() : " + officeCode + " ==> " + ExciseUtils.isBranch(officeCode));
		}
	}

	@Test
	public void test_whereInLocalOfficeCode() {
		for (String officeCode : officeCodes) {
			System.out.println("test_whereInLocalOfficeCode() : " + officeCode + " ==> " + ExciseUtils.whereInLocalOfficeCode(officeCode));
		}
	}
	
	@Test
	public void test_buildCustAddress() throws Exception {
		RegMaster60 regMaster60 = new RegMaster60();
		regMaster60.setCusAddrno("12");
		regMaster60.setCusBuildname("");
		regMaster60.setCusFloorno("");
		regMaster60.setCusRoomno("");
		regMaster60.setCusMoono("1");
		regMaster60.setCusVillage("");
		regMaster60.setCusSoiname("-");
		regMaster60.setCusThnname("-");
		regMaster60.setCusTambolcode("102901");
		regMaster60.setCusTambolname("บางซื่อ");
		regMaster60.setCusAmphurcode("102900");
		regMaster60.setCusAmphurname("บางซื่อ");
		regMaster60.setCusProvincecode("100000");
		regMaster60.setCusProvincename("กรุงเทพมหานคร");
		regMaster60.setCusZipcode("10800");
		System.out.println(ExciseUtils.buildCusAddress(regMaster60));
	}

}
