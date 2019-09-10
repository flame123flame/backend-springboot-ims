package th.go.excise.ims.ta.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ServicePaperTaxAmtAdditionalServiceTest {
	
	private static final String OUTPUT_PATH = "/tmp/excise/ims/report";
	
	@Autowired
	private ServicePaperTaxAmtAdditionalService servicePaperTaxAmtAdditionalService;
	
	@Test
	public void test_inquiry() {
		ServicePaperFormVo formVo = new ServicePaperFormVo();
		formVo.setAuditPlanCode("");
		formVo.setNewRegId("01055491068592001");
		formVo.setDutyGroupId("1702");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("12/2561");
		//formVo.setPaperSvNumber("");
		
		ServicePaperVo vo = servicePaperTaxAmtAdditionalService.inquiry(formVo);
		System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE));
		vo.getDataTableAjax().getData().forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SHORT_PREFIX_STYLE));
		});
	}
	
	//@Test
	public void test_export() {
		ServicePaperFormVo formVo = new ServicePaperFormVo();
		formVo.setAuditPlanCode("");
		formVo.setNewRegId("01055491068592001");
		formVo.setDutyGroupId("1702");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("12/2561");
		//formVo.setPaperSvNumber("");
		
		String fileName = "servicePaper01_" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + ".xlsx";
		
		try (FileOutputStream os = new FileOutputStream(OUTPUT_PATH + "/" + fileName)) {
			byte[] bytes = servicePaperTaxAmtAdditionalService.export(formVo);
			os.write(bytes);
			System.out.println("Creating excel " + fileName + " Done");
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}
	
	//@Test
	public void test_upload() {
		ServicePaperFormVo formVo = new ServicePaperFormVo();
		formVo.setAuditPlanCode("");
		formVo.setNewRegId("01055491068592001");
		formVo.setDutyGroupId("1702");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("12/2561");
		//formVo.setPaperSvNumber("");
		
	}
	
	//@Test
	public void test_save() {
		ServicePaperFormVo formVo = new ServicePaperFormVo();
		formVo.setAuditPlanCode("");
		formVo.setNewRegId("01055491068592001");
		formVo.setDutyGroupId("1702");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("12/2561");
		//formVo.setPaperSvNumber("");
		
	}
	
}
