package th.go.excise.ims.ia.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaGfdrawAccount;

public class IaGfdrawAccountRepositoryImpl implements IaGfdrawAccountRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaGfdrawAccount> iaGfDrawAccountList) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFDRAW_ACCOUNT", Arrays.asList("GFTRIAL_BALANCE_SEQ", "DEPT_DISB", "PERIOD_FROM", "PERIOD_TO", "REP_DATE", "REP_TYPE", "RECORD_DATE", "RECODE_APPROVE_DATE", "TYPE", "DOC_NO", "SELLER_NAME", "SELLER_BOOK_BANK", "REFERENCE_CODE",
				"BUDGET_CODE", "DISB_AMT", "TAX_AMT", "MULCT_AMT", "FEE_AMT", "NET_AMT", "CREATED_BY"), "IA_GFDRAW_ACCOUNT_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGfDrawAccountList, 1000, new ParameterizedPreparedStatementSetter<IaGfdrawAccount>() {
			public void setValues(PreparedStatement ps, IaGfdrawAccount entity) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(entity.getDeptDisb());
				paramList.add(entity.getPeriodFrom());
				paramList.add(entity.getPeriodTo());
				paramList.add(entity.getRepDate());
				paramList.add(entity.getRepType());
				paramList.add(entity.getRecordDate());
				paramList.add(entity.getRecodeApproveDate());
				paramList.add(entity.getType());
				paramList.add(entity.getDocNo());
				paramList.add(entity.getSellerName());
				paramList.add(entity.getSellerBookBank());
				paramList.add(entity.getReferenceCode());
				paramList.add(entity.getBudgetCode());
				paramList.add(entity.getDisbAmt());
				paramList.add(entity.getTaxAmt());
				paramList.add(entity.getMulctAmt());
				paramList.add(entity.getFeeAmt());
				paramList.add(entity.getNetAmt());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}

}
