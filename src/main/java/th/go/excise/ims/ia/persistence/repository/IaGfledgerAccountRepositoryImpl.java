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
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;

public class IaGfledgerAccountRepositoryImpl implements IaGfledgerAccountRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void insertBatch(List<IaGfledgerAccount> iaGfledgerAccountList) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFLEDGER_ACCOUNT", Arrays.asList("IA_GFLEDGER_ACCOUNT_ID"
				,"GFUPLOAD_H_ID"
				,"GL_ACC_NO"
				,"DEP_CODE"
				,"TYPE"
				,"PERIOD"
				,"DOC_DATE"
				,"POSTING_DATE"
				,"DOC_NO"
				,"REF_CODE"
				,"CURR_AMT"
				,"PK_CODE"
				,"ROR_KOR"
				,"DETERMINATON"
				,"MSG"
				,"KEY_REF_3"
				,"KEY_REF_1"
				,"KEY_REF_2"
				,"HLODING_TAXES"
				,"DEPOSIT_ACC"
				,"ACC_TYPE"
				,"COST_CENTER"
				,"DEPT_DISB"
				,"CLRNG_DOC"
				,"PERIOD_YEAR"
				,"CREATED_BY"), "IA_GFLEDGER_ACCOUNT_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGfledgerAccountList, 1000, new ParameterizedPreparedStatementSetter<IaGfledgerAccount>() {
			public void setValues(PreparedStatement ps, IaGfledgerAccount entity) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(entity.getGfuploadHId());
				paramList.add(entity.getGlAccNo());
				paramList.add(entity.getDepCode());
				paramList.add(entity.getType());
				paramList.add(entity.getPeriod());
				paramList.add(entity.getDocDate());
				paramList.add(entity.getPostingDate());
				paramList.add(entity.getDocNo());
				paramList.add(entity.getRefCode());
				paramList.add(entity.getCurrAmt());
				paramList.add(entity.getPkCode());
				paramList.add(entity.getRorKor());
				paramList.add(entity.getDeterminaton());
				paramList.add(entity.getMsg());
				paramList.add(entity.getKeyRef3());
				paramList.add(entity.getKeyRef1());
				paramList.add(entity.getKeyRef2());
				paramList.add(entity.getHlodingTaxes());
				paramList.add(entity.getDepositAcc());
				paramList.add(entity.getAccType());
				paramList.add(entity.getCostCenter());
				paramList.add(entity.getDeptDisb());
				paramList.add(entity.getClrngDoc());
				paramList.add(entity.getPeriodYear());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}

}
