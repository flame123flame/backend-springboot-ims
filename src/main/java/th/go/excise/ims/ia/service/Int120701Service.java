package th.go.excise.ims.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.repository.jdbc.IaMedicalWelfareJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaRentHouseJdbcRepository;
import th.go.excise.ims.ia.vo.Int120701FilterVo;
import th.go.excise.ims.ia.vo.Int120701Type6006Vo;
import th.go.excise.ims.ia.vo.int120701Type7131Vo;

@Service
public class Int120701Service {

	@Autowired
	private IaRentHouseJdbcRepository iaRentHouseJdbcRepository;

	@Autowired
	private IaMedicalWelfareJdbcRepository iaMedicalWelfareJdbcRepository;
	public List<Int120701Type6006Vo> filterByDate(Int120701FilterVo dataFilter) {
		List<Int120701Type6006Vo> dataRes = iaRentHouseJdbcRepository.filterByDate(dataFilter);
		return dataRes;
	}
	
	public List<int120701Type7131Vo> filterByDate7131(Int120701FilterVo dataFilter) {
		List<int120701Type7131Vo> dataRes = iaMedicalWelfareJdbcRepository.filterByDate(dataFilter);
		return dataRes;
	}
}
