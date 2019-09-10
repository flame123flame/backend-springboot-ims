package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int061402FilterVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;

@Repository
public class Int061402JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ws_Reg4000Vo> getDataFilter(Int061402FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT ");

//		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append(" A.AUDIT_TXINSUR_NO, ");
//		}

		sql.append(" D.DUTY_GROUP_NAME, R.* ");
		sql.append(" FROM TA_WS_REG4000 R ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP D ON R.DUTY_CODE  = D.DUTY_GROUP_CODE ");

//		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append("  LEFT JOIN (SELECT S.NEW_REG_ID, MAX(S.AUDIT_TXINSUR_NO) AUDIT_TXINSUR_NO ");
//			sql.append("  FROM IA_AUDIT_TXINSUR_D1 S GROUP BY S.NEW_REG_ID) A ");
//			sql.append("  ON A.NEW_REG_ID = R.NEW_REG_ID ");
//			sql.append("  AND A.AUDIT_TXINSUR_NO != ? ");
//			params.add(request.getAuditTxinsurNo());
//		}

		sql.append(" WHERE R.IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND R.OFFICE_CODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getRegDateStart())) {
			sql.append(" AND R.REG_DATE >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
		}

		if (StringUtils.isNotBlank(request.getRegDateEnd())) {
			sql.append(" AND R.REG_DATE <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		}
		
//		if(StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append(" AND A.AUDIT_TXINSUR_NO IS NOT NULL ");
//		}

		sql.append(" ORDER BY R.REG_DATE DESC");

		// String limit = OracleUtils.limitForDatable(sql.toString(),
		// request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ws_Reg4000Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ws_Reg4000Vo.class));

		return datas;
	}

	public Integer countDatafilter(Int061402FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT ");

//		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append(" A.AUDIT_TXINSUR_NO, ");
//		}

		sql.append(" D.DUTY_GROUP_NAME, R.* ");
		sql.append(" FROM TA_WS_REG4000 R ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP D ON R.DUTY_CODE  = D.DUTY_GROUP_CODE ");

//		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
//			sql.append("  LEFT JOIN (select * from IA_AUDIT_TXINSUR_D1 WHERE AUDIT_TXINSUR_NO != ?) A ");
//			sql.append("  ON  A.NEW_REG_ID = R.NEW_REG_ID ");
//			params.add(request.getAuditTxinsurNo());
//		}

		sql.append(" WHERE R.IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND R.OFFICE_CODE = ? ");
			params.add(request.getOfficeCode());
		}

		if (StringUtils.isNotBlank(request.getRegDateStart())) {
			sql.append(" AND R.REG_DATE >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
		}

		if (StringUtils.isNotBlank(request.getRegDateEnd())) {
			sql.append(" AND R.REG_DATE <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		}

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}
	
	public List<Ws_Reg4000Vo> findDataNotEqualAuditTxinsurNo(Int061402FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT R.*, E.DUTY_GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP E ");
		sql.append(" 	ON R.DUTY_CODE = E.DUTY_GROUP_CODE ");
		sql.append(" WHERE R.IS_DELETED = 'N' ");
		sql.append(" 	AND R.OFFICE_CODE = ? ");
		params.add(request.getOfficeCode());
		
		if (StringUtils.isNotBlank(request.getRegDateStart()) && !"-".equals(request.getRegDateStart())) {
			sql.append(" AND R.REG_DATE >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
		}
		
		if (StringUtils.isNotBlank(request.getRegDateEnd()) && !"-".equals(request.getRegDateEnd())) {
			sql.append(" AND R.REG_DATE <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		}
		
		sql.append(" 	AND R.NEW_REG_ID ");
		sql.append(" 		NOT IN ");
		sql.append(" 		( ");
		sql.append(" 		SELECT B.NEW_REG_ID ");
		sql.append(" 		FROM IA_AUDIT_TXINSUR_D1 B ");
		sql.append(" 		WHERE B.AUDIT_TXINSUR_NO = ? ");
		sql.append(" 		) ");
		params.add(request.getAuditTxinsurNo());

		// String limit = OracleUtils.limitForDatable(sql.toString(),
		// request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ws_Reg4000Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ws_Reg4000Vo.class));

		return datas;
	}

	public Integer countDataNotEqualAuditTxinsurNo(Int061402FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT R.*, E.DUTY_GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP E ");
		sql.append(" 	ON R.DUTY_CODE = E.DUTY_GROUP_CODE ");
		sql.append(" WHERE R.IS_DELETED = 'N' ");
		sql.append(" 	AND R.OFFICE_CODE = ? ");
		params.add(request.getOfficeCode());
		
		if (StringUtils.isNotBlank(request.getRegDateStart()) && !"-".equals(request.getRegDateStart())) {
			sql.append(" AND R.REG_DATE >= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
		}
		
		if (StringUtils.isNotBlank(request.getRegDateEnd()) && !"-".equals(request.getRegDateEnd())) {
			sql.append(" AND R.REG_DATE <= ? ");
			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
		}
		
		sql.append(" 	AND R.NEW_REG_ID ");
		sql.append(" 		NOT IN ");
		sql.append(" 		( ");
		sql.append(" 		SELECT B.NEW_REG_ID ");
		sql.append(" 		FROM IA_AUDIT_TXINSUR_D1 B ");
		sql.append(" 		WHERE B.AUDIT_TXINSUR_NO = ? ");
		sql.append(" 		) ");
		params.add(request.getAuditTxinsurNo());

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

}
