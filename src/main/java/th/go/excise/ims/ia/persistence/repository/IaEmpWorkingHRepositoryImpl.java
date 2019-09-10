package th.go.excise.ims.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingH;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrFormVo;

public class IaEmpWorkingHRepositoryImpl implements IaEmpWorkingHRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	private void buildByMonthQuery(StringBuilder sql, List<Object> params, IaEmpWorkingHdrFormVo formVo) {
		sql.append(" SELECT * ");
		sql.append(" FROM IA_EMP_WORKING_H ");
		sql.append(" WHERE IS_DELETED = ? ");
		sql.append(" AND USER_OFFCODE = ? ");
		sql.append(" AND WORKING_MONTH ");
		sql.append(" BETWEEN ? AND ? ");

		params.add(FLAG.N_FLAG);
		params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		params.add(formVo.getWorkingMonth());
		params.add(formVo.getWorkingMonth2());
	}

	@Override
	public List<IaEmpWorkingH> findByMonth(IaEmpWorkingHdrFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByMonthQuery(sql, params, formVo);

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), findByMonthRowMapper);
	}

	protected RowMapper<IaEmpWorkingH> findByMonthRowMapper = new RowMapper<IaEmpWorkingH>() {
		@Override
		public IaEmpWorkingH mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaEmpWorkingH vo = new IaEmpWorkingH();
			vo.setIaEmpWorkingHSeq(rs.getLong("IA_EMP_WORKING_H_SEQ"));
			vo.setUserLogin(rs.getString("USER_LOGIN"));
			vo.setUserName(rs.getString("USER_NAME"));
			vo.setUserPosition(rs.getString("USER_POSITION"));
			vo.setUserOffcode(rs.getString("USER_OFFCODE"));
			vo.setWorkingMonth(rs.getString("WORKING_MONTH"));
			vo.setOwnerCaseSpirits(rs.getBigDecimal("OWNER_CASE_SPIRITS"));
			vo.setAsstCaseSpirits(rs.getBigDecimal("ASST_CASE_SPIRITS"));
			vo.setRemarkCaseSpirits(rs.getString("REMARK_CASE_SPIRITS"));
			vo.setOwnerCaseTobacco(rs.getBigDecimal("OWNER_CASE_TOBACCO"));
			vo.setAsstCaseTobacco(rs.getBigDecimal("ASST_CASE_TOBACCO"));
			vo.setRemarkCaseTobacco(rs.getString("REMARK_CASE_TOBACCO"));
			vo.setOwnerCaseCard(rs.getBigDecimal("OWNER_CASE_CARD"));
			vo.setAsstCaseCard(rs.getBigDecimal("ASST_CASE_CARD"));
			vo.setRemarkCaseCard(rs.getString("REMARK_CASE_CARD"));
			vo.setOwnerCaseEdtax(rs.getBigDecimal("OWNER_CASE_EDTAX"));
			vo.setAsstCaseEdtax(rs.getBigDecimal("ASST_CASE_EDTAX"));
			vo.setRemarkCaseEdtax(rs.getString("REMARK_CASE_EDTAX"));
			vo.setOwnerCaseSpiritsFw(rs.getBigDecimal("OWNER_CASE_SPIRITS_FW"));
			vo.setAsstCaseSpiritsFw(rs.getBigDecimal("ASST_CASE_SPIRITS_FW"));
			vo.setOwnerCaseTobaccoFw(rs.getBigDecimal("OWNER_CASE_TOBACCO_FW"));
			vo.setAsstCaseTobaccoFw(rs.getBigDecimal("ASST_CASE_TOBACCO_FW"));
			vo.setOwnerCaseCardFw(rs.getBigDecimal("OWNER_CASE_CARD_FW"));
			vo.setAsstCaseCardFw(rs.getBigDecimal("ASST_CASE_CARD_FW"));
			vo.setOwnerCaseEdtaxFw(rs.getBigDecimal("OWNER_CASE_EDTAX_FW"));
			vo.setAsstCaseEdtaxFw(rs.getBigDecimal("ASST_CASE_EDTAX_FW"));
			return vo;
		}
	};
}
