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

@Repository
public class IncomeDsJdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(IncomeDsJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void batchInsert(List<String> lineList) {
		logger.info("batchInsert lineList.size()={}", lineList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"TIME_ID",
			"REG_SK",
			"OFFCODE_REC",
			"OFFCODE_OWN",
			"PRODUCT_GRP_CD",
			"IMPORT_STATUS",
			"CHANNEL_REC_CD",
			"LOC_RETE",
			"LOCOTH_RATE",
			"LOCEXP_RATE",
			"CHASSIS_NO",
			"ENGINE_NO",
			"TAX_AMT",
			"TAX_ADD_AMT",
			"TAX_PEN_AMT",
			"TAX_REDUCE_AMT",
			"TAX_CREDIT_ED_AMT",
			"TAX_NETTAX_AMT",
			"TAX_LOC_AMT",
			"TAX_CREDIT_MOI",
			"TAX_NETLOC_AMT",
			"TAX_PAID_AMT",
			"TAX_LOCOTH_AMT",
			"TAX_LOCEXP_AMT",
			"CHARGE_CASE_AMT",
			"BRIBE_AMT",
			"REWARD_AMT",
			"BENEFIT_AMT",
			"LIC_AMT",
			"LIC_LOC_AMT",
			"LIC_PAID_AMT",
			"MISCELLANEOUS_AMT",
			"TOTAL_MISCELL_AMT",
			"NUM_OF_DEFENDANT",
			"NUM_OF_LIC",
			"CUSTOM_AMT",
			"ESTIMATE",
			"ESTIMATE_YEAR",
			"ESTIMATE_KPI",
			"ESTIMATE_KPI_YEAR",
			"CREATE_DATE",
			"START_DAY_OF_MONTH",
			"END_DAY_OF_MONTH",
			"SRC_NAME",
			"CLOSE_STATUS",
			"IMPORT_STATUS_DESC",
			"RECEIPT_NO",
			"RECEIPT_DATE",
			"NEWREG_ID",
			"CUS_ID",
			"FAC_ID",
			"REG_ID",
			"CUS_ADDRSEQ",
			"FAC_ADDRSEQ",
			"SRC_TAX",
			"SRC_BRANCH"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("BI_INCOME_DS", insertColumnNames);
		
		commonJdbcTemplate.batchUpdate(sql.toString(), lineList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<String>() {
			public void setValues(PreparedStatement ps, String line) throws SQLException {
				String[] lines = line.split("\\t", -1);
				List<Object> paramList = new ArrayList<>();
				for (String data : lines) {
					paramList.add(data);
				}
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
