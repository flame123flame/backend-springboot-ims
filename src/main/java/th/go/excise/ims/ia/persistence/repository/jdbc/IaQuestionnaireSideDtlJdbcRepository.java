package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireSideDtl;
import th.go.excise.ims.ia.vo.Int02010101Vo;

@Repository
public class IaQuestionnaireSideDtlJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int02010101Vo> findByIdSide(BigDecimal idSide) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE_DTL WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID_SIDE = ? ");
		sqlBuilder.append(" AND QTN_LEVEL = 1 ");
		sqlBuilder.append(" ORDER BY SEQ ASC");
		params.add(idSide);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int02010101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),new BeanPropertyRowMapper(Int02010101Vo.class));
		return datas;
	}
	
	public List<Int02010101Vo> findDtlByIdSide(BigDecimal idSide, BigDecimal seq, BigDecimal idHeading) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE_DTL WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID_SIDE = ? ");
		sqlBuilder.append(" AND QTN_LEVEL = 2 ");
		sqlBuilder.append(" AND SEQ = ? ");
		sqlBuilder.append(" AND ID_HEADING = ? ");
		sqlBuilder.append(" ORDER BY SEQ_DTL ASC");
		params.add(idSide);
		params.add(seq);
		params.add(idHeading);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int02010101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),new BeanPropertyRowMapper(Int02010101Vo.class));
		return datas;
	}
	
	public List<Int02010101Vo> findDtlsByIdSide(BigDecimal idSide, BigDecimal seq, BigDecimal idHeading) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE_DTL WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID_SIDE = ? ");
		sqlBuilder.append(" AND QTN_LEVEL = 3 ");
		sqlBuilder.append(" AND SEQ = ? ");
		sqlBuilder.append(" AND ID_HEADING = ? ");
		sqlBuilder.append(" ORDER BY SEQ_DTL ASC");
		params.add(idSide);
		params.add(seq);
		params.add(idHeading);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Int02010101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(),new BeanPropertyRowMapper(Int02010101Vo.class));
		return datas;
	}
	
	public IaQuestionnaireSideDtl findById(BigDecimal id) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM  IA_QUESTIONNAIRE_SIDE_DTL WHERE 1=1 AND IS_DELETED = 'N' ");
		sqlBuilder.append(" AND ID = ? ");
		params.add(id);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		IaQuestionnaireSideDtl data = (IaQuestionnaireSideDtl) commonJdbcTemplate.queryForObject(sqlBuilder.toString(), params.toArray(),new BeanPropertyRowMapper(IaQuestionnaireSideDtl.class));
		return data;
	}
	
}
