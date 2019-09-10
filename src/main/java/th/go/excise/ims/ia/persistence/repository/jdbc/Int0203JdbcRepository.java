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
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.vo.Int0203FormVo;
import th.go.excise.ims.ia.vo.Int02Vo;

@Repository
public class Int0203JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int02Vo> getDataFilter(Int0203FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT DISTINCT HDR.* ");
		sql.append(
				" ,(SELECT COUNT(*) FROM IA_QUESTIONNAIRE_MADE_HDR WHERE ID_HDR = HDR.id AND IS_DELETED = 'N') AS COUNT_MH_ALL ");
		sql.append(
				" ,(SELECT COUNT(*) FROM IA_QUESTIONNAIRE_MADE_HDR WHERE ID_HDR = HDR.id AND IS_DELETED = 'N' AND status = ?) AS COUNT_MH_CHECK ");
		sql.append(
				" ,(SELECT COUNT(*) FROM IA_QUESTIONNAIRE_MADE_HDR WHERE ID_HDR = HDR.id AND IS_DELETED = 'N' AND status != ?) AS COUNT_MH_DONT_CHECK ");
		sql.append(" FROM IA_QUESTIONNAIRE_HDR HDR ");
		sql.append(" INNER JOIN IA_QUESTIONNAIRE_MADE_HDR MDR ");
		sql.append(" ON HDR.ID = MDR.ID_HDR ");
		sql.append(" WHERE 1=1 AND HDR.IS_DELETED='N' AND MDR.IS_DELETED='N' ");
		sql.append(" AND (HDR.STATUS= ? ");
		sql.append(" OR HDR.STATUS= ? ");
		sql.append(" OR HDR.STATUS= ? ) ");
		params.add(IaConstants.IA_STATUS.STATUS_3_CODE);
		params.add(IaConstants.IA_STATUS.STATUS_3_CODE);
		params.add(IaConstants.IA_STATUS.STATUS_4_CODE);
		params.add(IaConstants.IA_STATUS.STATUS_5_CODE);
		params.add(IaConstants.IA_STATUS.STATUS_6_CODE);
		if (StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND HDR.BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		if (StringUtils.isNotBlank(request.getCreatedBy())) {
			sql.append(" AND HDR.CREATED_BY LIKE ? ");
			params.add("%" + request.getCreatedBy() + "%");
		}
		if (StringUtils.isNotBlank(request.getStartDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			sql.append(" AND TRUNC(HDR.CREATED_DATE) >= ? ");
			sql.append(" AND TRUNC(HDR.CREATED_DATE) <= ? ");
			/* convert string to date */
			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH));
			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH));
		}
		if (StringUtils.isNotBlank(request.getNameQtn())) {
			sql.append(" AND UPPER(HDR.QTN_HEADER_NAME) LIKE ?");
			params.add("%" + request.getNameQtn().toUpperCase() + "%");
		}
		sql.append(" ORDER BY HDR.CREATED_DATE ASC");

//		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int02Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int02Vo.class));

		return datas;
	}
}
