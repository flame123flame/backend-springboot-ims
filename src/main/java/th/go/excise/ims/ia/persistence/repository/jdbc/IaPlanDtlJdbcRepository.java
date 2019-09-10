package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaPlanDtl;
import th.go.excise.ims.ia.vo.Int01DtlVo;

@Repository
public class IaPlanDtlJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaPlanDtl> findPlanDtlByidHdr(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_PLAN_DTL WHERE 1=1 AND IS_DELETED='N' ");
		if (idHdr != null) {
			sql.append(" AND PLAN_HDR_ID = ? ");
			params.add(idHdr);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaPlanDtl> planDtl = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaPlanDtl.class));
		return planDtl;
	}

	public List<IaPlanDtl> findPlanDtlGroupByInspectionWork() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT INSPECTION_WORK FROM IA_PLAN_DTL ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" GROUP BY INSPECTION_WORK ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaPlanDtl> inspectionWorkList = commonJdbcTemplate.query(sql.toString(),
				new BeanPropertyRowMapper(IaPlanDtl.class));
		return inspectionWorkList;
	}

	public List<Int01DtlVo> joinPlanDtlAndPlanDay(String budgetYear, String inspectionWork) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT D.PLAN_DTL_ID, D.PLAN_HDR_ID, D.ACTIVITY ACTIVITY_DTL, D.ACTIVITY_STATUS STATUS_DTL, ");
		sql.append(
				"	D.BUDGET_YEAR, D.FREQUENCY, D.INSPECTION_WORK, D.INSPECTOR, D.OFFICER, D.RESPONSIBLE_PERSON, D.UNIT,  ");
		sql.append(" 	A.PLAN_DAY_ACTIVITY_ID, A.ACTIVITY ACTIVITY_ACT , A.ACTIVITY_STATUS STATUS_ACT, ");
		sql.append(" 	A.DATE_START_ACTIVITY, A.DATE_END_ACTIVITY ");
		sql.append(" FROM IA_PLAN_DTL D ");
		sql.append(" LEFT JOIN IA_PLAN_DAY_ACTIVITY A ");
		sql.append(" ON D.PLAN_DTL_ID = A.PLAN_DTL_ID ");
		sql.append(" WHERE D.INSPECTION_WORK = ? ");
		sql.append(" 	AND D.BUDGET_YEAR = ? ");
		sql.append(" 	AND D.IS_DELETED = 'N' ");
		sql.append(" 	AND A.IS_DELETED = 'N' ");

		params.add(inspectionWork);
		params.add(budgetYear);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int01DtlVo> inspectionWorkList = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaPlanDtl.class));
		return inspectionWorkList;
	}

	public List<Int01DtlVo> findByIaPlanDtl(IaPlanDtl iaPlanDtl) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_PLAN_DTL ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" 	AND IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(iaPlanDtl.getInspectionWork())) {
			sql.append(" 	AND INSPECTION_WORK = ? ");
			params.add(iaPlanDtl.getInspectionWork());
		}
		if (StringUtils.isNotBlank(iaPlanDtl.getBudgetYear())) {
			sql.append(" 	AND BUDGET_YEAR = ? ");
			params.add(iaPlanDtl.getBudgetYear());
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int01DtlVo> iaPlanDtlList = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int01DtlVo.class));
		return iaPlanDtlList;
	}
	
	public Integer countSumFrequencyByInspectionWorkAndBudgetYear(IaPlanDtl iaPlanDtl) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT SUM(FREQUENCY) FROM IA_PLAN_DTL ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" 	AND IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(iaPlanDtl.getInspectionWork())) {
			sql.append(" 	AND INSPECTION_WORK = ? ");
			params.add(iaPlanDtl.getInspectionWork());
		}
		if (StringUtils.isNotBlank(iaPlanDtl.getBudgetYear())) {
			sql.append(" 	AND BUDGET_YEAR = ? ");
			params.add(iaPlanDtl.getBudgetYear());
		}

		Integer countSum = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
		return countSum;
	}

}
