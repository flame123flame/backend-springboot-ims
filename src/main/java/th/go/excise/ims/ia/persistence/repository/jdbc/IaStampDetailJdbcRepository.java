package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.vo.Int120101FormVo;
import th.go.excise.ims.ia.vo.Int120101Vo;

@Repository
public class IaStampDetailJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL = "SELECT * FROM IA_STAMP_DETAIL WHERE IS_DELETED='N' ";

	public Integer count(Int120101FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getParamCode())) {
			sql.append(" AND STATUS = ? ");
			params.add(ApplicationCache.getParamInfoByCode(IaConstants.STAMP_STATUS.PARAM_GROUP_CODE, formVo.getParamCode()).getValue1());
		}
		if (StringUtils.isNoneBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ? ");
			params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		}
//		if (StringUtils.isNotBlank(formVo.getSector())) {
//			sql.append(" AND EXCISE_DEPARTMENT = ? ");
//			params.add(StringUtils.trim(formVo.getSector()));
//		}
//		if (StringUtils.isNotBlank(formVo.getArea())) {
//			sql.append(" AND EXCISE_REGION = ? ");
//			params.add(StringUtils.trim(formVo.getArea()));
//		}
//		if (StringUtils.isNotBlank(formVo.getBranch())) {
//			sql.append(" AND EXCISE_DISTRICT = ? ");
//			params.add(StringUtils.trim(formVo.getBranch()));
//		}
		if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND TO_CHAR(DATE_OF_PAY,'DDMMYYYY') BETWEEN ? AND ?");
			params.add(formVo.getDateForm());
			params.add(formVo.getDateTo());
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);
		return count;
	}

	public List<Int120101Vo> findAll(Int120101FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getParamCode())) {
			sql.append(" AND STATUS = ? ");
			params.add(ApplicationCache.getParamInfoByCode(IaConstants.STAMP_STATUS.PARAM_GROUP_CODE, formVo.getParamCode()).getValue1());
		}
		if (StringUtils.isNoneBlank(formVo.getOfficeCode())) {
			sql.append(" AND OFFICE_CODE LIKE ? ");
			params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		}
