package th.go.excise.ims.ia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.repository.IaChartOfAccRepository;
import th.go.excise.ims.ia.vo.Int1505FormVo;

@Service
public class Int1505Service {
	
	@Autowired
	private IaChartOfAccRepository iaChartOfAccRepository;
	
	public void saveChartOfAcc(Int1505FormVo vo) {
		IaChartOfAcc chartOfAcc = null;
		try {
			if (vo.getCoaId() != null) {
				chartOfAcc = new IaChartOfAcc();
				chartOfAcc = iaChartOfAccRepository.findById(vo.getCoaId()).get();
				chartOfAcc.setCoaCode(vo.getCoaCode());
				chartOfAcc.setCoaName(vo.getCoaName());
				chartOfAcc.setCoaDes(vo.getCoaDes());
				chartOfAcc.setCoaType(vo.getCoaType());
				chartOfAcc = iaChartOfAccRepository.save(chartOfAcc);
			} else {
				chartOfAcc = new IaChartOfAcc();
				chartOfAcc.setCoaCode(vo.getCoaCode());
				chartOfAcc.setCoaName(vo.getCoaName());
				chartOfAcc.setCoaDes(vo.getCoaDes());
				chartOfAcc.setCoaType(vo.getCoaType());
				chartOfAcc = iaChartOfAccRepository.save(chartOfAcc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void delete(Int1505FormVo request) {
		iaChartOfAccRepository.deleteById(request.getCoaId());
	}
	
}
