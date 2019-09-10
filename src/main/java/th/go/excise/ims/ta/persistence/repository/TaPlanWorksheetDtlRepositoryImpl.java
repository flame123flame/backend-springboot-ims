package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.PersonAssignForm;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaPlanMasVo;

public class TaPlanWorksheetDtlRepositoryImpl implements TaPlanWorksheetDtlRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TaPlanWorksheetDtlRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, PlanWorksheetVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME ");
		sql.append("   ,R4000.FAC_FULLNAME ");
		sql.append("   ,R4000.FAC_ADDRESS ");
		sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000 ");
		sql.append("   ,R4000.REG_DATE ");
		sql.append("   ,R4000.REG_CAPITAL ");
		sql.append("   ,WK_DTL.DUTY_GROUP_ID ");
		sql.append("   ,ED_SECTOR.OFF_CODE SEC_CODE ");
		sql.append("   ,ED_SECTOR.OFF_SHORT_NAME SEC_DESC ");
		sql.append("   ,ED_AREA.OFF_CODE AREA_CODE ");
		sql.append("   ,ED_AREA.OFF_SHORT_NAME AREA_DESC ");
		sql.append("   ,PLAN_DTL.* ");
		sql.append("   ,ED_SUBDEPT.SUBDEPT_SHORT_NAME SUBDEPTSHORTNAME ");
		sql.append("   ,ED_PERSON.ED_PERSON_NAME PERSON_NAME ");
		sql.append("   ,WK_DTL.DUTY_GROUP_NAME  DUTY_GROUP_NAME ");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT ED_SUBDEPT ON PLAN_DTL.AU_SUBDEPT_CODE = ED_SUBDEPT.SUBDEPT_CODE " );
		sql.append(" LEFT JOIN EXCISE_PERSON ED_PERSON ON PLAN_DTL.CREATED_BY = ED_PERSON.ED_LOGIN ");
		sql.append("   AND ED_PERSON.SEQ = 0 ");
		sql.append(" LEFT JOIN TA_WORKSHEET_DTL WK_DTL ON PLAN_DTL.NEW_REG_ID = WK_DTL.NEW_REG_ID ");
		sql.append("  AND PLAN_DTL.ANALYSIS_NUMBER = WK_DTL.ANALYSIS_NUMBER");
	    
		sql.append(" WHERE PLAN_DTL.IS_DELETED = 'N' ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append("   AND PLAN_DTL.PLAN_NUMBER = ? ");

		params.add(formVo.getPlanNumber());
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			if(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE.equals(formVo.getPlanType())) {
//				if (ExciseUtils.isCentral(formVo.getOfficeCode())) {
//					sql.append("   AND PLAN_DTL.OFFICE_CODE LIKE ? ");
//					params.add("0014__");
//				}else {
//					sql.append("   AND PLAN_DTL.OFFICE_CODE LIKE ? ");
//					params.add(formVo.getOfficeCode());
//				}
				sql.append("   AND PLAN_DTL.OFFICE_CODE LIKE ? ");
				params.add(formVo.getOfficeCode());
			}else {
				sql.append("   AND PLAN_DTL.OFFICE_CODE LIKE ? ");
				params.add(formVo.getOfficeCode());
			}

		}
		
		if (StringUtils.isNotEmpty(formVo.getSubdeptCode())) {
			sql.append("   AND PLAN_DTL.AU_SUBDEPT_CODE = ? ");
			params.add(formVo.getSubdeptCode());
		}
		
		if (StringUtils.isNotEmpty(formVo.getUserLoginId())) {
			sql.append("   AND PLAN_DTL.AU_JOB_RESP LIKE ? ");
			params.add("%" + formVo.getUserLoginId() + "%");
		}
		
		if (StringUtils.isNotEmpty(formVo.getAuditStatus())) {
			sql.append("   AND PLAN_DTL.AUDIT_STATUS >= ? ");
			params.add(formVo.getAuditStatus());
		}
		
		if(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.SUMMARY.equals(formVo.getPlanType())) {
			if (StringUtils.isNotEmpty(formVo.getPlanType())) {
				sql.append("   AND PLAN_DTL.PLAN_TYPE IN ( ?,?)");
				params.add(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE);
				params.add(ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN);
			}
		}else {
			if (StringUtils.isNotEmpty(formVo.getPlanType())) {
				sql.append("   AND PLAN_DTL.PLAN_TYPE = ? ");
				params.add(formVo.getPlanType());
			}
		}

