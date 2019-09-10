package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.MySqlUtils;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.vo.MessageCriteriaVo;

public class MessageRepositoryImpl implements MessageRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageRepositoryImpl.class);
	
	private final CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public MessageRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	
	private void buildSearchQuery(StringBuilder sql, List<Object> params, MessageCriteriaVo criteria) {
		sql.append(" SELECT message_id, message_code, message_en, message_th, message_type ");
		sql.append(" FROM sys_message ");
		sql.append(" WHERE is_deleted = ? ");
		
		params.add(FLAG.N_FLAG);
		
		if (StringUtils.isNotBlank(criteria.getMessageCode())) {
			sql.append(" AND message_code LIKE ? ");
			params.add(criteria.getMessageCode());
		}
		
		if (StringUtils.isNotBlank(criteria.getMessageEn())) {
			sql.append(" AND message_en LIKE ? ");
			params.add(criteria.getMessageEn());
		}
		
		if (StringUtils.isNotBlank(criteria.getMessageTh())) {
			sql.append(" AND message_th LIKE ? ");
			params.add(criteria.getMessageTh());
		}
		
		if (StringUtils.isNotBlank(criteria.getMessageType())) {
			sql.append(" AND message_type = ? ");
			params.add(criteria.getMessageType());
		}
	}
	
	@Override
	public Integer countByCriteria(MessageCriteriaVo criteria) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, criteria);
		
		int count = commonJdbcTemplate.queryForObject(MySqlUtils.countForDataTable(sql), params.toArray(), Integer.class);
		
		logger.info("count={}", count);
		
		return count;
	}
	
	@Override
	public List<Message> findByCriteria(MessageCriteriaVo criteria, Pageable pageable) {
		logger.debug("findByCriteria message.messageCode={}, message.messageEn={}, message.messageTh={}, message.messageType={}, pageable={}",
			criteria.getMessageCode(), criteria.getMessageEn(), criteria.getMessageTh(), criteria.getMessageType(), pageable);
		
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildSearchQuery(sql, params, criteria);
		
		sql.append(" ORDER BY message_code ");
		
		List<Message> resultList = commonJdbcTemplate.query(
			MySqlUtils.limitForDataTable(sql.toString(), pageable),
			params.toArray(),
			new RowMapper<Message>() {
				@Override
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
					Message message = new Message();
					message.setMessageId(rs.getLong("message_id"));
					message.setMessageCode(rs.getString("message_code"));
					message.setMessageEn(rs.getString("message_en"));
					message.setMessageTh(rs.getString("message_th"));
					message.setMessageType(rs.getString("message_type"));
					return message;
				}
			}
		);
		
		logger.info("resultList.size()={}", resultList.size());
		
		return resultList;
	}
	
}
