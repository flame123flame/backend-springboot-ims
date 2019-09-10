package th.co.baiwa.buckwaframework.accesscontrol.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class RoleServiceTest {
	
	@Autowired
	private RoleService roleService;
	
	@Test
	public void test_getRoleAll() {
		List<Role> resultList = roleService.getRoleAll();
		assertNotEquals(0, resultList.size());
		for (Role role : resultList) {
			System.out.println(role);
		}
	}
	
	@Test
	public void test_getRoleById() {
		
	}
	
	@Test
	public void test_getRoleCount() {
		
	}
	
	@Test
	public void test_createRole() {
		
	}
	
}
