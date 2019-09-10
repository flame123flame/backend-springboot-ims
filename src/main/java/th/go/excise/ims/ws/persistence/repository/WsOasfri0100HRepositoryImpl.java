package th.go.excise.ims.ws.persistence.repository;

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
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100H;

public class WsOasfri0100HRepositoryImpl implements WsOasfri0100HRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(WsOasfri0100HRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsOasfri0100H> oasfri0100HList) {
		logger.info("batchInsert oasfri0100HList.size()={}", oasfri0100HList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"OASFRI0100_H_SEQ",
			"NEW_REG_ID",
			"TAX_YEAR",
			"TAX_MONTH",
			"FORMDOC_REC0142_NO",
			"FORMDOC_REC0142_DATE",
			"FORMDOC_REC0142_BY",
			"RCVDOC_SIGN_BY",
			"RCVDOC_SIGN_DATE",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_OASFRI0100_H", insertColumnNames, "WS_OASFRI0100_H_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), oasfri0100HList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsOasfri0100H>() {
			public void setValues(PreparedStatement ps, WsOasfri0100H oasfri0100H) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(oasfri0100H.getNewRegId());
				paramList.add(oasfri0100H.getTaxYear());
				paramList.add(oasfri0100H.getTaxMonth());
				paramList.add(oasfri0100H.getFormdocRec0142No());
				paramList.add(oasfri0100H.getFormdocRec0142Date());
				paramList.add(oasfri0100H.getFormdocRec0142By());
				paramList.add(oasfri0100H.getRcvdocSignBy());
				paramList.add(oasfri0100H.getRcvdocSignDate());
				paramList.add(oasfri0100H.getCreatedBy());
				paramList.add(oasfri0100H.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
