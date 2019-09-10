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
public class PrestaxDsJdbcRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(PrestaxDsJdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void batchInsert(List<String> lineList) {
		logger.info("batchInsert lineList.size()={}", lineList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"TIME_ID",
			"OFFCODE_REC",
			"OFFCODE_OWN",
			"PRODUCT_CD",
			"BRAND_MAIN_CD",
			"BRAND_SECOND_CD",
			"IMPORT_STATUS",
			"REG_SK",
			"UNIT_CD",
			"SIZE_UNIT_CD",
			"MODEL_CODE",
			"CAPA",
			"DEGREE",
			"TAX_AMT",
			"ADD_AMT",
			"PEN_AMT",
			"EXCEPT_AMT",
			"CREDIT_AMT",
			"REDUCE_AMT",
			"TOTAL_TAX_AMT",
			"VOLUMN",
			"EXCEPT_VOL_AMT",
			"CREDIT_VOL_AMT",
			"REDUCE_VOL_AMT",
			"TOTAL_VOLUMN",
			"CHARGE_CASE_AMT",
			"MISCELL_AMT",
			"LIC_AMT",
			"TOTAL_MISCELL_AMT",
			"TAX_EVALUATE",
			"CREATE_DATE",
			"PRODUCT_PRICE",
			"PRODUCT_VAL",
			"VAL_RATE",
			"TAXQTY_UNIT",
			"PRICE",
			"PRICE_FACTORY",
			"CREDIT_INIT",
			"CREDIT_EXPORT",
			"CREDIT_OIL_FILLED",
			"CREDIT_DETERIORATE",
			"CREDIT_PRIVILEDGE",
			"CREDIT_COMPENSATE",
			"CREDIT_RUINED",
			"CREDIT_VOL_INIT",
			"CREDIT_VOL_EXPORT",
			"CREDIT_VOL_OIL_FILLED",
			"CREDIT_VOL_DETERIORATE",
			"CREDIT_VOL_PRIVILEDGE",
			"CREDIT_VOL_COMPENSATE",
			"CREDIT_VOL_RUINED",
			"NUM_OF_LIC",
			"NUM_OF_TAX",
			"START_DAY_OF_MONTH",
			"END_DAY_OF_MONTH",
			"SRC_NAME",
			"CHANNEL_REC_CD",
			"BRAND_ALL_CD",
			"EST_AMT",
			"ALLEST_AMT",
			"EST_KPI_AMT",
			"ALLEST_KPI_AMT",
			"IMPORT_STATUS_FLAG",
			"ANA_STATUS",
			"HD_TAX_AMT",
			"HD_ADD_AMT",
			"HD_PEN_AMT",
			"HD_REDUCE_AMT",
			"HD_CREDIT_AMT",
			"HD_TOTAL_TAX_AMT",
			"IMPORT_STATUS_DESC",
			"TOTAL_VOLUMN_CAPA",
			"CAPA_NUM",
			"RECEIPT_NO",
			"RECEIPT_DATE",
			"IMPORT_STATUS_CODE",
			"OFFCODE",
			"VAT30",
			"SRC_PRODUCT_CODE_28",
			"SRC_BRAND_MAIN_CD",
			"SRC_BRAND_SECOND_CD",
			"SRC_MODEL_CD",
			"SRC_SIZE_CD",
			"SRC_UNIT_CD",
			"SCR_BARCODE",
			"SCR_CO2",
			"SRC_SUGAR",
			"SRC_DEGREE_CD",
			"TYPE_NAME",
			"PRODUCT_NAME",
			"NEWREG_ID",
			"CUS_ID",
			"FAC_ID",
			"REG_ID",
			"CUS_ADDRSEQ",
			"FAC_ADDRSEQ",
			"SRC_TAX",
			"SRC_BRANCH",
			"RETAIL_PRICE",
			"BRAND_MAIN_DESC",
			"BRAND_SECOND_DESC",
			"MODEL_DESC",
			"SIZE_DESC",
			"UNIT_DESC",
			"DEGREE_DESC",
			"CUSTOMER_NAME",
			"FAC_NAME",
			"CUSTOMER_LIST",
			"CUSTOMER_GROUP",
			"EXCISE_PRODUCT_CODE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("BI_PRESTAX_DS", insertColumnNames);
		
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
