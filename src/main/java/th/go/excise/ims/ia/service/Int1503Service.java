package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.vo.Int1503FormVo;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;
import th.go.excise.ims.preferences.persistence.repository.ExciseDepaccMasRepository;
import th.go.excise.ims.preferences.vo.Ed02FormVo;

@Service
public class Int1503Service {

	@Autowired
	private ExciseDepaccMasRepository exciseDepaccMasRepository;
	
	

	public List<ExciseDepaccMas> listData() {
		List<ExciseDepaccMas> dataList = new ArrayList<ExciseDepaccMas>();
		dataList = exciseDepaccMasRepository.listData();
		return dataList;
	}

	public void saveDepaccMas(Int1503FormVo vo) {
		ExciseDepaccMas exciseDepaccMas = new ExciseDepaccMas();
		exciseDepaccMas.setGfDepositCode(vo.getGfDepositCode());
		exciseDepaccMas.setGfDepositName(vo.getGfDepositName());
		exciseDepaccMas = exciseDepaccMasRepository.save(exciseDepaccMas);
	}

	public void editDepaccMas(Int1503FormVo vo) {
		ExciseDepaccMas exciseDepaccMas = null;
		try {
			exciseDepaccMas = new ExciseDepaccMas();
			exciseDepaccMas = exciseDepaccMasRepository.findByGfDepositCode(vo.getGfDepositCode());
			exciseDepaccMas.setGfDepositCode(vo.getGfDepositCode());
			exciseDepaccMas.setGfDepositName(vo.getGfDepositName());
			exciseDepaccMas = exciseDepaccMasRepository.save(exciseDepaccMas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Int1503FormVo request) {
		exciseDepaccMasRepository.deleteById(request);
	}
	
	
	

}
