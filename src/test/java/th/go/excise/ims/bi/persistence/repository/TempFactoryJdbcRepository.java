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
import th.go.excise.ims.bi.persistence.entity.TempFactory;

@Repository
public class TempFactoryJdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TempFactoryJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void batchInsert(List<TempFactory> tempFactoryList) {
		logger.info("batchInsert tempFactoryList.size()={}", tempFactoryList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"FAC_ID",
			"CUS_ID",
			"FACTORY_NAME",
			"CAPITAL",
			"EMP_TOT",
			"FAC_ADDRSEQ",
			"CUS_ADDRSEQ",
			"FAC_SEQ",
			"CUS_SEQ"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("TEMP_BI_FACTORY", insertColumnNames);
		
		commonJdbcTemplate.batchUpdate(sql.toString(), tempFactoryList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<TempFactory>() {
			public void setValues(PreparedStatement ps, TempFactory tempFactory) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(tempFactory.getFacId());
				paramList.add(tempFactory.getCusId());
				paramList.add(tempFactory.getFactoryName());
				paramList.add(tempFactory.getCapital());
				paramList.add(tempFactory.getEmpTot());
				paramList.add(tempFactory.getFacAddrseq());
				paramList.add(tempFactory.getCusAddrseq());
				paramList.add(tempFactory.getFacSeq());
				paramList.add(tempFactory.getCusSeq());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
