package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireHdr;
import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;
import th.go.excise.ims.ia.vo.Int0202FormVo;
import th.go.excise.ims.ia.vo.Int0202Vo;

@Repository
public class IaQuestionnaireMadeHdrJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int0202Vo> getDataFilter(Int0202FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_MADE_HDR WHERE 1=1 AND IS_DELETED='N' ");
		sql.append(" AND OFFICE_CODE = ? ");
		params.add(request.getOfficeCode());
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getCreatedBy())) {
			sql.append(" AND CREATED_BY LIKE ?");
			params.add("%" + request.getCreatedBy() + "%");
		}
		
		if(StringUtils.isNotBlank(request.getQtnName())){
            sql.append(" AND UPPER(QTN_HEADER_NAME) LIKE ?");
            params.add("%" + request.getQtnName().toUpperCase() + "%");
        }
//		if(StringUtils.isNotBlank(request.getStartDate())) {
//			sql.append(" AND TRUNC(START_DATE) >= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//			
//		}
//		if(StringUtils.isNotBlank(request.getEndDate())) {
//			sql.append(" AND TRUNC(END_DATE) <= ? ");
//			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//		}
 		sql.append(" ORDER BY CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0202Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(), new BeanPropertyRowMapper(Int0202Vo.class));

		return datas; 
	}
	
	/* count datatable */
	public Integer countDatafilter(Int0202FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_MADE_HDR WHERE IS_DELETED='N' ");
		sql.append(" AND OFFICE_CODE = ? ");
		params.add(request.getOfficeCode());
		
		if(StringUtils.isNotBlank(request.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(request.getBudgetYear());
		}
		
		if(StringUtils.isNotBlank(request.getCreatedBy())) {
			sql.append(" AND CREATED_BY LIKE ? ");
			params.add(request.getCreatedBy() + "%");
		}
		
		if(StringUtils.isNotBlank(request.getStartDate()) && StringUtils.isNotBlank(request.getEndDate())) {
			sql.append(" AND TRUNC(CREATED_DATE) >= ? ");
			sql.append(" AND TRUNC(CREATED_DATE) <= ? ");
			/* convert string to date */
			params.add(ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			params.add(ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}
		sql.append(" ORDER BY CREATED_DATE DESC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count; 
	}
	
	public List<IaQuestionnaireMadeHdr> findMadeHdrByIdHdr(IaQuestionnaireHdr request, String officeCode) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_QUESTIONNAIRE_MADE_HDR WHERE 1=1 AND IS_DELETED='N' ");
		
		if(StringUtils.isNotBlank(officeCode)) {
			sql.append(" AND OFFICE_CODE LIKE ? ");
			params.add(officeCode.substring(0, 2) + "%");
		}
		if(request.getId() != null) {
			sql.append(" AND ID_HDR = ? ");
			params.add(request.getId());
		}
		sql.append(" AND STATUS != " + IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE +" " );
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaQuestionnaireMadeHdr> data = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(IaQuestionnaireMadeHdr.class));

		return data; 
	}
	
	public BigDecimal updateStatus(BigDecimal idMadeHdr,String status,String username) {
		StringBuilder sql = new StringBuilder(" UPDATE IA_QUESTIONNAIRE_MADE_HDR SET STATUS = ?,UPDATED_BY = ?,UPDATED_DATE = sysdate WHERE ID = ? ");
		List<Object> params = new ArrayList<Object>();
		
		params.add(status);
		params.add(username);
		params.add(idMadeHdr);
		
		commonJdbcTemplate.update(sql.toString(), params.toArray());

		return idMadeHdr;
	}

	public BigDecimal deleteByIdHdr(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder(" DELETE FROM IA_QUESTIONNAIRE_MADE_HDR WHERE ID IN(SELECT ID FROM IA_QUESTIONNAIRE_MADE_HDR WHERE ID_HDR = ? ) ");
		List<Object> params = new ArrayList<Object>();
		
		params.add(idHdr);
		commonJdbcTemplate.update(sql.toString(), params.toArray());

		return idHdr;
	}
	
	public Integer checkCountMadeHdrStatus3(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder(" select COUNT(*) AS COUNT from IA_QUESTIONNAIRE_MADE_HDR mh WHERE mh.ID_HDR = ? AND mh.STATUS = ? ");
		List<Object> params = new ArrayList<Object>();
		
		params.add(idHdr);
		params.add(IaConstants.IA_STATUS_REPLY_QTN.STATUS_3_CODE);
		
		Integer count = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(),Integer.class);

		return count;
	}
	public Integer checkCountMadeHdrAll(BigDecimal idHdr) {
		StringBuilder sql = new StringBuilder(" select COUNT(*) AS COUNT from IA_QUESTIONNAIRE_MADE_HDR mh WHERE mh.ID_HDR = ?");
		List<Object> params = new ArrayList<Object>();
		
		params.add(idHdr);
		
		Integer count = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(),Integer.class);

		return count;
	}
}
