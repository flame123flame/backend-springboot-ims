package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.vo.Int02FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Repository
public class IaQuestionnaireHdrJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int02Vo> getDataFilter(Int02FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE 1=1 AND IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}

//		if(StringUtils.isNotBlank(request.getCreatedBy())) {
//			sql.append(" AND UPPER(CREATED_BY) LIKE ?");
//			params.add("%" + request.getCreatedBy().toUpperCase() + "%");
//		}

//		if(StringUtils.isNotBlank(request.getNameQtn())){
//           sql.append(" AND UPPER(QTN_HEADER_NAME) LIKE ?");
//            params.add("%" + request.getNameQtn().toUpperCase() + "%");
//        }

//		if(StringUtils.isNotBlank(request.getStartDate())) {
//			sql.append(" AND TRUNC(CREATED_DATE) >= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//
//		}
//		if(StringUtils.isNotBlank(request.getEndDate())){
//			sql.append(" AND TRUNC(CREATED_DATE) <= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//		}
		sql.append(" ORDER BY CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int02Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),
				new BeanPropertyRowMapper(Int02Vo.class));

		return datas;
	}

	public List<Int02Vo> getDataFilterStatusTreeFour(Int02FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE 1=1 AND IS_DELETED='N' ");
		sql.append(" AND (STATUS = " + IaConstants.IA_STATUS.STATUS_3_CODE);
		sql.append(" OR STATUS = " + IaConstants.IA_STATUS.STATUS_4_CODE + ") ");
		if (StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}

		sql.append(" ORDER BY CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int02Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),
				new BeanPropertyRowMapper(Int02Vo.class));

		return datas;
	}

	public Integer countDatafilterStatusTreeFour(Int02FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE IS_DELETED='N' ");
		sql.append(" AND (STATUS = " + IaConstants.IA_STATUS.STATUS_3_CODE);
		sql.append(" OR STATUS = " + IaConstants.IA_STATUS.STATUS_4_CODE + ") ");
		if (StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}

		sql.append(" ORDER BY CREATED_DATE DESC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	/* count datatable */
	public Integer countDatafilter(Int02FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}

//		if(StringUtils.isNotBlank(request.getCreatedBy())) {
//			sql.append(" AND UPPER(CREATED_BY) LIKE ?");
//			params.add("%" + request.getCreatedBy().toUpperCase() + "%");
//		}

//		if(StringUtils.isNotBlank(request.getNameQtn())){
//           sql.append(" AND UPPER(QTN_HEADER_NAME) LIKE ?");
//            params.add("%" + request.getNameQtn().toUpperCase() + "%");
//        }

//		if(StringUtils.isNotBlank(request.getStartDate()) && StringUtils.isNotBlank(request.getEndDate())) {
//			sql.append(" AND TRUNC(CREATED_DATE) >= ? ");
//			sql.append(" AND TRUNC(CREATED_DATE) <= ? ");
//			/* convert string to date */
//			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//		}
		sql.append(" ORDER BY CREATED_DATE DESC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public IaQuestionnaireHdr findOne(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_HDR WHERE IS_DELETED='N' ");
		sql.append(" AND ID=? ");
		params.add(id);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		IaQuestionnaireHdr data = (IaQuestionnaireHdr) commonJdbcTemplate.queryForObject(sql.toString(),
				params.toArray(), new BeanPropertyRowMapper(IaQuestionnaireHdr.class));

		return data;
	}

	public BigDecimal updateStatus(BigDecimal idHdr, String status) {
		StringBuilder sql = new StringBuilder(" UPDATE IA_QUESTIONNAIRE_HDR SET STATUS = ? WHERE ID = ? ");
		List<Object> params = new ArrayList<Object>();

		params.add(status);
		params.add(idHdr);

		commonJdbcTemplate.update(sql.toString(), params.toArray());

		return idHdr;
	}
}
