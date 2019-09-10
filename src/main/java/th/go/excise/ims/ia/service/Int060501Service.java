package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaCheckTaxReceiptJdbcRepository;
import th.go.excise.ims.ia.vo.Int060501FormVo;
import th.go.excise.ims.ia.vo.Int060501Vo;

@Service
public class Int060501Service {

	@Autowired
	private IaCheckTaxReceiptJdbcRepository iaCheckTaxReceiptJdbcRepository;
	
	public List<Int060501Vo> fillterDate(Int060501FormVo res) {
		List<Int060501Vo> data = null;
		data = iaCheckTaxReceiptJdbcRepository.fillterDate(res);
		return data;
	}
}
