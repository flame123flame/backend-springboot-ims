package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaCheckLicense;
import th.go.excise.ims.ia.vo.Int0606FormHdrId;
import th.go.excise.ims.ia.vo.Int0606FormVo;
import th.go.excise.ims.ia.vo.Int0606Vo;
import th.go.excise.ims.ia.vo.Int11FormVo;
import th.go.excise.ims.ia.vo.Int11Vo;

@Repository
public class Int0606JdbcRepository {
	

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
	private final String SQL_IA_CHECK_LICENSE = " SELECT * FROM IA_CHECK_LICENSE A WHERE OFFCODE = ?  ";

	public List<IaCheckLicense> list(Int0606FormVo form) {
		List<IaCheckLicense> iaCheckLicenseList = new ArrayList<IaCheckLicense>();

		StringBuilder sql = new StringBuilder(SQL_IA_CHECK_LICENSE);
		List<Object> params = new ArrayList<Object>();

		params.add(form.getOffCode());
		
		if (StringUtils.isNotBlank(form.getStartDate())) {
			sql.append("AND A.LICENS_DATE >=  ?  ");
			Date dateForm = ConvertDateUtils.parseStringToDate(form.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(dateForm);
		} 
		
		if (StringUtils.isNotBlank(form.getEndDate())) {
			sql.append("AND  A.LICENS_DATE <=  ?  ");
			Date dateTo = ConvertDateUtils.parseStringToDate(form.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH);
			params.add(dateTo);
		}
		
		iaCheckLicenseList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaCheckLicenseList;
	}
	
	private RowMapper<IaCheckLicense> listRowmapper = new RowMapper<IaCheckLicense>() {
		@Override
		public IaCheckLicense mapRow(ResultSet rs, int arg1) throws SQLException {
			IaCheckLicense vo = new IaCheckLicense();	
			vo.setOffCode(rs.getString("OFFCODE"));
			vo.setLicensDate(rs.getDate("LICENS_DATE"));
			vo.setDateRemittance(rs.getDate("DATE_REMITTANCE"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setPrintNumber(rs.getString("PRINT_NUMBER"));
			vo.setLicenseNumber(rs.getString("LICENSE_NUMBER"));
			vo.setAmountOutSystem(rs.getBigDecimal("AMOUNT_OUT_SYSTEM"));
			vo.setAmountCopyLicense(rs.getBigDecimal("AMOUNT_COPY_LICENSE"));
			vo.setAmountFees(rs.getBigDecimal("AMOUNT_FEES"));
			vo.setInteriorCostAmount(rs.getBigDecimal("INTERIOR_COST_AMOUNT"));
			vo.setTotal(rs.getBigDecimal("TOTAL"));
			vo.setDateLicenseEffective(rs.getDate("DATE_LICENSE_EFFECTIVE"));
			vo.setLicenseExpiratDate(rs.getDate("LICENSE_EXPIRAT_DATE"));
			return vo;
		}
	};
	
	
	private final String SQL_IA_CONCLUDE_FOLLOW_HDR = "SELECT A.ID FROM IA_CONCLUDE_FOLLOW_HDR A WHERE A.BUDGET_YEAR = ? AND A.EXCISE_CODE = ?  ";
	public List<Int0606Vo> FindIdHdr(Int0606FormHdrId form) {
		List<Int0606Vo> iaFindIdHdr = new ArrayList<Int0606Vo>();
		StringBuilder sql = new StringBuilder(SQL_IA_CONCLUDE_FOLLOW_HDR);
		List<Object> params = new ArrayList<Object>();
		params.add(form.getBudgetYear());
		params.add(form.getExciseCode());	
		iaFindIdHdr = commonJdbcTemplate.query(sql.toString(), params.toArray(), iaFindIdHdrRowmapper);
		return iaFindIdHdr;
	}	
	private RowMapper<Int0606Vo> iaFindIdHdrRowmapper = new RowMapper<Int0606Vo>() {
		@Override
		public Int0606Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0606Vo vo = new Int0606Vo();	
			vo.setId(rs.getLong("ID"));
			return vo;
		}
	};
	
	
	
	
	
	
//	public List<Int0606Vo> FindIdHdr(Int0606FormHdrId form) {
//		StringBuilder sql = new StringBuilder();
//		List<Object> params = new ArrayList<Object>();
//		sql.append(" SELECT A.ID FROM IA_CONCLUDE_FOLLOW_HDR A WHERE A.BUDGET_YEAR = ? AND A.EXCISE_CODE = ? ");
//		params.add(form.getBudgetYear());
//		params.add(form.getExciseCode());
//		@SuppressWarnings({ "rawtypes", "unchecked" })
//		List<Int0606Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
//				new BeanPropertyRowMapper(Int0606Vo.class));
//		return datas;
//	}	
}
