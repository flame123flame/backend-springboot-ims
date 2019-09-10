package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.DataTablesPageRequest;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;
import th.co.baiwa.buckwaframework.preferences.vo.MessageCriteriaVo;

@Service
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	private final MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public DataTableAjax<Message> searchByCriteria(MessageCriteriaVo criteria) {
		logger.info("searchByCriteria criteria={}", ToStringBuilder.reflectionToString(criteria, ToStringStyle.JSON_STYLE));
		
		Pageable pageable = new DataTablesPageRequest(criteria.getStart(), 10);
		
		Integer count = messageRepository.countByCriteria(criteria);
		List<Message> messageList = messageRepository.findByCriteria(criteria, pageable);
		
		DataTableAjax<Message> result = new DataTableAjax<Message>();
		result.setRecordsTotal(count);
		result.setRecordsFiltered(count);
		result.setData(messageList);
		
		return result;
	}
	
	public List<Message> getAll() {
		logger.info("getAll");
		
		return messageRepository.findAll();
	}
	
	public Message getById(Long messageId) {
		logger.info("getById messageId={}", messageId);
		
		return messageRepository.findById(messageId).orElse(null);
	}
	
	public long count() {
		logger.info("count");
		
		return messageRepository.count();
	}
	
	public Message addMessage(Message message) {
		logger.info("addMessage");
		
		messageRepository.save(message);
		
		return message;
	}
	
	public void updateMessage(Message message) {
		logger.info("updateMessage");
		
		Message updateMessage = messageRepository.findById(message.getMessageId()).orElse(null);
		updateMessage.setMessageEn(message.getMessageEn());
		updateMessage.setMessageTh(message.getMessageTh());
		
		messageRepository.save(updateMessage);
	}
	
	public void deleteById(Long messageId) {
		logger.info("deleteById messageId={}", messageId);
		
		messageRepository.deleteById(messageId);
	}
	
}