//		if (StringUtils.isNotEmpty(formVo.getPlanType())) {
//			sql.append("   AND PLAN_DTL.PLAN_TYPE = ? ");
//			params.add(formVo.getPlanType());
//		}
		
		if (StringUtils.isNotEmpty(formVo.getNewRegId())) {
			sql.append("   AND PLAN_DTL.NEW_REG_ID = ? ");
			params.add(formVo.getNewRegId());
		}
		
		if (StringUtils.isNotEmpty(formVo.getSectorCode()) && !"0".equals(formVo.getSectorCode())) {
			sql.append("   AND ED_SECTOR.OFF_CODE = ? ");
			params.add(formVo.getSectorCode());
		}
		
		if (StringUtils.isNotEmpty(formVo.getAreaCode()) && !"0".equals(formVo.getAreaCode())) {
			sql.append("   AND ED_AREA.OFF_CODE = ? ");
			params.add(formVo.getAreaCode());
		}
	}
	
	@Override
	public List<PlanWorksheetDatatableVo> findAllByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY WK_DTL.DUTY_GROUP_ID, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), planDtlDatatableRowMapper);
	}

	@Override
	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY WK_DTL.DUTY_GROUP_ID, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), planDtlDatatableRowMapper);
	}

	@Override
	public Long countByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	protected RowMapper<PlanWorksheetDatatableVo> planDtlDatatableRowMapper = new RowMapper<PlanWorksheetDatatableVo>() {
		@Override
		public PlanWorksheetDatatableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlanWorksheetDatatableVo vo = new PlanWorksheetDatatableVo();
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setDutyCode(rs.getString("DUTY_GROUP_ID"));
			vo.setDutyDesc(rs.getString("DUTY_GROUP_NAME"));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
			vo.setAuSubdeptCode(rs.getString("AU_SUBDEPT_CODE"));
			vo.setAuJobResp(rs.getString("AU_JOB_RESP"));
			vo.setPlanWorksheetDtlId(rs.getLong("PLAN_WORKSHEET_DTL_ID"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
			vo.setAuditPlanCode(rs.getString("AUDIT_PLAN_CODE"));
			vo.setPlanType(rs.getString("PLAN_TYPE"));
//            vo.setDeptShortName(rs.getString("DEPTSHORTNAME"));
			vo.setReplaceReason(rs.getString("REPLACE_REASON"));
			vo.setReplaceRegId(rs.getString("REPLACE_REG_ID"));
			vo.setRegDate(ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			if(vo.getOfficeCode()!= null) {
				try {
					vo.setDeptShortName(ApplicationCache.getExciseDepartment(vo.getOfficeCode()).getDeptShortName());
				} catch (Exception e) {
					// TODO: handle exception
					vo.setDeptShortName("");
				}
			}
			//vo.setDeptShortName(vo.getOfficeCode()!= null ? ApplicationCache.getExciseDepartment(vo.getOfficeCode()).getDeptShortName():"");
            vo.setSubdeptShortName(rs.getString("SUBDEPTSHORTNAME"));
            vo.setPersonName(rs.getString("PERSON_NAME"));
			String auditType = "-";
			if (StringUtils.isNotEmpty(rs.getString("AUDIT_TYPE"))) {
				auditType = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_AUDIT_TYPE, rs.getString("AUDIT_TYPE")).getValue1();
			}
			vo.setAuditType(auditType);
			String auditDate = "-";
			String start = ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), ConvertDateUtils.DD_MM_YYYY);
			String end = ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), ConvertDateUtils.DD_MM_YYYY);
			if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
				auditDate = start + " - " +end;
			}
			vo.setAuditDate(auditDate);
			vo.setAuditStatusDesc(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_AUDIT_STATUS, rs.getString("AUDIT_STATUS")).getValue1());
			return vo;
		}
	};
	
	private void buildByCalendarCriteriaQuery(StringBuilder sql, List<Object> params, AuditCalendarCriteriaFormVo formVo) {
		sql.append(" SELECT PLAN_DTL.*, "); 
		sql.append("     R4000.NEW_REG_ID, ");
		sql.append("     R4000.CUS_FULLNAME, ");
		sql.append("     R4000.FAC_FULLNAME, ");
		sql.append("     R4000.OFFICE_CODE OFFICE_CODE_R4000, ");
		//sql.append("     R4000.DUTY_CODE, ");
		sql.append("     ED_SECTOR.OFF_CODE SEC_CODE, ");
		sql.append("     ED_SECTOR.OFF_SHORT_NAME SEC_DESC, ");
		sql.append("     ED_AREA.OFF_CODE AREA_CODE, ");
		sql.append("     ED_AREA.OFF_SHORT_NAME AREA_DESC, ");
		sql.append("     ED_AREA.OFF_NAME DEPTSHORTNAME ");
//		sql.append(" 	 ED_SUBDEPT.SUBDEPT_SHORT_NAME SUBDEPTSHORTNAME	");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ");
		sql.append(" ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR "); 
		sql.append(" ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA "); 
		sql.append(" ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
//		sql.append(" LEFT JOIN EXCISE_DEPARTMENT  ED_DEPT ON ED_AREA.OFF_CODE = PLAN_DTL.OFFICE_CODE " );
//		sql.append(" LEFT JOIN EXCISE_SUBDEPT ED_SUBDEPT ON PLAN_DTL.AU_SUBDEPT_CODE = ED_SUBDEPT.SUBDEPT_CODE " );
		sql.append(" WHERE PLAN_DTL.IS_DELETED = ? ");  
		sql.append(" AND PLAN_DTL.OFFICE_CODE = ? ");
        
        params.add(FLAG.N_FLAG);
        params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());

        // AUDIT_TYPE
        sql.append(" AND AUDIT_TYPE IN (");
        List<AuditCalendarCheckboxVo> auditType = formVo.getAuditType().stream()
        		.filter(e -> e.getCheckbox().equals(true))
        		.collect(Collectors.toList());
        
        for (int i = 0; i < auditType.size(); i++) {
        	AuditCalendarCheckboxVo vo = auditType.get(i);
    		if (i == auditType.size()-1) {
    			params.add(vo.getParamCode());
    			sql.append(" ?");
			} else {
				params.add(vo.getParamCode());
				sql.append(" ?,");
			}
		}
        if (0 == auditType.size()) {
			params.add("F");
			sql.append(" ?, ");
			params.add("M");
			sql.append(" ?, ");
			params.add("C");
			sql.append(" ?, ");
			params.add("I");
			sql.append(" ? ");
		}
        sql.append(" )");

     // AUDIT_STATUS
        sql.append(" AND AUDIT_STATUS IN (");
        List<AuditCalendarCheckboxVo> auditStatus = formVo.getAuditStatus().stream()
        		.filter(e -> e.getCheckbox().equals(true))
        		.collect(Collectors.toList());
        
        for (int i = 0; i < auditStatus.size(); i++) {
        	AuditCalendarCheckboxVo vo = auditStatus.get(i);
    		if (i == auditStatus.size()-1) {
    			params.add(vo.getParamCode());
    			sql.append(" ?");
			} else {
				params.add(vo.getParamCode());
				sql.append(" ?,");
			}
		}
        if (0 == auditStatus.size()) {
        	params.add("0401");
			sql.append(" ?, ");
			params.add("I");
			sql.append(" ?, ");
			params.add("W");
			sql.append(" ?, ");
			params.add("P");
			sql.append(" ? ");
		}
        sql.append(" )");
    }
	
	public List<PlanWorksheetDtlVo> findByCriteria(AuditCalendarCriteriaFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		buildByCalendarCriteriaQuery(sql, params, formVo);
		
		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanWorksheetDtlRowMapper);
	}
	
	public List<PlanWorksheetDtlVo> findByPlanDtlByAssingPerson(AuditCalendarCriteriaFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		buildByCalendarCriteriaQuery(sql, params, formVo);
		sql.append(" AND PLAN_DTL.AU_JOB_RESP = ? ");
		params.add(UserLoginUtils.getCurrentUserBean().getUsername());
		
		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanWorksheetDtlRowMapper);
	}
	
	private static final RowMapper<PlanWorksheetDtlVo> taPlanWorksheetDtlRowMapper = new RowMapper<PlanWorksheetDtlVo>() {
        public PlanWorksheetDtlVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        	PlanWorksheetDtlVo vo = new PlanWorksheetDtlVo();
            vo.setPlanWorksheetDtlId(rs.getLong("PLAN_WORKSHEET_DTL_ID"));
            vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
            vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE"));
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setSystemType(rs.getString("SYSTEM_TYPE"));
            vo.setPlanType(rs.getString("PLAN_TYPE"));
            vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
            vo.setAuditType(rs.getString("AUDIT_TYPE"));
            vo.setAuditStartDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_EN));
            vo.setAuditEndDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_EN));
            vo.setStart(rs.getDate("AUDIT_START_DATE"));
            
            LocalDate localEnd = ConvertDateUtils.parseStringToLocalDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"),ConvertDateUtils.DD_MM_YYYY),
    				ProjectConstant.SHORT_DATE_FORMAT);
            localEnd = localEnd.plusDays(1);
            vo.setEnd(Date.from(localEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vo.setCusFullName(rs.getString("CUS_FULLNAME"));
            vo.setFacFullName(rs.getString("FAC_FULLNAME"));
            vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
            //vo.setDutyCode(rs.getString("DUTY_CODE"));
            vo.setSecCode(rs.getString("SEC_CODE"));
            vo.setSecDesc(rs.getString("SEC_DESC"));
            vo.setAreaCode(rs.getString("AREA_CODE"));
            vo.setAreaDesc(rs.getString("AREA_DESC"));
            vo.setTitle(rs.getString("FAC_FULLNAME"));
            vo.setAllDay(true);
//            vo.setDeptShortName(rs.getString("DEPTSHORTNAME"));
//            vo.setSubdeptShortName(rs.getString("SUBDEPTSHORTNAME"));
            return vo;
        }
    };

	@Override
	public void updateStatusPlanWorksheetDtl(ExcisePerson formVo,String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" UPDATE TA_PLAN_WORKSHEET_DTL SET AUDIT_STATUS = ? , ");
		if (ProjectConstants.TA_AUDIT_STATUS.CODE_0301.equals(status)) {
			sql.append("  RECEIVED_BY = ? , RECEIVED_DATE = ?  ");
			
		}else if (ProjectConstants.TA_AUDIT_STATUS.CODE_0400.equals(status)) {
			sql.append("  ASSIGNED_SUBDEPT_BY = ? , ASSIGNED_SUBDEPT_DATE = ?  ");
		}
		sql.append(" WHERE OFFICE_CODE = ? ");
		
		params.add(status);
		params.add(formVo.getEdLogin());
		params.add(new Date());
		params.add(formVo.getEdOffcode());
