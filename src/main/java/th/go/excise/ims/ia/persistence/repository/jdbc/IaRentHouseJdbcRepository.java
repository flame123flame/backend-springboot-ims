package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int120701FilterVo;
import th.go.excise.ims.ia.vo.Int120701Type6006Vo;

@Repository
public class IaRentHouseJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Int120701Type6006Vo> filterByDate(Int120701FilterVo dataFilter) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT IRH.* FROM IA_RENT_HOUSE IRH WHERE 1=1 ");
		if (StringUtils.isNotBlank(dataFilter.getStartDate())) {
			sql.append(" AND TRUNC(IRH.CREATED_DATE) >= TO_DATE(?,'YYYYMMDD') ");
			params.add(ConvertDateUtils.changPaettleStringDate(dataFilter.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH, ConvertDateUtils.LOCAL_EN));
		}
		if (StringUtils.isNotBlank(dataFilter.getEndDate())) {
			sql.append(" AND TRUNC(IRH.CREATED_DATE) <= TO_DATE(?,'YYYYMMDD') ");
			params.add(ConvertDateUtils.changPaettleStringDate(dataFilter.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH, ConvertDateUtils.LOCAL_EN));
		}
		sql.append(" AND IRH.IS_DELETED = 'N' ");
		List<Int120701Type6006Vo> dataRes = commonJdbcTemplate.query(sql.toString(), params.toArray(), filterRowmapper);
		return dataRes;
	}

	private RowMapper<Int120701Type6006Vo> filterRowmapper = new RowMapper<Int120701Type6006Vo>() {
		@Override
		public Int120701Type6006Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int120701Type6006Vo vo = new Int120701Type6006Vo();
			vo.setId(rs.getLong("RENT_HOUSE_ID"));
			vo.setName(rs.getString("NAME"));
			vo.setRanking(rs.getString("POSITION"));
			vo.setSector(rs.getString("AFFILIATION"));
			vo.setTypeUse(rs.getString("PAYMENT_COST"));
			vo.setTotalMonth(rs.getInt("TOTAL_MONTH"));
			vo.setTotalReceipt(rs.getInt("RECEIPTS"));
			vo.setTotalMoney(rs.getBigDecimal("TOTAL_WITHDRAW"));
			vo.setPeriod(rs.getString("PERIOD"));
			vo.setCreateDateStr(ConvertDateUtils.formatDateToString(rs.getDate("CREATED_DATE"),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			return vo;
		}
	};
}
