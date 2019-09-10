package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class OperationRepositoryTest {
	
	@Autowired
	private OperationRepository operationRepository;
	
	@Test
	public void test_findAll() {
		System.out.println("- - - - - findAll - - - - -");
		List<Operation> operationList = operationRepository.findAll();
		System.out.println(operationList);
		Assert.assertNotEquals(0, operationList.size());
	}

	@Test
	public void test_findOne_Found() {
		System.out.println("- - - - - findOne_Found - - - - -");
		Operation operation = operationRepository.findById(1L).get();
		System.out.println(operation);
		Assert.assertNotNull(operation);
	}

	@Test
	public void test_findOne_NotFound() {
		System.out.println("- - - - - findOne_NotFound - - - - -");
		Operation operation = operationRepository.findById(99L).get();
		System.out.println(operation);
		Assert.assertNull(operation);
	}

	@Test
	public void test_count() {
		System.out.println("- - - - - count - - - - -");
		long count = operationRepository.count();
		System.out.println(count);
		Assert.assertNotEquals(0, count);
	}

	//@Test
	public void test_save_insert() {
		System.out.println("- - - - - save_insert - - - - -");
		Operation operation = new Operation();
		operation.setOperationCode("unit operation code");
		operation.setOperationDesc("unit operation desc");
		operationRepository.save(operation);
	}

	//@Test
	public void test_save_update() {
		System.out.println("- - - - - save_update - - - - -");
		Operation operation = operationRepository.findById(3L).get();
		operation.setOperationDesc("edit");
		operationRepository.save(operation);
	}

	//@Test
	public void test_delete() {
		System.out.println("- - - - - delete - - - - -");
		operationRepository.deleteById(3L);
	}

}
