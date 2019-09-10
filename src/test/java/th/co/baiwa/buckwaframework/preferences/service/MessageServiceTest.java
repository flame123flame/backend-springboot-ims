package th.co.baiwa.buckwaframework.preferences.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.constant.MessageConstants.MESSAGE_TYPE;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class MessageServiceTest {
	
	@Autowired
	private MessageService messageService;
	
	@Test
	public void test_getMessageList() {
//		System.out.println("- - - - - getMessageList - - - - -");
//		List<Message> messageList = messageService.getMessageList(0, 10);
//		Assert.assertNotEquals(0, messageList.size());
//		for (Message message : messageList) {
//			System.out.println(message);
//		}
	}
	
	//@Test
	public void test_getMessageById_Found() {
		System.out.println("- - - - - getMessageById_Found - - - - -");
		Message message = messageService.getById(3L);
		Assert.assertNotNull(message);
		System.out.println(message);
	}
	
	//@Test
	public void test_getMessageCount() {
		System.out.println("- - - - - getMessageCount - - - - -");
		long count = messageService.count();
		Assert.assertNotEquals(0, count);
	}
	
	//@Test
	public void test_insertMessage() {
		System.out.println("- - - - - insertMessage - - - - -");
		Message message = new Message();
		message.setMessageCode("003");
		message.setMessageEn("desc eng");
		message.setMessageTh("desc thai");
		message.setMessageType(MESSAGE_TYPE.INFO);
		messageService.addMessage(message);
		
		Assert.assertNotNull(message.getMessageId());
	}
	
	//@Test
	public void test_updateMessage() {
		System.out.println("- - - - - updateMessage - - - - -");
		Message message = new Message();
		message.setMessageId(3L);
		message.setMessageCode("003");
		message.setMessageEn("desc eng 44");
		message.setMessageTh("desc thai 55");
		message.setMessageType(MESSAGE_TYPE.WARN);
		messageService.updateMessage(message);
	}
	
	//@Test
	public void test_deleteMessage() {
		System.out.println("- - - - - deleteMessage - - - - -");
//		messageService.deleteMessage(3L);
	}
	
}
