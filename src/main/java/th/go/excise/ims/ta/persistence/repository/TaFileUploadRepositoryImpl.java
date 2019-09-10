package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.FileUploadFormVo;
import th.go.excise.ims.ta.vo.FileUploadVo;

public class TaFileUploadRepositoryImpl implements TaFileUploadRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TaFileUploadRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<FileUploadVo> findVoListByCriteria(FileUploadFormVo formVo) {
		logger.info("findVoListByCriteria moduleCode={}, refNo={}", formVo.getModuleCode(), formVo.getRefNo());
		
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" SELECT UPLOAD_NO, MODULE_CODE, REF_NO, FILE_PATH, FILE_NAME ");
		sql.append(" FROM TA_FILE_UPLOAD ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		
		if (StringUtils.isNotEmpty(formVo.getModuleCode())) {
			sql.append("   AND MODULE_CODE = ? ");
			paramList.add(formVo.getModuleCode());
		}
		
		if (StringUtils.isNotEmpty(formVo.getRefNo())) {
			sql.append("   AND REF_NO = ? ");
			paramList.add(formVo.getRefNo());
		}
		
		sql.append(" ORDER BY UPLOAD_NO ");
		
		List<FileUploadVo> voList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new RowMapper<FileUploadVo>() {
			@Override
			public FileUploadVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				FileUploadVo vo = new FileUploadVo();
				vo.setUploadNo(rs.getString("UPLOAD_NO"));
				vo.setModuleCode(rs.getString("MODULE_CODE"));
				vo.setRefNo(rs.getString("REF_NO"));
				vo.setFileName(rs.getString("FILE_NAME"));
				return vo;
			}
		});
		
		return voList;
	}

}