//		if (StringUtils.isNotBlank(formVo.getAuSubdeptCode())) {
//			sql.append(" AND AU_SUBDEPT_CODE = ? ");
//			params.add(formVo.getAuSubdeptCode());
//		}
		
		commonJdbcTemplate.update(sql.toString(),params.toArray());
		
	}

	@Override
	public List<PlanWorksheetSendTableVo> findPlanWorksheetByDtl(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
//		sql.append( " SELECT COUNT(AUDIT_STATUS) COUNT_PLAN ,DPT.OFF_NAME,DPT.OFF_CODE OFFICE_CODE,SEND.SUBMIT_DATE ");
//		sql.append( " ,SEND.SEND_DATE FROM EXCISE_DEPARTMENT DPT ");
//		sql.append( " LEFT JOIN TA_PLAN_WORKSHEET_DTL DTL ON DTL.OFFICE_CODE = DPT.OFF_CODE AND dtl.is_deleted = '"+FLAG.N_FLAG+ "' " );
//		sql.append( " LEFT JOIN TA_PLAN_WORKSHEET_SEND SEND ON SEND.OFFICE_CODE = DPT.OFF_CODE AND SEND.BUDGET_YEAR =  ?  " );
//		sql.append( " WHERE DPT.OFF_CODE LIKE ?  " );
//		sql.append( " GROUP BY DPT.OFF_CODE,DPT.OFF_NAME,SEND.SUBMIT_DATE,SEND.SEND_DATE " );;
//		sql.append( " ORDER BY DPT.OFF_CODE ASC ");
		sql.append(" SELECT dtl.COUNT_PLAN,dtl2.COUNT_RES,dtl3.COUNT_OUT, DPT.OFF_NAME,DPT.OFF_CODE OFFICE_CODE,SEND.SUBMIT_DATE  ,SEND.SEND_DATE FROM EXCISE_DEPARTMENT DPT " );
		sql.append(" LEFT JOIN (  " );
		sql.append("     SELECT count(plan_type) COUNT_PLAN ,office_code from TA_PLAN_WORKSHEET_DTL " );
		sql.append("     where is_deleted = 'N' AND plan_type= 'I' and BUDGET_YEAR =  ? " );
		sql.append("     GROUP BY plan_type,office_code " );
		sql.append("     ) dtl on dtl.office_code = DPT.OFF_CODE " );
		sql.append(" LEFT JOIN ( " );
		sql.append("     SELECT count(plan_type) COUNT_RES ,office_code from TA_PLAN_WORKSHEET_DTL  " );
		sql.append("     where is_deleted = 'N' AND plan_type= 'R' and BUDGET_YEAR =  ? " );
		sql.append("     GROUP BY plan_type,office_code " );
		sql.append("     ) dtl2 on dtl2.office_code = DPT.OFF_CODE " );
		sql.append(" LEFT JOIN ( " );
		sql.append("     SELECT count(plan_type) COUNT_OUT ,office_code from TA_PLAN_WORKSHEET_DTL  " );
		sql.append("     where is_deleted = 'N' AND plan_type= 'E' and BUDGET_YEAR =  ? " );
		sql.append("     GROUP BY plan_type,office_code " );
		sql.append("     ) dtl3 on dtl3.office_code = DPT.OFF_CODE " );
		sql.append("      LEFT JOIN TA_PLAN_WORKSHEET_SEND SEND ON SEND.OFFICE_CODE = DPT.OFF_CODE AND SEND.BUDGET_YEAR =  ? ");
		sql.append(" WHERE DPT.off_CODE like  ? ORDER BY dpt.off_code " );
		params.add (formVo.getBudgetYear());
		params.add (formVo.getBudgetYear());
		params.add (formVo.getBudgetYear());
		params.add (formVo.getBudgetYear());
		params.add (formVo.getOfficeCode());

		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanWorksheetDtlSendRowMapper);
	}
	
	private static final RowMapper<PlanWorksheetSendTableVo> taPlanWorksheetDtlSendRowMapper = new RowMapper<PlanWorksheetSendTableVo>() {
        public PlanWorksheetSendTableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        	PlanWorksheetSendTableVo vo = new PlanWorksheetSendTableVo();
        	vo.setCountPlan(rs.getInt("COUNT_PLAN"));
        	vo.setCountRes(rs.getInt("COUNT_RES"));
        	vo.setCountOut(rs.getInt("COUNT_OUT"));
        	vo.setOfficeName(rs.getString("OFF_NAME"));
        	vo.setOfficeCode(rs.getString("OFFICE_CODE"));
        	vo.setSendDate(rs.getDate("SEND_DATE"));
        	vo.setSubmitDate(rs.getDate("SUBMIT_DATE"));
        	
//        	vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
//            vo.setAuditStartDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
//            vo.setAuditEndDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
            return vo;
        }
    };

	@Override
	public void updateStatusPlanWorksheetDtlByList(PersonAssignForm formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" UPDATE TA_PLAN_WORKSHEET_DTL SET AUDIT_STATUS = ? , ");
