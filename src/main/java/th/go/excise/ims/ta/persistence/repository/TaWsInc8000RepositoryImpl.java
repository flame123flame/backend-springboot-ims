package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000;

public class TaWsInc8000RepositoryImpl implements TaWsInc8000RepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<TaWsInc8000> wsInc8000List) {
		String sql = SqlGeneratorUtils.genSqlInsert(
			"TA_WS_INC8000",
			Arrays.asList(
				"WS_INC8000_ID", "REG_ID", "NEW_REG_ID", "RECEIPT_NO", "RECEIPT_DATE",
				"TAX_AMOUNT", "PEN_AMOUNT", "ADD_AMOUNT", "REDUCE_AMOUNT", "CREDIT_AMOUNT",
				"OFFICE_RECEIVE_CODE", "TRN_DATE", "DEPOSIT_DATE", "SEND_DATE", "INCOME_CODE",
				"INCOME_TYPE", "CREATED_BY", "CREATED_DATE"
			),
			"TA_WS_INC8000_SEQ"
		);

		commonJdbcTemplate.batchUpdate(sql, wsInc8000List, 1000, new ParameterizedPreparedStatementSetter<TaWsInc8000>() {
			public void setValues(PreparedStatement ps, TaWsInc8000 taWsInc8000) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(taWsInc8000.getRegId());
				paramList.add(taWsInc8000.getNewRegId());
				paramList.add(taWsInc8000.getReceiptNo());
				paramList.add(taWsInc8000.getReceiptDate());
				paramList.add(taWsInc8000.getTaxAmount());
				paramList.add(taWsInc8000.getPenAmount());
				paramList.add(taWsInc8000.getAddAmount());
				paramList.add(taWsInc8000.getReduceAmount());
				paramList.add(taWsInc8000.getCreditAmount());
				paramList.add(taWsInc8000.getOfficeReceiveCode());
				paramList.add(taWsInc8000.getTrnDate());
				paramList.add(taWsInc8000.getDepositDate());
				paramList.add(taWsInc8000.getSendDate());
				paramList.add(taWsInc8000.getIncomeCode());
				paramList.add(taWsInc8000.getIncomeType());
				paramList.add(SYSTEM_USER.SYSTEM);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
}
