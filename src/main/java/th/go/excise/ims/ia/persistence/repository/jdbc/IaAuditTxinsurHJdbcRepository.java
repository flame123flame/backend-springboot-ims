package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditTxinsurH;
import th.go.excise.ims.ia.vo.Int061401FilterVo;
import th.go.excise.ims.ia.vo.Ws_Reg4000Vo;

@Repository
public class IaAuditTxinsurHJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaAuditTxinsurH> getAudittxInsurNoList() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT AUDIT_TXINSUR_NO ");
		sql.append(" FROM IA_AUDIT_TXINSUR_H ");
		sql.append(" ORDER BY AUDIT_TXINSUR_NO ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaAuditTxinsurH> dropdownList = this.commonJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(IaAuditTxinsurH.class));

		return dropdownList;
	}
	
	public List<Ws_Reg4000Vo> getDataFilter(Int061401FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT R.*, D.DUTY_GROUP_NAME, A.* ");
		sql.append(" FROM IA_AUDIT_TXINSUR_D1 A ");
		sql.append(" LEFT JOIN TA_WS_REG4000 R ON A.NEW_REG_ID  = R.NEW_REG_ID ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP D ON R.DUTY_CODE = D.DUTY_GROUP_CODE ");
		sql.append(" WHERE A.IS_DELETED = 'N'  ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND A.OFFICE_CODE = ? ");
			params.add(request.getOfficeCode());
		}
		
//		if (StringUtils.isNotBlank(request.getRegDateStart())) {
//			sql.append(" AND A.REG_DATE >= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
//		}
//		
//		if (StringUtils.isNotBlank(request.getRegDateEnd())) {
//			sql.append(" AND A.REG_DATE <= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
//		}
		
		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
			sql.append(" AND A.AUDIT_TXINSUR_NO = ? ");
			params.add(request.getAuditTxinsurNo());
		}

		sql.append(" ORDER BY A.CREATED_DATE DESC");

//		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ws_Reg4000Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ws_Reg4000Vo.class));

		return datas;
	}
	
	public Integer countDatafilter(Int061401FilterVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT R.*, D.DUTY_GROUP_NAME, A.* ");
		sql.append(" FROM IA_AUDIT_TXINSUR_D1 A ");
		sql.append(" LEFT JOIN TA_WS_REG4000 R ON A.NEW_REG_ID  = R.NEW_REG_ID ");
		sql.append(" LEFT JOIN EXCISE_DUTY_GROUP D ON R.DUTY_CODE = D.DUTY_GROUP_CODE ");
		sql.append(" WHERE A.IS_DELETED = 'N'  ");

		if (StringUtils.isNotBlank(request.getOfficeCode())) {
			sql.append(" AND A.OFFICE_CODE = ? ");
			params.add(request.getOfficeCode());
		}
		
//		if (StringUtils.isNotBlank(request.getRegDateStart())) {
//			sql.append(" AND A.REG_DATE >= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateStart(), ConvertDateUtils.DD_MM_YYYY));
//		}
//		
//		if (StringUtils.isNotBlank(request.getRegDateEnd())) {
//			sql.append(" AND A.REG_DATE <= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getRegDateEnd(), ConvertDateUtils.DD_MM_YYYY));
//		}
		
		if (StringUtils.isNotBlank(request.getAuditTxinsurNo())) {
			sql.append(" AND A.AUDIT_TXINSUR_NO = ? ");
			params.add(request.getAuditTxinsurNo());
		}
		
		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}
}
