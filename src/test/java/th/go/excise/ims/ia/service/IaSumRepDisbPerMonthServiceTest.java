package th.go.excise.ims.ia.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class IaSumRepDisbPerMonthServiceTest {
	
	@Autowired
	private IaGfdrawAccountService iaGfdrawAccountService;
	
	@Test 
	public void addDataByExcel() throws FileNotFoundException, IOException {
		MockMultipartFile file = new MockMultipartFile("import_hardware_test_20181106", new FileInputStream(new File("F:/เอกสารพี่นก/รายงานสรุปรายการเบิกจ่ายของหน่วยงาน.xlsx")));
		iaGfdrawAccountService.addDataByExcel(file);
	}
}
