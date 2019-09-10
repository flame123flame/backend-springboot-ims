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
import th.go.excise.ims.ia.vo.IaAuditIncD3Vo;

public class IaAuditIncD3RepositoryImpl implements IaAuditIncD3RepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaAuditIncD3Vo> iaAuditIncD3List) {
		String sql = SqlGeneratorUtils.genSqlInsert("IA_AUDIT_INC_D3", Arrays.asList(
				"IA_AUDIT_INC_D3_ID"
				,"AUDIT_INC_NO"
				,"TAX_CODE"
				,"TAX_NAME"
				,"AMOUNT"
				,"COUNT_RECEIPT"
				,"AUDIT_CHECK"
				,"REMARK","CREATED_BY"), "IA_AUDIT_INC_D3_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaAuditIncD3List, 1000, new ParameterizedPreparedStatementSetter<IaAuditIncD3Vo>() {
			public void setValues(PreparedStatement ps, IaAuditIncD3Vo iaAuditInc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(iaAuditInc.getAuditIncNo());
				paramList.add(iaAuditInc.getTaxCode());
				paramList.add(iaAuditInc.getTaxName());
				paramList.add(iaAuditInc.getAmount());
				paramList.add(iaAuditInc.getCountReceipt());
				paramList.add(iaAuditInc.getAuditCheck());
				paramList.add(iaAuditInc.getRemark());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
