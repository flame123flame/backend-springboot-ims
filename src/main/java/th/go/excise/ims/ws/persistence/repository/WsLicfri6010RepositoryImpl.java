package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

public class WsLicfri6010RepositoryImpl implements WsLicfri6010RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsLicfri6010RepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsLicfri6010> licfri6010List) {
		logger.info("batchInsert licfri6010List.size()={}", licfri6010List.size());
		
		final int BATCH_SIZE = 1000;

		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"WS_LICFRI6010_ID",
			"OFFCODE",
			"LIC_TYPE",
			"LIC_NO",
			"LIC_NAME",
			"LIC_FEE",
			"LIC_INTERIOR",
			"LIC_PRICE",
			"LIC_DATE",
			"START_DATE",
			"EXP_DATE",
			"SEND_DATE",
			"PRINT_COUNT",
			"NID",
			"NEW_REG_ID",
			"CUS_ID",
			"CUS_ADDRSEQ",
			"CUS_FULLNAME",
			"FAC_ID",
			"FAC_ADDRSEQ",
			"FAC_FULLNAME",
			"INC_CODE",
			"CREATED_BY",
			"CREATED_DATE"
		));

		String sql = SqlGeneratorUtils.genSqlInsert("WS_LICFRI6010", insertColumnNames, "WS_LICFRI6010_SEQ");

		commonJdbcTemplate.batchUpdate(sql, licfri6010List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsLicfri6010>() {
			public void setValues(PreparedStatement ps, WsLicfri6010 licfri6010) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(licfri6010.getOffcode());
				paramList.add(licfri6010.getLicType());
				paramList.add(licfri6010.getLicNo());
				paramList.add(licfri6010.getLicName());
				paramList.add(licfri6010.getLicFee());
				paramList.add(licfri6010.getLicInterior());
				paramList.add(licfri6010.getLicPrice());
				paramList.add(licfri6010.getLicDate());
				paramList.add(licfri6010.getStartDate());
				paramList.add(licfri6010.getExpDate());
				paramList.add(licfri6010.getSendDate());
				paramList.add(licfri6010.getPrintCount());
				paramList.add(licfri6010.getNid());
				paramList.add(licfri6010.getNewRegId());
				paramList.add(licfri6010.getCusId());
				paramList.add(licfri6010.getCusAddrseq());
				paramList.add(licfri6010.getCusFullname());
				paramList.add(licfri6010.getFacId());
				paramList.add(licfri6010.getFacAddrseq());
				paramList.add(licfri6010.getFacFullname());
				paramList.add(licfri6010.getIncCode());
				paramList.add(licfri6010.getCreatedBy());
				paramList.add(licfri6010.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
