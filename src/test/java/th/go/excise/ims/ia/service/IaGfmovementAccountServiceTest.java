package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class IaGfmovementAccountServiceTest {
//	@Autowired
//	private IaGfledgerAccountService iaGfledgerAccountService;
	
	@Test 
	public void addDataByExcel() throws Exception {
		
		IaGfmovementAccountService iaGfledgerAccountService = new IaGfmovementAccountService();
		MockMultipartFile file = new MockMultipartFile("import_hardware_test_20181106", new FileInputStream(new File("F:\\เอกสารพี่นก\\excel\\02-05-2562\\รายงานเคลื่อนไหวเงินฝากคลัง.xlsx")));
		iaGfledgerAccountService.addDataByExcel(file);
	}
}