//		if (StringUtils.isNotBlank(formVo.getSector())) {
//			sql.append(" AND EXCISE_DEPARTMENT = ? ");
//			params.add(StringUtils.trim(formVo.getSector()));
//		}
//		if (StringUtils.isNotBlank(formVo.getArea())) {
//			sql.append(" AND EXCISE_REGION = ? ");
//			params.add(StringUtils.trim(formVo.getArea()));
//		}
//		if (StringUtils.isNotBlank(formVo.getBranch())) {
//			sql.append(" AND EXCISE_DISTRICT = ? ");
//			params.add(StringUtils.trim(formVo.getBranch()));
//		}
		if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND (? <= DATE_OF_PAY) AND (? >= DATE_OF_PAY)");
			params.add(ConvertDateUtils.parseStringToDate(formVo.getDateForm(), ConvertDateUtils.DD_MM_YY,ConvertDateUtils.LOCAL_TH));
			params.add(ConvertDateUtils.parseStringToDate(formVo.getDateTo(), ConvertDateUtils.DD_MM_YY,ConvertDateUtils.LOCAL_TH));
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");
		List<Int120101Vo> list = commonJdbcTemplate.query(sql.toString(), params.toArray(), stamRowmapper);
		return list;

	}

	public List<Int120101Vo> exportFile(Int120101FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getSector())) {
			sql.append(" AND EXCISE_DEPARTMENT=? ");
			params.add(StringUtils.trim(formVo.getSector()));
		}
		if (StringUtils.isNotBlank(formVo.getArea())) {
			sql.append(" AND EXCISE_REGION=? ");
			params.add(StringUtils.trim(formVo.getArea()));
		}
		if (StringUtils.isNotBlank(formVo.getBranch())) {
			sql.append(" AND EXCISE_DISTRICT=? ");
			params.add(StringUtils.trim(formVo.getBranch()));
		}
		if (StringUtils.isNotBlank(formVo.getDateForm()) && StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND TO_CHAR(DATE_OF_PAY,'YYYYMMDD') BETWEEN ? AND ?");
			params.add(formVo.getDateForm());
			params.add(formVo.getDateTo());
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");
		List<Int120101Vo> list = commonJdbcTemplate.query(sql.toString(), params.toArray(), stamRowmapper);
		return list;

	}
	
	private RowMapper<Int120101Vo> stamRowmapper = new RowMapper<Int120101Vo>() {
		@Override
		public Int120101Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int120101Vo vo = new Int120101Vo();
			vo.setDateOfPay(ConvertDateUtils.formatDateToString(rs.getDate("DATE_OF_PAY"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setStatus(rs.getString("STATUS"));
			vo.setBookNumberWithdrawStamp(rs.getString("BOOK_NUMBER_WITHDRAW_STAMP"));
			vo.setDateWithdrawStamp(ConvertDateUtils.formatDateToString(rs.getDate("DATE_WITHDRAW_STAMP"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setBookNumberDeliverStamp(rs.getString("BOOK_NUMBER_DELIVER_STAMP"));
			vo.setDateDeliverStamp(ConvertDateUtils.formatDateToString(rs.getDate("DATE_DELIVER_STAMP"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setFivePartNumber(rs.getString("FIVE_PART_NUMBER"));
			vo.setFivePartDate(ConvertDateUtils.formatDateToString(rs.getDate("FIVE_PART_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setStampCheckDate(ConvertDateUtils.formatDateToString(rs.getDate("STAMP_CHECK_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			vo.setStampChecker(rs.getString("STAMP_CHECKER"));
			vo.setStampChecker2(rs.getString("STAMP_CHECKER_2"));
			vo.setStampChecker3(rs.getString("STAMP_CHECKER_3"));
			vo.setStampBrand(rs.getString("STAMP_BRAND"));
			vo.setNumberOfBook(rs.getBigDecimal("NUMBER_OF_BOOK"));
			vo.setNumberOfStamp(rs.getBigDecimal("NUMBER_OF_STAMP"));
			vo.setValueOfStampPrinted(rs.getBigDecimal("VALUE_OF_STAMP_PRINTED"));
			vo.setSumOfValue(rs.getBigDecimal("SUM_OF_VALUE"));
			vo.setSerialNumber(rs.getString("SERIAL_NUMBER"));
			vo.setNote(rs.getString("NOTE"));
			vo.setWorkSheetDetailId(rs.getString("WORK_SHEET_DETAIL_ID"));
			vo.setStampType(rs.getString("STAMP_TYPE"));
			vo.setTaxStamp(rs.getBigDecimal("TAX_STAMP"));
			vo.setStampCodeStart(rs.getString("STAMP_CODE_START"));
			vo.setStampCodeEnd(rs.getString("STAMP_CODE_END"));
			vo.setFileName(rs.getString("FILE_NAME"));
			vo.setDepartmentName(rs.getString("DEPARTMENT_NAME"));

			return vo;
		}
	};

	public List<LabelValueBean> sector() {
		String SQL = "SELECT * FROM Sys_Lov where TYPE='SECTOR_VALUE' and LOV_ID_MASTER is null ORDER BY SUB_TYPE ASC";
		return commonJdbcTemplate.query(SQL, SoctorRowmapper);
	}

	private RowMapper<LabelValueBean> SoctorRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("VALUE1"), rs.getString("LOV_ID"));
		}
	};

	public List<LabelValueBean> area(String id) {
		String SQL = "SELECT * FROM SYS_LOV WHERE LOV_ID_MASTER=? AND TYPE='SECTOR_VALUE' ORDER BY SUB_TYPE ASC";
		return commonJdbcTemplate.query(SQL, new Object[] { id }, areaRowmapper);
	}

	private RowMapper<LabelValueBean> areaRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("SUB_TYPE_DESCRIPTION"), rs.getString("LOV_ID"));
		}
	};

	// public List<IaPlanDayActivity> getInfoLogin(String officeCode) {
	// StringBuilder sql = new StringBuilder();
	// List<Object> params = new ArrayList<Object>();
	// sql.append(" SELECT * FROM EXCISE_DEPARTMENT WHERE 1=1 AND IS_DELETED='N' ");
	// if (ExciseUtils.isCentral(officeCode)) {
	// sql.append(" AND OFF_CODE = '000000' ");
	// } else {
	// if(ExciseUtils.isSector(officeCode)) {
	// sql.append(" AND OFF_CODE LIKE ? AND OFF_INDC = 'D' ");
	// params.add(ExciseUtils.whereInLocalOfficeCode(officeCode) + '%');
	// } else if (ExciseUtils.isArea(officeCode)) {
	// sql.append(" AND OFF_CODE LIKE ? AND OFF_INDC = 'E' ");
	// params.add(ExciseUtils.whereInLocalOfficeCode(officeCode) + '%');
	// } else if (ExciseUtils.isBranch(officeCode)) {
	//
	// }
	// }
	//
	// @SuppressWarnings({ "rawtypes", "unchecked" })
	// List<IaPlanDayActivity> planDayAct = commonJdbcTemplate.query(sql.toString(),
	// params.toArray(),
	// new BeanPropertyRowMapper(IaPlanDayActivity.class));
	// return planDayAct;
	// }
}
