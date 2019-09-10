package th.go.excise.ims.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncSendD;
import th.go.excise.ims.ia.vo.SearchVo;
import th.go.excise.ims.ia.vo.WsIncr0003Vo;

public class IaAuditIncSendDRepositoryImpl implements IaAuditIncSendDRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public IaAuditIncSendD summaryByIncsendNo(SearchVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();

		sql.append(" SELECT SUM(INCSEND_CNT) INCSEND_CNT, SUM(INCSEND_TOTAL_AMT) INCSEND_TOTAL_AMT, SUM(INCSEND_AMOUNT) INCSEND_AMOUNT, ");
		sql.append(" 	SUM(INCSEND_EDC) INCSEND_EDC, SUM(INCSEND_EDC_LICENSE) INCSEND_EDC_LICENSE, SUM(INCSEND_AMT_DELIVERY) INCSEND_AMT_DELIVERY, ");
		sql.append(" 	SUM(INCSEND_INC_KTB) INCSEND_INC_KTB, SUM(INCSEND_INC_115010) INCSEND_INC_115010, SUM(INCSEND_INC_116010) INCSEND_INC_116010 ");
		sql.append(" FROM IA_AUDIT_INC_SEND_D ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" 	AND IS_DELETED = 'N' ");

		if (StringUtils.isNotBlank(request.getPaperNumber())) {
			sql.append(" AND INCSEND_NO = ? ");
			params.add(request.getPaperNumber());
		}

		return commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), new RowMapper<IaAuditIncSendD>() {
			@Override
			public IaAuditIncSendD mapRow(ResultSet rs, int rowNum) throws SQLException {
				IaAuditIncSendD vo = new IaAuditIncSendD();
				vo.setIncsendCnt(rs.getBigDecimal("INCSEND_CNT"));
				vo.setIncsendTotalAmt(rs.getBigDecimal("INCSEND_TOTAL_AMT"));
				vo.setIncsendAmount(rs.getBigDecimal("INCSEND_AMOUNT"));
				vo.setIncsendEdc(rs.getBigDecimal("INCSEND_EDC"));
				vo.setIncsendEdcLicense(rs.getBigDecimal("INCSEND_EDC_LICENSE"));
				vo.setIncsendAmtDelivery(rs.getBigDecimal("INCSEND_AMT_DELIVERY"));
				vo.setIncsendIncKtb(rs.getBigDecimal("INCSEND_INC_KTB"));
				vo.setIncsendInc115010(rs.getBigDecimal("INCSEND_INC_115010"));
				vo.setIncsendInc116010(rs.getBigDecimal("INCSEND_INC_116010"));
				return vo;
			}
		});
	}

}
