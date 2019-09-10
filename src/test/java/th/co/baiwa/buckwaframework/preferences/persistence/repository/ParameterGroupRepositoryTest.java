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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class ParameterGroupRepositoryTest {
	
	@Autowired
	private ParameterGroupRepository parameterGroupRepository;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<ParameterGroup> paramGroupList = parameterGroupRepository.findAll();
		paramGroupList.forEach(System.out::println);
		Assert.assertNotEquals(0, paramGroupList.size());
	}
	
	@Test
	public void test_findOne() {
		System.out.println("- - - - - findOne - - - - -");
		ParameterGroup paramGroup = parameterGroupRepository.findById(1L).get();
		System.out.println(paramGroup);
		Assert.assertNotNull(paramGroup);
	}
	
	//@Test
	public void test_save_new() {
		System.out.println("- - - - - save_new - - - - -");
		ParameterGroup paramGroup = new ParameterGroup();
		paramGroup.setParamGroupCode("UNIT_TEST");
		paramGroup.setParamGroupDesc("For Unit Testing");
		parameterGroupRepository.save(paramGroup);
		Assert.assertNotNull(paramGroup.getParamGroupId());
	}
	
	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		ParameterGroup paramGroup = parameterGroupRepository.findById(21L).get();
		paramGroup.setParamGroupDesc("For Unit Testing edit");
		parameterGroupRepository.save(paramGroup);
	}
	
	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		parameterGroupRepository.deleteById(21L);
	}
	
}
