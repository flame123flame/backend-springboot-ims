package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;
import th.go.excise.ims.ia.vo.Int0101PlanDayVo;

@Repository
public class IaPlanDayActivityJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<IaPlanDayActivity> findplanDayActByidDtl(BigDecimal idDtl) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_PLAN_DAY_ACTIVITY WHERE 1=1 AND IS_DELETED='N' ");
		if (idDtl != null) {
			sql.append(" AND PLAN_DTL_ID = ? ");
			params.add(idDtl);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaPlanDayActivity> planDayAct = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaPlanDayActivity.class));
		return planDayAct;
	}
	
	public List<IaPlanDayActivity> findActivity(BigDecimal idDtl, String activity) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_PLAN_DAY_ACTIVITY WHERE 1=1 AND IS_DELETED='N' ");
		if (idDtl != null) {
			sql.append(" AND PLAN_DTL_ID = ? ");
			params.add(idDtl);
		}
		
		if (StringUtils.isNotBlank(activity)) {
			sql.append(" AND ACTIVITY = ? ");
			params.add(activity);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaPlanDayActivity> planDayAct = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaPlanDayActivity.class));
		return planDayAct;
	}
	
	public List<Int0101PlanDayVo> getDataDtlAndAtc(BigDecimal idDtl) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT A.PLAN_DAY_ACTIVITY_ID, A.DATE_START_ACTIVITY, A.DATE_END_ACTIVITY, ");
		sql.append(" 	A.ACTIVITY, D.POSITION, D.RESPONSIBLE_PERSON ");
		sql.append(" FROM IA_PLAN_DTL D ");
		sql.append(" LEFT JOIN IA_PLAN_DAY_ACTIVITY A ");
		sql.append(" 	ON D.PLAN_DTL_ID = A.PLAN_DTL_ID ");
		sql.append(" WHERE D.IS_DELETED = 'N' ");
		sql.append(" 	AND A.IS_DELETED = 'N' ");

		if (idDtl != null) {
			sql.append(" AND D.PLAN_DTL_ID = ? ");
			params.add(idDtl);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0101PlanDayVo> planDayAct = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int0101PlanDayVo.class));
		return planDayAct;
	}

}
