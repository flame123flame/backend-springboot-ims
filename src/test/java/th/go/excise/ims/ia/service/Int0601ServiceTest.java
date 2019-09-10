package th.go.excise.ims.ia.service;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.go.excise.ims.Application;
import th.go.excise.ims.ia.vo.IaAuditIncD1Vo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class Int0601ServiceTest {

	@Autowired
	private Int0601Service int0601Service;
	
//	@Test
	public void test_findTab1ByCriteria() {
		Int0601RequestVo formVo = new Int0601RequestVo();
		formVo.setOfficeReceive("010100");
		formVo.setReceiptDateFrom("01/10/2561");
		formVo.setReceiptDateTo("31/10/2561");
		
		List<IaAuditIncD1Vo> voList = int0601Service.findTab1ByCriteria(formVo);
		voList.forEach(e -> {
			System.out.println(e.getReceiptNo() + " " + e.getWasteReceiptFlag());
		});
	}

	@Test
	public void test_export() throws Exception {
		String auditIncNo = "INC-010100/00000081";
		String fileName = "int0601";
		
		byte[] bytes = int0601Service.export(auditIncNo);
		IOUtils.write(bytes, new FileOutputStream(PATH.TEST_PATH + fileName + "." + FILE_EXTENSION.XLSX));
	}

}
