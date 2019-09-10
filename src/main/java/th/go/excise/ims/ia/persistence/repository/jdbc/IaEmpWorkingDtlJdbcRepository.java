package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int091201FormSearchVo;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Repository
public class IaEmpWorkingDtlJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int091201Vo> getList(Int091201FormSearchVo res) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT WD.USER_NAME , WD.USER_POSITION , COUNT(0)  DAY FROM IA_EMP_WORKING_DTL WD ");
		sql.append(" WHERE WD.IS_DELETED = 'N' ");
		sql.append(" AND WD.WORKING_FLAG = '2' ");
		sql.append(" AND WD.REIMBURSE_EXP_FLAG = 'Y' ");
		if (StringUtils.isNotEmpty(res.getSector())) {
			if (StringUtils.isEmpty(res.getArea()) || "0".equals(res.getArea())) {
				String area = res.getSector().substring(0, 2);
				sql.append(" AND WD.USER_OFFCODE Like ? ");
				params.add(area + "%");
			} else if (StringUtils.isEmpty(res.getBranch()) || "0".equals(res.getBranch())) {
				String branch = res.getArea().substring(0, 4);
				sql.append(" AND WD.USER_OFFCODE Like ? ");
				params.add(branch + "%");
			} else if (StringUtils.isNotEmpty(res.getBranch()) && !"0".equals(res.getBranch())) {
				sql.append(" AND WD.USER_OFFCODE = ? ");
				params.add(res.getBranch());
			}
		}

		if (StringUtils.isNotEmpty(res.getAuWorkingMonth())) {

			Date date = ConvertDateUtils.parseStringToDate(res.getAuWorkingMonth(), ConvertDateUtils.MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
			String dateStr = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.MM_YYYY,
					ConvertDateUtils.LOCAL_EN);

			String month = dateStr.split("/")[0];
			String year = dateStr.split("/")[1];
			sql.append(" AND EXTRACT( MONTH FROM \"WORKING_DATE\" ) IN ( ?, ? ) ");
			params.add(month);
			params.add(month);
			sql.append(" AND EXTRACT( YEAR FROM \"WORKING_DATE\" ) IN ( ?, ? ) ");
			params.add(year);
			params.add(year);
		}

//		EXTRACT( MONTH FROM "WORKING_DATE" ) IN ( 5, 5 ) and EXTRACT( YEAR FROM "WORKING_DATE" ) IN ( 2019, 2019 )
		sql.append(" GROUP BY WD.USER_NAME ,WD.USER_POSITION ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int091201Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int091201Vo.class));

		return datas;
	}

	public String generateAuditIncNo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT IA_AUDIT_WORKING_NO_SEQ.NEXTVAL FROM DUAL");
		String dataRes = commonJdbcTemplate.queryForObject(sql.toString(), String.class);
		return StringUtils.leftPad(dataRes, 8, "0");
	}
}
