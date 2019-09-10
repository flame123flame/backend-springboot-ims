package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;
import th.go.excise.ims.ia.persistence.entity.IaPlanDtl;
import th.go.excise.ims.ia.persistence.repository.IaPlanDtlRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanDayActivityJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaPlanDtlJdbcRepository;
import th.go.excise.ims.ia.vo.Int010201HeaderVo;

@Service
public class Int010201Service {
	
	@Autowired
	private IaPlanDtlRepository iaPlanDtlRepository;
	
	@Autowired
	private IaPlanDtlJdbcRepository iaPlanDtlJdbcRepository;
	
	@Autowired
	private IaPlanDayActivityJdbcRepository iaPlanDayActivityJdbcRepository;
	
	public Int010201HeaderVo findHeader(BigDecimal idDtl) {
		Int010201HeaderVo header = new Int010201HeaderVo();
		IaPlanDtl dataDtl = iaPlanDtlRepository.findById(idDtl).get();
		header.setInspectionWork(dataDtl.getInspectionWork());
		header.setInspectionWorkStr(ApplicationCache.getParamInfoByCode("IA_INSPECTION_WORK", dataDtl.getInspectionWork()).getValue1());
		header.setInspector(dataDtl.getInspector());
		header.setOfficer(dataDtl.getOfficer());
		
		List<IaPlanDayActivity> dataPlanDay = iaPlanDayActivityJdbcRepository.findActivity(idDtl, IaConstants.PLAN_DAY_WORDING.AUDIT_FULL);
		if(dataPlanDay.size() > 0) {
			header.setDateStartActivity(dataPlanDay.get(0).getDateStartActivity());
			header.setDateEndActivity(dataPlanDay.get(0).getDateEndActivity());
		}
		
		return header;
	}
}