//		sql.append( " AU_SUBDEPT_CODE = ? ,ASSIGNED_OFFICER_BY = ? ,ASSIGNED_OFFICER_DATE = ? ");
		params.add(formVo.getAuditStatus());
		if (ProjectConstants.TA_AUDIT_STATUS.CODE_0400.equals(formVo.getAuditStatus())) {
			sql.append(" AU_SUBDEPT_CODE = ?  , RECEIVED_BY = ? , RECEIVED_DATE = ?  ");
			params.add(formVo.getAuSubdeptCode());
			params.add(UserLoginUtils.getCurrentUserBean().getUsername());
			params.add(new Date());
			if (StringUtils.isNotEmpty(formVo.getEdOffcode())) {
				sql.append(", OFFICE_CODE = ? ");
				params.add(formVo.getEdOffcode());
			}
		}else if (ProjectConstants.TA_AUDIT_STATUS.CODE_0401.equals(formVo.getAuditStatus())) {
			sql.append(" AU_JOB_RESP = ? , ASSIGNED_SUBDEPT_BY = ? , ASSIGNED_SUBDEPT_DATE = ?  ");
			

			params.add(formVo.getAuJobResp());
//			params.add(formVo.getAuSubdeptCode());
			params.add(UserLoginUtils.getCurrentUserBean().getUsername());
			params.add(new Date());
		}
		
		sql.append(" WHERE PLAN_WORKSHEET_DTL_ID IN  ( ");
		
		for (int i = 0; i < formVo.getListCompany().size() ; i++) {
			if (i == formVo.getListCompany().size() - 1) {
				sql.append(" ? ");
			}else {
				sql.append(" ?, ");
			}
			
		}
		sql.append( " ) "); 
		
