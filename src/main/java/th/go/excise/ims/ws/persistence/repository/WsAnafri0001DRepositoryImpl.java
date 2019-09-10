package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

public class WsAnafri0001DRepositoryImpl implements WsAnafri0001DRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsAnafri0001DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsAnafri0001D> anafri0001DList) {
		logger.info("batchInsert anafri0001DList.size()={}", anafri0001DList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"ANAFRI0001_D_SEQ",
			"NEW_REG_ID",
			"FORM_CODE",
			"REG_IN_NO",
			"GOODS_SEQ",
			"PRODUCT_CODE",
			"PRODUCT_NAME",
			"BRAND_MAIN_CODE",
			"BRAND_MAIN_NAME",
			"BRAND_SECOND_CODE",
			"BRAND_SECOND_NAME",
			"MODEL_CODE",
			"MODEL_NAME",
			"SIZE_CODE",
			"SIZE_NAME",
			"UNIT_CODE",
			"UNIT_NAME",
			"PRODUCT_QTY",
			"PRODUCT_PRICE",
			"VALUE_RATE",
			"QTY_RATE",
			"TAX_VALUE_AMT",
			"TAX_QUANTITY_AMT",
			"TAX_AMT",
			"LOC_AMT",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_ANAFRI0001_D", insertColumnNames, "WS_ANAFRI0001_D_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), anafri0001DList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsAnafri0001D>() {
			public void setValues(PreparedStatement ps, WsAnafri0001D anafri0001D) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(anafri0001D.getNewRegId());
				paramList.add(anafri0001D.getFormCode());
				paramList.add(anafri0001D.getRegInNo());
				paramList.add(anafri0001D.getGoodsSeq());
				paramList.add(anafri0001D.getProductCode());
				paramList.add(anafri0001D.getProductName());
				paramList.add(anafri0001D.getBrandMainCode());
				paramList.add(anafri0001D.getBrandMainName());
				paramList.add(anafri0001D.getBrandSecondCode());
				paramList.add(anafri0001D.getBrandSecondName());
				paramList.add(anafri0001D.getModelCode());
				paramList.add(anafri0001D.getModelName());
				paramList.add(anafri0001D.getSizeCode());
				paramList.add(anafri0001D.getSizeName());
				paramList.add(anafri0001D.getUnitCode());
				paramList.add(anafri0001D.getUnitName());
				paramList.add(anafri0001D.getProductQty());
				paramList.add(anafri0001D.getProductPrice());
				paramList.add(anafri0001D.getValueRate());
				paramList.add(anafri0001D.getQtyRate());
				paramList.add(anafri0001D.getTaxValueAmt());
				paramList.add(anafri0001D.getTaxQuantityAmt());
				paramList.add(anafri0001D.getTaxAmt());
				paramList.add(anafri0001D.getLocAmt());
				paramList.add(anafri0001D.getCreatedBy());
				paramList.add(anafri0001D.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public List<WsAnafri0001Vo> findProductList(String newRegId, String dutyGroupId, String dateStart, String dateEnd) {
		logger.info("findProductList newRegId={}, dutyGroupId={}, dateStart={}, dateEnd={}", newRegId, dutyGroupId, dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT H.REG_IN_DATE, H.PAY_TYPE12, H.RECEIPT_NO, H.RECEIPT_DATE, D.* ");
		sql.append(" FROM WS_ANAFRI0001_D D ");
		sql.append(" INNER JOIN WS_ANAFRI0001_H H ON H.NEW_REG_ID = D.NEW_REG_ID ");
		sql.append("   AND H.FORM_CODE = D.FORM_CODE ");
		sql.append("   AND H.REG_IN_NO = D.REG_IN_NO ");
		sql.append("   AND H.IS_DELETED = 'N' ");
		sql.append(" WHERE D.NEW_REG_ID = ? ");
		sql.append("   AND SUBSTR(D.PRODUCT_CODE,0,4) = ? ");
		sql.append("   AND (TRUNC(H.REG_IN_DATE) >= TO_DATE(?,'YYYYMMDD') AND TRUNC(H.REG_IN_DATE) <= TO_DATE(?,'YYYYMMDD')) ");
		sql.append(" ORDER BY H.REG_IN_DATE, H.RECEIPT_NO ");
		
		List<Object> paramList = new ArrayList<>();
		paramList.add(newRegId);
		paramList.add(dutyGroupId);
		paramList.add(dateStart);
		paramList.add(dateEnd);
		
		List<WsAnafri0001Vo> voList = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new RowMapper<WsAnafri0001Vo>() {
			@Override
			public WsAnafri0001Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
				WsAnafri0001Vo vo = new WsAnafri0001Vo();
				vo.setNewRegId(rs.getString("NEW_REG_ID"));
				vo.setFormCode(rs.getString("FORM_CODE"));
				vo.setRegInNo(rs.getString("REG_IN_NO"));
				vo.setRegInDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("REG_IN_DATE")));
				vo.setPayType12(rs.getString("PAY_TYPE12"));
				vo.setReceiptNo(rs.getString("RECEIPT_NO"));
				vo.setReceiptDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("RECEIPT_DATE")));
				vo.setGoodsSeq(rs.getString("GOODS_SEQ"));
				vo.setProductCode(rs.getString("PRODUCT_CODE"));
				vo.setProductName(rs.getString("PRODUCT_NAME"));
				vo.setBrandMainCode(rs.getString("BRAND_MAIN_CODE"));
				vo.setBrandMainName(rs.getString("BRAND_MAIN_NAME"));
				vo.setBrandSecondCode(rs.getString("BRAND_SECOND_CODE"));
				vo.setBrandSecondName(rs.getString("BRAND_SECOND_NAME"));
				vo.setModelCode(rs.getString("MODEL_CODE"));
				vo.setModelName(rs.getString("MODEL_NAME"));
				vo.setSizeCode(rs.getString("SIZE_CODE"));
				vo.setSizeName(rs.getString("SIZE_NAME"));
				vo.setUnitCode(rs.getString("UNIT_CODE"));
				vo.setUnitName(rs.getString("UNIT_NAME"));
				vo.setProductQty(rs.getBigDecimal("PRODUCT_QTY"));
				vo.setProductPrice(rs.getBigDecimal("PRODUCT_PRICE"));
				vo.setValueRate(rs.getBigDecimal("VALUE_RATE"));
				vo.setQtyRate(rs.getBigDecimal("QTY_RATE"));
				vo.setTaxValueAmt(rs.getBigDecimal("TAX_VALUE_AMT"));
				vo.setTaxQuantityAmt(rs.getBigDecimal("TAX_QUANTITY_AMT"));
				vo.setTaxAmt(rs.getBigDecimal("TAX_AMT"));
				vo.setLocAmt(rs.getBigDecimal("LOC_AMT"));
				return vo;
			}
		});
		
		return voList;
	}
	
}
