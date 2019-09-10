package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.vo.Int030101FormVo;
import th.go.excise.ims.ia.vo.Int030101Vo;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Repository
public class Int030101JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int030101Vo> list(Int030101FormVo form) {
		List<Int030101Vo> iaRiskFactorsMasterList = new ArrayList<Int030101Vo>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_RISK_FACTORS A INNER JOIN IA_RISK_FACTORS_CONFIG B ON A.ID = B.ID_FACTORS WHERE A.ID = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(form.getIdFactors());
		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaRiskFactorsMasterList;
	}



	private RowMapper<Int030101Vo> listRowmapper = new RowMapper<Int030101Vo>() {
		@Override
		public Int030101Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			
			Int030101Vo vo = new Int030101Vo();
			Int030101FormVo iarfm = new Int030101FormVo();

			iarfm.setRiskFactorsMaster(rs.getString("RISK_FACTORS"));
			iarfm.setSide(rs.getString("SIDE"));
			iarfm.setInfoUsedRiskDesc(rs.getString("INFO_USED_RISK_DESC"));
			iarfm.setRiskIndicators(rs.getString("RISK_INDICATORS"));
			iarfm.setRiskUnit(rs.getString("RISK_UNIT"));
			
			
			iarfm.setDateFrom(ConvertDateUtils.formatDateToString(rs.getDate("START_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			iarfm.setDateTo(ConvertDateUtils.formatDateToString(rs.getDate("END_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			
			vo.setInt030101FormVo(iarfm);
			return vo;
		}
	};

	public void deleteFactosData(String idFactors) {
		StringBuilder sql = new StringBuilder(" DELETE FROM IA_RISK_FACTORS_DATA WHERE ID_FACTORS = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(idFactors);
		commonJdbcTemplate.update(sql.toString(), params.toArray());
	}
}
