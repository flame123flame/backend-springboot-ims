package th.co.baiwa.buckwaframework.preferences.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.preferences.service.MessageService;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.Message;

@Controller
@RequestMapping("/api/preferences/message")
public class MessageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	private final MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/{messageCode}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Message by Code"
	)
	@ResponseBody
	public ResponseData<Message> getMessageByCode(@PathVariable("messageCode") String messageCode) {
		logger.info("getMessageByCode messageCode={}", messageCode);
		
		ResponseData<Message> response = new ResponseData<Message>();
		Message message = ApplicationCache.getMessage(messageCode);
		if (message != null) {
			response.setData(message);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Message Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
//	@GetMapping
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Get All Message",
//		hidden = true
//	)
//	public ResponseEntity<?> getAll() {
//		logger.info("getAll");
//		
//		ResponseData<Map<String, Message>> response = new ResponseData<>();
//		response.setData(ApplicationCache.getMessages());
//		
//		return new ResponseEntity<ResponseData<Map<String, Message>>>(response, HttpStatus.OK);
//	}
//	
//	@PostMapping("search")
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Search Message by Criteria"
//	)
//	public ResponseEntity<?> search(
//			@RequestParam(name = "draw") Integer draw,
//			@RequestParam(name = "start") Integer start,
//			@RequestParam(name = "length") Integer length,
//			String messageCode,
//			String messageEn,
//			String messageTh,
//			String messageType) {
//		
//		MessageCriteria criteria = new MessageCriteria();
//		criteria.setMessageCode(messageCode);
//		criteria.setMessageEn(messageEn);
//		criteria.setMessageTh(messageTh);
//		criteria.setMessageType(messageType);
//		logger.info("search by {}", criteria);
//		
//		DataTableAjax<Message> dataTableAjax = messageService.searchByCriteria(criteria);
//		
//		ResponseDataTable<Message> response = new ResponseDataTable<Message>();
//		response.setDraw(++draw);
//		response.setStart(start);
//		response.setLength(length);
//		response.setData(dataTableAjax.getData());
//		response.setRecordsTotal(dataTableAjax.getRecordsTotal());
//		response.setRecordsFiltered(dataTableAjax.getRecordsFiltered());
//		
//		return new ResponseEntity<ResponseDataTable<Message>>(response, HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Get Message by Id"
//	)
//	public ResponseEntity<?> getMessage(@PathVariable("id") long id) {
//		logger.info("getMessage [id={}]", id);
//		
//		Message message = messageService.getById(id);
//		ResponseData<Message> response = new ResponseData<Message>();
//		response.setData(message);
//		return new ResponseEntity<ResponseData<Message>>(response, HttpStatus.OK);
//	}
//	
//	@PostMapping
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Create Message"
//	)
//	public ResponseEntity<?> create(@RequestBody Message message, UriComponentsBuilder ucBuilder) {
//		logger.info("create [message={}]", message);
//		
//		Message newMessage = messageService.addMessage(message);
//		
//		ResponseData<Message> response = new ResponseData<Message>();
//		response.setData(newMessage);
//		
//		return new ResponseEntity<ResponseData<Message>>(response, HttpStatus.CREATED);
//	}
//	
//	@PutMapping
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Update Message"
//	)
//	public ResponseEntity<?> update(@RequestBody  Message message, UriComponentsBuilder ucBuilder) {
//		logger.info("update [message={}]", message);
//		
//		messageService.updateMessage(message);
//		
//		ResponseData<Message> response = new ResponseData<Message>();
//		response.setData(message);
//		
//		return new ResponseEntity<ResponseData<Message>>(response , HttpStatus.CREATED);
//	}
//	
//	@DeleteMapping("/{id}")
//	@ApiOperation(
//		tags = MODULE_NAME.PREFERENCES,
//		value = "Delete Message"
//	)
//	public ResponseEntity<?> delete(@PathVariable("id") List<Long> ids) {
//		logger.info("delete [id={}]", StringUtils.collectionToCommaDelimitedString(ids));
//		
//		for (Long id : ids) {
//			messageService.deleteById(id);
//		}
//		
//		return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);
//	}
	
}
