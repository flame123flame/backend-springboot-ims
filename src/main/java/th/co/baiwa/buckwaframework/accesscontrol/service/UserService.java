package th.co.baiwa.buckwaframework.accesscontrol.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.repository.UserRepository;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
private final UserRepository userRepository;
	
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public DataTableAjax<User> list(UserFormVo userFormVo) {
		logger.info("searchByCriteria criteria={}", ToStringBuilder.reflectionToString(userFormVo, ToStringStyle.JSON_STYLE));
	
		if("A".equals(userFormVo.getEnabled())) {
			userFormVo.setEnabled("");
		}
	
		List<User> userList = userRepository.findByCriteria(userFormVo);
		DataTableAjax<User> result = new DataTableAjax<User>();
		result.setDraw(userFormVo.getDraw() + 1);
		result.setData(userList);
		result.setRecordsTotal(userRepository.countByCriteria(userFormVo));
		result.setRecordsFiltered(userRepository.countByCriteria(userFormVo));


		
		return result;
	}

	public List<User> getUserAll() {
		logger.info("getAllUser");
		return userRepository.findAll();
	}

	public User getUserById(String idStr) {
		logger.info("getUserById");
		return userRepository.findById(Long.valueOf(idStr)).get();
	}

	public long getUserCount() {
		logger.info("getUserCount");
		return userRepository.count();
	}

	public User createUser(User user) {
		logger.info("createUser");
		userRepository.save(user);
		return user;
	}

	public User updateUser(String idStr, User user) {
		logger.info("updateUser");
		User data = userRepository.findById(Long.valueOf(idStr)).get();
		data.setEnabled(user.getEnabled());
		data.setUserId(user.getUserId());
		return userRepository.save(data);
	}


	public User deleteUser(String idStr) {
		logger.info("deleteRole");
		User data = userRepository.findById(Long.valueOf(idStr)).get();
		 userRepository.deleteById(Long.valueOf(idStr));
		return data;
	}

}
