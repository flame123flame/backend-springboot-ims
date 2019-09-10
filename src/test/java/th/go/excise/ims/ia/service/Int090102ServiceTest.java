package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.Application;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseHoliday;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class Int090102ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;
	
	@Autowired
	private Int090102Service int090102Service;

//	@Test
	public void test_generateReport() throws Exception {
		Int090102Service int090102Service = new Int090102Service();

		IaEmpWorkingHdrVo formVo = new IaEmpWorkingHdrVo();
		formVo.setWorkingMonth("05/05/2562");

		byte[] reportFile = int090102Service.generateReport(formVo);
		IOUtils.write(reportFile,
				new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.IA_EMP_WORKING))));
	}
	
	@Test
	public void test_getHoliday() throws Exception {
		String workingDate = "01/05/2018";
		List<ExciseHoliday> holiday = int090102Service.getHoliday(workingDate);
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		holiday.forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		System.out.println("*********************************************");
	}
}
