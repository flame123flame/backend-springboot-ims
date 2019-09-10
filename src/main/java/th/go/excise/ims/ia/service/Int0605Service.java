package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaCheckTaxReceipt;
import th.go.excise.ims.ia.vo.Int0605FormVo;

@Service
public class Int0605Service {
	
//	@Autowired
//	Int0605JdbcRepository int0605JdbcRepository;

	public List<IaCheckTaxReceipt> list(Int0605FormVo form) {
		List<IaCheckTaxReceipt> iaCheckTaxReceipt = new ArrayList<IaCheckTaxReceipt>();
//		iaCheckTaxReceipt = int0606JdbcRepository.list(form);
		return iaCheckTaxReceipt;
	}
}
