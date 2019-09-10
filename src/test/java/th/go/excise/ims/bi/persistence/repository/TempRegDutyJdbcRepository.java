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
import th.go.excise.ims.bi.persistence.entity.TempRegDuty;

@Repository
public class TempRegDutyJdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TempRegDutyJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void batchInsert(List<TempRegDuty> tempTempRegDutyList) {
		logger.info("batchInsert tempTempRegDutyList.size()={}", tempTempRegDutyList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"NEWREG_ID",
			"REG_ID",
			"GROUP_ID",
			"PAYRTN_FLAG",
			"FACTYPE_OIL",
			"REG_DATE",
			"PROD_DATE",
			"ACTIVE_FLAG"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("TEMP_BI_REG_DUTY", insertColumnNames);
		
		commonJdbcTemplate.batchUpdate(sql.toString(), tempTempRegDutyList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<TempRegDuty>() {
			public void setValues(PreparedStatement ps, TempRegDuty tempRegDuty) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(tempRegDuty.getNewregId());
				paramList.add(tempRegDuty.getRegId());
				paramList.add(tempRegDuty.getGroupId());
				paramList.add(tempRegDuty.getPayrtnFlag());
				paramList.add(tempRegDuty.getFactypeOil());
				paramList.add(tempRegDuty.getRegDate());
				paramList.add(tempRegDuty.getProdDate());
				paramList.add(tempRegDuty.getActiveFlag());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
