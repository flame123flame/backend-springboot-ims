package th.go.excise.ims.preferences.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.vo.Int1503FormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;

public class ExciseDepaccMasRepositoryImpl implements ExciseDepaccMasRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseDepaccMasRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public List<ExciseDepaccMas> getDepaccMasDropdown() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT gf_deposit_code, gf_deposit_name ");
		sql.append(" FROM excise_depacc_mas ");
		sql.append(" WHERE is_deleted = 'N' ");
		sql.append(" ORDER BY gf_deposit_code ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<ExciseDepaccMas> response = commonJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ExciseDepaccMas.class));
		return response;
	}
	
	@Override
	public void deleteById(Int1503FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("UPDATE EXCISE_DEPACC_MAS SET IS_DELETED = 'Y' WHERE GF_DEPOSIT_CODE = ? ");
		params.add(request.getGfDepositCode());
		commonJdbcTemplate.update(sql.toString(), params.toArray());
	}	
}
