package th.go.excise.ims.ia.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ia.persistence.entity.IaWsLic6010;

public class IaWsLic6010RepositoryImpl implements IaWsLic6010RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(IaWsLic6010RepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(List<IaWsLic6010> iaWsLic6010List) {
		logger.info("batchInsert iaWsLic6010List.size()={}", iaWsLic6010List.size());
		
		final int BATCH_SIZE = 2000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"IA_WS_LIC6010_ID",
			"CURRENT_LIC_ID",
			"NEW_LIC_NO",
			"NEW_LIC_DATE",
			"NEW_LIC_ID"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("IA_WS_LIC6010", insertColumnNames, "IA_WS_LIC6010_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, iaWsLic6010List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<IaWsLic6010>() {
			public void setValues(PreparedStatement ps, IaWsLic6010 iaWsLic6010) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Insert Statement
				paramList.add(iaWsLic6010.getCurrentLicId());
				paramList.add(iaWsLic6010.getNewLicNo());
				paramList.add(iaWsLic6010.getNewLicDate());
				paramList.add(iaWsLic6010.getNewLicId());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
