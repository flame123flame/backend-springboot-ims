package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.vo.Int0610SearchVo;
import th.go.excise.ims.ia.vo.Int0610SumVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

public class WsIncfri8020IncRepositoryImpl implements WsIncfri8020IncRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8020IncRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsIncfri8020Inc> incfri8020IncList) {
		logger.info("batchInsert incfri8020IncList.size()={}", incfri8020IncList.size());
		
		final int BATCH_SIZE = 1000;
		
		String sql = SqlGeneratorUtils.genSqlInsert(
			"WS_INCFRI8020_INC",
			Arrays.asList(
				"WS_INCFRI8020_INC_ID", "RECEIPT_DATE", "DEPOSIT_DATE", "SEND_DATE", "INCOME_NAME",
				"RECEIPT_NO", "NET_TAX_AMT", "NET_LOC_AMT", "LOC_OTH_AMT", "LOC_EXP_AMT",
				"SSS_FUND_AMT", "TPBS_FUND_AMT", "SPORT_FUND_AMT", "OLDER_FUND_AMT", "SEND_AMT",
				"STAMP_AMT", "CUSTOM_AMT", "TRN_DATE", "OFFICE_RECEIVE", "INCOME_CODE",
				"RECEIPT_NO_SSS_FUND", "RECEIPT_NO_TPBS_FUND", "RECEIPT_NO_SPORT_FUND", "RECEIPT_NO_OLDER_FUND", "PIN_NID_ID",
				"NEW_REG_ID", "CUS_NAME", "FAC_NAME","INC_CTL_NO", "OFFLINE_STATUS", "CREATED_BY"
			),
			"WS_INCFRI8020_INC_SEQ");
		

		commonJdbcTemplate.batchUpdate(sql, incfri8020IncList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8020Inc>() {
			public void setValues(PreparedStatement ps, WsIncfri8020Inc incfri8020Inc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(incfri8020Inc.getReceiptDate());
				paramList.add(incfri8020Inc.getDepositDate());
				paramList.add(incfri8020Inc.getSendDate());
				paramList.add(incfri8020Inc.getIncomeName());
				paramList.add(incfri8020Inc.getReceiptNo());
				paramList.add(incfri8020Inc.getNetTaxAmt());
				paramList.add(incfri8020Inc.getNetLocAmt());
				paramList.add(incfri8020Inc.getLocOthAmt());
				paramList.add(incfri8020Inc.getLocExpAmt());
				paramList.add(incfri8020Inc.getSssFundAmt());
				paramList.add(incfri8020Inc.getTpbsFundAmt());
				paramList.add(incfri8020Inc.getSportFundAmt());
				paramList.add(incfri8020Inc.getOlderFundAmt());
				paramList.add(incfri8020Inc.getSendAmt());
				paramList.add(incfri8020Inc.getStampAmt());
				paramList.add(incfri8020Inc.getCustomAmt());
				paramList.add(incfri8020Inc.getTrnDate());
				paramList.add(incfri8020Inc.getOfficeReceive());
				paramList.add(incfri8020Inc.getIncomeCode());
				paramList.add(incfri8020Inc.getReceiptNoSssFund());
				paramList.add(incfri8020Inc.getReceiptNoTpbsFund());
				paramList.add(incfri8020Inc.getReceiptNoSportFund());
				paramList.add(incfri8020Inc.getReceiptNoOlderFund());
				paramList.add(incfri8020Inc.getPinNidId());
				paramList.add(incfri8020Inc.getNewRegId());
				paramList.add(incfri8020Inc.getCusName());
				paramList.add(incfri8020Inc.getFacName());
				paramList.add(incfri8020Inc.getIncCtlNo());
				paramList.add(incfri8020Inc.getOfflineStatus());
				paramList.add(SYSTEM_USER.BATCH);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	@Override
	public List<WsIncfri8020Inc> findTabs(String officeCode) {
		final String SQL = " SELECT DEPT_DISB FROM WS_INCFRI8020_INC " +
				" WHERE OFFICE_RECEIVE LIKE ? " +
				" GROUP BY DEPT_DISB " +
				" ORDER BY DEPT_DISB ";
		
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<Object>();
		params.add(ExciseUtils.whereInLocalOfficeCode(officeCode));
		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<WsIncfri8020Inc>() {
			@Override
			public WsIncfri8020Inc mapRow(ResultSet rs, int rowNum) throws SQLException {
				WsIncfri8020Inc vo = new WsIncfri8020Inc();
				vo.setDeptDisb(rs.getString("DEPT_DISB"));
//				vo.setGlAccNo(rs.getString("GL_ACC_NO"));
				return vo;
			}
		});
	}
	
	@Override
	public List<Int0610SumVo> summaryByDisburseUnit(Int0610SearchVo request) {
		final String SQL = " SELECT I.INCOME_CODE, I.INCOME_NAME, SUM(I.NET_TAX_AMT) SUM_NET_TAX_AMT " +
				"	,G.ACC_NO, G.ACC_NAME, SUM(G.CARRY_FORWARD) CARRY_FORWARD " +
				" FROM WS_INCFRI8020_INC I " +
				" LEFT JOIN IA_GFTRIAL_BALANCE G " +
				" 	ON I.GL_ACC_NO = G.ACC_NO " +
				" 	AND G.PERIOD_YEAR||G.PERIOD_FROM >= ? " +
				" 	AND G.PERIOD_YEAR||G.PERIOD_TO <= ? " +
				" 	AND G.DEPT_DISB = '00000'||? " +
//				" 	AND G.ACC_NO = ? " +
				" WHERE I.GL_ACC_NO LIKE '4%' " +
				" 	AND I.OFFICE_RECEIVE LIKE ? " +
				" 	AND TRUNC(I.RECEIPT_DATE) >= TO_DATE(?, 'dd/mm/yyyy') " +
				" 	AND TRUNC(I.RECEIPT_DATE) <= TO_DATE(?, 'dd/mm/yyyy') " +
				" 	AND I.DEPT_DISB = ? " +
				" GROUP BY I.INCOME_CODE, I.INCOME_NAME,G.ACC_NO, G.ACC_NAME " +
				" ORDER BY I.INCOME_CODE ";
		
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<Object>();
		params.add(request.getPeriodFromStr());
		params.add(request.getPeriodToStr());
		params.add(request.getDeptDisb());
//		params.add(request.getGlAccNo());
		params.add(ExciseUtils.whereInLocalOfficeCode(request.getOfficeCode()));
		params.add(request.getFromDateStr());
		params.add(request.getToDateStr());
		params.add(request.getDeptDisb());
		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<Int0610SumVo>() {
			@Override
			public Int0610SumVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Int0610SumVo vo = new Int0610SumVo();
				vo.setIncomeCode(rs.getString("INCOME_CODE"));
				vo.setIncomeName(rs.getString("INCOME_NAME"));
				vo.setNetTaxAmt(rs.getBigDecimal("SUM_NET_TAX_AMT"));
				vo.setAccNo(rs.getString("ACC_NO"));
				vo.setAccName(rs.getString("ACC_NAME"));
				vo.setCarryForward(rs.getBigDecimal("CARRY_FORWARD"));
				return vo;
			}
		});
	}
	
}
