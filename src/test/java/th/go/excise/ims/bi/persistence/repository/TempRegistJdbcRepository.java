package th.go.excise.ims.bi.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.bi.persistence.entity.TempRegist;

@Repository
public class TempRegistJdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TempRegistJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void batchInsert(List<TempRegist> tempRegistList) {
		logger.info("batchInsert tempRegistList.size()={}", tempRegistList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"REG_ID",
			"NEWREG_ID",
			"FAC_ID",
			"FAC_SEQ",
			"FAC_ADDRSEQ",
			"CUS_SEQ",
			"CUS_ADDRSEQ",
			"DIVCODE",
			"OFFCODE",
			"REG_REASON",
			"REG_STATUS",
			"STATUS_DATE",
			"ACTIVE_FLAG",
			"UPD_DATE",
			"CUS_ID"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("TEMP_BI_REGIST", insertColumnNames);
		
		commonJdbcTemplate.batchUpdate(sql.toString(), tempRegistList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<TempRegist>() {
			public void setValues(PreparedStatement ps, TempRegist tempRegist) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(tempRegist.getRegId());
				paramList.add(tempRegist.getNewregId());
				paramList.add(tempRegist.getFacId());
				paramList.add(tempRegist.getFacSeq());
				paramList.add(tempRegist.getFacAddrseq());
				paramList.add(tempRegist.getCusSeq());
				paramList.add(tempRegist.getCusAddrseq());
				paramList.add(tempRegist.getDivcode());
				paramList.add(tempRegist.getOffcode());
				paramList.add(tempRegist.getRegReason());
				paramList.add(tempRegist.getRegStatus());
				paramList.add(tempRegist.getStatusDate());
				paramList.add(tempRegist.getActiveFlag());
				paramList.add(tempRegist.getUpdDate());
				paramList.add(tempRegist.getCusId());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
