package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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
import th.go.excise.ims.ta.vo.FileUploadFormVo;
import th.go.excise.ims.ta.vo.FileUploadVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFileUploadServiceTest {
	
	@Autowired
	private TaFileUploadService taFileUploadService;
	
//	@Test
	public void test_upload() throws Exception {
		String mockFileName = "test-upload";
		String mockOrgFileName = "test-upload.txt";
		String mockFilePath = "/tmp/excise/ims/source/";
		String mockFileUpload = mockFilePath + mockOrgFileName;
		
		FileUploadFormVo formVo = new FileUploadFormVo();
		formVo.setModuleCode("PLAN_APPROVED");
		formVo.setRefNo("001401-2562-000001");
		formVo.setFile(new MockMultipartFile(mockFileName, mockOrgFileName, null, new FileInputStream(new File(mockFileUpload))));
		
		String uploadNumber = taFileUploadService.upload(formVo);
		System.out.println("uploadNumber=" + uploadNumber);
	}
	
	@Test
	public void test_getUploadFileList() {
		FileUploadFormVo formVo = new FileUploadFormVo();
		formVo.setModuleCode("PLAN_APPROVED");
		formVo.setRefNo("001401-2562-000001");
		
		List<FileUploadVo> voList = taFileUploadService.getUploadFileList(formVo);
		voList.forEach(e -> {
			System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.SIMPLE_STYLE));
		});
	}
	
}
