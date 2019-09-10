package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.UserRole;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<User> userList = userRepository.findAll();
		userList.forEach(r-> System.out.println(r.getCreatedDate()));
		Assert.assertNotEquals(0, userList.size());
	}

//	@Test
	public void test_findOne_Found() {
		System.out.println("- - - - - findOne_Found - - - - -");
		User user = userRepository.findById(1L).get();
		System.out.println(user);
		Assert.assertNotNull(user);
	}

//	@Test
	public void test_findOne_NotFound() {
		System.out.println("- - - - - findOne_NotFound - - - - -");
		User user = userRepository.findById(99L).get();
		System.out.println(user);
		Assert.assertNull(user);
	}

//	@Test
	public void test_findByUsername_Found() {
		System.out.println("- - - - - findByUsername_Found - - - - -");
		User user = userRepository.findByUsername("admin");
		System.out.println(user);
		Assert.assertNotNull(user);
	}

//	@Test
	public void test_findByUsername_NotFound() {
		System.out.println("- - - - - findByUsername_NotFound - - - - -");
		User user = userRepository.findByUsername("notExist");
		System.out.println(user);
		Assert.assertNull(user);
	}

//	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		long count = userRepository.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}

//	@Test
	public void test_save_insert() {
		System.out.println("- - - - - save_insert - - - - -");
		User user = new User();
		user.setUsername("mock");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		user.setEnabled(FLAG.Y_FLAG);
		user.setAccountNonExpired(FLAG.Y_FLAG);
		user.setCredentialsNonExpired(FLAG.Y_FLAG);
		user.setAccountNonLocked(FLAG.Y_FLAG);
		userRepository.save(user);
	}

	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		User user = userRepository.findById(82L).get();
		user.setPassword(new BCryptPasswordEncoder().encode("password2"));
		user.setEnabled(FLAG.N_FLAG);
		userRepository.save(user);
	}

	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		userRepository.deleteById(82L);
	}
	
//	@Test
	public void wsNa() {
		System.out.println("- - - - - wsNa - - - - -");
//		webServiceExciseService.testWs();
	}

//	@Test
	public void test_CreateTrainingUser() {
		User user = null;
		String username = null;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
//		// INT User
//		for (int i = 1; i <= 162; i++) {
//			user = new User();
//			username = "int" + StringUtils.leftPad(String.valueOf(i), 3, "0");
//			user.setUsername(username);
//			user.setPassword(passwordEncoder.encode(username));
//			user.setEnabled(FLAG.Y_FLAG);
//			user.setAccountNonExpired(FLAG.Y_FLAG);
//			user.setCredentialsNonExpired(FLAG.Y_FLAG);
//			user.setAccountNonLocked(FLAG.Y_FLAG);
//			user.setExciseBaseControl("TRN_INTERNAL_AUDIT");
//			userRepository.save(user);
//		}
//		
//		// TAX User
//		for (int i = 1; i <= 175; i++) {
//			user = new User();
//			username = "tax" + StringUtils.leftPad(String.valueOf(i), 3, "0");
//			user.setUsername(username);
//			user.setPassword(passwordEncoder.encode(username));
//			user.setEnabled(FLAG.Y_FLAG);
//			user.setAccountNonExpired(FLAG.Y_FLAG);
//			user.setCredentialsNonExpired(FLAG.Y_FLAG);
//			user.setAccountNonLocked(FLAG.Y_FLAG);
//			user.setExciseBaseControl("TRN_TAX_AUDITOR");
//			userRepository.save(user);
//		}
		
		// INT User
		for (int i = 1; i <= 190; i++) {
			username = "opr" + StringUtils.leftPad(String.valueOf(i), 3, "0");
			System.out.println(passwordEncoder.encode(username));
			
//			user = new User();
//			user.setUsername(username);
//			user.setPassword(passwordEncoder.encode(username));
//			user.setEnabled(FLAG.Y_FLAG);
//			user.setAccountNonExpired(FLAG.Y_FLAG);
//			user.setCredentialsNonExpired(FLAG.Y_FLAG);
//			user.setAccountNonLocked(FLAG.Y_FLAG);
//			user.setExciseBaseControl("OPERATION_AUDIT");
//			userRepository.save(user);
		}
	}
	
	@Test
	public void test_createAdminUser() {
//		User user = new User();
//		user.setUsername("admin");
//		user.setPassword(new BCryptPasswordEncoder().encode("password"));
//		user.setEnabled(FLAG.Y_FLAG);
//		user.setAccountNonExpired(FLAG.Y_FLAG);
//		user.setCredentialsNonExpired(FLAG.Y_FLAG);
//		user.setAccountNonLocked(FLAG.Y_FLAG);
//		userRepository.save(user);
		
		User user = userRepository.findByUsername("admin");
		List<String> roleCodeList = Arrays.asList(new String[] {
			ROLE.USER,
			ROLE.ADMIN,
			ROLE.IA_ADM,
			ROLE.TA_ADM,
			ROLE.OA_ADM
		});
		
		UserRole userRole = null;
		for (String roleCode : roleCodeList) {
			userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(roleRepository.findByRoleCode(roleCode).getRoleId());
			userRoleRepository.save(userRole);
		}
		
	}
	
}
