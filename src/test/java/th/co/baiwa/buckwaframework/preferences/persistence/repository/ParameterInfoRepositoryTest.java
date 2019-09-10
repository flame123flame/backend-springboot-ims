package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ParameterInfoRepositoryTest {
	
	@Autowired
	private ParameterInfoRepository parameterInfoRepository;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<ParameterInfo> paramInfoList = parameterInfoRepository.findAll();
		System.out.println(paramInfoList);
		Assert.assertNotEquals(0, paramInfoList.size());
	}
	
	@Test
	public void test_findOne() {
		System.out.println("- - - - - findOne - - - - -");
		ParameterInfo paramInfo = parameterInfoRepository.findById(1L).get();
		System.out.println(paramInfo);
		Assert.assertNotNull(paramInfo);
	}
	
	@Test
	public void test_findByParamGroupId() {
		System.out.println("- - - - - findByParamGroupId - - - - -");
		List<ParameterInfo> paramInfoList = parameterInfoRepository.findByParamGroupCode("SYSTEM_CONFIG");
		System.out.println(paramInfoList);
		Assert.assertNotEquals(0, paramInfoList.size());
	}
	
	//@Test
	public void test_save_new() {
		System.out.println("- - - - - save_new - - - - -");
		ParameterInfo paramInfo = new ParameterInfo();
		paramInfo.setParamGroupCode("Test Group");
		paramInfo.setParamCode("TEST2");
		paramInfo.setValue1("v1");
		paramInfo.setValue2("v2");
		paramInfo.setValue3("v3");
		paramInfo.setValue4("v4");
		paramInfo.setValue5("v5");
		paramInfo.setValue6("v6");
		paramInfo.setIsDefault(FLAG.N_FLAG);
		paramInfo.setSortingOrder(1);
		parameterInfoRepository.save(paramInfo);
	}
	
	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		ParameterInfo paramInfo = parameterInfoRepository.findById(33L).get();
		paramInfo.setValue2("edit");
		parameterInfoRepository.save(paramInfo);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		parameterInfoRepository.deleteById(33L);
	}
	
}
