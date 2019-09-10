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
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;

public class IaAuditIncD1RepositoryImpl implements IaAuditIncD1RepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public void batchInsert(List<IaAuditIncD1> iaAuditIncD1List, String auditIncNo) {
        String sql = SqlGeneratorUtils.genSqlInsert("IA_AUDIT_INC_D1", Arrays.asList(
                "IA_AUDIT_INC_D_ID"
                , "AUDIT_INC_NO"
                , "OFFICE_CODE"
                , "DOC_CTL_NO"
                , "RECEIPT_NO"
                , "RUN_CHECK"
                , "RECEIPT_DATE"
                , "TAX_NAME"
                , "TAX_CODE"
                , "AMOUNT"
                , "REMARK"
                , "CHECK_TAX0307"
                , "CHECK_STAMP"
                , "CHECK_TAX0704"
                , "REMARK_TAX"
                , "CREATED_BY"),
                "IA_AUDIT_INC_D1_SEQ");

        String username = UserLoginUtils.getCurrentUsername();

        commonJdbcTemplate.batchUpdate(sql, iaAuditIncD1List, 1000, new ParameterizedPreparedStatementSetter<IaAuditIncD1>() {
            public void setValues(PreparedStatement ps, IaAuditIncD1 iaAuditInc) throws SQLException {
                List<Object> paramList = new ArrayList<Object>();
                paramList.add(auditIncNo);
                paramList.add(iaAuditInc.getOfficeCode());
                paramList.add(iaAuditInc.getDocCtlNo());
                paramList.add(iaAuditInc.getReceiptNo());
                paramList.add(iaAuditInc.getRunCheck());
                paramList.add(iaAuditInc.getReceiptDate());
                paramList.add(iaAuditInc.getTaxName());
                paramList.add(iaAuditInc.getTaxCode());
                paramList.add(iaAuditInc.getAmount());
                paramList.add(iaAuditInc.getRemark());
                paramList.add(iaAuditInc.getCheckTax0307());
                paramList.add(iaAuditInc.getCheckStamp());
                paramList.add(iaAuditInc.getCheckTax0704());
                paramList.add(iaAuditInc.getRemarkTax());
                paramList.add(username);
                commonJdbcTemplate.preparePs(ps, paramList.toArray());
            }
        });
    }

}
