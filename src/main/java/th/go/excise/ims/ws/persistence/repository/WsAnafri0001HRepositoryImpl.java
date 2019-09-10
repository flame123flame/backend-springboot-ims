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
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001H;

public class WsAnafri0001HRepositoryImpl implements WsAnafri0001HRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsAnafri0001DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsAnafri0001H> anafri0001HList) {
		logger.info("batchInsert anafri0001HList.size()={}", anafri0001HList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"ANAFRI0001_H_SEQ",
			"NEW_REG_ID",
			"FORM_CODE",
			"REG_IN_NO",
			"REG_IN_DATE",
			"PAY_TYPE12",
			"RECEIPT_NO",
			"RECEIPT_DATE",
			"TAX_AMT",
			"REDUCE_AMT",
			"DIF_AMT",
			"PEN_AMT",
			"ADD_AMT",
			"CREDIT_AMT",
			"NET_TAX_AMT",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_ANAFRI0001_H", insertColumnNames, "WS_ANAFRI0001_H_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), anafri0001HList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsAnafri0001H>() {
			public void setValues(PreparedStatement ps, WsAnafri0001H anafri0001H) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(anafri0001H.getNewRegId());
				paramList.add(anafri0001H.getFormCode());
				paramList.add(anafri0001H.getRegInNo());
				paramList.add(anafri0001H.getRegInDate());
				paramList.add(anafri0001H.getPayType12());
				paramList.add(anafri0001H.getReceiptNo());
				paramList.add(anafri0001H.getReceiptDate());
				paramList.add(anafri0001H.getTaxAmt());
				paramList.add(anafri0001H.getReduceAmt());
				paramList.add(anafri0001H.getDifAmt());
				paramList.add(anafri0001H.getPenAmt());
				paramList.add(anafri0001H.getAddAmt());
				paramList.add(anafri0001H.getCreditAmt());
				paramList.add(anafri0001H.getNetTaxAmt());
				paramList.add(anafri0001H.getCreatedBy());
				paramList.add(anafri0001H.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
