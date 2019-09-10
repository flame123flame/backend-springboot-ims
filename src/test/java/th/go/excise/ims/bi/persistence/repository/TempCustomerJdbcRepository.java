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
import th.go.excise.ims.bi.persistence.entity.TempCustomer;

@Repository
public class TempCustomerJdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(TempCustomerJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void batchInsert(List<TempCustomer> tempCustomerList) {
		logger.info("batchInsert tempCustomerList.size()={}", tempCustomerList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"CUS_ID",
			"CUSTOMER_NAME",
			"TIN",
			"PINNIT_ID"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("TEMP_BI_CUSTOMER", insertColumnNames);
		
		commonJdbcTemplate.batchUpdate(sql.toString(), tempCustomerList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<TempCustomer>() {
			public void setValues(PreparedStatement ps, TempCustomer tempCustomer) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(tempCustomer.getCusId());
				paramList.add(tempCustomer.getCustomerName());
				paramList.add(tempCustomer.getTin());
				paramList.add(tempCustomer.getPinnitId());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
