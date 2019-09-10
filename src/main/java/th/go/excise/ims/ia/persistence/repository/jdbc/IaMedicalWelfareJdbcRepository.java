package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.vo.Int120701FilterVo;
import th.go.excise.ims.ia.vo.Int120701Type6006Vo;
import th.go.excise.ims.ia.vo.int120701Type7131Vo;

@Repository
public class IaMedicalWelfareJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<int120701Type7131Vo> filterByDate(Int120701FilterVo dataFilter) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT IMW.* FROM IA_MEDICAL_WELFARE IMW WHERE 1=1 ");
		if (StringUtils.isNotBlank(dataFilter.getStartDate())) {
			sql.append(" AND TRUNC(IMW.CREATED_DATE) >= TO_DATE(?,'YYYYMMDD') ");
			params.add(ConvertDateUtils.changPaettleStringDate(dataFilter.getStartDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH, ConvertDateUtils.LOCAL_EN));
		}
		if (StringUtils.isNotBlank(dataFilter.getEndDate())) {
			sql.append(" AND TRUNC(IMW.CREATED_DATE) <= TO_DATE(?,'YYYYMMDD') ");
			params.add(ConvertDateUtils.changPaettleStringDate(dataFilter.getEndDate(), ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.YYYYMMDD, ConvertDateUtils.LOCAL_TH, ConvertDateUtils.LOCAL_EN));
		}
		sql.append(" AND IMW.IS_DELETED = 'N' ");
		sql.append(" ORDER BY IMW.ID DESC ");
		List<int120701Type7131Vo> dataRes = commonJdbcTemplate.query(sql.toString(), params.toArray(), filterRowmapper);
		return dataRes;
	}

	private RowMapper<int120701Type7131Vo> filterRowmapper = new RowMapper<int120701Type7131Vo>() {
		@Override
		public int120701Type7131Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			int120701Type7131Vo vo = new int120701Type7131Vo();
			vo.setId(rs.getString("ID"));
			vo.setFullName(rs.getString("FULL_NAME"));
			vo.setGender(rs.getString("GENDER"));
			vo.setBirthdate(rs.getString("BIRTHDATE"));
			vo.setSiblingsOrder(rs.getString("SIBLINGS_ORDER"));
			vo.setPosition(rs.getString("POSITION"));
			vo.setAffiliation(rs.getString("AFFILIATION"));
			vo.setStatus(rs.getString("STATUS"));
			vo.setDisease(rs.getString("DISEASE"));
			vo.setHospitalName(rs.getString("HOSPITAL_NAME"));
			vo.setHospitalOwner(rs.getString("HOSPITAL_OWNER"));			
			vo.setTreatedDateFrom(ConvertDateUtils.formatDateToString(rs.getDate("TREATED_DATE_FROM"),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setTreatedDateTo(ConvertDateUtils.formatDateToString(rs.getDate("TREATED_DATE_TO"),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));		
			vo.setTotalMoney(rs.getString("TOTAL_MONEY"));
			vo.setReceiptQt(rs.getString("RECEIPT_QT"));
			vo.setClaimStatus(rs.getString("CLAIM_STATUS"));
			vo.setClaimMoney(rs.getString("CLAIM_MONEY"));
			vo.setCreatedDate(ConvertDateUtils.formatDateToString(rs.getDate("CREATED_DATE"),
					ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			return vo;
		}
	};

}
