package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaChartAndInc;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.persistence.repository.IaChartAndIncRepository;
import th.go.excise.ims.ia.persistence.repository.IaChartOfAccRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaChartAndIncJdbcRepository;
import th.go.excise.ims.ia.vo.IaChartAndIncVo;
import th.go.excise.ims.ia.vo.Int0501FormVo;
import th.go.excise.ims.ia.vo.Int0501Vo;
import th.go.excise.ims.ia.vo.Int1502FormVo;
import th.go.excise.ims.ia.vo.Int1503FormVo;

@Service
public class Int1502Service {

	@Autowired
	private IaChartOfAccRepository iaChartOfAccRepository;

	@Autowired
	private IaChartAndIncRepository iaChartAndIncRepository;
	
	@Autowired
	private IaChartAndIncJdbcRepository iaChartAndIncJdbcRepository;
	

	public List<IaChartOfAcc> getDropdownCoaCode() {
		return iaChartOfAccRepository.getCoaCodeList();
	}

	public void saveChartAndInc(Int1502FormVo vo) {
		IaChartAndInc iaChartAndInc = null;
		try {
			if (vo.getChartAndIncId() != null) {
				iaChartAndInc = new IaChartAndInc();
				iaChartAndInc = iaChartAndIncRepository.findById(vo.getChartAndIncId()).get();
				iaChartAndInc.setCoaCode(vo.getCoaCode());
				iaChartAndInc.setIncCode(vo.getIncCode());
				iaChartAndInc = iaChartAndIncRepository.save(iaChartAndInc);
			} else {
				iaChartAndInc = new IaChartAndInc();
				iaChartAndInc.setCoaCode(vo.getCoaCode());
				iaChartAndInc.setIncCode(vo.getIncCode());
				iaChartAndInc = iaChartAndIncRepository.save(iaChartAndInc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<IaChartAndIncVo> listData(Int1502FormVo form) {
		List<IaChartAndIncVo> dataList = new ArrayList<IaChartAndIncVo>();
		List<IaChartOfAcc> dataAcc = new ArrayList<IaChartOfAcc>();
		for (IaChartOfAcc iaChartOfAcc : dataAcc) {
			iaChartOfAcc = iaChartOfAccRepository.findBycoaCode(form.getCoaCode());
			iaChartOfAcc.setCoaCode(iaChartOfAcc.getCoaCode());
			
		}
		return dataList;
	}
	
	public List<IaChartAndIncVo> listData() {
		List<IaChartAndIncVo> dataList = new ArrayList<IaChartAndIncVo>();
		dataList = iaChartAndIncJdbcRepository.listData();
		return dataList;
	}
	
	public void delete(Int1502FormVo request) {
		iaChartAndIncJdbcRepository.deleteById(request);
	}


}
