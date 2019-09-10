package th.go.excise.ims.ia.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.vo.Int0804SearchVo;
import th.go.excise.ims.ia.vo.Int0804SummaryVo;

public class IaGfmovementAccountRepositoryImpl implements IaGfmovementAccountRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaGfmovementAccount> iaGfmovementAccounList) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFMOVEMENT_ACCOUNT",
				Arrays.asList("GFMOVEMENT_ACCOUNT_ID", "ACC_TYPE_NO", "ACC_TYPE_NAME", "GF_ACC_NO", "GF_DOC_DATE", "GF_DOC_NO", "GF_DOC_TYEP", "GF_REF_DOC", "CARE_INSTEAD", "DETERMINATON", "DEP_CODE", "DEBIT", "CREDIT", "CARRY_FORWARD", "CREATED_BY"), "IA_GFMOVEMENT_ACCOUNT_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGfmovementAccounList, 1000, new ParameterizedPreparedStatementSetter<IaGfmovementAccount>() {
			public void setValues(PreparedStatement ps, IaGfmovementAccount entity) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(entity.getAccTypeNo());
				paramList.add(entity.getAccTypeName());
				paramList.add(entity.getAccNo());
				paramList.add(entity.getGfAccNo());
				paramList.add(entity.getGfDocDate());
				paramList.add(entity.getGfDocNo());
				paramList.add(entity.getGfDocTyep());
				paramList.add(entity.getGfRefDoc());
				paramList.add(entity.getCareInstead());
				paramList.add(entity.getDeterminaton());
				paramList.add(entity.getDeptDisb());
				paramList.add(entity.getDebit());
				paramList.add(entity.getCredit());
				paramList.add(entity.getCarryForward());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}
	
	@Override
	public List<Int0804SummaryVo> getResultByconditon(Int0804SearchVo request) {
		 final String SQL = "SELECT O.GF_EXCISE_CODE, H.DATE_DEFAULT, SUM(G.CARRY_FORWARD) SUM_CARRT_FORWARD" + 
				" FROM (" + 
				"    SELECT TO_CHAR(TO_DATE(?, 'YYYYMMDD')+LEVEL-1 , 'YYYYMMDD') DATE_DEFAULT" + 
				"    FROM DUAL " + 
				"    CONNECT BY LEVEL <= CEIL( ADD_MONTHS(TO_DATE(?, 'YYYYMMDD'), 1)" + 
				"                          -TO_DATE(?, 'YYYYMMDD'))" + 
				"    ) H" + 
				" LEFT JOIN IA_GFMOVEMENT_ACCOUNT G  " + 
				"    ON H.DATE_DEFAULT =  TO_CHAR(G.GF_DOC_DATE, 'YYYYMMDD') " +
				"    AND G.IS_DELETED = 'N' " +
				"	 AND G.ACC_NO = ? " +
				" LEFT JOIN EXCISE_ORG_GFMIS O  " +
				"    ON 0 || G.DEPT_DISB = O.GF_DISBURSE_UNIT  " +
				"    AND O.IS_DELETED = 'N' " +
				"	 AND O.GF_EXCISE_CODE " +
				"		 NOT IN ( " +
				"	 			SELECT O.GF_EXCISE_CODE FROM EXCISE_ORG_GFMIS " +
				"	 			WHERE O.GF_EXCISE_CODE = ?  " +
				"	 			)  " +
				"    AND O.GF_EXCISE_CODE LIKE ? " + 
				" GROUP BY O.GF_EXCISE_CODE, H.DATE_DEFAULT " + 
				" ORDER BY H.DATE_DEFAULT, O.GF_EXCISE_CODE";
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<Object>();
	
		params.add(request.getDateFrom());
		params.add(request.getDateTo());
		params.add(request.getDateFrom());
		params.add(request.getGfDepositCode());
		params.add(request.getOfficeCode());
		params.add(ExciseUtils.whereInLocalOfficeCode(request.getOfficeCode()).concat("%"));
		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), mappingSumResult);
	}
	
	private RowMapper<Int0804SummaryVo> mappingSumResult = new RowMapper<Int0804SummaryVo>() {
		@Override
		public Int0804SummaryVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Int0804SummaryVo vo = new Int0804SummaryVo();
			vo.setGfExciseCode(rs.getString("GF_EXCISE_CODE"));
			vo.setDateDefault(rs.getString("DATE_DEFAULT"));
			vo.setSumCarryForward(rs.getString("SUM_CARRT_FORWARD"));
			return vo;
		}
	};

}