//		params.add(ProjectConstants.TA_AUDIT_STATUS.CODE_0400);
//		params.add(formVo.getAuSubdeptCode());
//		params.add(UserLoginUtils.getCurrentUserBean().getUsername());
//		params.add(new Date());
		
		for (int i = 0; i < formVo.getListCompany().size(); i++) {
			params.add(formVo.getListCompany().get(i).getPlanWorksheetDtlId());
		}
		commonJdbcTemplate.update(sql.toString(),params.toArray());
		
	}

	@Override
	public List<TaPlanWorksheetDtl> findByOfficeCodeAndPlanNumberForCentral(String planNumber ,String officeCode) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * from TA_PLAN_WORKSHEET_DTL WHERE PLAN_NUMBER= ? AND OFFICE_CODE like ? ");
		params.add(planNumber);
		params.add(ExciseUtils.whereInLocalOfficeCode(officeCode));
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<TaPlanWorksheetDtl>(TaPlanWorksheetDtl.class));
	}
	
	private void buildOutPlanDtl(StringBuilder sql, List<Object> params, PlanWorksheetVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME ");
		sql.append("   ,R4000.FAC_FULLNAME ");
		sql.append("   ,R4000.FAC_ADDRESS ");
		sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000 ");
		sql.append("   ,R4000.DUTY_CODE ");
		sql.append("   ,R4000.REG_DATE ");
		sql.append("   ,R4000.REG_CAPITAL ");
		sql.append("   ,WK_DTL.DUTY_GROUP_ID ");
		sql.append("   ,ED_SECTOR.OFF_CODE SEC_CODE ");
		sql.append("   ,ED_SECTOR.OFF_SHORT_NAME SEC_DESC ");
		sql.append("   ,ED_AREA.OFF_CODE AREA_CODE ");
		sql.append("   ,ED_AREA.OFF_SHORT_NAME AREA_DESC ");
		sql.append("   ,PLAN_DTL.* ");
		sql.append("   ,ED_SUBDEPT.SUBDEPT_SHORT_NAME SUBDEPTSHORTNAME ");
		sql.append("   ,ED_PERSON.ED_PERSON_NAME PERSON_NAME ");
		sql.append("   ,WK_DTL.DUTY_GROUP_NAME  DUTY_GROUP_NAME ");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT ED_SUBDEPT ON PLAN_DTL.AU_SUBDEPT_CODE = ED_SUBDEPT.SUBDEPT_CODE " );
		sql.append(" LEFT JOIN EXCISE_PERSON ED_PERSON ON PLAN_DTL.CREATED_BY = ED_PERSON.ED_LOGIN ");
		sql.append("   AND ED_PERSON.SEQ = 0 ");
		sql.append(" LEFT JOIN TA_WORKSHEET_DTL WK_DTL ON PLAN_DTL.NEW_REG_ID = WK_DTL.NEW_REG_ID ");
		sql.append("  AND PLAN_DTL.ANALYSIS_NUMBER = WK_DTL.ANALYSIS_NUMBER");
	    
		sql.append(" WHERE PLAN_DTL.IS_DELETED = 'N' ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		
		if (StringUtils.isNotEmpty(formVo.getPlanNumber())) {
			sql.append("   AND PLAN_DTL.PLAN_NUMBER = ? ");
			params.add(formVo.getPlanNumber());
		}
		
		if (StringUtils.isNotEmpty(formVo.getPlanType())) {
			if (ProjectConstants.TA_PLAN_WORKSHEET_STATUS.ONPLAN.equals(formVo.getPlanType())
					|| ProjectConstants.TA_PLAN_WORKSHEET_STATUS.RESERVE.equals(formVo.getPlanType()) ) {
				sql.append("   AND PLAN_DTL.AUDIT_STATUS <= ? ");
				params.add(ProjectConstants.TA_AUDIT_STATUS.CODE_0401);
			}
			
		}else {
			if (StringUtils.isNotEmpty(formVo.getAuditStatus())) {
				sql.append("   AND PLAN_DTL.AUDIT_STATUS LIKE ? ");
				params.add(formVo.getAuditStatus());
			}
		}
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append("   AND PLAN_DTL.OFFICE_CODE LIKE ? ");
			params.add(formVo.getOfficeCode());
		}
		if (StringUtils.isNotEmpty(formVo.getPlanType())) {
			sql.append("   AND PLAN_DTL.PLAN_TYPE = ? ");
			params.add(formVo.getPlanType());
		}

	}
	
	
	@Override
	public List<PlanWorksheetDatatableVo> findOutPlanDtl(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildOutPlanDtl(sql, params, formVo);

		sql.append(" ORDER BY R4000.DUTY_CODE, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), planDtlDatatableRowMapper);
	}

	@Override
	public Long countOutPlanDtl(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildOutPlanDtl(sql, params, formVo);

		return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	@Override
	public int countPlanTypeByOfficeCodeAndPlanType(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		sql.append(" SELECT COUNT(1) FROM TA_PLAN_WORKSHEET_DTL WHERE IS_DELETED = 'N' ");

		if (StringUtils.isNotEmpty(formVo.getPlanNumber())) {
			sql.append("   AND PLAN_NUMBER = ? ");
			params.add(formVo.getPlanNumber());
		}
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append("   AND OFFICE_CODE LIKE ? ");
			params.add(formVo.getOfficeCode());
		}
		if (StringUtils.isNotEmpty(formVo.getPlanType())) {
			sql.append("   AND PLAN_TYPE = ? ");
			params.add(formVo.getPlanType());
		}
		
		return commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
	}
	
	@Override
	public List<PlanWorksheetDtlVo> countPlanDtlAndAreaByOfficeCode(String officeCode,String budgerYear) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		sql.append(" SELECT OFF_CODE,OFF_SHORT_NAME,SUM(REGID_COUNT) OFFCODE_COUNT FROM ( ");
		sql.append(" SELECT AREA.OFF_CODE,AREA.OFF_SHORT_NAME,COUNT( DTL.NEW_REG_ID) REGID_COUNT FROM TA_PLAN_WORKSHEET_DTL DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 WS ON WS.NEW_REG_ID = DTL.NEW_REG_ID AND DTL.BUDGET_YEAR = ? AND DTL.IS_DELETED = 'N' AND DTL.AUDIT_TYPE IS NOT NULL ");
		sql.append(" RIGHT JOIN EXCISE_DEPARTMENT AREA ON AREA.OFF_CODE = WS.OFFICE_CODE ");
		sql.append(" GROUP BY AREA.OFF_CODE,AREA.OFF_SHORT_NAME,DTL.NEW_REG_ID ) ");
		sql.append(" WHERE  OFF_CODE LIKE ?  GROUP BY OFF_CODE,OFF_SHORT_NAME ORDER BY OFF_CODE ");
		params.add(budgerYear);
		params.add(officeCode);
		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(),taPlanWorksheetDtlCountAreaRowMapper);
	}
	
	private static final RowMapper<PlanWorksheetDtlVo> taPlanWorksheetDtlCountAreaRowMapper = new RowMapper<PlanWorksheetDtlVo>() {
        public PlanWorksheetDtlVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        	PlanWorksheetDtlVo vo = new PlanWorksheetDtlVo();
        	vo.setAreaCode(rs.getString("OFF_CODE"));
        	vo.setAreaDesc(rs.getString("OFF_SHORT_NAME"));
        	vo.setTitle(rs.getString("OFFCODE_COUNT"));
            return vo;
        }
    };

	@Override
	public List<TaPlanMasVo> countPlanDtlMonthByOfficeCode(String officeCode,String budgetYear) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append("   SELECT COUNT(PLAN.AUDIT_START_DATE) AUDITCOUNT, ");
		sql.append("    TO_NUMBER(TO_CHAR(PLAN.AUDIT_START_DATE, 'MM')) AUDITMONTH ");
		sql.append("    FROM  TA_PLAN_WORKSHEET_DTL PLAN ");
		sql.append("     WHERE  AUDIT_START_DATE IS NOT NULL AND OFFICE_CODE LIKE  ?  AND PLAN.AUDIT_STATUS = '0401' AND PLAN.BUDGET_YEAR = ? ");
		sql.append("   GROUP BY  TO_CHAR(PLAN.AUDIT_START_DATE, 'MM'),  PLAN.OFFICE_CODE ");
		params.add(officeCode);
		params.add(budgetYear);

		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanMasCentral);
	}
	
	private static final RowMapper<TaPlanMasVo> taPlanMasCentral = new RowMapper<TaPlanMasVo>() {
		@Override
		public TaPlanMasVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaPlanMasVo vo = new TaPlanMasVo();

			vo.setMonth(rs.getString("AUDITMONTH"));
			vo.setAuditCount(rs.getString("AUDITCOUNT"));

			return vo;
		}
	};
	
	public List<PlanWorksheetDatatableVo> countPlanDtlStatusByOfficeCode(String officeCode,String budgetYear) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append("  SELECT AUDIT_STATUS  FROM TA_PLAN_WORKSHEET_DTL ");
		sql.append("  WHERE OFFICE_CODE LIKE ? AND BUDGET_YEAR = ? AND AUDIT_START_DATE IS NOT NULL ");
//		sql.append("  GROUP BY AUDIT_STATUS ,OFFICE_CODE ");
		params.add(officeCode);
		params.add(budgetYear);

		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taAuditStatus);
	}
	
	private static final RowMapper<PlanWorksheetDatatableVo> taAuditStatus = new RowMapper<PlanWorksheetDatatableVo>() {
		@Override
		public PlanWorksheetDatatableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlanWorksheetDatatableVo vo = new PlanWorksheetDatatableVo();
			vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
			vo.setAuditStatusDesc(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_AUDIT_STATUS, rs.getString("AUDIT_STATUS")).getValue1());
//			vo.setNewRegId(rs.getString("COUNT_STATUS"));
			return vo;
		}
	};
    
    
    
}
