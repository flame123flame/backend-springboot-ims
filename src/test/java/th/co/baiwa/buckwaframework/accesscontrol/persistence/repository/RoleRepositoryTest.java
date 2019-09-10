package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;

//	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<Role> roleList = roleRepository.findAll();
		System.out.println(roleList);
		Assert.assertNotEquals(0, roleList.size());
	}

//	@Test
	public void test_findOne_Found() {
		System.out.println("- - - - - findOne_Found - - - - -");
		Role role = roleRepository.findById(1L).get();
		System.out.println(role);
		Assert.assertNotNull(role);
	}

//	@Test
	public void test_findOne_NotFound() {
		System.out.println("- - - - - findOne_NotFound - - - - -");
		Role role = roleRepository.findById(99L).get();
		System.out.println(role);
		Assert.assertNull(role);
	}

//	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		long count = roleRepository.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}

	//@Test
	public void test_save_insert() {
		System.out.println("- - - - - save_insert - - - - -");
		Role role = new Role();
		role.setRoleCode("unit role code");
		role.setRoleDesc("unit role desc");
		roleRepository.save(role);
	}

	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		Role role = roleRepository.findById(3L).get();
		role.setRoleDesc("edit");
		roleRepository.save(role);
	}

	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		roleRepository.deleteById(3L);
	}
	
	@Test
	public void test_initialRole() {
		
		List<Role> roleList = new ArrayList<>();
		
		Role roleUser = new Role();
		roleUser.setRoleCode(ROLE.USER);
		roleUser.setRoleDesc("Normal User");
		roleList.add(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setRoleCode(ROLE.ADMIN);
		roleAdmin.setRoleDesc("Administrator");
		roleList.add(roleAdmin);
		
		Role roleInternalAudit = new Role();
		roleInternalAudit.setRoleCode(ROLE.IA_ADM);
		roleInternalAudit.setRoleDesc("Internal Audit");
		roleList.add(roleInternalAudit);
		
		Role roleTaxAudit = new Role();
		roleTaxAudit.setRoleCode(ROLE.TA_ADM);
		roleTaxAudit.setRoleDesc("Tax Audit");
		roleList.add(roleTaxAudit);
		
		Role roleOperatorAudit = new Role();
		roleOperatorAudit.setRoleCode(ROLE.OA_ADM);
		roleOperatorAudit.setRoleDesc("Operator Audit");
		roleList.add(roleOperatorAudit);
		
//		Role roleExportAudit = new Role();
//		roleExportAudit.setRoleCode(ROLE.EA);
//		roleExportAudit.setRoleDesc("Export Audit");
//		roleList.add(roleExportAudit);
				
		roleRepository.saveAll(roleList);
	}

}
